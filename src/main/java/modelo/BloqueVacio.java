package modelo;

public class BloqueVacio extends Bloque {
    private Coordenada coordenada;

    @Override
    public boolean esVacio() {
        return true;
    }

    public void interactuarConLaser(Emisor emisor) {
        // El láser continúa sin ser afectado
        // laser.continuar();
    }

    public String toString() {
        return ".";
    }
}
