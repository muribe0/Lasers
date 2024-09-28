package modelo;

public class BloqueOpaco extends Bloque {

    @Override
    public boolean esOpaco() {
        return true;
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Detiene el láser
       // laser.detener();
    }

    @Override
    public String toString() {
        return "F";
    }
    //QUE HACEMOS CON OPACO MOVIL??
}


