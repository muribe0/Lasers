package modelo;
import java.util.List;
import java.util.ArrayList;

public class Juego {
    private List<Nivel> niveles;
    private Nivel nivelActual;
    private boolean nivelCompletado;

    public Juego() {
        niveles = new ArrayList<>();
        this.nivelCompletado = false;
    }


    // Verificar si el nivel se ha ganado y congelar el estado del nivel.
    public void verificarNivelCompletado() {
        if (nivelActual.validarSolucion()) {
            this.nivelCompletado = true;
        }
    }

    public void iniciarJuego() {
        // Cargar los 6 niveles
        for (int i = 1; i <= 6; i++) {
            cargarNivelDesdeArchivo("resources/nivel" + i + ".dat");
        }
        // Iniciar el primer nivel
        if (!niveles.isEmpty()) {
            nivelActual = niveles.get(0);
        }
    }

    private void cargarNivelDesdeArchivo(String archivoNivel) {

        Nivel nivel = new Nivel(archivoNivel);
        nivel.cargarDesdeArchivo(archivoNivel);

        niveles.add(nivel);
    }

    public void cargarNivel(int numeroNivel) {
        if (numeroNivel >= 0 && numeroNivel < niveles.size()) {
            nivelActual = niveles.get(numeroNivel);
        }
    }

    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        if (nivelCompletado) {
            //mensaje o alerta sobre que no puede mover mas bloques
            System.out.println("El nivel está completado. No puedes mover más bloques.");
        }else {
            nivelActual.moverBloque(x, y, nuevoX, nuevoY);
        }
    }


    public boolean verificarObjetivo() {
        for (Objetivo objetivo : nivelActual.getObjetivos()) {
            if (!objetivo.esAlcanzado()) {
                return false;
            }
        }
        return true;
    }

    // Verificar si todos los niveles han sido completados.
    public boolean esJuegoGanado() {
        for (Nivel nivel : niveles) {
            if (!nivel.validarSolucion()) {
                return false;
            }
        }
        return true;
    }
}
