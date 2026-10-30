import java.util.Random;

public class Personaje {
    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMax;
    private int poder;
    private int defensa;
    private int suerte;
    private int velocidad;

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


    //SET AND GETTERS
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

    public void getStats() {
    System.out.println("Nivel: " + this.nivel);
    System.out.println("Vida: " + this.vida + "/" + this.vidaMax);
    System.out.println("Poder: " + this.poder);
    System.out.println("Defensa: " + this.defensa);
    System.out.println("Suerte: " + this.suerte);
    System.out.println("Velocidad: " + this.velocidad);
    }

    public int calcularDano() {
        int min = this.poder;
        int max = this.poder /* * 2 */; 
        int danoBase = (int) (Math.random() * (max - min + 1)) + min;  
        int danoFinal = danoBase;      

        // 2. Comprobar crítico según la suerte (porcentaje)
        // Generamos un número de 1 a 100
        int tiradaPorcentaje = (int) (Math.random() * (100 - 1 + 1)) + 1; 

        // Si la tirada es menor o igual a la suerte, es un golpe crítico
        boolean esCritico = tiradaPorcentaje <= this.suerte;

        if (esCritico) {
            // Ejemplo: El crítico aumenta el daño un 50% (1.5x)
            danoFinal = (int) (danoBase * 1.5);
            System.out.println("¡GOLPE CRITICO! (" + this.suerte + "% de prob.)");
        }

        System.out.println("\n"+ this.nombre + " inflige " + danoFinal + " de daño. (Daño base: " + danoBase + ")");

        return danoFinal;
    }

    public void reestablecer() {
        this.vida = this.vidaMax;
    }
}