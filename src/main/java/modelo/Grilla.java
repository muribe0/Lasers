package modelo;

public class Grilla {
    private Bloque[][] grilla;
    private final static Integer dimensionBloque = 2;

    /**
     * Convierte las coordenadas reales a la posicion de la matriz grilla
     * Si los bloques son de 2x2, entonces la coordenada (0,1) es el bloque (0,0) en la grilla.
     * Si los bloques son de 2x2, entonces la coordenada (1,0) es el bloque (0,0) en la grilla.
     * Si los bloques son de 2x2, entonces la coordenada (1,1) es el bloque (0,0) en la grilla.
     * @param coordenada: coordenada del plano real. Cada bloque tiene dimensionBloque (2)
     * @return Coordenada es la posicion en la matriz grilla.
     */
    private Coordenada convertirCoordenadas(Coordenada coordenada) {
        return new Coordenada(coordenada.getX() / dimensionBloque, coordenada.getY() / dimensionBloque);
    }

    /**
     * Genera una grilla con Bloques vacios
     * @param ancho: ancho de la grilla (CANTIDAD de bloques a lo ancho)
     * @param alto: alto de la grilla (CANTIDAD de bloques a lo alto)
     */
    public Grilla(Integer ancho, Integer alto) {
        this.grilla = new Bloque[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                this.grilla[i][j] = new BloqueVacio();
            }
        }
    }

    public Integer getAncho() {
        return this.grilla[0].length;
    }

    public Integer getAlto() {
        return this.grilla.length;
    }

    /**
     * Agrega un bloque a la grilla en la posicion indicada
     * @param bloque: bloque a agregar a la grilla
     * @param coordenada: coordenada donde se agrega el bloque. Esta en formato real, no en formato grilla.
     * @param convertir: si es true, convierte las coordenadas reales a la posicion de la matriz grilla, sino no lo hace y las usa como fila/columna de la celda.
     */
    public void colocarBloque(Bloque bloque, Coordenada coordenada, boolean convertir) {
        if (convertir) {
            coordenada = convertirCoordenadas(coordenada);
        }
        this.grilla[coordenada.getY()][coordenada.getX()] = bloque;
    }

    /**
     * Devuelve el bloque en la posicion indicada
     * @param coordenada: coordenada donde se encuentra el bloque. Esta en formato real, no en formato grilla.
     * @return Bloque en la posicion indicada
     */
    public Bloque getBloque(Coordenada coordenada) {
        coordenada = convertirCoordenadas(coordenada);
        return this.grilla[coordenada.getY()][coordenada.getX()];
    }

    /**
     * Devuelve si la coordenada esta dentro de la grilla
     * @param coordenada: coordenada a verificar
     * @return true si la coordenada esta dentro de la grilla, false en caso contrario
     */
    public boolean estaDentro(Coordenada coordenada) {
        return coordenada.getX() >= 0 && coordenada.getX() < this.getAncho() * dimensionBloque &&
               coordenada.getY() >= 0 && coordenada.getY() < this.getAlto() * dimensionBloque;
    }

    /**
     * Genera una string con la representacion de la grilla. Cada bloque se representa con un caracter.
     * Ejemplo: "#...\n..#\n.#." donde '#' es un bloque opaco y '.' es un bloque vacio.
     * @return String con la representacion de la grilla
     */
    public String toString() {
        String grillaString = "";
        for (int i = 0; i < this.grilla.length; i++) {
            for (int j = 0; j < this.grilla[i].length; j++) {
                grillaString += this.grilla[i][j].toString();
            }
            grillaString += "\n";
        }
        return grillaString;
    }

    /**
     * Devuelve la dimension de los bloques de la grilla
     * @return dimension de los bloques de la grilla
     */
    public Integer getDimensionBloque() {
        return dimensionBloque;
    }
}
