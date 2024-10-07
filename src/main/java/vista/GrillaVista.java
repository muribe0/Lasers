package vista;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.Bloque;
import modelo.Grilla;
import modelo.Coordenada;

public class GrillaVista extends GridPane {
    private Grilla grillaModelo;

    public GrillaVista(Grilla grilla) {
        this.grillaModelo = grilla;
        inicializarBloques();
    }

    // Inicializa la vista visual de los bloques basados en el modelo.
    // REVISAR
    private void inicializarBloques() {
        int dimensionBloque = grillaModelo.getDimensionBloque();

        for (int x = 0; x < grillaModelo.getAncho(); x++) {
            for (int y = 0; y < grillaModelo.getAlto(); y++) {
                Coordenada coordenada = new Coordenada(x, y);
                Bloque bloque = grillaModelo.obtenerBloque(coordenada);
                Rectangle bloqueVisual = crearRectanguloParaBloque(bloque, dimensionBloque);
                this.add(bloqueVisual, x, y);
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
            rect.setFill(Color.SILVER);
        } else if (bloque instanceof modelo.BloqueVidrio) {
            rect.setFill(Color.LIGHTBLUE);
        } else if (bloque instanceof modelo.BloqueCristal) {
            rect.setFill(Color.BLUE);
        } else if (bloque instanceof modelo.BloqueVacio) {
            rect.setFill(Color.BEIGE);
        } else if (bloque instanceof modelo.BloqueSinPiso) {
            rect.setFill(Color.BLACK);
        }
        return rect;
    }

    // Método para actualizar la vista de la grilla cuando se modifique el modelo.
    public void actualizarVista() {
        this.getChildren().clear();
        inicializarBloques();
    }

    // Método para mover un bloque visualmente.
    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        grillaModelo.moverBloque(new Coordenada(x, y), new Coordenada(nuevoX, nuevoY));
        actualizarVista();
    }
}
