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
                    case 'F' -> new BloqueOpaco();
                    case 'R' -> new BloqueEspejo();
                    case 'B' -> new BloqueOpacoMovil();
                    case 'G' -> new BloqueVidrio();
                    case 'C' -> new BloqueCristal();
                    default -> new BloqueVacio();
                };
                var cord = new Coordenada(i * dimension, j * dimension);
                grilla.colocarBloque(bloque, cord, true);
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
                                j * grilla.getDimensionBloque()), true); // esto se fuerza para probar la converion de coordenadas
            }
        }
    }

    private static Nivel crearNivel() {
        var emisores = new ArrayList<Emisor>();
        var grilla = new Grilla(3, 3);
        llenarGrilla(grilla);

        emisores.add(new Emisor(new Coordenada(0, 1), new Direccion(1, 1)));

        return new Nivel(grilla, emisores, new ArrayList<>());
    }

    @Test
    public void testGrillaVacia() {
        System.out.println("INICIO TEST GRILLA VACIA");
        var emisores = new ArrayList<Emisor>();
        var grilla = new Grilla(3, 3);
        var objetivos = new ArrayList<Objetivo>();
        var nivel = new Nivel(grilla, emisores, objetivos);
        // nivel vacio

        assert Objects.equals(grilla.toString(), "...\n...\n...\n");
        System.out.println("FIN TEST GRILLA VACIA");
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
        String nivel = "..." + ".F." + "...";
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n.F.\n...\n");
    }

    @Test
    public void testLaserYTodosVacios() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + "..." + "..."; // B esta en
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n...\n...\n");

        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (4, 5)"); // TODO ESTO DEBE TERMINAR EN (5,6)
    }

    @Test
    public void testLaserYOpaco() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".F." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)");
    }

    @Test
    public void testLaserYEspejo() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".R." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(grilla.toString(), "...\n.R.\n...\n");
        assert Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)(2, 3) -> (0, 5)");
        String OK = Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)(2, 3) -> (0, 5)") ? " OK " : " ERROR ";
        System.out.println("El laser parte de 0,1 y rebota en 2,3 lateralmente hasta llegar a 0,5" + OK);

    }

    @Test
    public void testLaserYCristal() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".C." + "..."; // B esta en

        // ...
        // .B.
        // ...
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n.C.\n...\n");


        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (2, 3)(4, 3) -> (5, 4)");
    }

    @Test
    public void testCreacionNivelVacio() {
        String archivoNivel = "src/main/resources/level0.dat";
        var nivel = new Nivel(archivoNivel);
        // ....
        // .F..
        // .R.R
        assert Objects.equals(nivel.getGrilla().toString(), "....\n.F..\n.R.R\n");
        // TODO: falta testear aca el emisor y objetivo
        assert nivel.getEmisores().size() == 1;
        assert nivel.getObjetivos().size() == 1;
        assert Objects.equals(nivel.getObjetivos().getFirst().toString(), "(8, 9) No alcanzado");
        assert Objects.equals(nivel.getEmisores().getFirst().toString(), "(0, 1) -> (0, 1)");
    }

    @Test
    public void testMoverBloqueOpacoMovil() {
        var grilla = new Grilla(3, 3);
        String nivel = "..." + ".B." + "..."; // B esta en
        // ...
        // .B.
        // ...
        llenarGrilla(grilla, nivel);
        grilla.moverBloque(new Coordenada(3, 3), new Coordenada(5, 3));
        assert Objects.equals(grilla.toString(), "...\n..B\n...\n");
    }



    @Test
    public void testVarios() {
        Integer a = 3;
        System.out.println(a / 3);
    }
}



