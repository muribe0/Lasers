package controlador;

import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Nivel;
import vista.VistaJuego;
import vista.VistaNivel;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Parte MVC
        var juego = new Juego();
        var vista = new VistaJuego(stage, juego);

    }

    public static void main(String[] args) {
        launch(args);
    }
}


