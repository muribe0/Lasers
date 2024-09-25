package modelo;

import java.util.ArrayList;
import java.util.List;

public class Emisor {
    private List<Laser> lasers;

    private void resetear() {
        Laser inicio = this.lasers.get(0);
        this.lasers.clear();
        this.lasers.add(inicio);
    }

    public Emisor(Laser laser) {
        this.lasers = new ArrayList<>();
        this.lasers.add(laser);
    }

    public void emitir(Grilla grilla) {
        this.resetear();

        // mientras el ultimo laser no llegue al borde de la grilla o a un bloque opaco
        Laser ultimo = this.lasers.get(this.lasers.size() - 1);

        if (!ultimo.avanzar(grilla)) {
            Bloque siguienteBloque = ultimo.getSiguienteBloque(grilla);
            if (siguienteBloque.esOpaco()) {
                return;
            } else if (siguienteBloque.esVacio()) {
                return;
            }
        }
    }

    public String toString() {
        var laser_completo = "";
        for (Laser laser : this.lasers) {
            laser_completo += laser.toString();
        }
        return laser_completo;
    }
}
