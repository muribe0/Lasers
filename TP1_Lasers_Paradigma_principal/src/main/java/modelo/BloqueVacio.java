package modelo;

public class BloqueVacio extends Bloque {
    private Coordenada coordenada;

    @Override
    public boolean esVacio() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // El láser continúa sin ser afectado
       // laser.continuar();
    }

    public String toString() {
        return "bloque vacio";
    }
}
