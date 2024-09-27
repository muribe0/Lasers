package modelo;

public class Coordenada {
    private Integer x;
    private Integer y;

    public Coordenada(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada(Coordenada coordenada) {
        this.x = coordenada.getX();
        this.y = coordenada.getY();
    }

    public void sumar(Coordenada direccion) {
        this.x += direccion.getX();
        this.y += direccion.getY();
    }

    public void restar(Coordenada direccion) {
        this.x -= direccion.getX();
        this.y -= direccion.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
