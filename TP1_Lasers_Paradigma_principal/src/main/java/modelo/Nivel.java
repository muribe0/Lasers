import java.io.*;
import java.util.*;
package modelo;

public class Nivel {
    private Grilla grilla;
    private List<Emisor> emisores;
    private List<Objetivo> objetivos;

    public Nivel(Grilla grilla, List<Emisor> emisores, List<Objetivo> objetivos) {
        this.grilla = grilla;
        this.emisores = emisores;
        this.objetivos = objetivos;
    }

    public void cargarDesdeArchivo(String archivoNivel) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNivel))) {
            String linea;
            int fila = 0;
            boolean leyendoBloques = true;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

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
            grilla.colocarBloque(fila, columna, bloque); // Colocar el bloque en la grilla
        }
    }

    private Bloque crearBloqueDesdeSimbolo(char simbolo) {
        switch (simbolo) {
            case 'F': return new BloqueOpaco();
            case 'B': return new BloqueOpacoMovil();
            case 'R': return new BloqueEspejo();
            case 'G': return new BloqueVidrio();
            case 'C': return new BloqueCristal();
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
            emisores.add(new Emisor(columna, fila, direccion));
        } else if (tipo.equals("G")) {
            objetivos.add(new Objetivo(columna, fila));
        }
    }

    // deberia estar en nivel ???
    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        // Lógica para mover el bloque en la grilla
        // grilla.moverBloque(x, y, nuevoX, nuevoY);
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

    //public boolean validarSolucion() {
    // Validar si los objetivos fueron alcanzados
    //  for (Objetivo objetivo : objetivos) {
    //    if (!objetivo.esAlcanzado()) {
    //      return false;
    //  }
    //}
    //  return true;
    //}
}
