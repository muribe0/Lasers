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

    @Override
    public String toString() {
        return "R";
    }
}

