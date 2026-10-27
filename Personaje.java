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

    public void getStats(){
        System.out.println("");
    }
}