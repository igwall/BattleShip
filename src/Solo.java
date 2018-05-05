import java.util.Scanner;

public class Solo {
    private Human joueur1;
    private Bot joueur2;
    int nbShip;

    public Solo(String namePlayer1){
        joueur1 = new Human(namePlayer1,0);
        joueur2 = new Bot();
        nbShip = 5;
    }

    public void newGame() {

    }
}
