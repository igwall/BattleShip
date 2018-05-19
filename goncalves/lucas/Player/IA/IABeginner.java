package goncalves.lucas.Player.IA;
import goncalves.lucas.Player.IPlaying;

public class IABeginner extends IA implements IPlaying {

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

