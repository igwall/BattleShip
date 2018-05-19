package goncalves.lucas.Player.IA;

import goncalves.lucas.Player.IPlaying;

public class IAMedium extends IA implements IPlaying {

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
