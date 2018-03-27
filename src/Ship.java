package sheepTeam;

// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)

public class Ship {

    //Déclaration des variables de la classe:
    private char startCoord, endCoord;
    private char Coord[]; // On y stockera toute les coordonnées des bateaux depuis la fonction complete

    public Ship(char startCoord, char endCoord){ // Constructeur d'un bateau


    }
}



    private int traitement(char startCoord, char endCoord){
        // On récupère la lettre
        char lettreStart = getY(startCoord);
        int chiffreStart = getX(startCoord);
        // On récupère le chiffre
        char lettreEnd = getY(endCoord);
        int chiffreEnd = getX(endCoord);

    }

    private int getX(char coord){
        if(Character.getNumericValue(coord[0]) != -1){
            return coord[0];
        }else if(Character.getNumericValue(startCoord[1]) != -1){
            return coord[1];
        }else{
            System.out.println("La coordonnée entrée n'est pas correcte");
        }
    }

    private char getY(char coord){
        if(Character.getNumericValue(coord[0]) == -1){
            return coord[0];
        }else if(Character.getNumericValue(coord[1]) == -1){
            return coord[1];
        }else{
            // Gestion d'une erreur voir avec le prof
        }
    }