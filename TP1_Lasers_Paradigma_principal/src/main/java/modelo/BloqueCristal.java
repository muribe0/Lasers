package modelo;

public class BloqueCristal extends Bloque{
    private Coordenada coordenada;

    @Override
    public boolean esCristal() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Refleja el láser saliendo por el extremo opuesto del bloque, con la misma dirección de origen.
        //laser.refractar()
    }

    public String toString() {
        return "bloque cristal";
    }

}
