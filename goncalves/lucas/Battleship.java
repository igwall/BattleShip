package goncalves.lucas;

import goncalves.lucas.Elements.Coord;
import goncalves.lucas.Player.*;
import goncalves.lucas.Player.IA.*;

import java.util.Scanner;


public class Battleship {
    static private int sizeMap = 10;
    static private int nbShip = 5;

    public static void main(String[] args) throws InterruptedException {
        boolean goodChoice = false;
        while (!goodChoice) {
            System.out.println("Welcome on this edition on Battleship\n" +
                    "-- made by Lucas Gonçalves\n" + "==== ==== ==== ==== ==== ====\n\n" +
                    "2 mods are available : \n" +
                    "Single mod, you will play versus Jarvis, our iA\n" +
                    "Dual mod, fight against your friend\n" +
                    "Wich mod would you like to play to ? (D pour Dual, S for Single)");
            Scanner cs = new Scanner(System.in);
            String choice = cs.next();
            char letter = choice.charAt(0);
            letter = Character.toUpperCase(letter);

            if (letter == 'D') {
                goodChoice = true;
                System.out.println("Player one, please type your name: ");
                Scanner scan = new Scanner(System.in);
                String name1 = scan.next();
                System.out.println("Player two, please type your name: ");
                Scanner scan2 = new Scanner(System.in);
                String name2 = scan2.next();
                int ScoreTotalPlayer1 = 0;
                int ScoreTotalPlayer2 = 0;

                boolean stopGame = false;
                int countOfGames = 0;
                Human player1 = null;
                Human player2 = null;

                while(!stopGame) {

                    player1 = new Human(name1);
                    player2 = new Human(name2);
                    Human currentPlayer;
                    Human oppositePlayer;
                    Human playerBeginner;

                    if(countOfGames%2 ==0){
                        currentPlayer = player1;
                        oppositePlayer = player2;
                        playerBeginner = player1;
                    }
                    else{
                        currentPlayer = player2;
                        oppositePlayer = player1;
                        playerBeginner = player2;
                    }


                    currentPlayer.createFleet();
                    oppositePlayer.createFleet();

                    System.out.println("All the ship are generated, let's begin ! ");


                    while (player1.getScore() < nbShip && player2.getScore() < nbShip) {

                        System.out.println("\n\n== " + currentPlayer.getName() + " it's your turn to play");
                        System.out.println(currentPlayer.displayGridShip());
                        System.out.println(currentPlayer.displayGridShot());
                        System.out.println("\n == Your score :" + Integer.toString(currentPlayer.getScore()));
                        int coordCheck = -1;
                        String shot = currentPlayer.getShot();

                        boolean hit = oppositePlayer.isHit(shot);
                        if (hit) {
                            oppositePlayer.editShipHit(shot);
                            Coord newShot = new Coord(shot);
                            newShot.setHit();
                            System.out.println("You've hit a ship !");
                            currentPlayer.addShot(newShot);
                            currentPlayer.setScore(oppositePlayer.getCountDestroyed());
                        } else {
                            Coord newShot = new Coord(shot);
                            currentPlayer.addShot(newShot);
                            System.out.println("You've missed your shot :( ");
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
                        if(playerBeginner.equals(player1)){
                            ScoreTotalPlayer1 ++;
                        }else{
                            ScoreTotalPlayer2 ++;
                        }
                    } else {
                        System.out.println(player2.getName() + ", win the game !");
                        if(playerBeginner.equals(player2)){
                            ScoreTotalPlayer1 ++;
                        }else{
                            ScoreTotalPlayer2 ++;
                        }
                    }

                    System.out.println("Would you like to play one more time ? \n Type 'yes' to play \nType anything else to stop ");

                    Scanner input = new Scanner(System.in);
                    String answer = input.next();
                    if(answer.equals("yes")) {
                        stopGame = false;
                        countOfGames ++;
                        System.out.println("Total actuel des scores : \n"+
                                player1.getName()+": "+ScoreTotalPlayer1+ "\n" +
                                player2.getName()+": "+ScoreTotalPlayer2);
                    }
                    else {stopGame = true;}


                }

                System.out.println("Total des scores : \n"+
                        player1.getName()+": "+ScoreTotalPlayer1+ "\n" +
                        player2.getName()+": "+ScoreTotalPlayer2);


            } else if (letter == 'S') {
                System.out.println("Player one, please type your name: ");
                Scanner scan = new Scanner(System.in);
                String name = scan.next();
                IPlaying player1 = null;
                IPlaying player2 = null;
                int ScoreTotalPlayer1 = 0;
                int ScoreTotalPlayer2 = 0;
                int levelIA = 0;
                System.out.println("Now, you will choose the level of your IA\n" +
                "Type B for a Beginnner level (easy to beat)\n"+
                "Type M for a Medium level (little bit harder)\n"+
                "Type H for an Hardcore level (Good Luck)\n"+
                "What is your choice ? :");
                Scanner levelChoice = new Scanner(System.in);
                String level = levelChoice.next();

                if(level.equals("B")){levelIA = 0;}
                else if(level.equals("M")){levelIA = 1;}
                else{levelIA = 2;}

                goodChoice = true;

                boolean stopGame = false;
                int countOfGames = 0;

                while(!stopGame) {

                    player1 = new Human(name);
                    if(levelIA == 0) {
                    	player2 = new IABeginner();
                    }else if(levelIA == 1) {
                    	player2 = new IAMedium();
                    }else {
                    	player2 =  new IAHardcore();
                    }
                    IPlaying currentPlayer;
                    IPlaying oppositePlayer;
                    IPlaying playerBeginner;

                    if(countOfGames%2 ==0){
                        currentPlayer = player1;
                        oppositePlayer = player2;
                        playerBeginner = player1;
                    }
                    else{
                        currentPlayer = player2;
                        oppositePlayer = player1;
                        playerBeginner = player2;
                    }


                    currentPlayer.createFleet();
                    oppositePlayer.createFleet();


                    System.out.println("All the ship are generated, let's begin ! ");


                    while (currentPlayer.getScore() < nbShip && oppositePlayer.getScore() < nbShip) {

                        System.out.println("== " + currentPlayer.getName() + " it's your turn to play");
                        System.out.println(currentPlayer.displayGridShip());
                        System.out.println(currentPlayer.displayGridShot());
                        System.out.println("\n == Your score :" + Integer.toString(currentPlayer.getScore()));
                        String shot = currentPlayer.getShot();
                        boolean hit = oppositePlayer.isHit(shot);
                        if (hit) {
                            oppositePlayer.editShipHit(shot);
                            Coord newShot = new Coord(shot);
                            newShot.setHit();
                            System.out.println("You've hit a ship !");
                            currentPlayer.addShot(newShot);
                            currentPlayer.setScore(oppositePlayer.getCountDestroyed());
                        } else {
                            Coord newShot = new Coord(shot);
                            currentPlayer.addShot(newShot);
                            System.out.println("You've missed your shot :( ");
                        }
                        System.out.println("==== ==== ==== === ==== ==== ====");

                        if(currentPlayer.equals(player1)){
                            currentPlayer = player2;
                            oppositePlayer = player1;
                        }else{
                            currentPlayer = player1;
                            oppositePlayer = player2;
                        }

                    }

                    if (player1.getScore() == nbShip && player2.getScore() == nbShip) {
                        System.out.println("There no winner for this game...");
                    } else if (player1.getScore() == nbShip && player2.getScore() < nbShip) {
                        System.out.println(player1.getName() + ", win the game !");
                        ScoreTotalPlayer1 ++;
                    } else {
                        System.out.println(player2.getName() + ", win the game !");
                        ScoreTotalPlayer2++;
                    }

                    System.out.println("Would you like to play one more time ? \nType 'yes' to play \nType anything else to stop");

                    Scanner input = new Scanner(System.in);
                    String answer = input.next();
                    if(answer.equals("yes")) {
                        stopGame = false;
                        countOfGames ++;
                    }else{
                        stopGame = true;
                    }
                }

                System.out.println("Total des scores : \n"+
                                    player1.getName()+": "+ScoreTotalPlayer1+ "\n" +
                                    player2.getName()+": "+ScoreTotalPlayer2);

            } else {
                System.out.println("Life is easy. Why do we make it so hard ?");
            }
        }

    }
    
    public static int getSizeMap(){
        return sizeMap;
    }

    public static int getNbShip() {
        return nbShip;
    }
}


