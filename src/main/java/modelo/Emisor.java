package modelo;


import java.util.LinkedList;
import java.util.List;

public class Emisor {
    private List<Laser> lasers;

    /**
     * Crea un emisor con una ArrayList de un solo laser cuyo origen y direccion son los parametros dados.
     *
     * @param origen:    Coordenada de inicio del laser.
     * @param direccion: Direccion en la que se movera el laser.
     */
    public Emisor(Coordenada origen, Direccion direccion) {
        this.lasers = new LinkedList<>();
        this.lasers.add(new Laser(origen, direccion));
    }

    /**
     * Reinicia la generacion de este haz de luz.
     *
     * @post el haz de luz solo tiene un laser.
     */
    private void resetear() {
        this.lasers.getFirst().resetear();
        this.lasers.clear();
    }

    /**
     * Emite el laser y lo hace avanzar por la grilla.
     * Si el laser llega a un bloque opaco o a un borde, se detiene.
     * Si llega a un bloque Espejo, se refleja.
     * Si llega a un bloque Cristal, se refracta.
     * Si llega a un bloque Vidrio, sigue su camino y crea otro emisor.
     *
     * @param grilla es la grilla completa
     */
    public void emitir(Grilla grilla) throws NullPointerException {
        validarEmisorVacio();
        Laser ultimo = new Laser(this.lasers.getFirst());
        this.resetear();
        // mientras el ultimo laser no llegue al borde de la grilla o a un bloque opaco
        emitirDesde(ultimo, grilla);
    }

    /**
     * Emitir desde el laser dado.
     * @param laser
     */
    private void emitirDesde(Laser laser, Grilla grilla) {
        LinkedList<Laser> lasersBifurcados = new LinkedList<>();
        laser.resetear();
        while (! laser.estaDetenido()) {
            laser = new Laser(laser.getDestino(), laser.getDireccion());
            // si esta en un borde horizontal, fijarse el siguiente bloque e interactuar con ese bloque
            if (interactuarConSiguienteBloque(laser, grilla) && ! laser.noSeHaMovido()) {
                if (laser.estaBifurcado()) {
                    lasersBifurcados.add(laser.getBifurcado());
                }
                agregarLaser(laser);
            }
        }
        for (Laser bifurcado : lasersBifurcados) {
            emitirDesde(bifurcado, grilla);
        }
    }

    /**
     * Hace que un tramo de laser interactue con el bloque siguiente.
     * @param laser
     * @param grilla
     */
    public boolean interactuarConSiguienteBloque(Laser laser, Grilla grilla) {
        var destino = new Coordenada(laser.getDestino());
        if (grilla.estaEnBordeHorizontal(destino)) {
            destino.sumarVerticalmente(laser.getDireccion());
        } else if (grilla.estaEnBordeVertical(destino)) {
            destino.sumarHorizontalmente(laser.getDireccion());
        }
        if (! grilla.estaDentro(destino)) {
            laser.detener();
            return false;
        }
        var siguienteBloque = grilla.getBloque(destino);
        siguienteBloque.interactuarConLaser(laser);
        return true;
    }

    /**
     * Obtiene el ultimo laser, si es que existe.
     * @return
     */
    public Laser getPunta() throws NullPointerException {
        validarEmisorVacio();
        return this.lasers.getLast();
    }

    /**
     * Agrega un tramo de Laser al final de la lista de Lasers
     * @param laser final
     */
    public void agregarLaser(Laser laser) {
        this.lasers.add(laser);
    }

    /**
     * Verifica si el emisor pasa por una coordenada dada. Por ejemplo, un objetivo
     * @param coordenada de un lugar en la grilla
     * @return true si pasa por la coordenada dada.
     * @throws NullPointerException si el emisor esta vacio.
     */
    public boolean pasaPor(Coordenada coordenada) throws NullPointerException {
        for (Laser laser : this.lasers) {
            if (laser.pasaPor(coordenada)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Lista de tramos de Laser de este Emisor
     */
    public List<Laser> getTramos() {
        validarEmisorVacio();
        return this.lasers;
    }

    /**
     * Obtiene la coordenada de origen del Emisor.
     * @return Coordenada de origen.
     * @throws NullPointerException si el emisor esta vacio
     */
    public Coordenada getOrigen() throws NullPointerException {
        validarEmisorVacio();
        return this.lasers.getFirst().getOrigen();
    }

    /**
     * Genera una string del tipo (x1, y1) -> (x2, y2)(x2,y2) -> (x3, y3) donde x1,y1 son las coordenadas de inicio
     * del primer laser, (x2,y2) son las coordenadas de fin del primer laser e inicio del 2do, (x3,y3) son las
     * coordenadas del fin del segundo laser...
     *
     * @return String con la representacion de los lasers.
     */
    public String toString() {
        var laser_completo = "";
        for (Laser laser : this.lasers) {
            laser_completo += laser.toString();
        }
        return laser_completo;
    }

    /**
     * Valida que el emisor no este vacio.
     * @throws NullPointerException
     */
    private void validarEmisorVacio() throws NullPointerException {
        if (this.lasers.isEmpty()) {
            throw new NullPointerException("El emisor esta vacio");
        }
    }
}
