package fr.igwall.Battleship;

import java.util.Random;

public abstract class Bot extends Player {
    public Bot(){
        super("Jarvis");
    }


    public void createFleet(){
        boolean positionAvailable;
        boolean positionCheck;
        String coord1 ="";
        String coord2="";

        for(int i=0; i < GameEngine.getNbShip();i++){
            boolean vertical = getRandomVertical();
            boolean allCheck = false;
            while(!allCheck) {
                if (vertical) {

                    char letter = getRandomX();
                    int number = getRandomY();

                    coord1 = letter + Integer.toString(number);
                    int size = 0;
                    while(!isAvailableShipLength(size)){
                        size++;

                    }
                    coord2 = letter + Integer.toString(number+size-1);

                } else {
                    char letter = getRandomX();
                    int number = getRandomY();
                    int size=0;
                    coord1 = letter + Integer.toString(number);

                    while(!isAvailableShipLength(size)){
                        size++;
                    }

                    for(int k =1; k<size;k++){
                        letter++;
                    }
                    coord2 = letter + Integer.toString(number);

                }

                boolean coord2control = Coordonnee.isOnGrid(coord2);
                if(coord2control) {
                    positionCheck = this.positionControl(coord1, coord2);
                    if (positionCheck) {
                        int length = Coordonnee.calcLength(coord1, coord2);

                        positionAvailable = this.isAvailableShipLength(length);
                        if (positionAvailable) {
                            editAvailableShip(length);
                            allCheck = true;
                        } else {
                            positionAvailable = false;
                            positionCheck = false;
                        }
                    } else {
                        positionAvailable = false;
                        positionCheck = false;
                    }
                }else{
                    positionAvailable = false;
                    positionCheck = false;
                }
            }
            Ship ship = new Ship(coord1,coord2);
            this.addShip(ship);
        }
    }

    protected int getRandomY(){
        Random rand = new Random();
        int  number = rand.nextInt(GameEngine.getSizeMap())+1;
        return number;
    }

    protected char getRandomX(){
        char letter = 'A';
        Random rand = new Random();
        int  number = rand.nextInt(GameEngine.getSizeMap());
        for( int i = 0; i< number; i++){
            letter++;
        }
        return letter;
    }

    private boolean getRandomVertical() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }




}
