package modelo;

public abstract class Bloque {

    public boolean esMovible() {
        return false;
    }

    public boolean esVacio() {
        return false;
    }

    public boolean esOpaco() {
        return false;
    }

    public boolean esEspejo() {
        return false;
    }

    public boolean esVidrio() {
        return false;
    }

    public boolean esCristal() {
        return false;
    }

    // Método abstracto que cada subclase debe implementar.
    public void interactuarConLaser(Laser laser) {

    }

    @Override
    public String toString() {
        return " ";
    }
}
