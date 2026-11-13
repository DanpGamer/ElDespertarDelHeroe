import java.util.Scanner;

public class Bros extends Personaje implements IataqueElemental{
    public Bros(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad, String elemento) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad, elemento);
    }

    //ATAQUE BASICO SALTO (CUIDADO CON PINCHOS Y INOFENSIVO CON ENEMIGOS INVISIBLES)
    public void ataqueSalto(Personaje oponente) {
        int min = this.poder;
        int max = this.poder /* * 2 */; 
        int danoBase = (int) (Math.random() * (max - min + 1)) + min;  
        int danoFinal = checkCrit(this, (this.nombre + " inflige al saltar"));

        oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
    }

    public void ataqueMartillo(Personaje oponente) {
        int danoFinal;

        System.out.println("Selecciona (1) o (2), si aciertas sera ataque 100% Critico, sino 0 daño");
        Scanner select = new Scanner(System.in);
        int numeroCorrecto = (int) (Math.random() * (2 - 1 + 1)) + 1; 
        
        if (select.nextInt() == numeroCorrecto) {
            danoFinal = (int)(this.poder * 1.5);
            System.out.println("\n"+ "¡CRITICO! " + this.nombre + " usa el martillo con " + oponente.nombre + " " + danoFinal + " de daño. (Daño base: " + this.poder + ")");

        } else {
            System.out.println("\n" + this.nombre + " Ha fallado con el martillo, 0 de daño");
            danoFinal = 0;
        }

        oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
    }

    public void ataqueElemental(Personaje oponente) {
            int min = this.poder;
            int max = this.poder /* * 2 */; 
            int danoBase = (int) (Math.random() * (max - min + 1)) + min;  
            int danoFinal = checkCrit(this, (this.nombre + " lanza bola de " + elemento));    

            
            // Evitamos que la vida baje de 0
            oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
        }
}