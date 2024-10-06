package vista;

import javafx.scene.layout.GridPane;
import modelo.Grilla;

public class GrillaVista extends GridPane {
    private Grilla grillaModelo;

    public GrillaVista(Grilla grilla) {
        this.grillaModelo = grilla;
        // hay q inicializar visualmente los bloques desde el modelo
    }

    // Métodos para actualizar la vista de la grilla
}
