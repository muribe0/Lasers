package modelo;

public class Grilla {
    private Bloque[][] grilla;

    public Grilla(Integer alto, Integer ancho) {
        this.grilla = new Bloque[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                this.grilla[i][j] = new BloqueVacio();
            }
        }
    }

    public void agregarBloque(Bloque bloque, Coordenada coordenada) {
        this.grilla[coordenada.getY()][coordenada.getX()] = bloque;
    }

    public Bloque getBloque(Coordenada coordenada) {
        return this.grilla[coordenada.getY()][coordenada.getX()];
    }

    public boolean estaDentro(Coordenada coordenada) {
        return coordenada.getX() >= 0 && coordenada.getX() < this.grilla[0].length &&
               coordenada.getY() >= 0 && coordenada.getY() < this.grilla.length;
    }

    public String toString() {
        String grillaString = "";
        for (int i = 0; i < this.grilla.length; i++) {
            for (int j = 0; j < this.grilla[i].length; j++) {
                grillaString += this.grilla[i][j].toString();
            }
            grillaString += "\n";
        }
        return grillaString;
    }
}
