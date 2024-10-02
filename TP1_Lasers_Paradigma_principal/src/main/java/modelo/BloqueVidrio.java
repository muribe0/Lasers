package modelo;

public class BloqueVidrio extends Bloque {
    private Coordenada coordenada;

    @Override
    public boolean esVidrio() {
        return true;
    }

    public void interactuarConLaser(Emisor emisor) {
        // Refleja el láser saliendo por el extremo opuesto del bloque
        Laser laser = emisor.getPunta();
        Direccion direccionActual = laser.getDireccion();
        Coordenada ubicacionActual = laser.getDestino();
        // Reflejar el láser según su dirección y la ubicación en el bloque
        Direccion nuevaDireccion = reflejar(ubicacionActual, direccionActual);
        if (nuevaDireccion != null) {
            emisor.agregarLaser(nuevaDireccion);
        }
        Laser laserPunta = new Laser(ubicacionActual, nuevaDireccion);
        //Laser laserReflejado = new Laser(new Coordenada(ubicacionActual), nuevaDireccion);
        // Dejar que el láser actual continúe en la misma dirección
        //laserPunta
        //laser.setPosicion(nuevaPosicion);
        //laser.avanzar(emisor.getGrilla());

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
    public String toString() {
        return "G";
    }
}