public class Bowser extends Personaje implements IataqueElemental {
    private final String elemento = "fuego";
    
    public Bowser(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
    }

    public Bowser() {
        super("Bowser", 1, 100, 10, 10, 0, 10);
    }

    @Override 
    public void ataqueElemental(Personaje oponente) {
            int danoFinal = checkCrit(this, (this.nombre + " lanza bola de " + this.elemento));    

            // Evitamos que la vida baje de 0
            oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
        }

    @Override
    public String getElemento() {
        return this.elemento;
    }
}