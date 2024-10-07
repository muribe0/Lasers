package controlador;

import modelo.Nivel;
import vista.VistaNivel;

import java.util.ArrayList;
import java.util.List;

public class ControladorNivel {
    private Nivel nivel;
    private VistaNivel vista;

    public ControladorNivel(Nivel nivel, VistaNivel vista) {
        this.nivel = nivel;
        this.vista = vista;
    }

    // controlar modelo

    public void emitir() {
        nivel.getEmisores().forEach(emisor -> emisor.emitir(nivel.getGrilla()));
    }

    public void moverBloque(Integer x, Integer y, Integer nuevoX, Integer nuevoY) {
        nivel.moverBloque(x, y, nuevoX, nuevoY);
    }

    // controlar vista
    public void iniciar() {
    }
}
