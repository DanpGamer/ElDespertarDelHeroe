public class Bowser extends Personaje implements IataqueElemental {
    public Bowser(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad, String elemento) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad, elemento);
    }

    public Bowser() {
        super("Bowser", 1, 100, 10, 10, 10, 10, "fuego");
    }

    public void ataqueElemental(Personaje oponente) {
            int danoFinal = checkCrit(this, (this.nombre + " lanza bola de " + elemento));    

            // Evitamos que la vida baje de 0
            oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
        }
}