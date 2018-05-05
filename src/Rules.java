import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Character.isLetter;

public class Rules {
        private int[][] capacity = new int[2][6];
        private int sizeMap;
        private int nbShip;

        public Rules(){
            this.sizeMap = 10;
            this.capacity[0] = new int[]{0,0,1,2,1,1};
            this.capacity[1] = new int[]{0,0,1,2,1,1};
            nbShip = 5;
        }

        public Rules(int size, int nbShip, int[] capacity){
            this.sizeMap = size;
            this.nbShip = nbShip;
            for(int i = 0; i<= 1;i++){
                for(int j = 0; j<capacity.length; j++){
                    this.capacity[i][j] = capacity [j];
                }
            }
        }

        public int getNbShip(){
            return this.nbShip;
        }


        public void getCapacity(){
            for(int j = 0; j<capacity.length; j++) {
                System.out.println("capacity.length = " + capacity.length);
                System.out.println("capacity[j].length = " + capacity[j].length);
                String line = Integer.toString(j);
                for (int i = 0; i < capacity[j].length; i++) {
                        line += Integer.toString(capacity[j][i] ) + ",";
                }
                System.out.println(line);
            }
        }


    public boolean controlInput(String coord){
        boolean correctInput = true;
        boolean coordCheck = true;
        int countLetter = 0;
        int i = 0;

        while( i < coord.length() && correctInput){
            if(isLetter(coord.charAt(i))){
                countLetter +=1;
                if(countLetter > 1){
                    correctInput = false;
                    System.out.println("There too many letters");
                }
                i++;
            }
            else if (isSpecialCharacter(coord.charAt(i))){
                System.out.println("There a special character");
                correctInput = false;
            }
            else{
                i++;
            }
        }

        if (correctInput){
            coordCheck = isOnGrid(coord);
        }else{
            System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
        }
        return coordCheck;
    }

    private boolean isSpecialCharacter(Character c)
    {
        return c.toString().matches("[^a-z A-Z0-9]");
    }

    private boolean isOnGrid(String coord){
        Coordonnee coordToCheck = new Coordonnee(coord);
        int x = Character.getNumericValue(coordToCheck.getX())-9;
        System.out.println("X = " + Integer.toString(x));
        int y = coordToCheck.getY();
        System.out.println("Y = " + Integer.toString(y));
        return (x <= this.sizeMap
                && y <= this.sizeMap
                && x > 0
                && y > 0);
    }


    public boolean positionControl(Position position, Player player){
        boolean lengthCheck;
        int length = position.getLength();
        int index = player.getToken();
        if(length > capacity[index].length){
            lengthCheck = false;
        }
        else{
            if (capacity[index][length] == 0){
                lengthCheck = false;
            }
            else{
                lengthCheck = true;
            }
        }
        return lengthCheck;
    }

    public void updateCapacity(Position position,Player player){
        int length = position.getLength();
        int index = player.getToken();
        capacity[index][length] -=1;
    }

    public void getAvailableShip(Player player){
            int index = player.getToken();
            System.out.println("You still have :");
            for (int i=0; i < capacity[index].length; i++ ){
                if(capacity[index][i] != 0) {
                    System.out.println(Integer.toString(capacity[index][i]) + " ship of size " + Integer.toString(i));
                }
            }
    }

    public int getSizeMap() {
        return sizeMap;
    }
}
