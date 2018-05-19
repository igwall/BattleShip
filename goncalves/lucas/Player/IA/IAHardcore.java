package goncalves.lucas.Player.IA;

import goncalves.lucas.Elements.Coord;
import goncalves.lucas.Player.IPlaying;

public class IAHardcore extends IA implements IPlaying {
    private boolean locked = false;
    private int rigth = 0;
    private int left = 0;
    private int up = 0;
    private int down = 0;
    private int oldScore = 0;
    private char xInitialHit;
    private int yInitialHit;
    private char xLastShot;
    private int yLastShot;
    private Coord lastShot;

    public IAHardcore(){
        super();
    }




    public String getShot() {
        boolean lastShotHit;
        String newShot = "";

        if(shot.size() == 0){
            lastShotHit = false;
            newShot = randomShot();
        }
        else {
            lastShot = shot.get(shot.size() - 1);
            lastShotHit = lastShot.getHit();
            yLastShot = lastShot.getY();
            xLastShot = lastShot.getX();
            if (locked && lastShotHit) {
                if (rigth == 1) {
                    if (oldScore == getScore()) {
                        newShot = shotRigth();
                    } else {
                        newShot = randomShot();
                        resetLock();
                    }
                } else if (left == 1) {
                    if (oldScore == getScore()) {
                        newShot = shotLeft();
                    } else {
                        newShot = randomShot();
                        resetLock();
                    }
                } else if (up == 1) {
                    if (oldScore == getScore()) {
                        newShot = shotUp();
                    } else {
                        newShot = randomShot();
                        resetLock();
                    }
                } else {
                    if (oldScore == getScore()) {
                        newShot = shotDown();
                    } else {
                        newShot = randomShot();
                        resetLock();
                    }
                }

            } else if (locked && !lastShotHit) {

                if (rigth == 1) {
                    rigth = 2;
                    newShot = shotLeft();

                } else if (left == 1) {
                    left = 2;
                    newShot = shotUp();


                } else if (up == 1) {
                    up = 2;
                    newShot = shotDown();
                } else {
                    newShot = randomShot();
                    resetLock();
                }
            } else if (!locked && lastShotHit) {
                rigth = 1;
                xInitialHit = lastShot.getX();
                yInitialHit = lastShot.getY();
                newShot = shotRigth();

            } else {
                newShot = randomShot();
            }
        }
        return newShot;
    }


    private String randomShot(){
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


    private String shotRigth(){
        String coord = "";
        char letter = xLastShot;
        letter++;
        int number = yLastShot;
        coord = letter + Integer.toString(number);
        int controlInput = Coord.controlInput(coord);
        if(controlInput !=0){
            coord = shotLeft();
        }
        else{
            if(isShooted(coord)){
                coord = shotLeft();
            }
        }
        return coord;
    }

    private String shotLeft(){
        String coord;
        if(left == 0){
            rigth = 2;
            left =1;
            coord = "";
            char letter = xInitialHit;
            int number = yInitialHit;
            letter--;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = shotUp();
            }
            else{
                if(isShooted(coord)){
                    coord = shotUp();
                }
            }
            return coord;

        }
        else{
            coord = "";
            char letter = xLastShot;
            letter--;
            int number = yLastShot;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = shotUp();
            }
            else{
                if(isShooted(coord)){
                    coord = shotUp();
                }
            }
        }
        return coord;
    }

    private String shotUp(){
        String coord;
        if(up == 0){
            left = 2;
            up =1;
            coord = "";
            char letter = xInitialHit;
            int number = yInitialHit;
            number++;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = shotDown();
            }
            else{
                if(isShooted(coord)){
                    coord = shotDown();
                }
            }
            return coord;

        }
        else{
            coord = "";
            char letter = xLastShot;
            int number = yLastShot;
            number++;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = shotDown();
            }
            else{
                if(isShooted(coord)){
                    coord = shotDown();
                }
            }
            return coord;
        }
    }

    private String shotDown(){
        String coord;
        if(up == 0){
            up = 2;
            down = 1;
            coord = "";
            char letter = xInitialHit;
            int number = yInitialHit;
            number--;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = randomShot();
            }
            else{
                if(isShooted(coord)){
                    coord = randomShot();
                }
            }
            return coord;

        }
        else{
            coord = "";
            char letter = xLastShot;
            int number = yLastShot;
            number--;
            coord = letter + Integer.toString(number);
            int controlInput = Coord.controlInput(coord);
            if(controlInput !=0){
                coord = randomShot();
            }
            else{
                if(isShooted(coord)){
                    coord=randomShot();
                }
            }
            return coord;
        }
    }

    private void resetLock(){
        rigth = 0;
        left = 0;
        up = 0;
        down = 0;
        locked = false;
    }


}

