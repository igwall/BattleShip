import java.util.Scanner;

public class TestShip {
    public static void main(String[] args){
        Scanner cs = new Scanner(System.in);
        System.out.println("Veuillez saisir une première coordonnée:");
        String startCoord = cs.next();
        Scanner cs1 = new Scanner(System.in);
        System.out.println("Veuillez saisir une seconde coordonnée: ");
        String endCoord = cs1.next();
        Ship Bateau1 = new Ship(startCoord, endCoord);
        System.out.println(Bateau1.printCoordBateau());
        String tir = "B5";
        if(Bateau1.isHit(tir)){
            System.out.println("Touché");
            Bateau1.editShipCoord(tir);
            System.out.println(Bateau1.printCoordBateau());
        }else{
            System.out.println("Pas touché");
        }


    }
}
