import controlador.ControladorLasers;
import modelo.Juego;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del juego
        Juego juego = new Juego();

        // Iniciar el juego (esto carga los niveles)
        juego.iniciarJuego();

        // Inicializar el controlador con el nivel actual del juego
        ControladorLasers controlador = new ControladorLasers(juego);
        controlador.iniciarJuego();
    }
}
