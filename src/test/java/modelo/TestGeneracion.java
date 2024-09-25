package modelo;

import java.util.ArrayList;

public class TestGeneracion {
    public static void main(String[] args) {
        var emisores = new ArrayList<Emisor>();
        var grilla = new Grilla(3, 3);
        var nivel = new Nivel(grilla , emisores, new ArrayList<>());
        // nivel vacio

        grilla.agregarBloque(new BloqueVacio(), new Coordenada(0, 0));

        // emisor con un laser en 0,0 y direccion horizontal 1,1
        var emisor = new Emisor(new Laser(new Coordenada(0, 0), new Coordenada(1, 1)));
        emisores.add(emisor);

        System.out.println("Grilla:\n" + grilla.toString());

        emisor.emitir(grilla);
        // Terminar test: Falta que el rayo modifique la grilla
        System.out.println("Lasers:\n" + emisor.toString());
    }
}




