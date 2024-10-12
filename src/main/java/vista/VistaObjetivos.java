package vista;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modelo.Coordenada;
import modelo.Objetivo;

import java.util.List;

public class VistaObjetivos extends StackPane {
    private Integer tamanioBloque;
    List<Objetivo> objetivos;

    /**
     * Crea una instancia de VistaObjetivos como un StackPane alineado a la esquina superior izquierda de su contenedor.
     * Coloca los Objetivos del nivel relativo a el tamanioBloque.
     * @param objetivos: lista de objetivos
     * @param tamanioBloque: tamanioBloque en pixeles
     */
    public VistaObjetivos(List<Objetivo> objetivos, Integer tamanioBloque) {
        this.setAlignment(Pos.TOP_LEFT);
        this.tamanioBloque = tamanioBloque;
        this.objetivos = objetivos;

        dibujarObjetivos();
    }

    /**
     * Coloca los objetivos del nivel en el StackPane
     */
    private void dibujarObjetivos() {
        for (Objetivo objetivo : objetivos) {
            // Obtener la posición del objetivo en la grilla
            Coordenada posicion = objetivo.getPosicion();
            int columna = posicion.getX();
            int fila = posicion.getY();

            // Crear un rectángulo que represente el objetivo
            Circle circle = new Circle((double) tamanioBloque / 8, Color.RED);
            circle.setFill(Color.DARKORANGE); // Color del objetivo

            circle.setTranslateX(columna * tamanioBloque - (double) tamanioBloque / 16);
            circle.setTranslateY(fila * tamanioBloque - (double) tamanioBloque / 16);

            // Posicionar el rectángulo en la grilla
            this.getChildren().add(circle); // Añade el objetivo en la posición de la grilla
        }
    }
}
