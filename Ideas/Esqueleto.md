Deben estar bien separadas las clases relacionadas con la lógica y la interfaz gráfica, en 2 capas de abstracción.

Se debe utilizar polimorfismo para implementar los comportamientos de los disferentes bloques.

Agregar un diagrama de clases y/o secuencia en formato png o pdf. El diagrama debe incluir las clases principales de la
capa lógica.

Opcionalmente, acompañar el diagrama con un informe explicando y justificando las decisiones de diseño tomadas.

```mermaid
classDiagram
    class Bloque {
        +x
        +y
        -mover(x,y)
        -accion(direccion)
	}
	class OpacoFijo {
	    
	}
	class OpacoMovil {
	    -mover()
	}
	class Vidrio {
	    
	}
	class Espejo {
	    
	} 
	class Cristal {
	    
	}
	Cristal --> Bloque
	OpacoFijo --> Bloque
	OpacoMovil --> Bloque
	Vidrio --> Bloque
	Espejo --> Bloque
	
	class Coordenada {
	    
	}
	
	class Laser {
	    +posicion: Coordenada
	    +direccion: Coordenada
	    -choca()
	    -rebote()
	}
	
	class Lasers {
	    List<Laser>
	}
	
	class Objetivo {
        +posicion: Coordenada
	}
```

```
laser.xInicio
laser.yInicio
laser.xFin
laser.yFin
laser.dir #(dirx, diry)

def mover():
	laser.xFin = laser.xInicio
	laser.yFin = laser.yInicio
for _ in range(3):
	laser.xFin += 1
	laser.yFin += 1
	# Cada vez que se mueva un bloque, cada laser debe volver a fijarse sumando progresivamente
	# si se choca o no
```

![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img.png](img.png)
haciendo clicks

mover()
Puede mover solo si hace el click bien, sino no hace nada.

![img_3.png](img_3.png)
![img_4.png](img_4.png)