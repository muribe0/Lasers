package modelo;

public class BloqueOpaco extends Bloque {

    @Override
    public boolean esOpaco() {
        return true;
    }

    @Override
    public String toString() {
        return "#";
    }
}