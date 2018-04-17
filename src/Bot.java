

public class Bot extends Player {
    public Bot(){
        super("Jarvis");
    }

    public Coordonnee getTir(){
        // La partie complexe
        Coordonnee coord =  new Coordonnee("B3");
        return coord;
    }
}
