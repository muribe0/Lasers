package modelo;

public class BloqueEspejo extends Bloque{
    private Coordenada coordenada;

    @Override
    public boolean esEspejo() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Refleja el láser en una dirección diferente
     //   laser.reflejar();
    }

    public String toString() {
        return "bloque espejo";
    }
}
