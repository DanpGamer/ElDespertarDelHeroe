public class Goomba extends Personaje {
    public Goomba(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
    }

    public Goomba() {
        super("Goomba", 1, 40, 10, 10, 0, 10);
    }
}