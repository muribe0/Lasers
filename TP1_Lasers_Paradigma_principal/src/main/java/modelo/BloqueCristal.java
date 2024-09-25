package modelo;

public class BloqueCristal extends Bloque{
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
        return false;
    }

    @Override
    public boolean esVidrio() {
        return false;
    }

    @Override
    public boolean esMovible() {
        return false;
    }

    @Override
    public boolean esCristal() {
        return true;
    }

    public String toString() {
        return "bloque cristal";
    }
}
