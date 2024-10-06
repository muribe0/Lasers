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
    public void emitir(Grilla grilla) {
        Laser ultimo = this.lasers.getFirst();
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


    public Laser getPunta() {
        return this.lasers.getLast();
    }

    /**
     * Agrega un laser a la lista de lasers, efectivamente en la punta del ultimo laser. Utiliza la posicion de la punta del anterior laser como origen
     *
     * @param direccion: define la direccion del laser a agregar.
     */
    public void agregarLaser(Direccion direccion) {
        Laser ultimo = getPunta();
        Coordenada destino = ultimo.getDestino();
        Laser nuevo = new Laser(destino, direccion);
        this.lasers.add(nuevo);
    }

    public void agregarLaser(Laser laser) {
        this.lasers.add(laser);
    }

    public boolean pasaPor(Coordenada coordenada) {
        for (Laser laser : this.lasers) {
            if (laser.pasaPor(coordenada)) {
                return true;
            }
        }
        return false;
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
}
