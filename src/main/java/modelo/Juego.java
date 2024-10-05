package modelo;
import java.util.List;
import java.util.ArrayList;

public class Juego {
    private List<Nivel> niveles;
    private Nivel nivelActual;

    public Juego() {
        niveles = new ArrayList<>();
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
        nivelActual.moverBloque(x, y, nuevoX, nuevoY);
    }

    public boolean verificarObjetivo() {
        for (Objetivo objetivo : nivelActual.getObjetivos()) {
            if (!objetivo.esAlcanzado()) {
                return false;
            }
        }
        return true;
    }

}
