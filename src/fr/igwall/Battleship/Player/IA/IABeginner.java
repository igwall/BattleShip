package fr.igwall.Battleship.Player.IA;
import fr.igwall.Battleship.Player.IA.Bot;

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

