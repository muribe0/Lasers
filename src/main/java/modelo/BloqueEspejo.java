package modelo;

public class BloqueEspejo extends Bloque {

    public BloqueEspejo(Integer dimension) {
        super(dimension);
    }


    /**
     * Refleja al laser segun el sentido de incidencia del mismo. Si el laser inside hacia un borde horizontal del bloque,
     * se refleja horizontalmente. Es decir, rebota en el sentido contrario al que venia unicamente de forma horizontal,
     * manteniendo el sentido vertical intacto.
     * @param laser: el laser que incide sobre el bloque.
     */
    @Override
    public void interactuarConLaser(Laser laser) {
        if (this.estaEnBordeHorizontal(laser.getDestino())) {
            laser.reflejarHorizontalmente();
        } else if (this.estaEnBordeVertical(laser.getDestino())) {
            laser.reflejarVerticalmente();
        }
    }

    @Override
    public String toString() {
        return "R";
    }
}

