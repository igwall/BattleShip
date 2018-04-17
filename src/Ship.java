import static java.lang.Character.isLetter;

// Le bateau possède une coordonnee X (Lettre + Chiffre)  && une coordonnee Y (Lettre + Chiffre)
public class Ship {

    //Declaration des variables de la classe:
    private Coordonnee startCoord, endCoord;
    private Position position;
    private boolean destroyed = false;


    public Ship(Coordonnee startCoord, Coordonnee endCoord){ // Constructeur d'un bateau
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        position = new Position(startCoord, endCoord);
        destroyed = false;
    }

    // Verifie si le bateau est touche ou pas
    public boolean isHit(Coordonnee coord) {
        boolean hit = position.isIn(coord);
        if(hit){
            destroyed = position.allHit();
            if (destroyed){
                this.destroyed = true;
            }
        }
        return hit;
    }

    // Zone des getters et des setters :
    public int getLength(){
        return this.position.getLength();
    }

    public boolean isDestroyed(){
        return this.destroyed;
    }

}
