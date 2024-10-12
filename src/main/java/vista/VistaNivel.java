package vista;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import modelo.Nivel;

public class VistaNivel extends StackPane {
    private Nivel nivel;

    private VistaGrilla panelGrilla;
    private VistaLasers panelLaser;
    private VistaObjetivos panelObjetivos;

    private final Integer tamanioBloque;

    boolean bloqueSeleccionado;
    Integer origenX;
    Integer origenY;


    /**
     * Crea una instancia de la Vista de un Nivel alineado en la esquina superior izquierda respecto a su contenedor.
     * Inicializa un GridPanel y StackPane(s) para representar la grilla, los lasers y objetivos del nivel.
     * Agrega un listener para mover bloques y actualizar el estado del juego luego de moverlos.
     * @param nivel: Nivel de la vista
     * @param tamanioBloque: Tamanio de cada bloque del Nivel en pixeles.
     */
    public VistaNivel(Nivel nivel, Integer tamanioBloque) {
        this.setAlignment(Pos.TOP_LEFT);

        this.nivel = nivel;

        this.bloqueSeleccionado = false;
        this.tamanioBloque = tamanioBloque;

        this.panelGrilla = new VistaGrilla(nivel.getGrilla(), tamanioBloque);
        this.panelLaser = new VistaLasers(nivel.getEmisores(), tamanioBloque);
        this.panelObjetivos = new VistaObjetivos(nivel.getObjetivos(), tamanioBloque);
        panelLaser.setMouseTransparent(true);
        panelObjetivos.setMouseTransparent(true);

        this.getChildren().addAll(panelGrilla, panelLaser, panelObjetivos);
        // panelLaser esta encima
        this.setOnMouseClicked(event -> new MoverBloque().handle(event));

    }

    /**
     * Listener que se encarga de mover el bloque y actualizar el nivel, estado de juego y vistas luego de moverlo.
     *
     */
    public class MoverBloque implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent evento) {
            if (nivel.estaCompletado()) {
                return;
            }
            if (!bloqueSeleccionado) {
                // Primer clic: Seleccionar el bloque
                origenX = (int) evento.getX();
                origenY = (int) evento.getY();
                if (nivel.bloqueEsMovible(origenX / tamanioBloque, origenY / tamanioBloque)) {
                    bloqueSeleccionado = true;
                }
            } else {
                // Segundo clic: Mover el bloque seleccionado a la nueva posición
                int destinoX = (int) evento.getX();
                int destinoY = (int) evento.getY();

                // Llamar a la lógica del juego para mover el bloque
                nivel.moverBloque(origenX / tamanioBloque, origenY / tamanioBloque,
                        destinoX / tamanioBloque, destinoY / tamanioBloque);
                panelGrilla.actualizarVista();
                panelLaser.inicializarEmisores();

                // Reiniciar el estado de selección
                bloqueSeleccionado = false;

                nivel.actualizarObjetivos();
                nivel.validarSolucion();
            }
        }

    }
}

