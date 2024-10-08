package vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Juego;

public class VistaJuego {
    private HBox hBox;
    private VBox buttons;
    private Juego juego;

    private final Integer TAM_PX_BLOQUE = 40;

    public VistaJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.hBox = new HBox();
        this.buttons = new VBox();

        String rutaNiveles[] = {"src/main/resources/level1.dat",
                "src/main/resources/level2.dat",
                "src/main/resources/level3.dat",
                "src/main/resources/level4.dat",
                "src/main/resources/level5.dat",
                "src/main/resources/level6.dat"};
        poblarBotones(rutaNiveles);
        var scene = new Scene(hBox, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Método para poblar los botones con sus eventos
    public void poblarBotones(String[] rutaNiveles) {
        for (int i = 0; i < rutaNiveles.length; i++) {
            String ruta = rutaNiveles[i];
            int numeroNivel = i; // Para usar en el evento

            // Crear el botón para el nivel
            Button boton = new Button("Nivel " + (numeroNivel + 1));
            // Agregar manejador de eventos
            boton.setOnAction(e -> {
                // Cambiar al nivel correspondiente usando el controlador
                juego.iniciarNivel(ruta);
                hBox.getChildren().clear();
                hBox.getChildren().add(buttons);
                hBox.getChildren().add(new VistaNivel(juego.getNivelActual(), TAM_PX_BLOQUE));
            });
            // Añadir el botón al layout
            buttons.getChildren().add(boton);
        }
        // Añadir el VBox con botones al StackPane
        hBox.getChildren().add(buttons);
    }
}

