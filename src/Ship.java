import static java.lang.Character.isLetter;

// Le bateau possède une coordonnee X (Lettre + Chiffre)  && une coordonnee Y (Lettre + Chiffre)
public class Ship {
    private Position position;
    private boolean destroyed = false;

    public Ship(String startCoord, String endCoord){ // Constructeur d'un bateau
        position = new Position(startCoord, endCoord);
        destroyed = false;
    }

    // Verifie si le bateau est touche ou pas
    public boolean isUsed(Coordonnee value) {
        return position.isUsed(value);
    }

    public boolean isHit(String value){
        return position.isHit(value);
    }

    public void editHit(String shot){
        position.editHit(shot);
        boolean allHit = position.allHit();
        if(allHit){
            this.destroyed = true;
        }
    }

    public boolean isDestroyed(){
        return this.destroyed;
    }

}
