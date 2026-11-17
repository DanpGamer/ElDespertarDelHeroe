public class Bowser extends Personaje implements IataqueElemental, IconPinchos {
    private final String elemento = "fuego";
    
    public Bowser(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
    }

    public Bowser() {
        super("Bowser", 1, 80, 10, 10, 0, 10);
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
}