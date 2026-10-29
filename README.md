# Despertar del Heroe

El héroe comenzará con 100 puntos de vida y tres pociones de 25 puntos de curación. Su ataque causará entre 10 
y 20 puntos de daño, ambos incluidos. Los enemigos tendrán 40, 60 y 80 puntos de vida y causarán entre 5 y 15 
puntos de daño. La vida nunca será negativa ni superará 100 tras curarse. Estos valores se definirán mediante 
constantes. 

### Atributos de Personajes
    nombre; (nombre del personaje)
    nivel; (nivel de personaje)
    vida; (vida actual)
    vidaMax; (vida maxima del personaje)
    poder; (ataque base)
    defensa; (reducción de daño)
    suerte; (será la probabilidad de un ataque critico (poder * 2))
    velocidad; (determinará a quien le toca el primer turno )

### Inventario con 3 espacios
[0]pocion de +25HP
[1]pocion de +25HP
[2]pocion de +25HP

### Ataque con las siguientes habilidades disponibles [2]
[0]salto (salto basico que RESTA 10HP y tiene una probabilidad en base al stat suerte de pegar critico (MULTIPLICACION POR 2))
[1]martillo (sera un golpe con un prompt de escoger UN numero entre 1-3 y si escoges el correcto RESTA -20HP sino 0)

## Requisitos
Java 21

## Como ejecutarlo
