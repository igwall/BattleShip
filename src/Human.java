import java.util.Scanner;
public class Human extends Player {

    public Human(String name){
        super(name);
    }

    public Coordonnee getTir(){
        System.out.println("Entrez votre coordonnée de tir");
        Scanner cs = new Scanner(System.in);
        String value = cs.next();
        Coordonnee coord = new Coordonnee(value);
        return coord;
    }

}
