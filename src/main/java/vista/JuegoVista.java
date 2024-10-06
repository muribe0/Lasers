package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.*;

public class JuegoVista extends Application {
    private Grilla grillaModelo;  // Referencia al modelo
    private GridPane gridPane;  // Representación gráfica de la grilla

    @Override
    public void start(Stage primaryStage) {
        grillaModelo = new Grilla(6, 5); // Ejemplo de una grilla de 6x5

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        actualizarVistaGrilla();

        Scene scene = new Scene(gridPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Juego de Lasers");
        primaryStage.show();
    }

    private void actualizarVistaGrilla() {
        gridPane.getChildren().clear();

        for (int row = 0; row < grillaModelo.getFilas(); row++) {
            for (int col = 0; col < grillaModelo.getColumnas(); col++) {
                Bloque bloque = grillaModelo.getBloque(new Coordenada(row, col));
                Rectangle rect = new Rectangle(50, 50, Color.LIGHTGRAY);

                // Cambiar el color según el tipo de bloque
                if (bloque instanceof BloqueCristal) {
                    rect.setFill(Color.AQUA);
                } else if (bloque instanceof BloqueOpaco) {
                    rect.setFill(Color.BLACK);
                } else if (bloque instanceof BloqueEspejo) {
                    rect.setFill(Color.GOLD);
                } else if (bloque instanceof BloqueVacio) {
                    rect.setFill(Color.LIGHTGRAY);
                }

                // Evento de mouse para arrastrar y mover bloques
                rect.setOnMousePressed(event -> iniciarArrastreBloque(event, rect));
                rect.setOnMouseDragged(event -> moverBloqueDuranteArrastre(event, rect));
                rect.setOnMouseReleased(event -> soltarBloque(event, row, col));

                gridPane.add(rect, col, row);
            }
        }
    }

    private void iniciarArrastreBloque(MouseEvent event, Rectangle rect) {
        rect.setFill(Color.GRAY);  // Cambiar color para indicar que está siendo arrastrado
    }

    private void moverBloqueDuranteArrastre(MouseEvent event, Rectangle rect) {
        rect.setX(event.getSceneX() - rect.getWidth() / 2);
        rect.setY(event.getSceneY() - rect.getHeight() / 2);
    }

    private void soltarBloque(MouseEvent event, int origenFila, int origenColumna) {
        int destinoFila = (int) (event.getY() / 55);  // Asumimos 5 px de separación
        int destinoColumna = (int) (event.getX() / 55);

        // Verificar si la coordenada es válida dentro de la grilla
        if (destinoFila >= 0 && destinoFila < grillaModelo.getFilas() &&
                destinoColumna >= 0 && destinoColumna < grillaModelo.getColumnas()) {
            // Mover el bloque en el modelo y actualizar la vista
            grillaModelo.moverBloque(new Coordenada(origenFila, origenColumna), new Coordenada(destinoFila, destinoColumna));
            actualizarVistaGrilla();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
