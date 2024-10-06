package modelo;

public class BloqueSinPiso extends Bloque {

    public BloqueSinPiso(Integer dimension) {
        super(dimension);
    }

    public boolean esPiso() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        laser.continuar();
    }
}
