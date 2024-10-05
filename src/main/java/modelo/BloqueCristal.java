package modelo;

public class BloqueCristal extends Bloque{

    public BloqueCristal(Integer dimension) {
        super(dimension);
    }

    @Override
    public boolean esCristal() {
        return true;
    }


    /**
     * Refracta al laser segun el sentido de incidencia del mismo. Si el laser inside hacia un borde horizontal del bloque,
     * se refracta verticalmente. Es decir, el Laser avanza hacia abajo o arriba verticalmente.
     * @param laser: el laser que incide sobre el bloque.
     */
    @Override
    public void interactuarConLaser(Laser laser) {
        if (this.estaEnBordeHorizontal(laser.getDestino())) {
            laser.refractarVerticalmente();
        } else if (this.estaEnBordeVertical(laser.getDestino())) {
            laser.refractarHorizontalmente();
        }
    }

    @Override
    public String toString() {
        return "C";
    }
}
