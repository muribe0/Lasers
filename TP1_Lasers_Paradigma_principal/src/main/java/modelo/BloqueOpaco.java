package modelo;

public class BloqueOpaco extends Bloque {

    @Override
    public boolean esOpaco() {
        return true;
    }

    @Override
    public void interactuarConLaser(Emisor emisor) {
        emisor.detener();
    }

    @Override
    public String toString() {
        return "F";
    }
}


