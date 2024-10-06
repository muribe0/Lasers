package controlador;

import modelo.Juego;

public class ControladorLasers {
    private Juego juego;

    public ControladorLasers(Juego juego) {
        this.juego = juego;
    }

    public void iniciarJuego() {
        // Hay q configurar la lógica del juego
        // y la interacción con la interfaz gráfica.
        // Por ejemplo, inicializar la vista y los eventos.

        // imprime  el estado inicial del juego.
        System.out.println("Juego iniciado. Nivel actual: " + juego.getNivelActual().toString());
    }

    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        juego.moverBloque(x, y, nuevoX, nuevoY);
        juego.verificarNivelCompletado();
        if (juego.isNivelCompletado()) {
            // aca yo pienso q podemos manejar la transición al siguiente nivel
            System.out.println("¡Nivel completado!");
            // Cargar el siguiente nivel
            // juego.cargarNivel(siguienteNivel);
        }
    }

    public void verificarGanador() {
        if (juego.esJuegoGanado()) {
            // existe ya que no se muevan mas los bloques
            //tambien puede haber un mensaje, por ejemplo:
            System.out.println("¡Has ganado el juego!");
        }
    }
}
