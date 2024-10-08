package modelo;

public class BloqueVidrio extends Bloque {

    public BloqueVidrio(Integer dimension) {
        super(dimension);
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        var bifurcacion = new Laser(laser.getDestino(), laser.getDireccion());
        if (this.estaEnBordeHorizontal(bifurcacion.getDestino())) {
            bifurcacion.reflejarDireccionHorizontalmente();
        } else if (this.estaEnBordeVertical(bifurcacion.getDestino())) {
            bifurcacion.reflejarDireccionVerticalmente();
        }
        laser.bifurcar(bifurcacion);
        laser.continuar();
    }


    public String toString() {
        return "G";
    }
}







