package modelo;

import java.io.*;
import java.util.*;

public class Nivel {
    private Grilla grilla;
    private List<Emisor> emisores;
    private List<Objetivo> objetivos;


    public Nivel(String archivoNivel) {
        Coordenada dimensiones = obtenerDimensiones(archivoNivel);
        if (dimensiones == null) {
            throw new RuntimeException("No se pudieron obtener las dimensiones del nivel");
        }
        this.grilla = new Grilla(dimensiones.getX(), dimensiones.getY());
        this.emisores = new ArrayList<Emisor>();
        this.objetivos = new ArrayList<Objetivo>();
        cargarDesdeArchivo(archivoNivel);
    }

    public Nivel(Grilla grilla, List<Emisor> emisores, List<Objetivo> objetivos) {
        this.grilla = grilla;
        this.emisores = emisores;
        this.objetivos = objetivos;
    }

    /**
     * Obtiene las dimensiones de la grilla del nivel a partir del archivo de ingreso.
     * @param archivoNivel: archivo de texto que contiene la configuración del nivel
     *                    El archivo está separado en 2 secciones, y las secciones están separadas por una línea en blanco.
     *                    La primer seccion indica la configuracion de los bloques. Por lo que se usa esto para obtener las dimensiones.
     * @return Coordenada con las dimensiones de la grilla
     */
    private Coordenada obtenerDimensiones(String archivoNivel) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNivel))) {
            String linea;
            Integer filas = 0;
            Integer columnas = 0;
            boolean leyendoBloques = true;
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

    public void cargarDesdeArchivo(String archivoNivel) {
        // El archivo está separado en 2 secciones, y las secciones están separadas por una línea en blanco.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNivel))) {
            String linea;
            int fila = 0;
            boolean leyendoBloques = true;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim(); // todo esto puede tirar problemas para grillas con bloques sin piso
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

    private void procesarLineaDeBloques(String linea, int fila) {
        for (int columna = 0; columna < linea.length(); columna++) {
            char simbolo = linea.charAt(columna);
            Bloque bloque = crearBloqueDesdeSimbolo(simbolo);
            var ubicacion = new Coordenada(columna, fila);
            grilla.colocarBloque(bloque, ubicacion, false);
        }
    }

    private Bloque crearBloqueDesdeSimbolo(char simbolo) {
        switch (simbolo) {
            case 'F': return new BloqueOpaco();
//            case 'B': return new BloqueOpacoMovil();
            case 'R': return new BloqueEspejo();
            case 'G': return new BloqueVidrio();
//            case 'C': return new BloqueCristal();
            case '.': return new BloqueVacio(); // Espacio vacío con piso
            case ' ': return null; // Celda sin piso
            default: return null;
        }
    }

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

    // deberia estar en nivel ???
    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        // Lógica para mover el bloque en la grilla
        // grilla.moverBloque(x, y, nuevoX, nuevoY);
    }

    public boolean validarSolucion() {
        for (Objetivo objetivo : objetivos) {
            if (!objetivo.esAlcanzado()) {
                return false;
            }
        }
        return true;
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
