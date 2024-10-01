package modelo;

public class BloqueCristal extends Bloque{

    @Override
    public boolean esCristal() {
        return true;
    }


    /**
     * Dada la punta del emisor, agrega un nuevo laser al emisor de manera tal de que el origen del nuevo laser este
     * refractado a partir de la punta de la anterior punta. Dependiendo de la Direccion de incidencia del Laser, se va
     * a refractar de una manera u otra.
     * @param emisor
     */
    public void interactuarConLaser(Emisor emisor) {
        // Refleja el láser saliendo por el extremo opuesto del bloque
        Laser laser = emisor.getPunta();
        Direccion direccionActual = laser.getDireccion();
        Coordenada ubicacionActual = laser.getDestino();
        // Reflejar el láser según su dirección y la ubicación en el bloque
        Coordenada nuevoOrigen = refractar(ubicacionActual, direccionActual);
        emisor.agregarLaser(new Laser(nuevoOrigen, direccionActual));
    }

    public Coordenada refractar(Coordenada ubicacion, Direccion direccion) {
        var nuevoOrigen = new Coordenada(ubicacion);

        if (this.estaEnBordeVertical(ubicacion)) {
            nuevoOrigen.sumarHorizontalmente(new Direccion(direccion.getX() * 2, direccion.getY()));
            return nuevoOrigen;
        } else if (this.estaEnBordeHorizontal(ubicacion)) {
            nuevoOrigen.sumarVerticalmente(new Direccion(direccion.getX(), direccion.getY()  * 2));
            return nuevoOrigen;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "C";
    }
}
