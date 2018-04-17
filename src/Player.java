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

    public Ship shipMaker() {
        boolean positionValide;
        boolean lengthValide;
        boolean allValide = false;

        //On demande les coordonnées des joueurs
        Scanner cs = new Scanner(System.in);
        System.out.println("Veuillez saisir une première coordonnée: ");
        String coord1 = cs.next();
        Coordonnee startCoord = new Coordonnee(coord1);

        Scanner cs1 = new Scanner(System.in);
        System.out.println("Veuillez saisir une seconde coordonnée: ");
        String coord2 = cs.next();
        Coordonnee endCoord = new Coordonnee(coord2);

        boolean coordControl = this.grilleBateau.coordControl(startCoord);
        boolean coordControl2 = this.grilleBateau.coordControl(startCoord);

        while(!allValide) {
            //Si une des coordonnées n'est pas valide
            if (!coordControl || !coordControl2) {
                allValide = false;
                System.out.println("Coordonnées invalides, veuillez ressaisir des coordonnées valides\n");
            } else {
                Position position = new Position(startCoord, endCoord);
                positionValide = grilleBateau.positionValide(position);
                lengthValide = shipLengthControl(position.getLength());
                if (positionValide && lengthValide) {
                    allValide = true;
                    System.out.println("Coordonnées valides");
                } else {
                    allValide = false;
                    System.out.println("Coordonnées invalides, veuillez ressaisir des coordonnées valides\n");
                }
            }
        }
        return new Ship(startCoord, endCoord);

    }


    private boolean shipLengthControl(int length){
        boolean lengthValide;
        //Si aucun bateau de taille length n'est possible à poser
        if (length > 5 || length == 1){ lengthValide = false; }
        else if(this.capicity[length] == 0){lengthValide = false;}
        else {lengthValide = true;}
        return lengthValide;
    }




    //Cette fonction permet de vérifier que les coordonnées entrées ne sont pas déjà utilisées par un bateau


}



























