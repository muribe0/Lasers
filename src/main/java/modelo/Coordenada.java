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

    public void contraer(Integer dimension) {
        this.x /= dimension;
        this.y /= dimension;
    }

    public void redimensionar(Integer dimension) {
        this.x *= dimension;
        this.y *= dimension;
    }

    public void sumarHorizontalmente(Coordenada direccion) {
        this.x += direccion.getX();
    }

    public void sumarVerticalmente(Coordenada direccion) {
        this.y += direccion.getY();
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

    /**
    *  verifica si el laser ha llegado a una coordenada objetivo, puedes usar equals.
       Podría ser útil para determinar si un bloque específico se encuentra en la misma
       posición que otro elemento (por ejemplo, un emisor o un objetivo).
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenada that = (Coordenada) obj;
        return x.equals(that.x) && y.equals(that.y);
    }


    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
