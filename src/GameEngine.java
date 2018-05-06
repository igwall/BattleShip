import java.util.Scanner;
import static java.lang.Character.isLetter;

public class GameEngine {
        static private Player player1;
        static private Player player2;
        static private int[] capacity;
        static private int sizeMap;
        static private int nbShip;
        static Player currentPlayer;
        static Player oppositePlayer;

    public static void main(String[] args){

        System.out.println("Welcome on this edition on Battleship\n"+
                "-- made by Lucas Gonçalves\n"+"==== ==== ==== ==== ==== ====\n\n"+
                "Two playing mods are available : \n" +
                "Single mod, you will play versus Jarvis, our bot\n" +
                "Dual mod, fight against your friend\n"+
                "Wich mod would you like to play to ? (D pour Dual, S for Single");
        Scanner cs = new Scanner(System.in);
        String choice = cs.next();
        char letter = choice.charAt(0);
        letter = Character.toUpperCase(letter);
        if(letter == 'D'){
            typeGameDuo = true;
            System.out.println("Would you like to play with traditionnal rules ?");
            Scanner scanRules = new Scanner(System.in);
            String answer = scanRules.next();
            char letter2 = answer.charAt(0);
            if(letter2 == 'Y'){
                System.out.println("Player one, please type your name: ");
                Scanner scan = new Scanner(System.in);
                String name1= scan.next();

                System.out.println("Player two, please type your name: ");
                Scanner scan2 = new Scanner(System.in);
                String name2= scan2.next();
                player1 = new Human(name1);
                player2 = new Human(name2);
                nbShip = 5;
                sizeMap = 10;
            }

        }

        else if( letter == 'S'){
            System.out.println("Player one, please type your name: ");
            Scanner scan = new Scanner(System.in);
            String name= scan.next();
            player1 = new Human(name);
            player2 = new Bot();
        }
        else{
            System.out.println("Life is hard... Please make the good choice...");
        }

//==================== METTRE UN WHILE DE VERIFICATION

        currentPlayer = player1;
        for(int loop = 0; loop <= 1; loop ++){
            if (isHuman(currentPlayer)) {
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
                            coord1Check = controlInput(coord1);
                            switch (coord1Check) {
                                case 1:
                                    System.out.println("Trop de lettres dans votre saisie");
                                    break;
                                case 2:
                                    System.out.println("Vous avez inséré un caractère incorrect");
                                    break;

                                case 3:
                                    System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                    break;
                                default:
                                    System.out.println("La coordonnée entrée est valide.");
                            }

                        }

                        while (coord2Check != 0) {
                            System.out.println("Please input the second coord :");
                            Scanner inputCoord2 = new Scanner(System.in);
                            coord2 = inputCoord2.next();
                            coord2Check = controlInput(coord2);
                            switch (coord1Check) {
                                case 1:
                                    System.out.println("Trop de lettres dans votre saisie");
                                    break;
                                case 2:
                                    System.out.println("Vous avez inséré un caractère incorrect");
                                    break;

                                case 3:
                                    System.out.println("The input is not valid... try something like A1 - a1 - 1a - 1A");
                                    break;
                                default:
                                    System.out.println("La coordonnée entrée est valide.");
                            }
                        }

                        Position position = new Position(coord1, coord2);

                        positionCheck = positionControl(position, currentPlayer);
                        if (positionCheck) {
                            positionAvailable = !currentPlayer.isUsed(position);
                            if (positionAvailable) {
                                allCheck = true;
                                currentPlayer.editAvailableShip(position.getLength());
                            } else {
                                System.out.println("This position is used by another ship...");
                            }
                        } else {
                            System.out.println("Sorry, but the length of this ship is not available...");
                        }
                    }
                    Ship ship = new Ship(coord1, coord2);
                    currentPlayer.addShip(ship);
                    i++;
                }
            }
            else{

                    String coord1 = player2.getCoord();
                    String coord2 = player2.getCoord();
                    Ship ship = new Ship(coord1,coord2);
                }

            if(currentPlayer.equals(player1)) {
                currentPlayer = player2;
            }
            else {
                currentPlayer = player1;
            }

        }

        // FIN DE L'INITIALISATION

        System.out.println("All the ship are generated, let's begin ! ");
        currentPlayer = player1;
        oppositePlayer = player2;

        while(player1.getScore() < nbShip && player2.getScore() < nbShip){

            if(isHuman(currentPlayer)){
                System.out.println(currentPlayer.getName()+" it's your turn to play");
                System.out.println(displayGridShip(currentPlayer));
                System.out.println(displayGridShot(currentPlayer));
                System.out.println("Your score :" + Integer.toString(currentPlayer.getScore()));
                System.out.println("Please enter the coordinates of your shot :");
                int coordCheck = -1;
                String shot ="";
                while(coordCheck != 0) {
                    System.out.println("Please input the value of your shot :");
                    Scanner inputCoord = new Scanner(System.in);
                    shot = inputCoord.next();
                    coordCheck = controlInput(shot);
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
                        default:
                            System.out.println("Your input is correct.");
                    }
                }
                System.out.println(shot);
                boolean hit = oppositePlayer.isHit(shot);
                if(hit){
                    oppositePlayer.editShipHit(shot);
                    Coordonnee newShot = new Coordonnee(shot);
                    newShot.setHit();
                    System.out.println("Nice shot !");
                    currentPlayer.addShot(newShot);
                    currentPlayer.setScore(oppositePlayer.getCountDestroyed());
                }else{
                    Coordonnee newShot = new Coordonnee(shot);
                    currentPlayer.addShot(newShot);
                }


            }
            else{
                String shot = player2.getShot();
            }


            if(currentPlayer.equals(player1)) {
                currentPlayer = player2;
            }
            else {
                currentPlayer = player1;
            }
        }

        if(player1.getScore() == nbShip && player2.getScore() == nbShip){
            System.out.println("There no winner for this game...");
        }
        else if(player1.getScore() == nbShip && player2.getScore() < nbShip){
            System.out.println(player1.getName()+", win the game !");
        } else{
            System.out.println(player2.getName()+", win the game !");
        }
    }

        private static String displayGridShot(Player player){
            System.out.println("Grid of your shots: ");
            char index = 'A';
            String line = "  ";
            for (int i = 1; i < sizeMap; i++) {
                line += "  " + Character.toString(index);
                index++;
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
                        line += "  •";

                    } else {
                        line += "   ";
                    }
                    x++;
                }

                line +="\n";
            }
            return line;
        }

        private static int controlInput(String coord){
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
                else{
                    i++;
                }
            }

            if (correctInput){
                if(isOnGrid(coord)) {
                    coordCheck = 0;
                }
            }else{
                coordCheck = 3;
            }
            return coordCheck;
        }

        private static boolean isSpecialCharacter(Character c){
            return c.toString().matches("[^a-z A-Z0-9]");
        }

        private static boolean isOnGrid(String coord){
            Coordonnee coordToCheck = new Coordonnee(coord);
            int x = Character.getNumericValue(coordToCheck.getX())-9;
            int y = coordToCheck.getY();
            return (x <= sizeMap
                    && y <= sizeMap
                    && x > 0
                    && y > 0);
        }

        public static boolean positionControl(Position position, Player player){
            boolean lengthCheck;
            int length = position.getLength();
            int index = player.getToken();
            if(length > player.getMaxLengthShip()){
                lengthCheck = false;
            }
            else{
                if (player.isAvailableShipLength(length)){
                    lengthCheck = true;
                }
                else{
                    lengthCheck = false;
                }
            }
            return lengthCheck;
        }

        public static void updateCapacity(Position position,Player player){
            int length = position.getLength();
            player.editAvailableShip(length);
        }

        private static boolean isHuman(Player player){
        return player instanceof Human;
        }

    }
