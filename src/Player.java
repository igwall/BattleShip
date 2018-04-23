import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    // Tableau des unités du joueur (1 bateau de taille 2, 2 bateaux de taille 3, etc...
    protected int capacity[] = {0,0,1,2,1,1,0,0,0,0,0};

    protected int score = 0; // Nombre de bateau détruit par le joueur

    //Contient l'ensemble des bateaux du joueur
    protected List<Ship> army = new ArrayList<Ship>();
    protected String name;
    protected GrilleBateau grilleBateau;
    protected GrilleTir grilleTir;


    public Player(String name){
        this.name = name;
        int current = 0;
        this.grilleBateau = new GrilleBateau();
        this.grilleTir = new GrilleTir();
    }


    public void shipGenerator(int current) {
        System.out.println("Construction du bateau "+Integer.toString(current));
        boolean positionValide;
        boolean lengthValide;
        boolean allValide = false;
        //On demande les coordonnées des joueurs
        Coordonnee endCoord = null;
        Coordonnee startCoord = null;

        while(!allValide) {
            //On demande les coordonnées des joueurs
            Scanner cs = new Scanner(System.in);
            System.out.println("Veuillez saisir une première coordonnée: ");
            String coord1 = cs.next();
            startCoord = new Coordonnee(coord1);

            Scanner cs1 = new Scanner(System.in);
            System.out.println("Veuillez saisir une seconde coordonnée: ");
            String coord2 = cs.next();
            endCoord = new Coordonnee(coord2);

            boolean coordControl = grilleBateau.coordControl(startCoord);
            boolean coordControl2 = grilleBateau.coordControl(startCoord);

            //Si une des coordonnées n'est pas valide
            if (!coordControl || !coordControl2) {
                allValide = false;
                System.out.println("La valeur indiquée dépasse la grille\n");
            } else {
                System.out.println("Coordonnée valides");
                Position position = new Position(startCoord, endCoord);
                positionValide = grilleBateau.positionValide(position);
                lengthValide = shipLengthControl(position.getLength());
                if (positionValide && lengthValide) {
                    allValide = true;
                    System.out.println("Position est valide");
                    this.grilleBateau.updateGrille(position);
                    this.capacity[position.getLength()] -=1;
                } else {
                    allValide = false;
                    System.out.println("Position invalide\n");
                }
            }
        }
        Ship ship = new Ship(startCoord, endCoord);
        this.army.add(ship);
    }

    public boolean isHit(Coordonnee coord){
        boolean hit = false;
        for(Ship s:army){
            boolean shipHit;
            shipHit = s.isHit(coord);
            if(shipHit){
                hit =true;
                grilleBateau.updatePrintHit(coord);
            }
        }
        return hit;
    }


    private boolean shipLengthControl(int length){
        boolean lengthValide;
        //Si aucun bateau de taille length n'est possible à poser
        if (this.capacity[length] == 0){
            lengthValide = false;
        }
        else {lengthValide = true;}
        return lengthValide;
    }

    public int getCountDestroyed(){
        int countDestroyedShip =0;
        for(Ship s: army){
            if(s.isDestroyed()){
                countDestroyedShip +=1;
                System.out.println(Integer.toString(countDestroyedShip));
            }
        }
        return countDestroyedShip;
    }

    public int getScore(){
        return this.score;
    }

    public void displayInfos(){
        System.out.println("Votre score : "+getScore());
        System.out.println("Affichage de la position de vos bateaux : \n\n");
        this.grilleBateau.printOwnShip();
        System.out.println("==== ==== ==== ==== ====\n");
        System.out.println("Affichage des tirs effectués : \n\n");
        this.grilleTir.affichageGrilleTir();
    }

    public void addHit(Coordonnee coord){
        grilleTir.addHit(coord);
    }

    public void addMiss(Coordonnee coord){
        grilleTir.miss(coord);
    }

    public String getName(){
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void displayShip(){
        grilleBateau.printOwnShip();
    }
}



























