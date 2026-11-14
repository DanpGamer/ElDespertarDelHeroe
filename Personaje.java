import java.util.Scanner;

public class Personaje {
    protected String nombre;
    protected int nivel;
    protected int vida;
    protected int vidaMax;
    protected int poder;
    protected int defensa;
    protected int suerte;
    protected int velocidad;
    protected String elemento;
    protected int [] pociones = new int[5]; 

    public Personaje(String nombre, int nivel, int vidaMax, int poder, int defensa, int suerte, int velocidad){
        this.nombre = nombre;
        this.nivel = 1;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.poder = poder;
        this.defensa = defensa;
        this.suerte = suerte;
        this.velocidad = velocidad;
    }

    public void getStats() {
        // System.out.println("Nivel: " + this.nivel);
        // System.out.println("Vida: " + this.vida + "/" + this.vidaMax);
        // System.out.println("Poder: " + this.poder);
        // System.out.println("Defensa: " + this.defensa);
        // System.out.println("Suerte: " + this.suerte);
        // System.out.println("Velocidad: " + this.velocidad);

        String hp = this.vida + "/" + this.vidaMax;
        System.out.printf("|     HP: %-12sPoder: %-10dDefensa: %-8d   |\n", hp, this.poder, this.defensa);
        System.out.printf("|     Suerte: %-8dVelocidad: %-24d  |\n", this.suerte, this.velocidad);
    }

    public int checkCrit(Personaje atacante, String mensaje) {
        // 2. Comprobar crítico según la suerte (porcentaje)
        // Generamos un número de 1 a 100
        int tiradaPorcentaje = (int) (Math.random() * (100 - 1 + 1)) + 1; 

        // Si la tirada es menor o igual a la suerte, es un golpe crítico
        boolean esCritico = tiradaPorcentaje <= this.suerte;

        int danoFinal = this.poder;

        if (esCritico) {
            // Ejemplo: El crítico aumenta el daño un 50% (1.5x)
            danoFinal = (int) (this.poder * 1.5);
            System.out.println("¡CRITICO! " + mensaje + " " + danoFinal + " de daño. (Daño base: " + this.poder + ")");
            return danoFinal;
        } else {
            System.out.println(mensaje + " " + danoFinal + " de daño.");
            return danoFinal;
        }
    }

    public void ataqueBasico(Personaje oponente) {
        int danoFinal = checkCrit(this, this.nombre + " inflige");
        oponente.setVida(Math.max(0, oponente.getVida() - danoFinal));
    }

    //Metodo para reestablecer vida de personaje
    public void reestablecer() {
        this.vida = this.vidaMax;
    }

    //[[[[[[[[[[[[[[[[SET AND GETTERS]]]]]]]]]]]]]]]]]]
    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getSuerte() {
        return suerte;
    }

    public void setSuerte(int suerte) {
        this.suerte = suerte;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}