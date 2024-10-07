package controlador;

import modelo.Juego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Nivel;
import vista.VistaNivel;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Parte MVC
        var nivel1 = new Nivel("src/main/resources/levelVista.dat");
        var vista = new VistaNivel(stage, nivel1);
        var controlador = new ControladorNivel(nivel1, vista);


//        controlador.iniciar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


