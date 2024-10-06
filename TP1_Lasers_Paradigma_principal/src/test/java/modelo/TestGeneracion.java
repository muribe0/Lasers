package modelo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class TestGeneracion {
    private static void llenarGrilla(Grilla grilla, String nivel) {
        Integer ancho = grilla.getAncho();
        Integer alto = grilla.getAlto();
        Integer dimension = grilla.getDimensionBloque();
        for (int i = 0; i < alto; i += 1) {
            for (int j = 0; j < ancho; j += 1) {
                var cord = new Coordenada(j * dimension, i * dimension);
                Bloque bloque = switch (nivel.charAt(i * ancho + j)) {
                    case '.' -> new BloqueVacio(dimension);
                    case 'F' -> new BloqueOpaco(dimension);
                    case 'R' -> new BloqueEspejo(dimension);
                    case 'B' -> new BloqueOpacoMovil(dimension);
                    case 'G' -> new BloqueVidrio(dimension);
                    case 'C' -> new BloqueCristal(dimension);
                    default -> new BloqueSinPiso(dimension);
                };

                grilla.colocarBloque(bloque, cord, true);
            }
        }
    }

    private static void llenarGrilla(Grilla grilla) {
        Integer ancho = grilla.getAncho();
        Integer alto = grilla.getAlto();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                var cord = new Coordenada(i * grilla.getDimensionBloque(), j * grilla.getDimensionBloque());
                grilla.colocarBloque(new BloqueVacio(2), cord, true); // esto se fuerza para probar la converion de coordenadas
            }
        }
    }


    @Test
    public void testGrillaVacia() {
        System.out.println("INICIO TEST GRILLA VACIA");
        var grilla = new Grilla(3, 3, 2);
        // nivel vacio

        assert Objects.equals(grilla.toString(), "...\n...\n...\n");
        System.out.println("FIN TEST GRILLA VACIA");
    }

    @Test
    public void testGrillaLlenaDeBloquesVacios() {
        var grilla = new Grilla(3, 5,2 );
        llenarGrilla(grilla);
        assert Objects.equals(grilla.toString(), "...\n...\n...\n...\n...\n");
    }

    @Test
    public void testGrillaLlena() {
        var grilla = new Grilla(3, 3, 2);
        String nivel = "..." + ".F." + "...";
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n.F.\n...\n");
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
    public void testLaserYTodosVacios() {
        var grilla = new Grilla(3, 3, 2);
        String nivel = "..." + "..." + "..."; // B esta en
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n...\n...\n");

        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (3, 4)(3, 4) -> (4, 5)(4, 5) -> (5, 6)");
    }

    @Test
    public void testLaserYOpaco() {
        var grilla = new Grilla(3, 3, 2);
        String nivel = "..." + ".F." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (1, 2)(1, 2) -> (2, 3)");
    }

    @Test
    public void testLaserYEspejo() {
        var grilla = new Grilla(3, 3, 2);
        String nivel = "..." + ".R." + "...";
        llenarGrilla(grilla, nivel);
        var emisores = new ArrayList<Emisor>();
        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisores.add(emisor);
        emisor.emitir(grilla);
        assert Objects.equals(grilla.toString(), "...\n.R.\n...\n");
        assert Objects.equals(emisor.toString(), "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (1, 4)(1, 4) -> (0, 5)");
        String OK = Objects.equals(emisor.toString(), "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (1, 4)(1, 4) -> (0, 5)") ? " OK " : " ERROR ";
        System.out.println("El laser parte de 0,1 y rebota en 2,3 lateralmente hasta llegar a 0,5" + OK);

    }

    @Test
    public void testLaserYCristal() {
        var grilla = new Grilla(3, 3, 2);
        String nivel = "..." + ".C." + "...";
        // ...
        // .C.
        // ...
        llenarGrilla(grilla, nivel);
        assert Objects.equals(grilla.toString(), "...\n.C.\n...\n");


        var emisor = new Emisor(new Coordenada(0, 1), new Direccion(1, 1));
        emisor.emitir(grilla);
        assert Objects.equals(emisor.toString(), "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(4, 3) -> (5, 4)(5, 4) -> (6, 5)");
    }

    @Test
    public void testLaserYVidrio() {
        String archivoNivel = "src/main/resources/levelVidrio.dat";
        var nivel = new Nivel(archivoNivel);
        var emisor = nivel.getEmisores().getFirst();
        var grilla = nivel.getGrilla();

        emisor.emitir(grilla);
        assert Objects.equals(grilla.toString(), " ...\n.G..\nF...\n....\n....\n");
        assert Objects.equals(emisor.toString(), "(5, 2) -> (4, 3)(4, 3) -> (3, 4)(3, 4) -> (2, 5)(4, 3) -> (5, 4)(5, 4) -> (6, 5)(6, 5) -> (7, 6)(7, 6) -> (8, 7)");
    }

    @Test
    public void testLaserYMultiplesVidrios() {
        String archivoNivel = "src/main/resources/levelMultiplesVidrios.dat";
        var nivel = new Nivel(archivoNivel);
        var emisor = nivel.getEmisores().getFirst();
        var grilla = nivel.getGrilla();

        emisor.emitir(grilla);
        assert Objects.equals(grilla.toString(), " ...\n.G..\nF..G\n..G.\n....\n");
        assert Objects.equals(emisor.toString(), "(5, 2) -> (4, 3)(4, 3) -> (3, 4)(3, 4) -> (2, 5)(4, 3) -> (5, 4)(5, 4) -> (6, 5)(6, 5) -> (7, 6)(7, 6) -> (8, 7)(6, 5) -> (5, 6)(5, 6) -> (4, 7)(4, 7) -> (3, 8)(3, 8) -> (2, 9)(2, 9) -> (1, 10)(5, 6) -> (4, 5)(4, 5) -> (3, 4)(3, 4) -> (2, 3)(2, 3) -> (1, 2)(1, 2) -> (0, 1)(3, 4) -> (2, 5)");
    }

    @Test
    public void testLaserNivel1() {
        String archivoNivel = "src/main/resources/level1.dat";
        var nivel = new Nivel(archivoNivel);
        var emisor = nivel.getEmisores().getFirst();
        var laser = emisor.getPunta();
        var objetivo = nivel.getObjetivos().getFirst();

        // Carga de nivel correcta
        assert Objects.equals(nivel.getGrilla().toString(), " .R.\n....\n.R.R\n..F.\n....\n.R..\n");
        assert Objects.equals(laser.getDireccion(), new Direccion(1, 1));
        assert Objects.equals(laser.getDestino(), new Coordenada(0, 1));
        assert Objects.equals(objetivo.toString(), "(8, 9) No alcanzado");

        // Emisor emite correctamente desde el inicio
        emisor.emitir(nivel.getGrilla());
        assert Objects.equals(emisor.toString(),
                "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (3, 4)(3, 4) -> (4, 3)(4, 3) -> (5, 2)(5, 2) -> (6, 3)(6, 3) -> (7, 4)(7, 4) -> (8, 3)");

        // mover un bloque espejo
        nivel.moverBloque(6, 4, 6, 2);
        assert Objects.equals(nivel.getGrilla().toString(), " .R.\n...R\n.R..\n..F.\n....\n.R..\n");

        // TODO: Hay que hacer que esto ocurra automaticamente luego de mover algun bloque
        emisor.emitir(nivel.getGrilla());
        assert Objects.equals(emisor.toString(),
                "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (3, 4)(3, 4) -> (4, 3)(4, 3) -> (5, 2)(5, 2) -> (6, 3)(6, 3) -> (5, 4)(5, 4) -> (4, 5)(4, 5) -> (5, 6)");
    }

    @Test
    public void testNivelCompleto() {
        String archivoNivel = "src/main/resources/levelTestObjetivo.dat";
        var nivel = new Nivel(archivoNivel);
        var emisor = nivel.getEmisores().getFirst();
        var laser = emisor.getPunta();
        var objetivo = nivel.getObjetivos().getFirst();

        // Carga de nivel correcta
        assert Objects.equals(nivel.getGrilla().toString(), " ...\n....\n..RR\nR.F.\n....\n..R.\n");
        assert Objects.equals(laser.getDireccion(), new Direccion(1, 1));
        assert Objects.equals(laser.getDestino(), new Coordenada(0, 1));
        assert Objects.equals(objetivo.toString(), "(8, 9) No alcanzado");

        // Emisor emite correctamente desde el inicio
        emisor.emitir(nivel.getGrilla());
        assert Objects.equals(emisor.toString(),
                "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (3, 4)(3, 4) -> (4, 5)(4, 5) -> (3, 6)(3, 6) -> (2, 7)(2, 7) -> (3, 8)(3, 8) -> (4, 9)(4, 9) -> (5, 10)(5, 10) -> (6, 9)(6, 9) -> (7, 8)(7, 8) -> (8, 7)");

        nivel.moverBloque(6, 4, 6, 6);
        assert Objects.equals(nivel.getGrilla().toString(), " ...\n....\n..R.\nR.FR\n....\n..R.\n");
        emisor.emitir(nivel.getGrilla());
        assert Objects.equals(emisor.toString(),
                "(0, 1) -> (1, 2)(1, 2) -> (2, 3)(2, 3) -> (3, 4)(3, 4) -> (4, 5)(4, 5) -> (3, 6)(3, 6) -> (2, 7)(2, 7) -> (3, 8)(3, 8) -> (4, 9)(4, 9) -> (5, 10)(5, 10) -> (6, 9)(6, 9) -> (7, 8)(7, 8) -> (8, 9)");
        assert !objetivo.esAlcanzado();
        nivel.actualizarObjetivos();
        assert objetivo.esAlcanzado();
    }


    @Test
    public void testVarios() {
        Integer a = 3;
        System.out.println(a / 3);
    }
}