package modelo;

public class Laser {
    private Coordenada origen;
    private Coordenada direccion;
    private Coordenada destino;

    public Laser(Coordenada origen, Coordenada direccion) {
        this.origen = origen;
        this.direccion = direccion;
        this.destino = new Coordenada(origen);
    }

    /**
     * Avanza el laser en la direccion que tiene asignada.
     * @return falso cuando el laser llega al borde de la grilla o a un bloque opaco.
     */
    public boolean avanzar(Grilla grilla) {
        this.destino.mover(this.direccion);
        while (this.getSiguienteBloque(grilla).esVacio() && grilla.estaDentro(this.destino)) {
            this.destino.mover(this.direccion);
        }
        return false;
    }

    /**
     * Obtiene el siguiente bloque con el que interactua el laser.
     * @return el bloque con el que interactua el laser.
     */
    public Bloque getSiguienteBloque(Grilla grilla) {
        return grilla.getBloque(destino.getMitad());
    }

    public String toString() {
        return "Laser: " + this.origen.toString() + " -> " + this.destino.toString() + "\n";
    }
}
