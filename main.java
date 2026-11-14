import java.util.Scanner;

public class Main {
    static Bros[] listaPersonajes = {
        new Bros("Mario", 1, 100, 15, 10, 10, 15, "fuego"), 
        new Bros("Luigi", 1, 100, 10, 15, 10, 10, "electrica"),
        new Bros("Wario", 1, 100, 20, 20, 10, 5, "fuego")
    };
    static Bros jugador;

    static Personaje oponente = new Boo();
    static Personaje personajeTurno;
    static Personaje[] listaJefes = {
        new Personaje("Bowser", 10, 100, 10, 20, 20, 15)
    };

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
            System.out.println("\n+==========================================================+");
            System.out.println("|                                                          |");
            System.out.println("|               *  EL DESPERTAR DEL HÉROE  *               |");
            System.out.println("|               ~ Aventura RPG por Turnos ~                |");
            System.out.println("|                                                          |");
            System.out.println("+==========================================================+");
            System.out.println("|                                                          |");
            System.out.println("|    [1] Nueva Partida                                     |");
            System.out.println("|    [2] Consultar Ranking                                 |");
            System.out.println("|    [3] Ayuda y Reglas                                    |");
            System.out.println("|                                                          |");
            System.out.println("|    [0] Salir                                             |");
            System.out.println("|                                                          |");
            System.out.println("+----------------------------------------------------------+");
            System.out.print("> Elige una opción: ");

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
            System.out.println("\n+==========================================================+");
            System.out.println("|                 SELECCIONA TU PERSONAJE                  |");
            System.out.println("+==========================================================+");

            for (int i = 0; i < listaPersonajes.length; i++) {
                //System.out.println("\n" + "[" + (i + 1) + "] " + listaPersonajes[i].getNombre());
                //listaPersonajes[i].getStats();

                
                String titulo = "[" + (i + 1) + "] " + listaPersonajes[i].getNombre().toUpperCase() + " (Nv. " + listaPersonajes[i].getNivel() + ")";
                System.out.printf("| %-56s |\n", titulo);
                listaPersonajes[i].getStats();
                System.out.println("+----------------------------------------------------------+");
            }

            System.out.println("| [0] Volver al Menú Principal                             |");
            System.out.println("+----------------------------------------------------------+");
            System.out.print("> Elige un personaje: ");

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

    // IA: Método auxiliar para dibujar barras visuales de HP y PB
    public static String generarBarra(int actual, int max, int longitud) {
        if (max <= 0) max = 1;
        int llenos = (int) Math.round(((double) actual / max) * longitud);
        llenos = Math.max(0, Math.min(longitud, llenos)); // Evita valores fuera de rango
        int vacios = longitud - llenos;

        return "[" + "█".repeat(llenos) + "░".repeat(vacios) + "]";
    }

    public static void mostrarEstado() {
        // [MOSTRAR ESTADO INICIAL]
        // System.out.println("=== " + jugador.getNombre().toUpperCase() + " ===");
        // System.out.println("Nivel: " + jugador.getNivel());
        // System.out.println("Vida: " + jugador.getVida() + "/" + jugador.getVidaMax());
        // System.out.println("=== " + oponente.getNombre().toUpperCase() + " ===");
        // System.out.println("Nivel: " + oponente.getNivel());
        // System.out.println("Vida: " + oponente.getVida() + "/" + oponente.getVidaMax());
        
        //MENU CON AYUDA DE LA IA
        String barraHpJugador = generarBarra(jugador.getVida(), jugador.getVidaMax(), 10);
        String barraHpOponente = generarBarra(oponente.getVida(), oponente.getVidaMax(), 10);

        String tituloJugador = jugador.getNombre().toUpperCase() + " (Nv. " + jugador.getNivel() + ")";
        String tituloOponente = oponente.getNombre().toUpperCase() + " (Nv. " + oponente.getNivel() + ")";

        System.out.println("\n+----------------------------------------------------------+");
        System.out.printf("| %-24s VS   %-26s |\n", tituloJugador, tituloOponente);
        System.out.printf("| HP: %s %3d/%-3d      HP: %s %3d/%-3d   |\n",
                barraHpJugador, jugador.getVida(), jugador.getVidaMax(),
                barraHpOponente, oponente.getVida(), oponente.getVidaMax());
        System.out.println("+----------------------------------------------------------+");
    
    }

    public static void turnoJugador(){
        int selectMenu;
        do {
            System.out.println("\n================= [ TURNO " + turno + ": " + jugador.getNombre().toUpperCase() + " ] =================");
            System.out.println("  [1] Ataque de Salto");
            System.out.println("  [2] Ataque Martillo");
            System.out.println("  [3] Ataque Elemental");
            System.out.println("  [4] Usar Poción (x" + /* jugador.getPociones() + */ " disponibles)");
            System.out.println("------------------------------------------------------");
            System.out.print("> Elige una acción: ");
            selectMenu = seleccionar();
        } while (selectMenu < 0 || selectMenu > 3); 

        if (selectMenu == 1) {
            jugador.ataqueSalto(oponente);
        }

        if (selectMenu == 2) {
            jugador.ataqueMartillo(oponente);
        }

        if (selectMenu == 3) {
            jugador.ataqueElemental(oponente);
        }

    }

    public static void turnoOponente() {
        oponente.ataqueBasico(jugador);
    }

    public static void victoria() {
        puntuacion = puntuacion + 100;
;
        System.out.println("\n================= [ " + jugador.getNombre().toUpperCase() + " HA GANADO ] =================");
        System.out.println("  Turnos: " + turno);
        System.out.println("  Puntuación: " + puntuacion);
        System.out.println("  [1] Siguiente Combate");
        System.out.println("  [0] Volver al Menu Principal");
        System.out.println("------------------------------------------------------");
        System.out.print("> Elige una acción: ");


        turno = 0;
        jugador.reestablecer();
        oponente.reestablecer();

        int selectMenu = seleccionar();
        switch (selectMenu) {
            case 1:
                empezarCombate();
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