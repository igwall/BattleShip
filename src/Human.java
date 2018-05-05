import java.util.Scanner;
public class Human extends Player {

    public Human(String name, int tok){
        super(name,tok);
    }


    public String getCoord(){
        System.out.println("Entrez votre coordonnée de tir");
        Scanner cs = new Scanner(System.in);
        String value = cs.next();
        return value;
    }

}
