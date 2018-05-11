package fr.igwall.Battleship;

public class IAMedium extends Bot {

    public IAMedium(){
        super();
    }

    public String getShot(){
        boolean correctShot = false;
        String coord = "";
        while(!correctShot){
            char letter = getRandomX();
            int number = getRandomY();
            coord = letter + Integer.toString(number);
            correctShot = !isShooted(coord);
        }
        return coord;
    }

}
