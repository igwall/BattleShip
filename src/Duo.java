import java.util.Scanner;

public class Duo {
    private Human joueur1;
    private Human joueur2;
    private Rules rules;

    public Duo(String namePlayer1, String namePlayer2, Rules rulesGame){
        joueur1 = new Human(namePlayer1,0);
        joueur2 = new Human(namePlayer2,1);
        rules = rulesGame;
    }




    public void newGame(){
        System.out.println(joueur1.getName()+", you will complete your army :");
        int i = 1;
        while(i <= rules.getNbShip()) {
            displayGridShip(joueur1);
            rules.getAvailableShip(joueur1);
            System.out.println("Informations for ship "+ Integer.toString(i));
            Ship ship = shipCreator(joueur1);
            joueur1.addShip(ship);
            i++;
        }

        System.out.println("joueur2.getToken() = " + joueur2.getToken());
        System.out.println(joueur2.getName()+", you will complete your army :");
        i = 1;
        while(i <= rules.getNbShip()) {
            displayGridShip(joueur2);
            rules.getAvailableShip(joueur2);
            System.out.println("Informations for ship "+ Integer.toString(i));
            Ship ship = shipCreator(joueur2);
            joueur2.addShip(ship);
            i++;
        }


        System.out.println("All the ship are generated, let's begin ! ");
        while(joueur1.getScore() < rules.getNbShip() && joueur2.getScore() < rules.getNbShip()){

            System.out.println(joueur1.getName()+" it's your turn to play");
            displayGridShip(joueur1);
            displayGridShot(joueur1);
            System.out.println("Your score :" + Integer.toString(joueur1.getScore()));
            System.out.println("Please enter the coordinates of your shot :");
            String shot = joueur1.getCoord();

            boolean hit = joueur2.isHit(shot);
            if(hit){
                joueur2.editShipHit(shot);
                Coordonnee newShot = new Coordonnee(shot);
                newShot.setHit();
                System.out.println("Nice shot !");
                joueur1.addShot(newShot);
                joueur1.setScore(joueur2.getCountDestroyed());
            }else{
                Coordonnee newShot = new Coordonnee(shot);
                joueur1.addShot(newShot);
            }

            System.out.println(joueur2.getName()+" it's your turn to play");
            displayGridShip(joueur2);
            displayGridShot(joueur2);
            System.out.println("Your score :" + Integer.toString(joueur2.getScore()));
            System.out.println("Please enter the coordinates of your shot :");
            shot = joueur2.getCoord();
            hit = joueur1.isHit(shot);
            if(hit){
                joueur1.editShipHit(shot);
                Coordonnee newShot = new Coordonnee(shot);
                newShot.setHit();
                System.out.println("Nice shot !");
                joueur2.addShot(newShot);
                joueur2.setScore(joueur1.getCountDestroyed());
            }else{
                Coordonnee newShot = new Coordonnee(shot);
                joueur2.addShot(newShot);
            }


        }

        if(joueur1.getScore() == rules.getNbShip() && joueur2.getScore() == rules.getNbShip()){
            System.out.println("Egalité parfaite");
        }
        else if(joueur1.getScore() == rules.getNbShip() && joueur2.getScore() < rules.getNbShip()){
            System.out.println(joueur1.getName()+", win the game !");
        }
        else{
            System.out.println("Bravo "+joueur2.getName()+", vous remportez cette partie !");
        }


    }



    public Ship shipCreator(Player player){
        boolean allCheck = false;
        String coord1 = "";
        String coord2 = "";

        while(!allCheck){
            boolean coord1Check = false;
            boolean coord2Check = false;
            boolean positionCheck;
            boolean positionAvailable = false;


            while(!coord1Check) {
                System.out.println("Please input the first coord :");
                Scanner inputCoord1 = new Scanner(System.in);
                coord1 = inputCoord1.next();
                coord1Check = rules.controlInput(coord1);
            }

            while(!coord2Check) {
                System.out.println("Please input the second coord :");
                Scanner inputCoord2 = new Scanner(System.in);
                coord2 = inputCoord2.next();
                coord2Check = rules.controlInput(coord2);
                System.out.println("coord2Check = " + coord2Check);
            }

            Position position = new Position(coord1,coord2);
             positionCheck = rules.positionControl(position, player);
            System.out.println("positionCheck = " + positionCheck);
            if(positionCheck){
                positionAvailable = !player.isUsed(position);
                if (positionAvailable){
                    allCheck = true;
                    rules.updateCapacity(position,player);

                }
                else{
                    System.out.println("This position is used by another ship...");
                }

            }
            else{
                System.out.println("Sorry, but the length of this ship is not available...");
            }
        }
        return new Ship(coord1, coord2);
    }

    private void displayGridShot(Player player){
        System.out.println("Grid of your shots: ");
        char index = 'A';
        String element = "    " + Character.toString(index);
        for (int i = 1; i < rules.getSizeMap(); i++) {
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);
        char x = 'A';

        for (int i = 1; i<= rules.getSizeMap(); i++){
            String line;
            if(i<10){
                line = " "+Integer.toString(i);
            }
            else{
                line = Integer.toString(i);
            }
            //Chiffre
            x = 'A';
            for(int y = 1; y <= rules.getSizeMap(); y++ ){
                String coord = x + Integer.toString(i);
                if (player.isShooted(coord)) {
                    if(player.isHitSHot(coord)){
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

            System.out.println(line);
        }
    }

    private void displayGridShip(Player player){
        System.out.println("Grid of your ships: ");
        char index = 'A';
        String element = "    " + Character.toString(index);
        for (int i = 1; i < rules.getSizeMap(); i++) {
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);
        char x = 'A';

        for (int i = 1; i<= rules.getSizeMap(); i++){
            String line;
            if(i<10){
               line = " "+Integer.toString(i);
            }
            else{
                line = Integer.toString(i);
            }

            //Chiffre
            x = 'A';
            for(int y = 1; y <= rules.getSizeMap(); y++ ){
                    String coord = x + Integer.toString(i);
                    if (player.isHit(coord)) {
                        line += "  •";
                    } else {
                        line += "   ";
                    }
                    x++;
            }

            System.out.println(line);
        }
    }









}
