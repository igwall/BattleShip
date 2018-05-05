

public class Bot extends Player {
    public Bot(){
        super("Jarvis",0);
    }

    public Coordonnee getCoord(){
        // La partie complexe
        Coordonnee coord =  new Coordonnee("B3");
        return coord;
    }
}
