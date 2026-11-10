import java.util.Scanner;

public class Main {
    static Personaje mario = new Personaje("Mario", 1, 100, 10, 10, 10, 10, "fuego");
    static Personaje luigi = new Personaje("Luigi", 1, 100, 10, 10, 70, 20, "electrica");
    static Personaje[] listaPersonajes = {mario, luigi};
    static Personaje jugador;
    static Personaje oponente = new Personaje("Bowser", 10, 100, 10, 20, 20, 15, "fuego");
    static Personaje personajeTurno;
    static int turno = 1;
    static int puntuacion;
    static String[] ranking = new String[5];

    public static void main (String[] args) {
        menuPrincipal();
    }

    public static int seleccionar() {
        Scanner menuSc = new Scanner(System.in);
        int seleccion = menuSc.nextInt();

        return seleccion;
    }

    public static void menuPrincipal() {
        int selectMenu;

        do {
            System.out.println("\n" + "[[EL DESPERTAR DEL HEROE]]" + "\n");
            System.out.println("[1] Nueva Partida");
            System.out.println("[2] Consultar Ranking");
            System.out.println("[3] Ayuda y Reglas");
            System.out.println("[0] Salir");

            selectMenu = seleccionar();

            switch (selectMenu) {
                case 1:
                    menuJuego();
                    break;
                case 2:
                    consultarRanking();
                    break;
                case 3:
                    ayudaReglas();
                    break;
                case 0:
                    
                    break;
            
                default:
                    System.out.println("No has escogido una opcion valida");
                    break;
            }
        } while (selectMenu < 0 || selectMenu > 3);
    }

    public static void menuJuego() {
        int selectMenu;

        do {
            System.out.println("\n" + "[[SELECCIONA PERSONAJE]]");

            for (int i = 0; i < listaPersonajes.length; i++) {
                System.out.println("\n" + "[" + (i + 1) + "] " + listaPersonajes[i].getNombre());
                listaPersonajes[i].getStats();
            }

            System.out.println("[0] Volver");

            selectMenu = seleccionar();

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
        } while (selectMenu < 0 || selectMenu > listaPersonajes.length);
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
        int selectMenu;
        do {
            System.out.println("[TURNO " + turno + ": " + jugador.getNombre() +"]");

            System.out.println("[1] ATAQUE DE SALTO");
            selectMenu = seleccionar();
        } while (selectMenu < 0 || selectMenu > 1); 

        if (selectMenu == 1) {
            System.out.println("Has seleccionado ATAQUE DE SALTO");
            int dano = jugador.atacar(jugador.ataqueBasico());
            
            // Evitamos que la vida baje de 0
            int nuevaVida = Math.max(0, oponente.getVida() - dano);
            oponente.setVida(nuevaVida);
        }

    }

    public static void turnoOponente() {
        int dano = oponente.atacar(oponente.ataqueBasico());

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

    public static void consultarRanking() {
        int selectMenu;

        do {
            System.out.println("[[RANKING]]");

            for (int i = 0; i < ranking.length; i++) {
                String rankingGuardado = ranking[i];

                if (rankingGuardado == null) {
                    rankingGuardado = ("[" + (i + 1) + "] " + "No hay puntuacion guardada");
                }

                System.out.println(rankingGuardado);
            }

            System.out.println("\n[0] Volver al Menu Principal");

            selectMenu = seleccionar();

            if (selectMenu == 0) {
                menuPrincipal();
            }

        } while (selectMenu > 0 || selectMenu < 0); 

            
    }

    public static String guardarPuntuacion(int puntuacion){
        Scanner nombreSc = new Scanner(System.in);
        String nombre = nombreSc.nextLine();

        return (nombre + puntuacion);
        
    }

    public static void ayudaReglas() {
        int selectMenu;
        do {
            System.out.println("REGLAS Y AYUDA");

            System.out.println();

            System.out.println("[0] Volver al Menu Principal");
            selectMenu = seleccionar();
        } while (selectMenu < 0 || selectMenu > 1); 
    }
}