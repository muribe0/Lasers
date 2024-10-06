package controlador;

import modelo.Nivel;
import vista.VistaLasers;

public class ControladorLasers {
    private Nivel nivel; // Modelo
    private VistaLasers vista; // Vista

    public ControladorLasers(Nivel nivel) {
        this.nivel = nivel;
        this.vista = new VistaLasers(nivel);
    }

    public void iniciarJuego() {
        vista.start(new Stage());
    }

    public void moverBloque(Integer x, Integer y, Integer nuevoX, Integer nuevoY) {
        nivel.moverBloque(x, y, nuevoX, nuevoY);
        vista.actualizarGrilla();
    }

    public boolean verificarGanador() {
        return nivel.validarSolucion();
    }
}
