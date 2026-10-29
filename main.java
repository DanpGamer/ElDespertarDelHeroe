import java.util.Scanner;

public class Main {
    static Personaje mario;
    static Personaje luigi;
    static Personaje[] listaPersonajes;

    public static void main (String[] args) {
        Personaje mario = new Personaje("Mario", 1, 100, 10, 10, 10, 10);
        Personaje luigi = new Personaje("Luigi", 1, 100, 10, 10, 15, 20);

        Personaje[] listaPersonajes = {
            mario,
            luigi,
        };
        
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
        System.out.println("[[NUEVA PARTIDA]] \s");
        System.out.println("[1] Jugar");
        System.out.println("[2] Seleccionar Personaje");
        System.out.println("[0] Salir");

        int selectMenu = seleccionar();

        switch (selectMenu) {
            case 1:
                
                break;
            case 2:
                mostrarPersonajes();
                break;
            case 0:
                
                break;
        
            default:
                System.out.println("No has escogido una opcion valida");
                break;
        }
    }

    public static void mostrarPersonajes() {
        for (int i = 0; i < listaPersonajes.length; i++) {
            System.out.println("Disponible: " + listaPersonajes[i].getNombre());
            listaPersonajes[i].getStats();
        }

        int selectMenu = seleccionar();

    }

}