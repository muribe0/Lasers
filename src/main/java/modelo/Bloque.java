package modelo;

public abstract class Bloque { // Considerar implementar una interfaz para los bloques.
    private Integer dimension;
    private boolean admiteBloque;

    public Bloque(Integer dimension) {
        this.dimension = dimension;
        this.admiteBloque = true;
    }

    /**
     * Interacctua con el Laser utilizando los metodos de Laser de acuerdo a como incida el mismo al bloque y el tipo de bloque que implemente Bloque
     *
     * @param laser: laser incidente al bloque
*    * @post: el laser fue reflejado, refractado, movido, etc. segun el tipo de bloque
     */
    public abstract void interactuarConLaser(Laser laser);

    /**
     * Devuelve True si una coordenada se encuentra en el borde horizontal de un bloque
     *
     * @param coordenada: coordenada a verificar
     * @return True si la coordenada se encuentra en el borde horizontal de un bloque
     */
    public boolean estaEnBordeVertical(Coordenada coordenada) {
        return coordenada.getX() % dimension == 0;
    }

    /**
     * Devuelve True si una coordenada se encuentra en el borde vertical de un bloque
     *
     * @param coordenada: coordenada a verificar
     * @return True si la coordenada se encuentra en el borde vertical de un bloque
     */
    public boolean estaEnBordeHorizontal(Coordenada coordenada) {
        return coordenada.getY() % dimension == 0;
    }

    /**
     * Establece que el Bloque no admite otro Bloque encima
     * @post: el bloque no admite otro bloque encima
     */
    public void setNoAdmiteBloque() {
        admiteBloque = false;
    }

    /**
     * @return: True si el bloque admite otro bloque encima.
     */
    public boolean admiteBloque() {
        return admiteBloque;
    }

    @Override
    public String toString() {
        return " ";
    }
}
