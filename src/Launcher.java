import java.util.Scanner;

public class Launcher {

    //Demander la taille de la grille !!!
    //Garder le score de tout les joueurs !
    //Ca peut être coolos
    public void main(){
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
            System.out.println("Player one, please type your name: ");
            Scanner scan = new Scanner(System.in);
            String name1= scan.next();

            System.out.println("Player two, please type your name: ");
            Scanner scan2 = new Scanner(System.in);
            String name2= scan2.next();
            Duo game = new Duo(name1,name2);
            game.newGame();
        }
        else if( letter == 'S'){
            System.out.println("Player one, please type your name: ");
            Scanner scan = new Scanner(System.in);
            String name= scan.next();
            Solo game = new Solo(name);
            game.newGame();

        }
    }


}
