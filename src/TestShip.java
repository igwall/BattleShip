import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TestShip {
    public static void main(String[] args){
        GrilleBateau grilleBoat = new GrilleBateau();
        Coordonnee coord1 = new Coordonnee("B3");
        Coordonnee coord2 = new Coordonnee("B8");
        Position pos = new Position(coord1,coord2);

        Coordonnee coord3 = new Coordonnee("D2");
        Coordonnee coord4 = new Coordonnee("H2");
        Position pos2 = new Position(coord3,coord4);

        grilleBoat.updateGrille(pos);
        grilleBoat.updateGrille(pos2);
        grilleBoat.affichageGrilleBateau();

        GrilleTir grilleTir = new GrilleTir();
        grilleTir.affichageGrilleTir();

    }
}
