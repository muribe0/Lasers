package vista;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import modelo.Emisor;
import modelo.Coordenada;

import java.util.List;

public class VistaLasers extends StackPane {
    private List<Emisor> emisores; // Grilla para mostrar los bloques

    private Integer tamanoBloque = 40;


    public VistaLasers(List<Emisor> emisores, Integer tamanoBloque) {
        this.tamanoBloque = tamanoBloque;
        this.emisores = emisores;
        this.setAlignment(Pos.TOP_LEFT);
        inicializarEmisores();

    }

    public void inicializarEmisores() {
        this.getChildren().clear();
        for (Emisor emisor : emisores) {
            inicializarEmisor(emisor);
        }
    }

    private void inicializarEmisor(Emisor emisor) {
        Coordenada desde;
        Coordenada hasta;
        for (var tramo : emisor.getTramos()) {
            desde = tramo.getOrigen();
            hasta = tramo.getDestino();
            // Defino la linea
            Line linea = new Line(desde.getX() * tamanoBloque, desde.getY() * tamanoBloque, hasta.getX() * tamanoBloque, hasta.getY() * tamanoBloque);
            // Movemos la linea
            Integer x = Math.min(desde.getX(), hasta.getX());
            Integer y = Math.min(desde.getY(), hasta.getY());

            linea.setTranslateX(x * tamanoBloque);
            linea.setTranslateY(y * tamanoBloque);

            linea.strokeWidthProperty().set(2);
            linea.setStroke(Color.RED);

            this.getChildren().add(linea);
        }
    }



}
