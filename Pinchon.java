public class Pinchon extends Personaje implements IconPinchos {
    public Pinchon(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad) {
        super(nombre, nivel, vidaMax, poder, defensa, suerte, velocidad);
    }

    public Pinchon() {
        super("Pinchon", 1, 40, 10, 10, 0, 10);
    }
}