package modelo;

public class BloqueVacio extends Bloque {

    public BloqueVacio(Integer dimension) {
        super(dimension);
    }

    @Override
    public boolean esVacio() {
        return true;
    }

    public void interactuarConLaser(Laser laser) {
        laser.continuar();
    }

    public String toString() {
        return ".";
    }
}