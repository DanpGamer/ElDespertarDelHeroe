import java.util.Scanner;

public class Bros extends Personaje implements IataqueElemental{
    public String[] pociones = new String[3];
    public int pocionesDisponibles = 3;
    public String elemento;
    
    

    public Bros(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad, String elemento) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
        this.elemento = elemento;

        for (int i = 0; i < pociones.length; i++) {
            pociones[i] = "Poción (+25 HP)";
        }
    }

    // Consultar inventario (NO gasta turno)
    public void mostrarInventario() {
        System.out.println("\n+-- [ INVENTARIO DE POCIONES (" + pocionesDisponibles + "/3) ] ------------------+");
        for (int i = 0; i < pociones.length; i++) {
            String estado = (pociones[i] != null) ? pociones[i] : "[ VACÍO ]";
            System.out.printf("|  [%d] %-50s |\n", (i + 1), estado);
        }
        System.out.println("|  [0] Volver");
        System.out.println("+----------------------------------------------------------+");
    }

    // Usar poción por slot (1 a 3)
    public boolean usarPocion(int slot) {
        int indice = slot - 1;

        // 1. Validar límites del array
        if (indice < 0 || indice >= pociones.length) {
            System.out.println("(!) Posición fuera de rango. Elige entre 1 y 3.");
            return false;
        }

        // 2. Validar que la posición no esté vacía
        if (pociones[indice] == null) {
            System.out.println("(!) Ese espacio está vacío. No hay ninguna poción ahí.");
            return false;
        }

        // 3. Evitar curar si ya tiene 100 HP
        if (this.vida >= this.vidaMax) {
            System.out.println("(!) Ya tienes la vida al máximo (" + this.vida + "/" + this.vidaMax + ").");
            return false;
        }

        // 4. Curar 25 HP sin superar el máximo y retirar la poción
        this.vida = Math.min(this.vidaMax, this.vida + 25);
        System.out.println(this.nombre + " usa una poción (+25 HP). Vida actual: " + this.vida + "/" + this.vidaMax);
        pociones[indice] = null;
        pocionesDisponibles--;
        return true;
    }

    //ATAQUE BASICO SALTO (CUIDADO CON PINCHOS Y INOFENSIVO CON ENEMIGOS INVISIBLES)
    public void ataqueSalto(Personaje oponente) {
        if (oponente instanceof IconPinchos) {
            System.out.println(this.nombre + " ha saltado y se ha pinchado -5 de daño");
            this.setVida(this.getVida() - 5);
        }

        else if (oponente instanceof Ifantasma) {
            System.out.println(this.nombre + " ha saltado pero no ha pasado nada");
        }

        else {
            int dano = checkCrit(this.poder, (this.nombre + " inflige al saltar"));
            oponente.setVida(Math.max(0, oponente.getVida() - dano));
        }
        
    }

    public void ataqueMartillo(Personaje oponente) {
        int dano = 0;
        
        if (oponente instanceof Ifantasma) {
            System.out.println(this.nombre + " ha usado el martillo pero no ha pasado nada");
        } 

        else {
            int eleccion;
            do {
                System.out.println("Selecciona (1) o (2), si aciertas sera ataque 100% Critico, sino 0 daño");
                Scanner select = new Scanner(System.in);
                eleccion = select.nextInt();

                if (eleccion < 1 || eleccion > 2) {
                    System.out.println("(!) Opción incorrecta. Solo puedes elegir 1 o 2.");
                }
            }while(eleccion < 1 || eleccion > 2);

            int numeroCorrecto = (int) (Math.random() * (2 - 1 + 1)) + 1; 

            if (eleccion == numeroCorrecto) {
            dano = this.poder;
            System.out.println("\n"+ "¡CRITICO! " + this.nombre + " usa el martillo con " + oponente.nombre + " " + dano + " de daño.");
            } 

            else {
                System.out.println("\n" + this.nombre + " Ha fallado con el martillo, " + dano + " de daño");
            }

            oponente.setVida(Math.max(0, oponente.getVida() - dano));
        }
    }

    @Override 
    public void ataqueElemental(Personaje oponente) {
        int dano = checkCritFijo(this.poder / 2, this.poder, (this.nombre + " lanza bola de " + elemento));    

        // Evitamos que la vida baje de 0
        oponente.setVida(Math.max(0, oponente.getVida() - dano));
    }

    @Override
    public String getElemento() {
        return this.elemento;
    }


    //GETS AND SETS

    public int getPocionesDisponibles() {
        return pocionesDisponibles;
    }
}