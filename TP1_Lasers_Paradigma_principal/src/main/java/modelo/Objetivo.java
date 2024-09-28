public class Objetivo {
    private Coordenada posicion;  // Posición del objetivo en la grilla
    private boolean alcanzado;    // Indica si el objetivo ha sido alcanzado por un láser

    // Constructor para inicializar el objetivo en una posición dada
    public Objetivo(int x, int y) {
        this.posicion = new Coordenada(x, y);
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

    // Método para resetear el estado del objetivo a no alcanzado
    public void resetear() {
        this.alcanzado = false;
    }

    @Override
    public String toString() {
        return "Objetivo{" +
                "posicion=" + posicion +
                ", alcanzado=" + alcanzado +
                '}';
    }
}
