import java.util.Scanner;

public class Duo {
    private Human joueur1;
    private Human joueur2;
    int nbShip;

    public Duo(String namePlayer1, String namePlayer2){
        joueur1 = new Human(namePlayer1);
        joueur2 = new Human(namePlayer2);
        nbShip = 2;
    }

    public void newGame(){
        System.out.println(joueur1.getName()+", vous allez saisir les coordonnées de vos bateaux :");
        int i = 1;
        while(i <= nbShip) {
            joueur1.displayShip();
            joueur1.shipGenerator(i);
            i++;
        }

        System.out.println(joueur2.getName()+", vous allez saisir les coordonnées de vos bateaux :");
        i = 1;
        while(i <= nbShip) {
            joueur2.displayShip();
            joueur2.shipGenerator(i);
            i++;
        }


        System.out.println("Tout les bateaux sont générés, la partie peut commencer");
        while(joueur1.getScore() < nbShip && joueur2.getScore() < nbShip){

            System.out.println("C'est au tour de "+joueur1.getName());
            joueur1.displayInfos();
            System.out.println("Entrez votre shot :");
            Coordonnee shot = joueur1.getTir();
            boolean hit = joueur2.isHit(shot);
            if(hit){
                System.out.println("Cible touchée");
                joueur1.addHit(shot);
                joueur1.setScore(joueur2.getCountDestroyed());
            }else{
                joueur1.addMiss(shot);
            }

            //Au tour du joueur 2

            System.out.println("C'est au tour de "+joueur2.getName());
            joueur2.displayInfos();
            System.out.println("Entrez votre shot :");
            Coordonnee shot2 = joueur2.getTir();
            hit = joueur1.isHit(shot2);
            if(hit){
                System.out.println("Cible touchée");
                joueur2.addHit(shot2);
                joueur2.setScore(joueur1.getCountDestroyed());
            }else{
                joueur2.addMiss(shot2);
            }

        }

        if(joueur1.getScore() == nbShip && joueur2.getScore() == nbShip){
            System.out.println("Egalité parfaite");
        }
        else if(joueur1.getScore() == nbShip && joueur2.getScore() < nbShip){
            System.out.println("Bravo joueur1");
        }
        else{
            System.out.println("Bravo joueur2");
        }


    }
}
