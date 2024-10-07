package modelo;

public class Laser {
    private Coordenada origen;
    private Direccion direccion;
    private Coordenada destino;
    private boolean detenido;
    private Laser bifurcado;

    public Laser(Coordenada origen, Direccion direccion) {
        this.origen = new Coordenada(origen);
        this.direccion = new Direccion(direccion);
        this.destino = new Coordenada(origen);
        this.detenido = false;
        this.bifurcado = null;
    }

    public void continuar() {
        this.origen = new Coordenada(this.destino);
        this.destino.sumar(this.direccion);
    }

    /**
     * Detiene el laser, es decir, lo deja sin destino.
     * @post el laser esta detenido.
     */
    public void detener() {
        this.detenido = true;
    }

    /**
     *
     * @return true si el laser esta detenido, false en caso contrario.
     */
    public boolean estaDetenido() {
        return this.detenido;
    }

    /**
     * Refleja la direccion del laser horizontalmente como rebotando en un espejo horizontal. Es decir, si tiene direccion incidente
     * (dx, dy), la direccion reflejada sera (dx, -dy).
     * @pre el laser esta en un borde horizontal y viene de un bloque vacio
     * @post la direccion del laser ha sido reflejada. SIN trasladar su destino
     */
    public void reflejarDireccionHorizontalmente() {
        this.direccion = new Direccion(this.direccion.getX(), -this.direccion.getY());
    }

    /**
     * Refleja la direccion del laser verticalmente como rebotando en un espejo vertical. Es decir, si tiene direccion incidente
     * (dx, dy), la direccion reflejada sera (-dx, dy).
     * @pre el laser esta en un borde vertical y viene de un bloque vacio
     * @post la direccion del laser ha sido reflejada. SIN trasladar su destino
     */
    public void reflejarDireccionVerticalmente() {
        this.direccion = new Direccion(-this.direccion.getX(), this.direccion.getY());
    }

    /**
     * Refleja la direccion del laser horizontalmente como rebotando en un espejo horizontal. Es decir, si tiene direccion incidente
     * (dx, dy), la direccion reflejada sera (dx, -dy).
     * @pre el laser esta en un borde horizontal y viene de un bloque vacio
     * @post El Laser se ha movido en la direccion reflejada
     */
    public void reflejarHorizontalmente() {
        this.direccion = new Direccion(this.direccion.getX(), -this.direccion.getY());
        this.destino.sumar(this.direccion);
    }

    /**
     * Refleja la direccion del laser verticalmente como rebotando en un espejo vertical. Es decir, si tiene direccion incidente
     * (dx, dy), la direccion reflejada sera (-dx, dy).
     * @pre el laser esta en un borde vertical y viene de un bloque vacio
     * @post El Laser se ha movido en la direccion reflejada
     */
    public void reflejarVerticalmente() {
        this.direccion = new Direccion(-this.direccion.getX(), this.direccion.getY());
        this.destino.sumar(this.direccion);
    }

    /**
     * Refracta la direccion del laser. Si el laser esta en un borde vertical, la direccion se refleja horizontalmente.
     * Si el laser esta en un borde horizontal, la direccion se refleja verticalmente.
     * @post la direccion del laser ha sido refractada. Ocasionando que el laser traslade su origen en la direccion refractada.
     */
    public void refractarHorizontalmente() {
        var direccionAmpliada = new Direccion(direccion);
        direccionAmpliada.redimensionar(2);
        this.origen.sumarHorizontalmente(direccionAmpliada);
        this.destino = new Coordenada(this.origen);
    }

    public void refractarVerticalmente() {
        var direccionAmpliada = new Direccion(direccion);
        direccionAmpliada.redimensionar(2);
        this.origen.sumarVerticalmente(direccionAmpliada);
        this.destino = new Coordenada(this.origen);
    }

    public boolean noSeHaMovido() {
        return this.origen.equals(this.destino);
    }

    /**
     * @post el laser vuelve a su estado inicial: Destino == Origen. Y detenido == false.
     */
    public void resetear() {
        this.destino = new Coordenada(this.origen);
        this.detenido = false;
    }

    /**
     * El laser agrega la informacion de su bifurcacion.
     * @pre: Ambos deben compartir el mismo origen.
     * @post: el laser contiene la informacion de su bifurcacion.
     */
    public void bifurcar(Laser bifurcado) {
        this.bifurcado = bifurcado;
    }

    public boolean estaBifurcado() {
        return this.bifurcado != null;
    }

    public Laser getBifurcado() {
        return this.bifurcado;
    }

    public boolean pasaPor(Coordenada coordenada) {
        return this.origen.equals(coordenada) || this.destino.equals(coordenada);
    }

    public String toString() {
        return this.origen.toString() + " -> " + this.destino.toString();
    }

    /**
     * @return la coordenada de la punta del Laser.
     */
    public Coordenada getDestino() {
        return this.destino;
    }

    /**
     * @return la direccion del Laser.
     */
    public Direccion getDireccion() {
        return this.direccion;
    }

    public Coordenada getOrigen() {
        return this.origen;
    }
}
