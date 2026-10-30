import java.util.Scanner;

public class Main {
    static Personaje mario = new Personaje("Mario", 1, 100, 10, 10, 10, 10);
    static Personaje luigi = new Personaje("Luigi", 1, 100, 10, 10, 70, 20);
    static Personaje[] listaPersonajes = {mario, luigi};
    static Personaje jugador;
    static Personaje oponente = new Personaje("Bowser", 10, 100, 10, 20, 20, 15);
    static Personaje personajeTurno;
    static int turno = 1;
    static int puntuacion;

    public static void main (String[] args) {
        menuPrincipal();
    }

    public static int seleccionar() {
        Scanner menuSc = new Scanner(System.in);
        int seleccion = menuSc.nextInt();

        return seleccion;
    }

    public static void menuPrincipal() {
        System.out.println("\n" + "[[EL DESPERTAR DEL HEROE]]" + "\n");
        System.out.println("[1] Nueva Partida");
        System.out.println("[2] Consultar Ranking");
        System.out.println("[3] Ayuda y Reglas");
        System.out.println("[0] Salir");

        int selectMenu = seleccionar();

        switch (selectMenu) {
            case 1:
                menuJuego();
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 0:
                
                break;
        
            default:
                System.out.println("No has escogido una opcion valida");
                break;
        }
    }

    public static void menuJuego() {
        System.out.println("\n" + "[[SELECCIONA PERSONAJE]]");

        for (int i = 0; i < listaPersonajes.length; i++) {
            System.out.println("\n" + "[" + (i + 1) + "] " + listaPersonajes[i].getNombre());
            listaPersonajes[i].getStats();
        }

        System.out.println("[0] Volver");

        int selectMenu = seleccionar();

        if (selectMenu == 0) {
        menuPrincipal();
        } 
            // 2. Validación dinámica: ¿La opción está entre 1 y la cantidad total de personajes?
            else if (selectMenu >= 1 && selectMenu <= listaPersonajes.length) {
        
                // Obtenemos el personaje seleccionado (opcion - 1 nos da el índice exacto en el array)
                jugador = listaPersonajes[selectMenu - 1];
        
                System.out.println("Has seleccionado a " + jugador.getNombre());
                empezarCombate();
            } 
            // 3. Opción fuera de rango
            else {
                System.out.println("No has escogido una opción válida.");
                }
    }

    public static void empezarCombate(){
        Boolean endGame = false;
        System.out.println("\n" + jugador.getNombre() + " VS " + oponente.getNombre());

        Boolean esTurnoJugador = decidirTurno();

        while (!endGame) {
            if (esTurnoJugador) {
                personajeTurno = jugador;
                mostrarEstado();
                turnoJugador();
                
                if (oponente.getVida() <= 0) { //CONDICION DE VICTORIA SI OPONENTE VIDA ES IGUAL O MENOR A 0
                    System.out.println("¡" + oponente.getNombre() + " ha sido derrotado!");
                    endGame = true;
                    victoria();
                }   
            }
                else if (!esTurnoJugador){
                    personajeTurno = oponente;
                    turnoOponente();
                    
                    // Comprobar si el jugador ha sido derrotado
                    if (jugador.getVida() <= 0) {
                        System.out.println("¡" + jugador.getNombre() + " ha sido derrotado!");
                        System.out.println("\n" + "¡Has perdido!");
                        endGame = true;
                    }
                }
            // 3. Alternar turno para la siguiente ronda (si es true pasa a false, y viceversa)
            esTurnoJugador = !esTurnoJugador;
            turno++;
        }
    }

    public static boolean decidirTurno(){
        //LOGICA DECISION DE PRIMER TURNO
        if (jugador.getVelocidad() > oponente.getVelocidad()) {
            return true;
        }

        else  if (jugador.getVelocidad() < oponente.getVelocidad()){
            return false;
        }

        else { //TURNO ALEATORIO SI TIENEN LA MISMA VELOCIDAD
            int turnoAleatorio = (int) (Math.random() * 2) + 1;
            if (turnoAleatorio == 1) {
                System.out.println("\n" + "Empieza " + jugador.getNombre());
                return true;
            } 
            else {
                System.out.println("\n" + "Empieza " + oponente.getNombre());
                return false;
            }
        }
    }

    public static void mostrarEstado() {
        System.out.println("=== " + jugador.getNombre().toUpperCase() + " ===");
        System.out.println("Nivel: " + jugador.getNivel());
        System.out.println("Vida: " + jugador.getVida() + "/" + jugador.getVidaMax());
        System.out.println("=== " + oponente.getNombre().toUpperCase() + " ===");
        System.out.println("Nivel: " + oponente.getNivel());
        System.out.println("Vida: " + oponente.getVida() + "/" + oponente.getVidaMax());
    }

    public static void turnoJugador(){
        System.out.println("[TURNO " + turno + ": " + jugador.getNombre() +"]");

        System.out.println("[1] ATAQUE DE SALTO");
        int selectMenu = seleccionar();

        if (selectMenu == 1) {
            System.out.println("Has seleccionado ATAQUE DE SALTO");
            int dano = jugador.calcularDano();
            
            // Evitamos que la vida baje de 0
            int nuevaVida = Math.max(0, oponente.getVida() - dano);
            oponente.setVida(nuevaVida);
        }

    }

    public static void turnoOponente() {
        int dano = oponente.calcularDano();

        // Evitamos que la vida baje de 0
        int nuevaVida = Math.max(0, jugador.getVida() - dano);
        jugador.setVida(nuevaVida);
    }

    public static void victoria() {
        puntuacion = puntuacion + 100;
        turno = 0;
        jugador.reestablecer();
        oponente.reestablecer();
        
        System.out.println("¡Has ganado!");

        System.out.println("Puntuacion: " + puntuacion);

        System.out.println("[1] Siguiente Combate");
        System.out.println("[0] Volver al Menu Principal");


        int selectMenu = seleccionar();
        switch (selectMenu) {
            case 1:
                
                break;
        
            case 0:
                menuPrincipal();
                break;
            default:
                break;
        }
    }
}