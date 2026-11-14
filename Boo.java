public class Boo extends Personaje implements Ifantasma {
    public Boo(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad, String elemento) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad, elemento);
    }

    public Boo() {
        super("Boo", 1, 40, 10, 10, 10, 50, "fantasma");
    }
}