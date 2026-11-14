public class Goomba extends Personaje implements IconPinchos {
    public Goomba(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad, String elemento) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad, elemento);
    }

    public Goomba() {
        super("Goomba", 1, 20, 10, 10, 10, 10, "fuego");
    }
}