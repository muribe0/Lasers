package modelo;

public class BloqueOpacoMovil extends Bloque {

    public BloqueOpacoMovil(Integer dimension) {
        super(dimension);
    }


    @Override
    public void interactuarConLaser(Laser laser) {
        laser.detener();
    }


    @Override
    public String toString() {
        return "B";
    }
}

