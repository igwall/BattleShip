package fr.igwall.Battleship;

import fr.igwall.Battleship.Player.IA.IAMedium;

public class TestRandom {


    public static void main(String args[]){
        IAMedium player = new IAMedium();
        for(int i=0;i<100; i++){
           String shot =player.getShot();
           Coordonnee coord = new Coordonnee(shot);
           player.addShot(coord);
            System.out.println(shot);
        }
        System.out.println("les 100 coordonnées ont été print");

    }


}
