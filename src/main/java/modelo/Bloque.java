package modelo;

public abstract class Bloque {

    public boolean esMovible() {
        return false;
    }

    public boolean esVacio() {
        return false;
    }

    public boolean esOpaco() {
        return false;
    }

    public boolean esEspejo() {
        return false;
    }

    public boolean esVidrio() {
        return false;
    }

    public boolean esCristal() {
        return false;
    }

    // Mét.odo abstracto que cada subclase debe implementar.
    public abstract void interactuarConLaser(Emisor emisor);

    // TODO: Esto no me convence porque depende de saber desde Bloque
    // que cada bloque tiene 2 de ancho (El 2 puede ser atributo de Bloque, pero no se si me convence)
    public boolean estaEnBordeVertical(Coordenada coordenada) {
        return coordenada.getX() % 2 == 0;
    }

    public boolean estaEnBordeHorizontal(Coordenada coordenada) {
        return coordenada.getY() % 2 == 0;
    }

    /**
     * Refleja la direccion del laser en el bloque espejo.
     * @param ubicacion el punto donde se encuentra la punta del laser a reflejar.
     * @param direccion la direccion del laser a reflejar.
     * @return la nueva direccion del laser.
     */
    public Direccion reflejar(Coordenada ubicacion, Direccion direccion) { // TODO
        if (this.estaEnBordeHorizontal(ubicacion)) {
            return new Direccion(direccion.getX(), -direccion.getY());
        } else if (this.estaEnBordeVertical(ubicacion)) {
            return new Direccion(-direccion.getX(), direccion.getY());
        }
        return null;
    }

    @Override
    public String toString() {
        return " ";
    }
}
