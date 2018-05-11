package fr.igwall.Battleship;

import java.sql.SQLOutput;
import java.util.Scanner;
import static java.lang.Character.isLetter;

public class GameEngine {
    static private Player player1;
    static private Player player2;
    static private int sizeMap = 10;
    static private int nbShip = 5;

    public static void main(String[] args) throws InterruptedException {
        boolean goodChoice = false;
        while (!goodChoice) {
            System.out.println("Welcome on this edition on Battleship\n" +
                    "-- made by Lucas Gonçalves\n" + "==== ==== ==== ==== ==== ====\n\n" +
                    "3 mods are available : \n" +
                    "Single mod, you will play versus Jarvis, our bot\n" +
                    "Dual mod, fight against your friend\n" +
                    "iA mod, two iA will fight\n" +
                    "Wich mod would you like to play to ? (D pour Dual, S for Single, I fir iA vs iA)");
            Scanner cs = new Scanner(System.in);
            String choice = cs.next();
            char letter = choice.charAt(0);
            letter = Character.toUpperCase(letter);


            if (letter == 'D') {

                System.out.println("Player one, please type your name: ");
                Scanner scan = new Scanner(System.in);
                String name1 = scan.next();
                System.out.println("Player two, please type your name: ");
                Scanner scan2 = new Scanner(System.in);
                String name2 = scan2.next();
                Human player1 = new Human(name1);
                Human player2 = new Human(name2);
                goodChoice = true;

                Human currentPlayer = player1;

                for (int j = 0; j <= 1; j++) {

                    System.out.println(currentPlayer.getName() + ", you will complete your army :");
                    int i = 1;
                    while (i <= nbShip) {
                        System.out.println(displayGridShip(currentPlayer));
                        System.out.println(currentPlayer.getAvailableShip());

                        System.out.println("Informations for ship " + Integer.toString(i));
                        boolean allCheck = false;
                        String coord1 = "";
                        String coord2 = "";

                        while (!allCheck) {
                            int coord1Check = -1;
                            int coord2Check = -1;
                            boolean positionCheck;
                            boolean positionAvailable = false;

                            while (coord1Check != 0) {
                                System.out.println("Please input the first coord :");
                                Scanner inputCoord1 = new Scanner(System.in);
                                coord1 = inputCoord1.next();
                                coord1Check = Coordonnee.controlInput(coord1);
                                switch (coord1Check) {
                                    case 1:
                                        System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 2:
                                        System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                                        break;

                                    case 3:
                                        System.out.println("The input is not valid... Try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 4:
                                        System.out.println("Your input is not in this grid... ");
                                        break;
                                    default:
                                        System.out.println("Input checked");
                                }

                            }

                            while (coord2Check != 0) {
                                System.out.println("Please input the second coord :");
                                Scanner inputCoord2 = new Scanner(System.in);
                                coord2 = inputCoord2.next();
                                coord2Check = Coordonnee.controlInput(coord2);
                                switch (coord2Check) {
                                    case 1:
                                        System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 2:
                                        System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                                        break;

                                    case 3:
                                        System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 4:
                                        System.out.println("Your input is not in this grid... ");
                                        break;

                                    default:
                                        System.out.println("Input checked");
                                }
                            }

                            positionCheck = currentPlayer.positionControl(coord1, coord2);
                            if (positionCheck) {
                                int length = Coordonnee.calcLength(coord1, coord2);
                                positionAvailable = currentPlayer.isAvailableShipLength(length);
                                if (positionAvailable) {
                                    allCheck = true;
                                    currentPlayer.editAvailableShip(length);
                                } else {
                                    System.out.println("You can't place a ship like this...");
                                }
                            } else {
                                System.out.println("Sorry, but you can't put a ship there, there already a ship");
                            }
                        }
                        Ship ship = new Ship(coord1, coord2);
                        currentPlayer.addShip(ship);
                        i++;
                    }
                    currentPlayer = player2;
                }

                currentPlayer = player1;
                Human oppositePlayer = player2;
                System.out.println("All the ship are generated, let's begin ! ");
                Thread.sleep(2000);
                while (player1.getScore() < nbShip && player2.getScore() < nbShip) {

                    System.out.println("== " + currentPlayer.getName() + " it's your turn to play");
                    System.out.println(displayGridShip(currentPlayer));
                    System.out.println(displayGridShot(currentPlayer));
                    System.out.println("\n == Your score :" + Integer.toString(currentPlayer.getScore()));
                    int coordCheck = -1;
                    String shot = "";

                    boolean allcheck = false;
                    while (!allcheck) {
                        shot = "";
                        while (coordCheck != 0) {
                            System.out.println("Please input the value of your shot :");
                            Scanner inputCoord = new Scanner(System.in);
                            shot = inputCoord.next();
                            coordCheck = Coordonnee.controlInput(shot);
                            switch (coordCheck) {
                                case 1:
                                    System.out.println("Too many letters in your input, not correct");
                                    break;
                                case 2:
                                    System.out.println("There a special character... What could I do with it ?!");
                                    break;
                                case 3:
                                    System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                    break;
                                case 4:
                                    System.out.println("Your input is not in this grid... ");
                                    break;
                                default:
                                    System.out.println("Your shot is correct.");
                            }
                        }
                        if (currentPlayer.isShooted(shot)) {
                            allcheck = false;
                            System.out.println("You've already shot there... please enter an other shot: ");
                        } else {
                            allcheck = true;
                        }
                    }

                    boolean hit = oppositePlayer.isHit(shot);
                    if (hit) {
                        oppositePlayer.editShipHit(shot);
                        Coordonnee newShot = new Coordonnee(shot);
                        newShot.setHit();
                        System.out.println("You've hit a ship !");
                        currentPlayer.addShot(newShot);
                        currentPlayer.setScore(oppositePlayer.getCountDestroyed());
                        Thread.sleep(2000);
                    } else {
                        Coordonnee newShot = new Coordonnee(shot);
                        currentPlayer.addShot(newShot);
                        System.out.println("You've missed your shot :( ");
                        Thread.sleep(4000);
                    }
                    System.out.println("==== ==== ==== === ==== ==== ====");

                    if (currentPlayer.equals(player1)) {
                        currentPlayer = player2;
                        oppositePlayer = player1;
                    } else {
                        currentPlayer = player1;
                        oppositePlayer = player2;
                    }

                }

                if (player1.getScore() == nbShip && player2.getScore() == nbShip) {
                    System.out.println("There no winner for this game...");
                } else if (player1.getScore() == nbShip && player2.getScore() < nbShip) {
                    System.out.println(player1.getName() + ", win the game !");
                } else {
                    System.out.println(player2.getName() + ", win the game !");
                }


            } else if (letter == 'S') {
                System.out.println("Player one, please type your name: ");
                Scanner scan = new Scanner(System.in);
                String name = scan.next();
                Human player1 = new Human(name);
                IAMedium player2 = new IAMedium();
                goodChoice = true;

                //========= DEBUT SOLO


                    System.out.println(player1.getName() + ", you will complete your army :");
                    int i = 1;
                    while (i <= nbShip) {
                        System.out.println(displayGridShip(player1));
                        System.out.println(player2.getAvailableShip());

                        System.out.println("Informations for ship " + Integer.toString(i));
                        boolean allCheck = false;
                        String coord1 = "";
                        String coord2 = "";

                        while (!allCheck) {
                            int coord1Check = -1;
                            int coord2Check = -1;
                            boolean positionCheck;
                            boolean positionAvailable = false;

                            while (coord1Check != 0) {
                                System.out.println("Please input the first coord :");
                                Scanner inputCoord1 = new Scanner(System.in);
                                coord1 = inputCoord1.next();
                                coord1Check = Coordonnee.controlInput(coord1);
                                switch (coord1Check) {
                                    case 1:
                                        System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 2:
                                        System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                                        break;

                                    case 3:
                                        System.out.println("The input is not valid... Try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 4:
                                        System.out.println("Your input is not in this grid... ");
                                        break;
                                    default:
                                        System.out.println("Input checked");
                                }

                            }

                            while (coord2Check != 0) {
                                System.out.println("Please input the second coord :");
                                Scanner inputCoord2 = new Scanner(System.in);
                                coord2 = inputCoord2.next();
                                coord2Check = Coordonnee.controlInput(coord2);
                                switch (coord2Check) {
                                    case 1:
                                        System.out.println("Too many letters in your input... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 2:
                                        System.out.println("There a special character ! Try something like A1 - a1 - 1a - 1A");
                                        break;

                                    case 3:
                                        System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                        break;
                                    case 4:
                                        System.out.println("Your input is not in this grid... ");
                                        break;

                                    default:
                                        System.out.println("Input checked");
                                }
                            }

                            positionCheck = player1.positionControl(coord1, coord2);
                            if (positionCheck) {
                                int length = Coordonnee.calcLength(coord1, coord2);
                                positionAvailable = player1.isAvailableShipLength(length);
                                if (positionAvailable) {
                                    allCheck = true;
                                    player1.editAvailableShip(length);
                                } else {
                                    System.out.println("You can't place a ship like this...");
                                }
                            } else {
                                System.out.println("Sorry, but you can't put a ship there, there already a ship");
                            }
                        }
                        Ship ship = new Ship(coord1, coord2);
                        player1.addShip(ship);
                        i++;
                    }

                    player2.createFleet();

                System.out.println("All the ship are generated, let's begin ! ");
                Thread.sleep(2000);
                while (player1.getScore() < nbShip && player2.getScore() < nbShip) {

                    System.out.println("== " + player1.getName() + " it's your turn to play");
                    System.out.println(displayGridShip(player1));
                    System.out.println(displayGridShot(player1));
                    System.out.println("\n == Your score :" + Integer.toString(player1.getScore()));
                    int coordCheck = -1;
                    String shot = "";

                    boolean allcheck = false;
                    while (!allcheck) {
                        shot = "";
                        while (coordCheck != 0) {
                            System.out.println("Please input the value of your shot :");
                            Scanner inputCoord = new Scanner(System.in);
                            shot = inputCoord.next();
                            coordCheck = Coordonnee.controlInput(shot);
                            switch (coordCheck) {
                                case 1:
                                    System.out.println("Too many letters in your input, not correct");
                                    break;
                                case 2:
                                    System.out.println("There a special character... What could I do with it ?!");
                                    break;
                                case 3:
                                    System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                    break;
                                case 4:
                                    System.out.println("Your input is not in this grid... ");
                                    break;
                                default:
                                    System.out.println("Your shot is correct.");
                            }
                        }
                        if (player1.isShooted(shot)) {
                            allcheck = false;
                            System.out.println("You've already shot there... please enter an other shot: ");
                        } else {
                            allcheck = true;
                        }
                    }

                    boolean hit = player2.isHit(shot);
                    if (hit) {
                        player2.editShipHit(shot);
                        Coordonnee newShot = new Coordonnee(shot);
                        newShot.setHit();
                        System.out.println("You've hit a ship !");
                        player1.addShot(newShot);
                        player1.setScore(player2.getCountDestroyed());
                        Thread.sleep(2000);
                    } else {
                        Coordonnee newShot = new Coordonnee(shot);
                        player1.addShot(newShot);
                        System.out.println("You've missed your shot :( ");
                        Thread.sleep(4000);
                    }
                    System.out.println("==== ==== ==== === ==== ==== ====");

                    System.out.println(displayGridShip(player2));
                    System.out.println(displayGridShot(player2));
                    shot = player2.getShot();
                    hit = player1.isHit(shot);
                    if (hit) {
                        player1.editShipHit(shot);
                        Coordonnee newShot = new Coordonnee(shot);
                        newShot.setHit();
                        System.out.println("You've hit a ship !");
                        player2.addShot(newShot);
                        player2.setScore(player1.getCountDestroyed());
                        Thread.sleep(2000);
                    } else {
                        Coordonnee newShot = new Coordonnee(shot);
                        player2.addShot(newShot);
                        System.out.println("You've missed your shot :( ");
                        Thread.sleep(4000);
                    }



                }

                if (player1.getScore() == nbShip && player2.getScore() == nbShip) {
                    System.out.println("There no winner for this game...");
                } else if (player1.getScore() == nbShip && player2.getScore() < nbShip) {
                    System.out.println(player1.getName() + ", win the game !");
                } else {
                    System.out.println(player2.getName() + ", win the game !");
                }





            } else if (letter == 'I') {

                boolean correctInput = false;
                boolean correctInput2 = false;
                /*
                while (!correctInput) {
                    System.out.println("Please input the level of the first iA (B for Beginner, M for Medium and H for Hardcore");

                    Scanner scanIaOne = new Scanner(System.in);
                    String level1 = scanIaOne.next();
                    char letterLevel = level1.charAt(0);
                    if (letterLevel == 'B') {
                        iA1 = new IABeginner();
                        correctInput = true;
                    } else if (letterLevel == 'M') {
                        iA1 = new IAMedium();
                        correctInput = true;
                    } else if (letterLevel == 'H') {
                        iA1 = new IAHardcore();
                        correctInput = true;
                    } else {
                        System.out.println("Please input a letter like B, M or H");
                    }
                }


                while (!correctInput2) {
                    System.out.println("Please input the level of the second iA (B for Beginner, M for Medium and H for Hardcore");
                    Scanner scanIaTwo = new Scanner(System.in);
                    String level2 = scanIaTwo.next();
                    char letterLevel2 = level2.charAt(0);
                    if (letterLevel2 == 'B') {
                        iA2 = new IABeginner();
                        correctInput2 = true;
                    } else if (letterLevel2 == 'M') {
                        iA2 = new IAMedium();
                        correctInput2 = true;
                    } else if (letterLevel2 == 'H') {
                        iA2 = new IAHardcore();
                        correctInput2 = true;
                    } else {
                        System.out.println("Please input a letter like B, M or H");
                        correctInput2 = false;
                    }

                }
                */
                int scoreiA1 = 0;
                int scoreiA2 = 0;
                int pile = 0;
                int face = 0;
                for(int i =0; i<100;i++){

                    if(Math.random() < 0.5){
                        pile += 1;
                    }
                    else{
                        face +=1;
                    }

                }
                System.out.println("face = " + face);
                System.out.println("pile = " + pile);
                
                
                for(int j = 0;j<10; j++) {
                    System.out.println("========");
                    for (int i = 0; i < 100; i++) {
                        IAMedium iA1 = new IAMedium();
                        IABeginner iA2 = new IABeginner();

                        iA1.createFleet();
                        iA2.createFleet();

                        while (iA1.getScore() < nbShip && iA2.getScore() < nbShip) {

                            String shot = iA1.getShot();
                            boolean hit = iA2.isHit(shot);
                            if(hit){
                                iA2.editShipHit(shot);
                                Coordonnee newShot = new Coordonnee(shot);
                                newShot.setHit();
                                iA1.addShot(newShot);
                                iA1.setScore(iA2.getCountDestroyed());
                            } else {
                                Coordonnee newShot = new Coordonnee(shot);
                                iA1.addShot(newShot);
                            }

                            String shot2 = iA2.getShot();
                            boolean hit2 = iA1.isHit(shot);
                            if (hit2) {
                                iA1.editShipHit(shot);
                                Coordonnee newShot2 = new Coordonnee(shot2);
                                newShot2.setHit();
                                iA2.addShot(newShot2);
                                iA2.setScore(iA1.getCountDestroyed());
                            } else {
                                Coordonnee newShot2 = new Coordonnee(shot2);
                                iA2.addShot(newShot2);
                            }

                        }
                        if (iA1.getScore() == nbShip && iA2.getScore() < nbShip) {
                            scoreiA1++;
                        } else {
                            scoreiA2++;
                        }
                    }
                    System.out.println("Score de l'IA medium: "+ scoreiA1);
                    System.out.println("Score de l'IA beginner: "+ scoreiA2);
                }
            } else {
                System.out.println("Life is easy. Why do we make it so hard ?");
            }
        }

    }




        private static String displayGridShot(Player player){
            System.out.println("Grid of your shots: ");
            char index = 'A';
            String line = "    "+ Character.toString(index);
            for (int i = 1; i < sizeMap; i++) {
                index++;
                line += "  " + Character.toString(index);

            }
            line += "\n";
            char x = 'A';

            for (int i = 1; i<= sizeMap; i++){
                if(i<10){
                    line += " "+Integer.toString(i);
                }
                else{
                    line += Integer.toString(i);
                }
                //Chiffre
                x = 'A';
                for(int y = 1; y <= sizeMap; y++ ){
                    String coord = x + Integer.toString(i);
                    if (player.isShooted(coord)){
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

                line += "\n";
            }
            return line;
        }

        private static String displayGridShip(Player player){
            System.out.println("Grid of your ships: ");
            char index = 'A';
            String line = "    " + Character.toString(index);
            for (int i = 1; i < sizeMap; i++) {
                index++;
                line += "  " + Character.toString(index);
            }

            line +="\n";
            char x = 'A';
            for (int i = 1; i<= sizeMap; i++){
                if(i<10){
                    line += " "+Integer.toString(i);
                }
                else{
                    line += Integer.toString(i);
                }

                //Chiffre
                x = 'A';
                for(int y = 1; y <= sizeMap; y++ ){
                    String coord = x + Integer.toString(i);
                    if (player.isHit(coord)) {
                        if(player.isDammagedShip(coord))
                            line += "  X";
                        else{
                            line += "  •";
                        }

                    } else {
                        line += "   ";
                    }
                    x++;
                }

                line +="\n";
            }
            return line;
        }



        public static int getSizeMap(){
            return sizeMap;
        }

    public static int getNbShip() {
        return nbShip;
    }



}


