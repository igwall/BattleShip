import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // Tableau des unités du joueur
    private int capicity[] = {0,0,1,2,1,1};

    private int token; // Jeton du joueur (1 ou 2)
    private Ship ship1, ship2, ship3, ship4,ship5;
    private Ship[] army = { ship1, ship2, ship3, ship4, ship5};
    private String name;
    private int[] ownGrid[];
    private GrilleBateau grilleBateau;
    private GrilleTir grilleTir;

    public Player(int tok,String name){
        this.name = name;
        this.token = tok;
        int current = 0;
        this.army[0] = shipMaker(current);
        current ++;
        this.army[1]= shipMaker(current);
        current ++;
        this.army[2] = shipMaker(current);
        current ++;
        this.army[3] = shipMaker(current);
        current ++;
        this.army[4] = shipMaker(current);
    }

    // Faire la matrice qui va accueillir les bateaux
    // Faire une fonction qui calcule le bateau demandé
    //Vérifier les coordonnées ici !

    public Ship shipMaker(int current){

        boolean coordValide = false;
        String startCoord = "";
        String endCoord = "";
        int curr = current;


        while(!coordValide){
            Scanner cs = new Scanner(System.in);
            System.out.println("Veuillez saisir une première coordonnée:");
            startCoord = cs.next();

            Scanner cs1 = new Scanner(System.in);
            System.out.println("Veuillez saisir une seconde coordonnée: ");
            endCoord = cs1.next();

            coordValide = shipControl(startCoord,endCoord,curr);
        }
        Ship ship = new Ship(startCoord, endCoord);
        return ship;
    }

    public boolean shipControl(String startCoord, String endCoord, int current){ // Vérifie les coordonnées du bateau
       boolean valide = false;
       for(int i = 0; i <= current; i++){

           //
           //  A compléter avec les informations de la grille des joueurs.
           //
           //
           //
       }

       return valide;
    }


}