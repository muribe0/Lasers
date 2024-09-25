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

    public void mover(Coordenada direccion) {
        this.x += direccion.getX();
        this.y += direccion.getY();
    }

    public Coordenada getMitad() {
        return new Coordenada(this.x / 2, this.y / 2);
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
