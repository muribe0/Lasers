package vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.Coordenada;
import modelo.Nivel;
import modelo.Objetivo;

public class VistaNivel extends StackPane {
    private Nivel nivel;
    private Integer alto;
    private Integer ancho;


    private VistaGrilla panelGrilla;
    private VistaLasers panelLaser;

    private final Integer tamanioBloque;

    boolean bloqueSeleccionado;
    Integer origenX;
    Integer origenY;


    public VistaNivel(Nivel nivel, Integer tamanioBloque) {
        this.setAlignment(Pos.TOP_LEFT);

        this.nivel = nivel;
        this.alto = nivel.getGrilla().getAlto();
        this.ancho = nivel.getGrilla().getAncho();

        this.bloqueSeleccionado = false;
        this.tamanioBloque = tamanioBloque;

        this.panelGrilla = new VistaGrilla(nivel.getGrilla(), tamanioBloque);
        this.panelLaser = new VistaLasers(nivel, tamanioBloque);

        this.getChildren().addAll(panelGrilla, panelLaser);
        // panelLaser esta encima
        agregarEventosDeBloque();
        dibujarObjetivos();
    }

    private void agregarEventosDeBloque() {

        // Evento cuando se hace clic sobre un bloque o celda de la grilla
        this.setOnMouseClicked(event -> {
            if (nivel.estaCompletado()) {
                return;
            }
            if (!bloqueSeleccionado) {
                // Primer clic: Seleccionar el bloque
                origenX = (int) event.getX();
                origenY = (int) event.getY();
                bloqueSeleccionado = true;
            } else {
                // Segundo clic: Mover el bloque seleccionado a la nueva posición
                int destinoX = (int) event.getX();
                int destinoY = (int) event.getY();

                // Llamar a la lógica del juego para mover el bloque
                nivel.moverBloque(origenX / tamanioBloque, origenY / tamanioBloque, destinoX / tamanioBloque, destinoY / tamanioBloque);
                panelGrilla.actualizarVista();
                panelLaser.inicializarEmisores();

                // Reiniciar el estado de selección
                bloqueSeleccionado = false;

                nivel.validarSolucion();
            }
        });
    }

    // Método para dibujar los objetivos
    private void dibujarObjetivos() {
        for (Objetivo objetivo : nivel.getObjetivos()) {
            // Obtener la posición del objetivo en la grilla
            Coordenada posicion = objetivo.getPosicion();
            int columna = posicion.getX();
            int fila = posicion.getY();

            // Crear un rectángulo que represente el objetivo
            Rectangle rect = new Rectangle((double) tamanioBloque / 8, (double) tamanioBloque / 8);
            rect.setFill(Color.RED); // Color del objetivo

            rect.setTranslateX(columna * tamanioBloque);
            rect.setTranslateY(fila * tamanioBloque);

            // Posicionar el rectángulo en la grilla
            this.getChildren().add(rect); // Añade el objetivo en la posición de la grilla
        }
    }

}
