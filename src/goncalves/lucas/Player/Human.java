package goncalves.lucas.Player;
import goncalves.lucas.Elements.Coordonnee;
import goncalves.lucas.Elements.Ship;
import goncalves.lucas.Battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human implements IPlaying{

    int score = 0; // Nombre de bateau détruit par le joueur
    //Contient l'ensemble des bateaux du joueur
    int[] capacity;
    List<Ship> army = new ArrayList<Ship>();
    String name;
    int token;
    List<Coordonnee> shot = new ArrayList<>();


    public Human(String name) {
        this.name = name;
        this.capacity = new int[]{0,0,1,2,1,1};
    }

    public Human(){
            this.name = "Jarvis";
            this.capacity = new int[]{0,0,1,2,1,1};
    }

    public String getShot() {
        boolean allcheck = false;
        String shot = "";
        while (!allcheck) {
            int coordCheck = -1;
            while (coordCheck != 0) {
                System.out.println("Please input the value of your shot :");
                Scanner inputCoord = new Scanner(System.in);
                shot = inputCoord.next();
                coordCheck = Coordonnee.controlInput(shot);
                switch (coordCheck) {
                    case 1:
                        System.out.println("Too many letters in your input, not correct");
                        break;
                    case 2:
                        System.out.println("There a special character... What could I do with it ?!");
                        break;
                    case 3:
                        System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                        break;
                    case 4:
                        System.out.println("Your input is not in this grid... ");
                        break;
                    default:
                        System.out.println("Your shot is correct.");
                }
            }
            Coordonnee coord = new Coordonnee(shot);
            if (this.isShooted(shot)) {
                allcheck = false;
                System.out.println("You've already shot there... please enter an other shot: ");
            } else {
                allcheck = true;
            }
        }
        return shot;
    }

    public void createFleet() {
        System.out.println(this.name + ", you will complete your army :");
        int i = 1;
        while (i <= Battleship.getNbShip()) {
            System.out.println(displayGridShip());
            System.out.println(getAvailableShip());
            System.out.println("Informations for ship " + Integer.toString(i));
            boolean allCheck = false;
            String coord1 = "";
            String coord2 = "";
            while (!allCheck) {
                int coord1Check = -1;
                int coord2Check = -1;
                boolean positionCheck;
                boolean positionAvailable = false;

                while (coord1Check != 0) {
                    System.out.println("Please input the first coord :");
                    Scanner inputCoord1 = new Scanner(System.in);
                    coord1 = inputCoord1.next();
                    coord1Check = Coordonnee.controlInput(coord1);
                    switch (coord1Check) {
                        case 1:
                            System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                            break;
                        case 2:
                            System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                            break;

                        case 3:
                            System.out.println("The input is not valid... Try something like A1 - a1 - 1a - 1A");
                            break;
                        case 4:
                            System.out.println("Your input is not in this grid... ");
                            break;
                        default:
                            System.out.println("Input checked");
                    }

                }

                while (coord2Check != 0) {
                    System.out.println("Please input the second coord :");
                    Scanner inputCoord2 = new Scanner(System.in);
                    coord2 = inputCoord2.next();
                    coord2Check = Coordonnee.controlInput(coord2);
                    switch (coord2Check) {
                        case 1:
                            System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                            break;
                        case 2:
                            System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                            break;

                        case 3:
                            System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                            break;
                        case 4:
                            System.out.println("Your input is not in this grid... ");
                            break;

                        default:
                            System.out.println("Input checked");
                    }
                }

                positionCheck = positionControl(coord1, coord2);
                if (positionCheck) {
                    int length = Coordonnee.calcLength(coord1, coord2);
                    positionAvailable = isAvailableShipLength(length);
                    if (positionAvailable) {
                        allCheck = true;
                        editAvailableShip(length);
                    } else {
                        System.out.println("You can't place a ship like this...");
                    }
                } else {
                    System.out.println("Sorry, but you can't put a ship there, there already a ship");
                }
            }
            Ship ship = new Ship(coord1, coord2);
            addShip(ship);
            i++;
        }

    }


    public void addShip(Ship ship){
        this.army.add(ship);
    }

    // Fonctions de controle


    /** Checking if the shot input is already in the list of shot by the player */
    public boolean isShooted(String coord){
        boolean inList = false;
        int i = 0;
        for (Coordonnee coordMyShot : shot){
            coord = Coordonnee.traitment(coord);
            if( coordMyShot.equals(coord)){
                inList = true;
            }
        }
        return inList;
    }

    /** Look if the shot input is a shot who hit a ship*/
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

    /** Look if the string of the shot hit a ship in the army of the player */
    public boolean isHit(String coord){
        boolean hit = false;
        for(Ship s:army){
            boolean shipHit = s.isHit(coord);
            if(shipHit){
                hit =true;
            }
        }
        return hit;
    }


    public boolean isDammagedShip(String coord){
        boolean dammaged = false;
        for(Ship s : army ){
            if(s.isDammaged(coord)){
                dammaged = true;
            }
        }
        return dammaged;
    }

    /** Control if the length input as attribute is available in the capacity of ship available for the player*/
    public boolean isAvailableShipLength(int length){
        boolean available;
        if(length > capacity.length -1 || length <0){
            available = false;
        }
        else{
            available = capacity[length] > 0;

        }
        return available;
    }

    /** look for the ship with the same value as the shot and set the state of one of this coordinate to true*/
    public void editShipHit(String shot){
        for (Ship s:army){
            s.editHit(shot);
        }
    }


    //Getter area :

    public int getScore(){
        return this.score;
    }

    public String getName(){
        return name;
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

    public int getCountDestroyed(){
        int countDestroyedShip =0;
        for(Ship s: army){
            if(s.isDestroyed()){
                countDestroyedShip +=1;
            }
        }
        return countDestroyedShip;
    }




    //Setter area :

    public void setScore(int score) {
        this.score = score;
    }

    public void addShot(Coordonnee coord){
        shot.add(coord);
    }

    public void editAvailableShip(int length){
        capacity[length] -= 1;
    }

    public boolean positionControl(String coord1,String coord2){
        boolean positionCheck = true;
        char startX = Coordonnee.calcX(coord1);
        int startY = Coordonnee.calcY(coord1);
        char endX = Coordonnee.calcX(coord2);
        int endY = Coordonnee.calcY(coord2);
        int length = 0;

        if (startX != endX && startY == endY) {
            if (startX > endX) {
                int j = 0;
                length = (int) startX - (int) endX + 1;
                for (int i = 0; i < length; i++) {
                    String coord = endX + Integer.toString(endY);
                    boolean occuped = isHit(coord);
                    if(occuped){
                        positionCheck = false;
                    }

                }

            } else {
                length = (int) endX - (int) startX + 1;
                for (int i = 0; i < length; i++) {
                    String coord = startX + Integer.toString(endY);
                    boolean occuped = isHit(coord);
                    if(occuped){
                        positionCheck = false;
                    }

                }
            }

        }
        else if(startX == endX && startY != endY){
            if(startY > endY){
                int j = 0;
                length = startY - endY + 1;
                int k = endY;
                for (int i = 0; i < length; i++) {
                    String coord = startX + Integer.toString(k);
                    boolean occuped = isHit(coord);
                    if(occuped){
                        positionCheck = false;
                    }
                    k++;
                }
            }
            else{
                int j = 0;
                length = endY - startY + 1;
                int k = startY;
                for (int i = 0; i < length; i++) {
                    String coord = startX + Integer.toString(k);
                    boolean occuped = isHit(coord);
                    if(occuped){
                        positionCheck = false;
                    }
                    k++;
                }
            }
        }

        else{
            positionCheck = false;
        }
        return positionCheck;
    }

    //Diplay grid area :
    public String displayGridShot(){
        System.out.println("Grid of your shots: ");
        char index = 'A';
        String line = "    "+ Character.toString(index);
        for (int i = 1; i < Battleship.getSizeMap(); i++) {
            index++;
            line += "  " + Character.toString(index);

        }
        line += "\n";
        char x = 'A';

        for (int i = 1; i<= Battleship.getSizeMap(); i++){
            if(i<10){
                line += " "+Integer.toString(i);
            }
            else{
                line += Integer.toString(i);
            }
            //Chiffre
            x = 'A';
            for(int y = 1; y <= Battleship.getSizeMap(); y++ ){
                String coord = x + Integer.toString(i);
                if (isShooted(coord)){
                    if(this.isHitSHot(coord)){
                        line += "  •";
                    }
                    else{
                        line += "  X";
                    }
                } else {
                    line += "   ";
                }
                x++;
            }

            line += "\n";
        }
        return line;
    }

    public String displayGridShip(){
        System.out.println("Grid of your ships: ");
        char index = 'A';
        String line = "    " + Character.toString(index);
        for (int i = 1; i < Battleship.getSizeMap(); i++) {
            index++;
            line += "  " + Character.toString(index);
        }

        line +="\n";
        char x = 'A';
        for (int i = 1; i<= Battleship.getSizeMap(); i++){
            if(i<10){
                line += " "+Integer.toString(i);
            }
            else{
                line += Integer.toString(i);
            }

            //Chiffre
            x = 'A';
            for(int y = 1; y <= Battleship.getSizeMap(); y++ ){
                String coord = x + Integer.toString(i);
                if (isHit(coord)) {
                    if(isDammagedShip(coord))
                        line += "  X";
                    else{
                        line += "  •";
                    }

                } else {
                    line += "   ";
                }
                x++;
            }

            line +="\n";
        }
        return line;
    }






}
