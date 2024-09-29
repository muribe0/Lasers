package modelo;

import java.util.ArrayList;
import java.util.List;

public class Emisor {
    private List<Laser> lasers;

    public Emisor(Coordenada origen, Direccion direccion) {
        this.lasers = new ArrayList<>();
        this.lasers.add(new Laser(origen, direccion));
    }

    /**
     * Reinicia la generacion de este haz de luz.
     *
     * @post el haz de luz solo tiene un laser.
     */
    private void resetear() {
        Laser inicio = this.lasers.get(0);
        this.lasers.clear();
        this.lasers.add(inicio);
    }

    /**
     * Emite el laser y lo hace avanzar por la grilla.
     * Si el laser llega a un bloque opaco o a un borde, se detiene.
     * Si llega a un bloque Espejo, cambia su direccion creando otro Laser.
     *
     * @param grilla es la grilla completa
     */
    public void emitir(Grilla grilla) {
        this.resetear();

        // mientras el ultimo laser no llegue al borde de la grilla o a un bloque opaco
        Laser ultimo = getPunta();

        while (ultimo != null) {
            if (!ultimo.avanzar(grilla)) {
                Bloque siguienteBloque = ultimo.getSiguienteBloque(grilla);
                if (siguienteBloque.esOpaco() || siguienteBloque.esVacio() || !grilla.estaDentro(ultimo.getDestino())) {
                    return;
                } else if (siguienteBloque.esEspejo()) {
                    siguienteBloque.interactuarConLaser(this);
                    ultimo = getPunta();
                }
            }
        }
    }

    public Laser getPunta() {
        return this.lasers.get(this.lasers.size() - 1);
    }

    public void agregarLaser(Direccion direccion) {
        Laser ultimo = getPunta();
        Coordenada destino = ultimo.getDestino();
        Laser nuevo = new Laser(destino, direccion);
        this.lasers.add(nuevo);
    }

    /**
     * Genera una string del tipo (x1, y1) -> (x2, y2)(x2,y2) -> (x3, y3) donde x1,y1 son las coordenadas de inicio
     * del primer laser, (x2,y2) son las coordenadas de fin del primer laser e inicio del 2do, (x3,y3) son las
     * coordenadas del fin del segundo laser...
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
