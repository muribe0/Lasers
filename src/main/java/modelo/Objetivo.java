package modelo;


public class Objetivo {
    private final Coordenada posicion;  // Posición del objetivo en la grilla
    private boolean alcanzado;    // Indica si el objetivo ha sido alcanzado por un láser

    // Constructor para inicializar el objetivo en una posición dada
    public Objetivo(Coordenada posicion) {
        this.posicion = posicion;
        this.alcanzado = false;
    }

    // Devuelve la posición del objetivo
    public Coordenada getPosicion() {
        return posicion;
    }

    // Verifica si el objetivo ha sido alcanzado
    public boolean esAlcanzado() {
        return alcanzado;
    }

    // Marca el objetivo como alcanzado
    public void marcarComoAlcanzado() {
        this.alcanzado = true;
    }


    @Override
    public String toString() {
        return posicion.toString() + " " + (alcanzado ? "Alcanzado" : "No alcanzado");
    }
}
