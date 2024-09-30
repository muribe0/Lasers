package modelo;

public class Laser {
    private Coordenada origen;
    private Direccion direccion;
    private Coordenada destino;

    public Laser(Coordenada origen, Direccion direccion) {
        this.origen = origen;
        this.direccion = direccion;
        this.destino = new Coordenada(origen);
    }

    /**
     * Avanza el laser en la direccion que tiene asignada.
     * @return falso cuando el laser llega al borde de la grilla o a un bloque opaco.
     */
    public boolean avanzar(Grilla grilla) {

        this.destino.sumar(this.direccion);
        while (grilla.estaDentro(this.destino) && this.getSiguienteBloque(grilla).esVacio()) {
            this.destino.sumar(this.direccion);
        }
        if (!grilla.estaDentro(this.destino)) {
            this.destino.restar(this.direccion);
        }
        return false;
    }

    /**
     * Obtiene el siguiente bloque con el que interactua el laser.
     * @return el bloque con el que interactua el laser.
     */
    public Bloque getSiguienteBloque(Grilla grilla) {
        return grilla.getBloque(this.destino);
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
}
