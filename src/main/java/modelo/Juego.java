package modelo;

public class Juego {
    private Nivel nivelActual;

    public void iniciarNivel(String ruta) {
        nivelActual = new Nivel(ruta);
    }

    public Nivel getNivelActual() {
        return nivelActual;
    }

}
