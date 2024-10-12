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

    /**
     * Inicializa los bloques y los coloca en en GridPane.
     */
    public void inicializarBloques() {
        Integer dimensionBloque = grilla.getDimensionBloque();
        for (int x = 0; x < grilla.getAncho(); x++) {
            for (int y = 0; y < grilla.getAlto(); y++) {
                Coordenada coordenada = new Coordenada(x * dimensionBloque, y * dimensionBloque);
                Bloque bloque = grilla.getBloque(coordenada);
                Rectangle bloqueVisual = crearRectanguloParaBloque(bloque, dimensionBloque * tamanioBloque);
                this.add(bloqueVisual, x, y);
            }
        }
    }

    /**
     * Inicializa un rectangulo de acuerdo al tipo de bloque con listeners para los bloques seleccionables.
     *
     * @param bloque:          bloque a colocar en la grilla
     * @param dimensionBloque: dimension del bloque en relacion a la grilla
     * @return
     */
    private Rectangle crearRectanguloParaBloque(Bloque bloque, int dimensionBloque) {
        Rectangle rect = new Rectangle(dimensionBloque, dimensionBloque);
        if (bloque == null) {
            rect.setFill(Color.TRANSPARENT);
        } else if (bloque instanceof modelo.BloqueOpaco) {
            rect.setFill(Color.GRAY);
        } else if (bloque instanceof modelo.BloqueOpacoMovil) {
            rect.setFill(Color.DARKGRAY);
            rect.setOnMouseClicked(e -> {
                rect.setFill(Color.DARKSLATEGRAY);
            });
        } else if (bloque instanceof modelo.BloqueEspejo) {
            rect.setFill(Color.MEDIUMPURPLE);
            rect.setOnMouseClicked(e -> {
                rect.setFill(Color.PURPLE);
            });
        } else if (bloque instanceof modelo.BloqueVidrio) {
            rect.setFill(Color.LIGHTBLUE);
            rect.setOnMouseClicked(e -> {
                rect.setFill(Color.BLUEVIOLET);
            });
        } else if (bloque instanceof modelo.BloqueCristal) {
            rect.setFill(Color.BLUE);
            rect.setOnMouseClicked(e -> {
                rect.setFill(Color.DARKBLUE);
            });
        } else if (bloque instanceof modelo.BloqueVacio) {
            rect.setFill(Color.BEIGE);
        } else if (bloque instanceof modelo.BloqueSinPiso) {
            rect.setFill(Color.BLACK);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Actualiza la vista para reflejar los cambios del nivel. Para ello, limpia la vista y vuelve a inicializar los bloques.
     */
    public void actualizarVista() {
        this.getChildren().clear();
        inicializarBloques();
    }
}
