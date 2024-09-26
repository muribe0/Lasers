package modelo;

public class BloqueVacio extends Bloque {
    private Coordenada coordenada;

    @Override
    public boolean esVacio() {
        return true;
    }

    public String toString() {
        return ".";
    }
}
