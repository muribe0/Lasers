package modelo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class TestGeneracion {
    private static void llenarGrilla(Grilla grilla, String nivel) {
        Integer ancho = grilla.getAncho();
        Integer alto = grilla.getAlto();
        Integer dimension = grilla.getDimensionBloque();
        for (int i = 0; i < ancho; i += 1) {
            for (int j = 0; j < alto; j += 1) {
                Bloque bloque = switch (nivel.charAt(i * ancho + j)) {
                    case '.' -> new BloqueVacio();
                    case '#' -> new BloqueOpaco();
                    case 'R' -> new BloqueEspejo();
                    default -> new Bloque();
                };
                var cord = new Coordenada(i * dimension, j * dimension);
                grilla.colocarBloque(bloque, cord);
            }
        }
    }

    private static void llenarGrilla(Grilla grilla) {
        Integer ancho = grilla.getAncho();
        Integer alto = grilla.getAlto();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                grilla.colocarBloque(new BloqueVacio(),
                        new Coordenada(i * grilla.getDimensionBloque(),
                                j * grilla.getDimensionBloque()));
            }
        }
    }

    private static Nivel crearNivel() {
        var emisores = new ArrayList<Emisor>();
        var grilla = new Grilla(3, 3);
        llenarGrilla(grilla);

        emisores.add(new Emisor(new Laser(new Coordenada(0, 1), new Coordenada(1, 1))));

        return new Nivel(grilla, emisores, new ArrayList<>());
    }

    @Test
    public void testGrillaVacia() {
        System.out.println("INICIO TEST GRILLA VACIA");
        var emisores = new ArrayList<Emisor>();
        var grilla = new Grilla(3, 3);
        var nivel = new Nivel(grilla, emisores, new ArrayList<>());
        // nivel vacio

        assert Objects.equals(grilla.toString(), "   \n   \n   \n");
    }

    @Test
    public void testGrillaLlenaDeBloquesVacios() {
        var grilla = new Grilla(3, 5);
        llenarGrilla(grilla);
        assert Objects.equals(grilla.toString(), "...\n...\n...\n...\n...\n");
    }

    @Test
    public void testGrillaLlena() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".#." + "...";
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n.#.\n...\n");
    }

    @Test
    public void testNivelConLaserYOpaco() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".#." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Laser(new Coordenada(0, 1), new Coordenada(1, 1)));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)");
    }

    @Test
    public void testNivelConLaserYEspejo() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".R." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Laser(new Coordenada(0, 1), new Coordenada(1, 1)));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(grilla.toString(), "...\n.R.\n...\n");
        System.out.println("El laser parte de 0,1 y rebota en 2,3 lateralmente hasta llegar a 0,5");
        assert Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)(2, 3) -> (0, 5)");

    }

    @Test
    public void testVarios() {
        Integer a = 3;
        System.out.println(a / 3);
    }
}



