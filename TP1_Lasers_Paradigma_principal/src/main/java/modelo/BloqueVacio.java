package modelo;

public class BloqueVacio extends Bloque {
    private Coordenada coordenada;

    @Override
    public boolean esOpaco() {
        return false;
    }

    @Override
    public boolean esVacio() {
        return true;
    }

    public String toString() {
        return "bloque vacio";
    }
}
