package modelo;

public class BloqueSinPiso extends Bloque {

    public BloqueSinPiso(Integer dimension) {
        super(dimension);
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        laser.continuar();
    }
}
