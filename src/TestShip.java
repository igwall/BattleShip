import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TestShip {
    public static void main(String[] args){
        Human joueur1 = new Human("Bill");
        Human joueur2 = new Human("Clone");

        Coordonnee coord = joueur1.getTir();
        Coordonnee coord2 = joueur1.getTir();
        Position position = new Position (coord, coord2);
        joueur1.displayInfos();

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
        joueur2.displayInfos();
    }
}
