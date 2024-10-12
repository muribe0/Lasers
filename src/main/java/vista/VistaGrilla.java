package vista;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.Bloque;
import modelo.Grilla;
import modelo.Coordenada;

public class VistaGrilla extends GridPane {
    private Grilla grilla;

    private Integer tamanioBloque;


    public VistaGrilla(Grilla grilla, Integer tamanioBloque) {
        this.tamanioBloque = tamanioBloque;
        this.grilla = grilla;
        inicializarBloques();
    }

    // Inicializa la vista visual de los bloques basados en el modelo.
    public void inicializarBloques() {
        Integer dimensionBloque = grilla.getDimensionBloque();
        for (int x = 0; x < grilla.getAncho(); x++) {
            for (int y = 0; y < grilla.getAlto(); y++) {
                Coordenada coordenada = new Coordenada(x * dimensionBloque, y * dimensionBloque);
                Bloque bloque = grilla.getBloque(coordenada);
                Rectangle bloqueVisual = crearRectanguloParaBloque(bloque, dimensionBloque * tamanioBloque);
                this.add(bloqueVisual, x , y );
            }
        }
    }

    // Método auxiliar para crear un rectángulo basado en el tipo de bloque.
    private Rectangle crearRectanguloParaBloque(Bloque bloque, int dimensionBloque) {
        Rectangle rect = new Rectangle(dimensionBloque, dimensionBloque);
        if (bloque == null) {
            rect.setFill(Color.TRANSPARENT);
        } else if (bloque instanceof modelo.BloqueOpaco) {
            rect.setFill(Color.GRAY);
        } else if (bloque instanceof modelo.BloqueOpacoMovil) {
            rect.setFill(Color.DARKGRAY);
        } else if (bloque instanceof modelo.BloqueEspejo) {
            rect.setFill(Color.PURPLE);
        } else if (bloque instanceof modelo.BloqueVidrio) {
            rect.setFill(Color.LIGHTBLUE);
        } else if (bloque instanceof modelo.BloqueCristal) {
            rect.setFill(Color.BLUE);
        } else if (bloque instanceof modelo.BloqueVacio) {
            rect.setFill(Color.BEIGE);
        } else if (bloque instanceof modelo.BloqueSinPiso) {
            rect.setFill(Color.BLACK);
        }
        rect.setStroke(Color.DARKORANGE);
        return rect;
    }

    // Método para actualizar la vista de la grilla cuando se modifique el modelo.
    public void actualizarVista() {
        this.getChildren().clear();
        inicializarBloques();
    }
}
