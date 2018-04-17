import java.util.Scanner;

public class Duo {
    private Human joueur1;
    private Human joueur2;
    int nbShip;

    public Duo(String namePlayer1, String namePlayer2){
        joueur1 = new Human(namePlayer1);
        joueur2 = new Human(namePlayer2);
        nbShip = 5;
    }

    public void newGame(){
        System.out.println(joueur1.getName()+", vous allez saisir les coordonnées de vos bateaux :");
        int i = 1;
        while(i <= nbShip) {
            joueur1.shipGenerator(i);
            i++;
        }

        System.out.println(joueur2.getName()+", vous allez saisir les coordonnées de vos bateaux :");
        i = 1;
        while(i <= nbShip) {
            joueur2.shipGenerator(i);
            i++;
        }


        System.out.println("Tout les bateaux sont générés, la partie peut commencer");
        while(joueur1.getScore() < 5 && joueur2.getScore() < 5){

            System.out.println("C'est au tour de "+joueur1.getName());
            joueur1.displayInfos();
            System.out.println("Entrez votre shot :");
            Coordonnee shot = joueur1.getTir();
            if(joueur2.isHit(shot)){
                System.out.println("Cible touchée");
                joueur1.addHit(shot);
            }else{
                joueur1.addMiss(shot);
            }

            //Au tour du joueur 2

            System.out.println("C'est au tour de "+joueur2.getName());
            joueur2.displayInfos();
            System.out.println("Entrez votre shot :");
            Coordonnee shot2 = joueur2.getTir();
            if(joueur1.isHit(shot2)){
                System.out.println("Cible touchée");
                joueur2.addHit(shot2);
            }else{
                joueur2.addMiss(shot2);
            }

        }

        if(joueur1.getScore() == 5 && joueur2.getScore() == 5){
            System.out.println("Egalité parfaite, vous êtes aussi fort que Jarvis");
        }
        else if(joueur1.getScore() == 5 && joueur2.getScore() < 5){
            System.out.println("Vous avez battu notre Bot, bravo !");
        }
        else{
            System.out.println("Jarvis est très fort... Vous ferez mieux la prochaine fois :)");
        }


    }
}
