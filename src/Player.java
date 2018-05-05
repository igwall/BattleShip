import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {
    // Tableau des unités du joueur (1 bateau de taille 2, 2 bateaux de taille 3, etc...

    protected int score = 0; // Nombre de bateau détruit par le joueur
    //Contient l'ensemble des bateaux du joueur
    protected int[] capacity;
    protected List<Ship> army = new ArrayList<Ship>();
    protected String name;
    protected int token;
    protected List<Coordonnee> shot = new ArrayList<>();


    public Player(String name){
        this.name = name;
        this.capacity = new int[]{0,0,1,2,1,1};
    }

    public Player(String name, int[] customArmy){
        this.name = name;
        this.capacity = customArmy;
    }


    public boolean isHit(String coord){
        boolean hit = false;
        for(Ship s:army){
            boolean shipHit;
            shipHit = s.isHit(coord);
            if(shipHit){
                hit =true;
            }
        }
        return hit;
    }

    public void addShip(Ship ship){
        this.army.add(ship);
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

    public boolean isUsed(Position position) {
        boolean used = false;
        boolean inShip;
        int i = 0;
        int j = 0;
        // Tant que l'on a pas parcourru tout l'objet position
        while (i < position.getLength() && !used){
            Coordonnee coord = position.getCoordonnee(i);
            // Tant que l'on a pas parcourru tous les bateaux
            for (Ship ship : army) {
                used = ship.isUsed(position.getCoordonnee(i));
            }
            i++;
        }
        return used;
    }

    public boolean isShooted(String coord){
        boolean inList = false;
        int i = 0;
        for (Coordonnee coordMyShot : shot){
            if( coordMyShot.equals(coord)){
                inList = true;
            }
        }
        return inList;
    }

    public boolean isHitSHot(String input){
        boolean inList = false;
        int i = 0;
        for (Coordonnee coordMyShot : shot){
            if( coordMyShot.equals(input)){
                inList = coordMyShot.getHit();
            }
        }
        return inList;
    }

    public void editShipHit(String shot){
        for (Ship s:army){
            s.editHit(shot);
        }
    }

    public String getName(){
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getToken(){
        return this.token;
    }

    public void addShot(Coordonnee coord){
        this.shot.add(coord);
    }

    public boolean isAvailableShipLength(int length){
        return (capacity[length] > 0 );
    }

    public void editAvailableShip(int length){
        capacity[length] -= 1;
    }

    public String getAvailableShip(){
        String  output = "You still have :\n";
        for (int i=0; i < capacity.length; i++ ){
            if(capacity[i] != 0) {
                output += Integer.toString(capacity[i]) + " ship of size " + Integer.toString(i) +"\n";
            }
        }
        return output;
    }

    public int getMaxLengthShip(){
        return capacity.length;
    }

    public String getCoord(){
        return "A1";
    }
    public String getShot(){
        return "A1";
    }

}



























