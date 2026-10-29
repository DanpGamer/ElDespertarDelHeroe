import java.util.Scanner;

public class Main {
    static Personaje mario = new Personaje("Mario", 1, 100, 10, 10, 10, 10);
    static Personaje luigi = new Personaje("Luigi", 1, 100, 10, 10, 70, 20);
    static Personaje[] listaPersonajes = {mario, luigi};
    static Personaje elegido;

    public static void main (String[] args) {
        menuPrincipal();
    }

    public static int seleccionar() {
        Scanner menuSc = new Scanner(System.in);
        int seleccion = menuSc.nextInt();

        return seleccion;
    }

    public static void menuPrincipal() {
        System.out.println("[[EL DESPERTAR DEL HEROE]] \s");
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
        System.out.println("[[SELECCIONA PERSONAJE]] \s");

        for (int i = 0; i < listaPersonajes.length; i++) {
            System.out.println("[" + (i + 1) + "] Disponible: " + listaPersonajes[i].getNombre());
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
                elegido = listaPersonajes[selectMenu - 1];
        
                System.out.println("Has seleccionado a " + elegido.getNombre());
            } 
            // 3. Opción fuera de rango
            else {
                System.out.println("No has escogido una opción válida.");
                }
    }
}