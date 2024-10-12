package modelo;

import java.io.*;
import java.util.*;

public class Nivel {
    private final Grilla grilla;
    private final List<Emisor> emisores;
    private final List<Objetivo> objetivos;

    private boolean estaCompletado;


    public Nivel(String archivoNivel) {
        Coordenada dimensiones = obtenerDimensiones(archivoNivel);
        if (dimensiones == null) {
            throw new RuntimeException("No se pudieron obtener las dimensiones del nivel");
        }
        this.grilla = new Grilla(dimensiones.getX(), dimensiones.getY(), 2);
        this.emisores = new ArrayList<Emisor>();
        this.objetivos = new ArrayList<Objetivo>();
        cargarDesdeArchivo(archivoNivel);
        reiniciarEmisores();
        this.estaCompletado = false;
    }


    /**
     * Obtiene las dimensiones de la grilla del nivel a partir del archivo de ingreso.
     *
     * @param archivoNivel: archivo de texto que contiene la configuración del nivel
     *                      El archivo está separado en 2 secciones, y las secciones están separadas por una línea en blanco.
     *                      La primer seccion indica la configuracion de los bloques. Por lo que se usa esto para obtener las dimensiones.
     * @return Coordenada con las dimensiones de la grilla
     */
    private Coordenada obtenerDimensiones(String archivoNivel) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNivel))) {
            String linea;
            Integer filas = 0;
            Integer columnas = 0;
            while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                filas++;
                columnas = Math.max(columnas, linea.trim().length());
            }
            return new Coordenada(columnas, filas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Carga desde una ruta de archivo el nivel completo: sus emisores, bloques y objetivos.
     * El formato del archivo debe estar organizado en dos secciones separadas por una linea en blanco entre si.
     *
     * Primera sección: configuración de bloques.
     * (espacio) celda sin piso
     * . celda con piso vacía
     * F bloque opaco fijo
     * B bloque opaco móvil
     * R bloque espejo
     * G bloque vidrio
     * C bloque cristal
     *
     * Segunda sección: configuración de emisores y objetivos
     * E <columna> <fila> <direccion> emisor laser, donde direccion puede ser:
     * SE sur-este
     * SW sur-oeste
     * NE nor-este
     * NW nor-oeste
     * G <columna> <fila> objetivo

     * @param archivoNivel: ruta del archivo
     */
    public void cargarDesdeArchivo(String archivoNivel) {
        // El archivo está separado en 2 secciones, y las secciones están separadas por una línea en blanco.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNivel))) {
            String linea;
            int fila = 0;
            boolean leyendoBloques = true;
            while ((linea = br.readLine()) != null) {
                linea = linea.stripTrailing(); // todo esto va a tirar problemas para grillas con bloques sin piso
                if (linea.isEmpty()) {
                    leyendoBloques = false; // Pasamos a la segunda sección
                    continue;
                }
                if (leyendoBloques) {
                    // Procesar la primera sección (configuración de bloques)
                    procesarLineaDeBloques(linea, fila);
                    fila++;
                } else {
                    // Procesar la segunda sección (emisores y objetivos)
                    procesarLineaDeEmisoresYObjetivos(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procesa una linea del archivo correspondiente a los bloques y coloca el bloque en la grilla.
     * @param linea: linea del archivo con la informacion del bloque (tipo y coordenadas)
     * @param fila: fila donde se va a colocar en la grilla, asumiendola como una matriz.
     * @post: el bloque fue colocado con su tipo especifico.
     */
    private void procesarLineaDeBloques(String linea, int fila) {
        for (int columna = 0; columna < linea.length(); columna++) {
            char simbolo = linea.charAt(columna);
            colocarBloqueDesdeSimbolo(simbolo, fila, columna);
        }
    }

    /**
     * Coloca el Bloque nuevo en la grilla segun el simbolo que se lea del archivo.
     *
     * @param simbolo: String de un caracter conteniendo el simbolo representativo de un bloque.
     * @param fila:    fila en la que se encuentra el bloque si se ubicase en una matriz.
     * @param columna: columna en la que se encuentra el bloque si se ubicase en una matriz.
     */
    private void colocarBloqueDesdeSimbolo(char simbolo, Integer fila, Integer columna) {
        var ubicacion = new Coordenada(columna, fila);
        Integer dimensionBloque = grilla.getDimensionBloque();
        ubicacion.redimensionar(dimensionBloque);
        var bloque = switch (simbolo) {
            case 'F' -> new BloqueOpaco(dimensionBloque);
            case 'B' -> new BloqueOpacoMovil(dimensionBloque);
            case 'R' -> new BloqueEspejo(dimensionBloque);
            case 'G' -> new BloqueVidrio(dimensionBloque);
            case 'C' -> new BloqueCristal(dimensionBloque);
            case '.' -> new BloqueVacio(dimensionBloque); // Espacio vacío con piso
            case ' ' -> new BloqueSinPiso(dimensionBloque); // Celda sin piso
            default -> null;
        };
        grilla.colocarBloque(bloque, ubicacion, true);
    }

    /**
     * Procesa una linea del archivo de nivel que contiene la configuración de los emisores y objetivos.
     *
     * @param linea: cada linea tiene la informacion de la posicion de un emisor o un objetivo. Si es un emisor, se indica ademas su orientacion diagonal inicial (NE, NO, SE, SO).
     */
    private void procesarLineaDeEmisoresYObjetivos(String linea) {
        String[] partes = linea.split(" ");
        String tipo = partes[0];
        int columna = Integer.parseInt(partes[1]);
        int fila = Integer.parseInt(partes[2]);

        if (tipo.equals("E")) {
            String direccion = partes[3];
            var origen = new Coordenada(columna, fila);
            var dir = new Direccion(direccion);
            emisores.add(new Emisor(origen, dir));
        } else if (tipo.equals("G")) {
            var posicion = new Coordenada(columna, fila);
            objetivos.add(new Objetivo(posicion));
        }
    }

    /**
     * Mueve el Bloque seleccionado por las coordenadas a su nueva posicion si es posible. Si lo logra,
     * reinicia los emisores pues la grilla puede haber cambiado y el camino de cada emisor verse afectado.
     * Las coordenadas deben tener en cuenta las dimensiones de cada Bloque
     * @param x: coordenada horizontal del bloque a mover
     * @param y: coordenada vertical del bloque a mover
     * @param nuevoX: nueva coordenada horizontal
     * @param nuevoY: nueva coordenada vertical
     * @post: si tiene exito, se mueve el bloque y reinician los emisores.
     */
    public void moverBloque(Integer x, Integer y, Integer nuevoX, Integer nuevoY) {
        Coordenada origen = new Coordenada(x, y);
        Coordenada destino = new Coordenada(nuevoX, nuevoY); // TODO: EVITAR que se pueda poner bloques en origen de emisores.
        if (grilla.moverBloque(origen, destino)) {
            reiniciarEmisores();
        }
    }

    public void validarSolucion() {
        for (Objetivo objetivo : objetivos) {
            for (var emisor : emisores) {
                verificarSiObjetivoAlcanzado(emisor, objetivo);
            }
            if (!objetivo.esAlcanzado()) {
                estaCompletado = false;
                return;
            }
        }
        estaCompletado = true;
    }

    public boolean estaCompletado() {
        return this.estaCompletado;
    }

    private void verificarSiObjetivoAlcanzado(Emisor emisor, Objetivo objetivo) {
        if (emisor.pasaPor(objetivo.getPosicion())) {
            objetivo.marcarComoAlcanzado();
        }
    }

    /**
     * Actualizar el estado de los objetivos.
     *
     * @pre: los emisores han sido emitidos.
     * @post: los objetivos han sido actualizados. Si algun emisor atraviesa un objetivo, este se marca como alcanzado.
     */
    public void actualizarObjetivos() {
        for (Objetivo objetivo : objetivos) {
            // Si la posición del objetivo ha sido alcanzada por un láser, marcar como alcanzado.
            for (Emisor emisor : emisores) {
                verificarSiObjetivoAlcanzado(emisor, objetivo);
            }
        }
    }

    /**
     * Reinicia los emisores, emitiendolos nuevamente uno por uno.
     * @post: emisores son emitidos nuevamente desde su origen inicial y con su direccion inicial.
     */
    public void reiniciarEmisores() {
        for (var emisor : this.emisores) {
            emisor.emitir(this.grilla);
        }
    }

    /*
    TESTING
     */
    public Grilla getGrilla() {
        return grilla;
    }

    public List<Emisor> getEmisores() {
        return emisores;
    }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

}
