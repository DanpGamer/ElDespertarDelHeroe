import java.util.Scanner;

public class Main {
    static Bros jugador;
    static Personaje oponente;
    
    static Personaje personajeTurno;
    static int puntuacion;
    static int turno = 1;
    static String[] ranking = new String[10];

    static Bros[] listaPersonajes = {
        new Bros("Mario", 1, 100, 20, 10, 10, 15, "fuego"), 
        new Bros("Luigi", 1, 100, 20, 15, 10, 10, "electrica"),
    };

    public static void main (String[] args) {
        menuPrincipal();
    }

    public static int seleccionar() {
        Scanner menuSc = new Scanner(System.in);

        while (!menuSc.hasNextInt()) {
                System.out.print("(!) Entrada no válida.");
                menuSc.next();
        }

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
                    seleccionarJugador();                    
                    empezarHistoria();
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

    public static void seleccionarJugador() {
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
                } 
                // 3. Opción fuera de rango
                else {
                    System.out.println("No has escogido una opción válida.");
                    }
        } while (jugador == null);
    }

    public static void empezarHistoria(){
        // 1. Prólogo y Primer Combate (Goomba o Pinchón)
        historiaPrologo();
        oponente = new Goomba();
        if (!empezarCombate()) { // Si pierde o abandona, termina la aventura
            if (jugador.getVida() <= 0) historiaDerrota();
            return;
        }

        // 2. Interludio I y Segundo Combate (Boo)
        historiaBosque();
        oponente = new Boo();
        if (!empezarCombate()) {
            if (jugador.getVida() <= 0) historiaDerrota();
            return;
        }

        // 3. Interludio II y Combate Final (Bowser)
        historiaCastilloBowser();
        oponente = new Bowser();
        if (!empezarCombate()) {
            if (jugador.getVida() <= 0) historiaDerrota();
            return;
        }


        // 4. Si supera los 3 combates, muestra el final
        historiaVictoria();
        puntuacion = puntuacion + jugador.getVida() + (jugador.getPocionesDisponibles() * 25);
        ranking [0] = guardarPuntuacion();

        menuPrincipal();
    }

    public static void narrarEscena(String capitulo, String[] parrafos) {
        System.out.println("\n+==========================================================+");
        System.out.printf("| %-56s |\n", capitulo.toUpperCase());
        System.out.println("+==========================================================+");
        System.out.println("|                                                          |");

        for (String linea : parrafos) {
            System.out.printf("| %-56s |\n", linea);
        }

        System.out.println("|                                                          |");
        System.out.println("+----------------------------------------------------------+");
        System.out.print("  [ Pulsa ENTER para continuar... ]");
        Scanner scannersc = new Scanner(System.in);
        scannersc.nextLine(); // Espera a que el jugador pulse Enter
        System.out.println();
}

    public static void historiaPrologo() {
        String[] texto = {
            "Era una mañana tranquila en el Reino Champiñón...",
            "De pronto, el cielo se oscureció bajo la sombra de",
            "una gigantesca fortaleza voladora.",
            "",
            "¡Bowser ha irrumpido en el castillo y ha secuestrado",
            "a la Princesa Peach!",
            "",
            jugador.getNombre() + " ajusta su gorra y parte de inmediato hacia el",
            "Sendero Champiñón para seguir el rastro del villano.",
            "",
            "¡Pero la vanguardia de Bowser bloquea el camino!"
        };
        narrarEscena("CAPÍTULO 1: EL RAPTO EN EL CASTILLO", texto);
    } 
    
    public static void historiaBosque() {
        String[] texto = {
            "Con el camino despejado, " + jugador.getNombre() + " avanza sin descanso",
            "hasta adentrarse en el Bosque Sombrío.",
            "",
            "La niebla cubre el sendero y los árboles parecen",
            "observar cada paso. Un escalofrío recorre el aire...",
            "",
            "De entre las sombras se escucha una risa burlona.",
            "¡Un espectro bloquea el paso hacia el volcán!"
        };
        narrarEscena("CAPÍTULO 2: SOMBRAS EN EL BOSQUE", texto);
    }

    public static void historiaCastilloBowser() {
        String[] texto = {
            "Tras dejar atrás la niebla, el calor se vuelve",
            "insoportable. Las puertas del Castillo de Bowser se",
            "alzan frente a " + jugador.getNombre() + ".",
            "",
            "Al cruzar el salón del trono, un rugido estremece",
            "las columnas de piedra. Bowser aparece tras la jaula",
            "de la Princesa Peach.",
            "",
            "BOWSER: '¡Gwahaha! ¡Llegaste demasiado tarde!'",
            "¡Prepárate para el combate final!"
        };
        narrarEscena("CAPÍTULO 3: EL TRONO DE FUEGO", texto);
    }    

    public static void historiaVictoria() {
        String[] texto = {
            "¡Bowser cae derrotado y la fortaleza comienza a",
            "temblar! " + jugador.getNombre() + " libera a la Princesa Peach.",
            "",
            "PEACH: '¡Gracias por salvarme una vez más!'",
            "",
            "La paz regresa al Reino Champiñón y el nombre del",
            "héroe resuena en cada rincón del reino.",
            "",
            "*** ¡HAS COMPLETADO EL DESPERTAR DEL HÉROE! ***"
        };
        narrarEscena("EPÍLOGO: EL REGRESO DE LA PAZ", texto);
    }

    public static void historiaDerrota() {
        String[] texto = {
            jugador.getNombre() + " ha caído sin fuerzas en batalla...",
            "La risa de Bowser resuena en la distancia mientras",
            "la oscuridad cubre el Reino Champiñón.",
            "",
            "*** FIN DE LA PARTIDA - INTÉNTALO DE NUEVO ***"
        };
        narrarEscena("DERROTA: EL HÉROE HA CAÍDO", texto);
    }


//LOGICA DE COMBATE //  //LOGICA DE COMBATE //  //LOGICA DE COMBATE //  //LOGICA DE COMBATE //  
    public static boolean empezarCombate(){
        Boolean esVictoria = null;
        Boolean esTurnoJugador = decidirTurno();

        while (esVictoria == null) {
            if (esTurnoJugador) {
                personajeTurno = jugador;
                turnoJugador();
                
                if (oponente.getVida() <= 0) { //CONDICION DE VICTORIA SI OPONENTE VIDA ES IGUAL O MENOR A 0
                    System.out.println("¡" + oponente.getNombre() + " ha sido derrotado!");
                    puntuacion = puntuacion + 100;
                    esVictoria = true;
                }   
            }
                else if (!esTurnoJugador){
                    personajeTurno = oponente;
                    turnoOponente();
                    
                    // Comprobar si el jugador ha sido derrotado
                    if (jugador.getVida() <= 0) {
                        System.out.println("¡" + jugador.getNombre() + " ha sido derrotado!");
                        System.out.println("\n" + "¡Has perdido!");
                        esVictoria = false;
                    }
                }
            // 3. Alternar turno para la siguiente ronda (si es true pasa a false, y viceversa)
            esTurnoJugador = !esTurnoJugador;
            turno++;
        }
        //DESPUES DEL COMBATE
        turno = 1;
        return esVictoria;
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

    public static void turnoJugador(){
        int selectMenu;
        boolean turnoTerminado = false;

        do {
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

            System.out.println("\n================= [ TURNO " + turno + ": " + jugador.getNombre().toUpperCase() + " ] =================");
            System.out.println("  [1] Ataque de Salto");
            System.out.println("  [2] Ataque Martillo");
            System.out.println("  [3] Ataque Elemental");
            System.out.println("  [4] Inventario / Inventario / Pociones (" + jugador.getPocionesDisponibles() + "/3)");
            System.out.println("------------------------------------------------------");
            System.out.print("> Elige una acción: ");
            selectMenu = seleccionar();

            // ATAQUE DE SALTO
            if (selectMenu == 1) {
            jugador.ataqueSalto(oponente);
            turnoTerminado = true;
            
            //ATAQUE MARTILLO
            } else if (selectMenu == 2) {
                jugador.ataqueMartillo(oponente);
                turnoTerminado = true;

            //ATAQUE ELEMENTAL
            } else if (selectMenu == 3) {
                jugador.ataqueElemental(oponente);
                turnoTerminado = true;
            
            //INVENTARIO / POCIONES
            } else if (selectMenu == 4) {
                int slotElegido;
                do {
                    jugador.mostrarInventario();
                    slotElegido = seleccionar();
                } while (slotElegido < 0 || slotElegido > 3);

                if (slotElegido != 0) {
                    turnoTerminado = jugador.usarPocion(slotElegido);
                }
            } else {
                System.out.println("(!) Opción no válida.");
            }
    } while (turnoTerminado == false); 

    }

    public static void turnoOponente() {
        oponente.ataqueBasico(jugador);
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

    public static String guardarPuntuacion(){
        System.out.println("Introduce tu nombre para guardar puntuación.");
        Scanner nombreSc = new Scanner(System.in);
        String nombre = nombreSc.nextLine();

        return (nombre + puntuacion);
    }

    public static void ayudaReglas() {
        int selectMenu;
        do {
            String[] text = {
                "",
                ""
            };

            System.out.println("REGLAS Y AYUDA");

            System.out.println();

            System.out.println("[0] Volver al Menu Principal");
            selectMenu = seleccionar();
        } while (selectMenu < 0 || selectMenu > 1); 
    }
}