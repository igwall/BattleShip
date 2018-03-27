
// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)
public class Ship {

    //Déclaration des variables de la classe:
    private char startCoord, endCoord;
    private char Coord[]; // On y stockera toute les coordonnées des bateaux depuis la fonction complete

    public Ship(char startCoord, char endCoord){ // Constructeur d'un bateau
        Coord = traitement(char startCoord, char endCoord);
    }
}
    // La fonction de traitement permet de récupérer un ensemble de coordonnées pour notre bateau
    private char[] traitement(char startCoord, char endCoord){
        // L'ensemble des coordonnées sont enregistrées de la forme "Lettre + Chiffre : B3, F3, etc..."
        private char[] coord;

        // On récupère les lettres
        char lettreStart = getY(startCoord);
        char lettreEnd = getY(endCoord);

        // On récupère les chiffres
        char chiffreStart = getX(startCoord);
        char chiffreEnd = getX(endCoord);

        // Si le bateau est positionné de façon verticale
        if (chiffreEnd == chiffreStart && lettreEnd != lettreStart){
            if (lettreStart < lettreEnd){
                int j = 0;
                for (char i = lettreStart, i<lettreEnd, i++){
                    coord[j] = i.append(chiffreStart);
                }
                return coord;
            }
            else if(lettreStart > lettreEnd){
                int j = 0;
                for (char i = lettreEnd, i<lettreStart, i++){
                    coord[j] = i.append(chiffreStart);
                }
                return coord;
            }
        }

        // Si le bateau est positionné à l'horizontale
        else if (chiffreEnd != chiffreStart && lettreEnd == lettreStart){
            if (chiffreStart < chiffreEnd){
                int j = 0;
                for (char i = chiffreStart, i<chiffreEnd, i++){
                    coord[j] = lettreStart.append(i);
                }
                return coord;
            }
            else if(chiffreStart > chiffreEnd){
                int j = 0;
                for (char i = chiffreEnd, i<chiffreStart, i++){
                    coord[j] = lettreStart.append(i);
                }
                return coord;
            }
        }

        // Le bateau est positionné en diagonale
        else{
            System.out.println("Vos coordonnées sont incorrectes, le bateau est positionné en diagonale");
            // Voir gestion des erreurs avec le prof
        }
    }


    // Fonction de récupération du chiffre de la coordonnée
    private char getX(char coord){
        if(Character.getNumericValue(coord[0]) != -1){
            return coord[0];
        }else if(Character.getNumericValue(startCoord[1]) != -1){
            return coord[1];
        }else{
            System.out.println("La coordonnée entrée n'est pas correcte");
        }
    }


    // Fonction de récupération de la lettre de la coordonnée
    private char getY(char coord){
        if(Character.getNumericValue(coord[0]) == -1){
            return coord[0];
        }else if(Character.getNumericValue(coord[1]) == -1){
            return coord[1];
        }else{
            // Gestion d'une erreur voir avec le prof
        }
    }