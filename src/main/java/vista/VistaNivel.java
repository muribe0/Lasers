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
    private VBox vbox;
    private HBox buttons;

    private VistaGrilla panelGrilla;

    public VistaNivel(Stage stage, Nivel nivel) {
        this.nivel = nivel;
        this.alto = nivel.getGrilla().getAlto();
        this.ancho = nivel.getGrilla().getAncho();

        this.panelGrilla = new VistaGrilla(nivel.getGrilla());

        this.vbox = new VBox();
        this.buttons = new HBox();

        vbox.getChildren().add(panelGrilla);

        String rutaNiveles[] = { "level0.dat", "level1.dat", "level2.dat" };
        poblarBotones(rutaNiveles);

        var tamanioBloque = panelGrilla.getTamanioDeCelda();

        // Parte visual
        var label = new Label("Hola mundo!");

        scene = new Scene(vbox, ancho * tamanioBloque, alto * tamanioBloque + tamanioBloque);
        stage.setScene(scene);
        stage.show();
    }

    private void poblarBotones(String rutaNiveles[]) {
        for (var ruta : rutaNiveles) {
            Button boton = new Button("src/main/resources/" + ruta);
            buttons.getChildren().add(boton);
        }

        vbox.getChildren().add(buttons);
    }

}
