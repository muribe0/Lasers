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
}

// Subclase Bloque Opaco Fijo
//public class BloqueOpacoFijo extends Bloque {
    //@Override
    //public void interactuarConLaser(Laser laser) {
        // El rayo se detiene aquí
  //  }
//}

