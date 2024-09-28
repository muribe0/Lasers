package modelo;

public class BloqueCristal extends Bloque{

    @Override
    public boolean esCristal() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Refleja el láser saliendo por el extremo opuesto del bloque
        Coordenada nuevaDireccion = new Coordenada(laser.getDireccion());
        laser.setDireccion(nuevaDireccion);
    }

    @Override
    public String toString() {
        return "C";
    }
}
