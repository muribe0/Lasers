package modelo;

public class Direccion extends Coordenada {
    public Direccion(int x, int y) {
        super(x, y);
    }

    public Direccion(Direccion direccion) {
        super(direccion.getX(), direccion.getY());
    }

    public Direccion(String direccion) {
        super(
                switch (direccion) {
                    case "NE", "SE" -> 1;
                    case "NW", "SW" -> -1;
                    default -> throw new IllegalArgumentException("Dirección inválida: " + direccion);
                },
                switch (direccion) {
                    case "NE", "NW" -> -1;
                    case "SE", "SW" -> 1;
                    default -> throw new IllegalArgumentException("Dirección inválida: " + direccion);
                }
        );
    }
}