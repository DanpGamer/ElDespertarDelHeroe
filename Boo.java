public class Boo extends Personaje implements Ifantasma {
    public Boo(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
    }

    public Boo() {
        super("Boo", 1, 40, 10, 10, 0, 50);
    }
}