package fr.igwall.Battleship;

import static java.lang.Character.isLetter;

public class Coordonnee {
    private char x;
    private int y;
    private boolean hit;
    private String value;


    public Coordonnee(String coord) {
        this.x = calcX(coord);
        this.y = calcY(coord);
        this.hit = false;
        this.value = this.x+Integer.toString(this.y);
    }

    public static int calcY(String coord) {
        StringBuilder y = new StringBuilder(coord);
        String newCoord = "";
        for (int i = 0; i < coord.length(); i++) {
            if (isLetter(coord.charAt(i))) {
                y.deleteCharAt(i); //On retire la lettre de la coordonee
                newCoord = y.toString();
            }
        }
        int resultat = Integer.valueOf(newCoord);
        return resultat;
    }

    // Fonction de recuperation de la lettre de la coordonnee
    public static char calcX(String coord) {
        //Par defaut, la valeur sera A.
        char x ='A'; //Valeur par defaut obligatoire...
        for (int i = 0; i < coord.length(); i++) {
            if (isLetter(coord.charAt(i))) {
                x = coord.charAt(i);
                x = Character.toUpperCase(x);
            }
        }
        return x;
    }


    public static int controlInput(String coord){
        boolean correctInput = true;
        int coordCheck = -1;
        int countLetter = 0;
        int i = 0;

        while( i < coord.length() && correctInput){
            if(isLetter(coord.charAt(i))){
                countLetter +=1;
                if(countLetter > 1){
                    correctInput = false;
                    coordCheck = 1;
                }
                i++;
            }
            else if (isSpecialCharacter(coord.charAt(i))){
                coordCheck = 2;
                correctInput = false;
            }
            else if(countLetter ==0){
                correctInput = false;
            }
            else{
                i++;
            }
        }


        if (correctInput){
            if(isOnGrid(coord)) {
                coordCheck = 0;
            }
            else{
                coordCheck = 4;
            }
        }else{
            coordCheck = 3;
        }
        return coordCheck;
    }


    public static boolean isOnGrid(String coord){
        Coordonnee coordToCheck = new Coordonnee(coord);
        int x = Character.getNumericValue(coordToCheck.getX())-9;
        int y = coordToCheck.getY();
        return (x <= GameEngine.getSizeMap()
                && y <= GameEngine.getSizeMap()
                && x > 0
                && y > 0);
    }


    private static boolean isSpecialCharacter(Character c){
        return c.toString().matches("[^a-z A-Z0-9]");
    }

    public static int calcLength(String coord1, String coord2){
        boolean lengthCheck;
        char startX = Coordonnee.calcX(coord1);
        int startY = Coordonnee.calcY(coord1);
        char endX = Coordonnee.calcX(coord2);
        int endY = Coordonnee.calcY(coord2);
        int length = 0;
        if (startX != endX && startY == endY) {
            if (startX > endX) {
                length = (int) startX - (int) endX + 1;

            } else {
                length = (int) endX - (int) startX + 1;
            }
        }
        else{
            if(startY > endY){
                length = startY - endY + 1;
            }
            else{
                length = endY - startY + 1;
            }
        }
        return length;
    }

    public boolean equals(String coord){
        coord = traitment(coord);
        return this.getValue().equals(coord);

    }

public static String traitment(String coord){
        char x = calcX(coord);
        int y = calcY(coord);
        coord = x+Integer.toString(y);
        return coord;
}

    public void setHit(){
        this.hit = true;
    }

    public char getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getValue(){
        return this.value;
    }

    public boolean getHit(){
        return this.hit;
    }

}
