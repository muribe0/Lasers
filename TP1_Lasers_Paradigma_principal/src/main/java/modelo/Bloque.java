package modelo;

public class Bloque {

    public boolean esMovible() {
        return false;
    }

    public boolean esVacio() {
        return false;
    }

    public boolean esOpaco() {
        return false;
    }

    public boolean esEspejo(){return false;}

    public boolean esVidrio() {
        return false;
    }

    public boolean esCristal() {
        return false;
    }

    public abstract void interactuarConLaser(Laser laser);
}


