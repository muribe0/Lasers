package modelo;

public class BloqueOpacoMovil extends Bloque {

    @Override
    public boolean esMovible() {
        return true;
    }

    @Override
    public boolean esOpaco() {
        return true;
    }

    @Override
    public void interactuarConLaser(Emisor emisor) {
        emisor.detener();
    }

    // Juego llame a Emisor.emitir() cada vez que se mueva un bloque
    // Grilla tiene que haber un metodo que intercambie dos bloques

    // Si hizo clic en un bloque y fue movible, me guardo las Coordenadas
    // Si volves a hacer clic tengo las Coordenadas, ejecutas el metodo de Grilla.moverBloque()

    // Ver si la coordenada que le pasas es de un bloque vacio, la coordenada origen
    // moverBloque() {
    // se fija si la coordenada origen es Movil, si el destino es vacio
    //
    //}

    @Override
    public String toString() {
        return "B";
    }
}

