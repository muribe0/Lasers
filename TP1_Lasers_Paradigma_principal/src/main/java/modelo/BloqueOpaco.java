package modelo;

public class BloqueOpaco extends Bloque {

    @Override
    public boolean esOpaco() {
        return true;
    }

    public String toString() {
        return "bloque opaco";
    }
}
