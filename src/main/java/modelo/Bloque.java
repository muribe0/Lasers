package modelo;

public class Bloque {

    public boolean esMovible() {
        return false;
    }

    public boolean esVacio() {
        return false;
    }

    public boolean esOpaco() {
        return false;
    }

    public boolean esEspejo() {
        return false;
    }

    @Override
    public String toString() {
        return " ";
    }

    public Coordenada reflejar(Coordenada ubicacion, Coordenada direccion) {
        return null;
    }
}

// Subclase Bloque Opaco Fijo
//public class BloqueOpacoFijo extends Bloque {
    //@Override
    //public void interactuarConLaser(Laser laser) {
        // El rayo se detiene aquí
  //  }
//}

