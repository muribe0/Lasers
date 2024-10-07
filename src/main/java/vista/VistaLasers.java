//package vista;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import modelo.Nivel;
//import modelo.Coordenada;
//
//public class VistaLasers extends Application {
//    private Nivel nivel; // Instancia del nivel
//    private GridPane grillaVisual; // Grilla para mostrar los bloques
//
//    public VistaLasers(Nivel nivel) {
//        this.nivel = nivel;
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Lasers Game");
//
//        grillaVisual = new GridPane();
//        cargarGrilla();
//
//        VBox root = new VBox();
//        root.getChildren().add(grillaVisual);
//
//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void cargarGrilla() {
//        int filas = nivel.getGrilla().getFilas();
//        int columnas = nivel.getGrilla().getColumnas();
//
//        for (int fila = 0; fila < filas; fila++) {
//            for (int columna = 0; columna < columnas; columna++) {
//                Coordenada posicion = new Coordenada(columna, fila);
//                var bloque = nivel.getGrilla().getBloque(posicion); // Obtener bloque de la grilla
//                Button botonBloque = crearBotonBloque(bloque, posicion);
//                grillaVisual.add(botonBloque, columna, fila);
//            }
//        }
//    }
//
//    private Button crearBotonBloque(var bloque, Coordenada posicion) {
//        Button boton = new Button(bloque.toString()); // hay q cambiar por el símbolo del bloque
//        boton.setPrefSize(50, 50);
//
//        // Event handler para mover bloques
//        boton.setOnAction(e -> {
//            // Lógica para mover el bloque si es un bloque móvil
//            if (bloque instanceof esMovible) {
//                moverBloque(posicion.getX(), posicion.getY(), posicion.getX() + 1, posicion.getY());
//            }
//        });
//        return boton;
//    }
//
//    private void moverBloque(Integer x, Integer y, Integer nuevoX, Integer nuevoY) {
//        nivel.moverBloque(x, y, nuevoX, nuevoY);
//        // Luego actualizar la vista
//        grillaVisual.getChildren().clear();
//        cargarGrilla();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
