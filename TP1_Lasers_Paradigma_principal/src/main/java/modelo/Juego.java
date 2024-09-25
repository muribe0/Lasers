package modelo;

import java.util.List;

public class Juego {
    private List<Nivel> niveles;
    private Nivel nivelActual;

    public void iniciarJuego() {
        // Lógica para cargar y mostrar los niveles disponibles
    }

    public void cargarNivel(int numeroNivel) {
        // Cargar un nivel específico
        nivelActual = niveles.get(numeroNivel);
    }

    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        // Lógica para mover un bloque de una posición a otra
        nivelActual.moverBloque(x, y, nuevoX, nuevoY);
    }

    public boolean verificarObjetivo() {
        //logica cuando laser toque el objetivo final true
        return true;
        //logica cuando laser no toque el objetivo final que siga avanzando el laser
    }
}
