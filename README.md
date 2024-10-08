# TP1 Lasers Paradigma
Trabajo practico 1 de la materia Paradigmas de la programación (TB025)

# INTEGRANTES
 - Alan Richmond, padron: 106783
 - Mauricio Uribe, padron: 105971

# Diagrama bloques
```mermaid
classDiagram
    class Bloque {
        <<abstract>>    
        interactuarConLaser(Laser): void
	}
	
	class BloqueCristal {
	    
	}
	class BloqueEspejo {
	    
	}
    class BloqueOpaco {

    }
    class BloqueOpacoMovil {

    }
    class BloqueSinPiso {

    }
    class BloqueVacio {

    }
    class BloqueVidrio {

    }
    Bloque <|.. BloqueCristal
    Bloque <|.. BloqueEspejo
    Bloque <|.. BloqueOpaco
    Bloque <|.. BloqueOpacoMovil
    Bloque <|.. BloqueSinPiso
    Bloque <|.. BloqueVacio
    Bloque <|.. BloqueVidrio
```
# Diagrama general
```mermaid
classDiagram
    class Emisor {
        
	} 
	class Grilla {
	    
	}
	class Juego {
	    
	}
	class Laser {
	    
	}
	class Nivel {
	    
	}
	class Objetivo {
	    
	}
	class Bloque {
	    <<abstract>>
	}
	Juego "1"*--"*" Nivel
	Nivel "1"*--"1" Grilla
    Nivel "1"*--"*" Emisor
    Nivel "1"*--"*" Objetivo
    Emisor "1"*--"*" Laser
    Grilla "1"*--"*" Bloque
```