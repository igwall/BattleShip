import static java.lang.Character.isLetter;

// Le bateau possède une coordonnee X (Lettre + Chiffre)  && une coordonnee Y (Lettre + Chiffre)
public class Ship {

    //Declaration des variables de la classe:
    private Position position;
    private boolean destroyed = false;


    public Ship(String startCoord, String endCoord){ // Constructeur d'un bateau
        position = new Position(startCoord, endCoord);
        destroyed = false;
    }

    // Verifie si le bateau est touche ou pas
    public boolean isHit(Coordonnee coord) {
        boolean hit = position.isIn(coord);
        if(hit){
            System.out.println(position.printCoord());
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
