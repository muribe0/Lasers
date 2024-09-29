package modelo;

public class BloqueEspejo extends Bloque {

    @Override
    public boolean esEspejo() {
        return true;
    }

    @Override
    public void interactuarConLaser(Emisor emisor) {
        Laser laser = emisor.getPunta();
        Direccion direccionActual = laser.getDireccion();
        Coordenada ubicacionActual = laser.getDestino();
        // Reflejar el láser según su dirección y la ubicación en el bloque
        Direccion nuevaDireccion = reflejar(ubicacionActual, direccionActual);
        emisor.agregarLaser(nuevaDireccion);
    }

    // TODO: Esto no me convence porque depende de saber desde Bloque
    // que cada bloque tiene 2 de ancho (El 2 puede ser atributo de Bloque, pero no se si me convence)
    private boolean estaEnBordeVertical(Coordenada coordenada) {
        return coordenada.getX() % 2 == 0;
    }
    private boolean estaEnBordeHorizontal(Coordenada coordenada) {
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
        return "R";
    }
}

