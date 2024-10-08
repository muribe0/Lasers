package modelo;

public class BloqueOpaco extends Bloque {

    public BloqueOpaco(Integer dimension) {
        super(dimension);
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        laser.detener();
    }

    @Override
    public String toString() {
        return "F";
    }
}


