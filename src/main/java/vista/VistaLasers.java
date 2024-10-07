package vista;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import modelo.Emisor;
import modelo.Nivel;
import modelo.Coordenada;

import java.util.List;

public class VistaLasers extends StackPane {
    private List<Emisor> emisores; // Grilla para mostrar los bloques

    private Integer TAM_CELDA_PX = 40;


    public VistaLasers(Nivel nivel) {
        this.emisores = nivel.getEmisores();
        this.setAlignment(Pos.TOP_LEFT);
//        inicializarEmisores();
        var tramo1 = new Line(0, 40, 40, 80);
        tramo1.setTranslateY(40);
        this.getChildren().add(tramo1);
    }

    private void inicializarEmisores() {
        for (Emisor emisor : emisores) {
            Coordenada origen = emisor.getOrigen();
            inicializarEmisor(origen, emisor);
        }
    }

    private void inicializarEmisor(Coordenada origen, Emisor emisor) {
        Coordenada desde = origen;
        for (var tramo : emisor.getTramos()) {
            Coordenada hasta = tramo.getDestino();
            Line linea = new Line(desde.getX() * TAM_CELDA_PX, desde.getY() * TAM_CELDA_PX, hasta.getX() * TAM_CELDA_PX, hasta.getY() * TAM_CELDA_PX);
            linea.setFill(Color.RED);
            linea.setStroke(Color.RED);
            Integer x = desde.getX() / 2;
            Integer y = desde.getY() / 2;
            this.getChildren().add(linea);
            desde = hasta;
        }
    }



}
