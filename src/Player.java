import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Player {
    // Tableau des unités du joueur (1 bateau de taille 2, 2 bateaux de taille 3, etc...
    private int capicity[] = {0,0,1,2,1,1};

    private int token; // Jeton du joueur (1 ou 2)

    private int score = 0; // Nombre de bateau détruit par le joueur

    //Par défaut, tout les joueurs ont exactement 5 bateaux
    private Ship ship1, ship2, ship3, ship4,ship5;

    //Contient l'ensemble des bateaux du joueur
    private Ship[] army = { ship1, ship2, ship3, ship4, ship5};
    private String name;
    private GrilleBateau grilleBateau;
    private GrilleTir grilleTir;


    public Player(int tok,String name){
        this.name = name;
        this.token = tok;
        int current = 0;

        //Ajouter la grille ici

        this.army[current] = shipMaker();
        current ++;
        this.army[current]= shipMaker();
        current ++;
        this.army[current] = shipMaker();
        current ++;
        this.army[current] = shipMaker();
        current ++;
        this.army[current] = shipMaker();

        this.grilleBateau = new GrilleBateau();
        this.grilleTir = new GrilleTir();

    }

    // Faire la matrice qui va accueillir les bateaux
    // Faire une fonction qui calcule le bateau demandé
    //Vérifier les coordonnées ici !

    public Ship shipMaker(){

        Coordonnee startCoord;
        Coordonnee endCoord;
        boolean coordValide = false;

        while(!coordValide){

            //On demande les coordonnées des joueurs
            Scanner cs = new Scanner(System.in);
            System.out.println("Veuillez saisir une première coordonnée: ");
            String coord = cs.next();
            startCoord = new Coordonnee(coord);

            Scanner cs1 = new Scanner(System.in);
            System.out.println("Veuillez saisir une seconde coordonnée: ");
            String coord2 = cs.next();
            endCoord = new Coordonnee(coord2);

            Position position = new Position(startCoord, endCoord);
            coordValide = grilleBateau.positionValide(position);
        }

        return new Ship(startCoord, endCoord);
    }


    // Coord controle va vérifier avant de lancer la construction d'un bateau
    private boolean coordControl(String startCoord, String endCoord) {

        // L'ensemble des coordonnées sont enregistrées de la forme "Lettre + Chiffre : B3, F3, etc..."
        int lenght = 0;
        // On décompose les coordonnées
        int startX = getX(startCoord);
        char startY = getY(startCoord);
        int endX = getX(endCoord);
        char endY = getY(endCoord);
        String[] tab;
        boolean lenghtControl = false;
        boolean coordValide = false;
        boolean positionValide = false;
        boolean vertical;




    private boolean shipLengthControl(int coord1, int coord2){
        int length = coord1 - coord2;
        boolean coordValide;
        //Si aucun bateau de taille length n'est possible à poser
        if (length > 5 || length == 1){ coordValide = false; }
        else if(this.capicity[length] == 0){coordValide = false;}
        else {coordValide = true;}
        return coordValide;
    }

    private boolean matrixPositionControl(char startX, char endX, int startY, int endY, boolean vertical){
        boolean coordValide = true;
        if (vertical){
            int convertX = convertCoord(startX);
            while(startY < endY && coordValide ){
                if(this.grilleBateau.getOccupe(convertX, startY)){
                    startY++;
                }
                else{
                    coordValide = false;
                }
            }
        }
        else{
            int startXConvert = convertCoord(startX);
            int endXConvert = convertCoord(endX);
            while(startXConvert < endXConvert && coordValide ){
                if(this.grilleBateau.getOccupe(startXConvert, startY)){
                    startXConvert++;
                }
                else{
                    coordValide = false;
                }
            }
        }
        return coordValide;


    }

    private int getX(String coord){
        StringBuilder x = new StringBuilder(coord);
        String newCoord ="";
        for(int i = 0 ; i< coord.length(); i++){
            if (isLetter(coord.charAt(i))){
                x.deleteCharAt(i); //On retire la lettre de la coordonée
                newCoord = x.toString();
            }
        }
        int resultat = Integer.parseInt(newCoord);
        return resultat;
    }

    // Fonction de récupération de la lettre de la coordonnée
    private char getY(String coord){
        //Par défaut, la valeur sera A.
        char Y ='A';
        for(int i = 0 ; i< coord.length(); i++){
            if (isLetter(coord.charAt(i))){
                Y = coord.charAt(i);
            }
        }
        return Y;
    }


    //Cette fonction permet de vérifier que les coordonnées entrées ne sont pas déjà utilisées par un bateau


}