package modelo;

public class BloqueVidrio extends Bloque{
    private Coordenada coordenada;

    @Override
    public boolean esEspejo() {
        return true;
    }

    @Override
    public boolean esVidrio() {
        return true;
    }

    public void interactuarConLaser(Laser laser) {
        // El láser atraviesa el bloque sin detenerse
        //laser.continuar();
        //laser.reflejar();
    }

    public String toString() {
        return "bloque vidrio";
    }
}
