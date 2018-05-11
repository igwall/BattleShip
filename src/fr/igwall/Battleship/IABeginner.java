package fr.igwall.Battleship;
import java.util.Random;
public class IABeginner extends Bot {

    public IABeginner(){
        super();
    }


    public String getShot(){
        char letter = getRandomX();
        int number = getRandomY();
        String coord = letter + Integer.toString(number);
        return coord;
    }

}

