package modelo;

public class BloqueVidrio extends Bloque{
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
    public boolean esVidrio() {
        return true;
    }

    @Override
    public boolean esMovible() {
        return false;
    }

    public String toString() {
        return "bloque vidrio";
    }
}
