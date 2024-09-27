package modelo;

import java.util.List;

public class Nivel {
    private Grilla grilla;
    private List<Emisor> emisores;
    private List<Objetivo> objetivos;

    public Nivel(Grilla grilla, List<Emisor> emisores, List<Objetivo> objetivos) {
        this.grilla = grilla;
        this.emisores = emisores;
        this.objetivos = objetivos;
    }

    public void cargarDesdeArchivo(String archivoNivel) {
        // Lógica para cargar la configuración del nivel desde un archivo
    }

    public void moverBloque(int x, int y, int nuevoX, int nuevoY) {
        // Lógica para mover el bloque en la grilla
        // grilla.moverBloque(x, y, nuevoX, nuevoY);
    }

    /*
    TESTING
     */
    public Grilla getGrilla() {
        return grilla;
    }

    public List<Emisor> getEmisores() {
        return emisores;
    }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    //public boolean validarSolucion() {
    // Validar si los objetivos fueron alcanzados
    //  for (Objetivo objetivo : objetivos) {
    //    if (!objetivo.esAlcanzado()) {
    //      return false;
    //  }
    //}
    //  return true;
    //}
}
