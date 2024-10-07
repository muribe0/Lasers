package vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Nivel;

public class VistaNivel {
    private Nivel nivel;
    private Integer alto;
    private Integer ancho;
    private Scene scene;
    private StackPane stackPane;
    private HBox buttons;

    private VistaGrilla panelGrilla;
    private VistaLasers panelLaser;

    public VistaNivel(Stage stage, Nivel nivel) {
        this.nivel = nivel;
        this.alto = nivel.getGrilla().getAlto();
        this.ancho = nivel.getGrilla().getAncho();

        this.panelGrilla = new VistaGrilla(nivel.getGrilla());
        this.panelLaser = new VistaLasers(nivel);

        this.stackPane = new StackPane();

        stackPane.getChildren().addAll(panelGrilla, panelLaser);

        var tamanioBloque = panelGrilla.getTamanioDeCelda();

        // Parte visual

        scene = new Scene(stackPane, ancho * tamanioBloque, alto * tamanioBloque + tamanioBloque);
        stage.setScene(scene);
        stage.show();
    }

    private void poblarBotones(String rutaNiveles[]) {
        //String rutaNiveles[] = { "level0.dat", "level1.dat", "level2.dat" };
        //        poblarBotones(rutaNiveles);
        // usar afuera

        for (var ruta : rutaNiveles) {
            Button boton = new Button("src/main/resources/" + ruta);
            buttons.getChildren().add(boton);
        }

        stackPane.getChildren().add(buttons);
    }

}
