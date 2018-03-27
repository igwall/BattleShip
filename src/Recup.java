
package sheepTeam;

// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)

public class Ship {



    //Déclaration des variables de la classe:
    private char startCoord, endCoord;
    private char Coord[]; // On y stockera toute les coordonnées des bateaux depuis la fonction complete

    //Zone des setters et getters
    public char getStartCoord() { return startCoord; }

    public char getEndCoord() { return this.endCoord; }

    public void setStartCoord(char startCoord) {this.startCoord = startCoord;}

    public void setEndCoord(char endCoord) {this.endCoord = endCoord;}

    //Constructeur de la classe :
    public Ship  Ship(char startCoord, char endCoord) { // Constructeur de la classe Ship
        this.startCoord = startCoord;
        this.endCoord = endCoord;
    }

    private String[] coordCalcul(char startCoord, char endCoord){
        boolean coordCorrecte = true;
        //Le premier caractère lu est une lettre et le second un chiffre
        if(Character.getNumericValue(startCoord[0]) == -1 && Character.getNumericValue(startCoord[1]) != -1){
            char LX = Character.getNumericValue(startCoord[0]);  // L'objectif est d'exploiter l'erreur -1
            char CX = Character.getNumericValue(startCoord[1]);  // On récupère le chiffre
        }

        else if(Character.getNumericValue(startCoord[1]) == -1 && Character.getNumericValue(startCoord[0]) != -1){
            char LX = Character.getNumericValue(startCoord[1]);  // L'objectif est d'exploiter l'erreur -1
            char CX = Character.getNumericValue(startCoord[0]);  // On récupère le chiffre

        }else{

            coordCorrecte = false;
        }

        if(Character.getNumericValue(endCoord[0]) == -1 && Character.getNumericValue(endCoord[1]) != -1){
            char LY = Character.getNumericValue(endCoord[0]);  // L'objectif est d'exploiter l'erreur -1
            char CY = Character.getNumericValue(endCoord[1]);  // On récupère le chiffre
        }

        else if(Character.getNumericValue(endCoord[1]) == -1 && Character.getNumericValue(endCoord[0]) != -1){
            char LY = Character.getNumericValue(endCoord[1]);  // L'objectif est d'exploiter l'erreur -1
            char CY = Character.getNumericValue(endCoord[0]);  // On récupère le chiffre
        }else{
            coordCorrecte = false;
        }

        if (coordCorrecte){ // Si les deux coordonnées sont correctes, on a décomposé, on créé la liste

        }

    }

    public boolean isHit(char missileCoord){



    }



}









