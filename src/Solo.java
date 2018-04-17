import java.util.Scanner;

public class Solo {
    private Human joueur1;
    private Bot joueur2;
    int nbShip;

    public Solo(String namePlayer1){
        joueur1 = new Human(namePlayer1);
        joueur2 = new Bot();
        nbShip = 5;
    }

    public void newGame(){
        System.out.println("Joueur 1, vous allez saisir les coordonnées de vos bateaux :");
        int i = 1;
        //On construit les bateaux du joueur 1 & 2
        // Possible car le bot agit sans saisie
        while(i <= nbShip) {
            joueur1.shipGenerator(i);
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
