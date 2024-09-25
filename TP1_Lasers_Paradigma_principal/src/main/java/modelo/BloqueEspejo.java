package modelo;

public class BloqueEspejo extends Bloque{
    private Coordenada coordenada;

    @Override
    public boolean esOpaco() {
        return false;
    }

    @Override
    public boolean esVacio() {
        return false;
    }

    @Override
    public boolean esEspejo() {
        return true;
    }

    @Override
    public boolean esMovible() {
        return true;
    }

    
    public String toString() {
        return "bloque espejo";
    }
}
