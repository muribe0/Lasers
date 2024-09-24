public abstract class Bloque {
    public abstract void interactuarConLaser(Laser laser);

    public boolean esMovible() {
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

