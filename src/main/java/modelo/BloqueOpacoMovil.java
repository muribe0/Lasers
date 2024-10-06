package modelo;

public class BloqueOpacoMovil extends Bloque {

    public BloqueOpacoMovil(Integer dimension) {
        super(dimension);
    }

    @Override
    public boolean esMovible() {
        return true;
    }

    @Override
    public boolean esOpaco() {
        return true;
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