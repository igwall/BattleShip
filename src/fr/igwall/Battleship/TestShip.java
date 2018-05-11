package fr.igwall.Battleship;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TestShip {
/*
    public static void main(String[] args) {
        System.out.println("Lancement des tests unitaires\n\n");
        Rules rules = new Rules();

        System.out.println("===== ===== ===== ===== ===== ===== =====");
        System.out.println("Verification des paramètres de règles: \n");
        System.out.println("rules.getSizeMap() = " + Integer.toString(rules.getSizeMap()));
        System.out.println("rules.getNbShip() = " + Integer.toString(rules.getNbShip()));
        System.out.println("===== ===== ===== ===== ===== ===== =====");



        System.out.println("\n\n===== ===== ===== ===== ===== ===== =====");
        String coord = "A1";
        System.out.println("rules.controlInput(coord) = " + rules.controlInput(coord));
        String coord2 = "A5";
        System.out.println("rules.controlInput(coord2) = " + rules.controlInput(coord2));
        System.out.println("===== ===== ===== ===== ===== ===== =====");

        Position position = new Position(coord,coord2);
        System.out.println("position.getLength() = " + position.getLength());
        fr.igwall.Battleship.Human player1 = new fr.igwall.Battleship.Human("Bill",0);
        fr.igwall.Battleship.Human player2 = new fr.igwall.Battleship.Human("Jack",1);

        System.out.println("rules.positionControl(position, player1) = " + rules.positionControl(position, player1));
        rules.updateCapacity(position,player1);

        System.out.println("\n\n===== ===== ===== ===== ===== ===== =====");
        System.out.println("Controle des tirs");
        fr.igwall.Battleship.Ship ship = new fr.igwall.Battleship.Ship(coord, coord2);
        player1.addShip(ship);

        String coordShot = "A2" ;
        System.out.println("player1.isHit(\"A2\") = " + player1.isHit(coordShot));

        fr.igwall.Battleship.Coordonnee newShot = new fr.igwall.Battleship.Coordonnee(coordShot);
        newShot.setHit();
        player2.addShot(newShot);

        player1.editShipHit(coordShot);
        System.out.println("player1.isHit(\"A2\") = " + player1.isShooted(coordShot));
        System.out.println("===== ===== ===== ===== ===== ===== =====");

    }
    */
}