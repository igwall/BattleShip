package fr.battleship;

import goncalves.lucas.Elements.Coordonnee;
import goncalves.lucas.Player.IA.IA;
import goncalves.lucas.Player.IA.IABeginner;
import goncalves.lucas.Player.IA.IAHardcore;
import goncalves.lucas.Player.IA.IAMedium;

import java.io.FileWriter;
import java.io.IOException;

public class TestIA {

    static int scoreIA1;
    static int scoreIA2;

    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("ai_proof.csv");
        IA iA1;
        IA iA2;

        System.out.println("==== ==== ==== ");
        iA1 = new IABeginner();
        iA2 = new IAMedium();
        System.out.println(" iA1 : Beginner vs iA2 : Medium\n Result:");
        String resultOfFigth = iaFighter(iA1, iA2);
        System.out.println(resultOfFigth);
        StringBuilder buffer = new StringBuilder();
        buffer.append("AI Name; score; AI Name2; score2\n"+ "AI Level Beginner;"+scoreIA1+";Level Medium;"+scoreIA2+"\n");

        System.out.println("==== ==== ==== ");
        iA1 = new IABeginner();
        iA2 = new IAHardcore();
        System.out.println(" iA1 : Beginner vs iA2 : Hardcore\n Result:");
        resultOfFigth = iaFighter(iA1, iA2);
        System.out.println(resultOfFigth);
        buffer.append("AI Level Beginner;"+scoreIA1+";Level Hardcore;"+scoreIA2+"\n");

        System.out.println("==== ==== ==== ");
        iA1 = new IAMedium();
        iA2 = new IAHardcore();
        System.out.println(" iA1 : Medium vs iA2 : Hardcore\n Result:");
        resultOfFigth = iaFighter(iA1, iA2);
        System.out.println(resultOfFigth);
        buffer.append("AI Level Medium;"+scoreIA1+";Level Hardcore;"+scoreIA2+"\n");
        file.write(buffer.toString());
        file.close();

    }


    private static String iaFighter(IA player1, IA player2){
        int scoreiA1 = 0;
        int scoreiA2 = 0;
        int nbShip = 5;
        System.out.println("========");
        for (int i = 0; i < 100; i++) {
            IA iA1;
            if (player1 instanceof IABeginner){
                iA1 = new IABeginner();
            }
            else if(player1 instanceof IAMedium){
                iA1 = new IAMedium();
            }
            else{
                iA1 = new IAHardcore();
            }

            iA1.createFleet();

            IA iA2;
            if (player2 instanceof IABeginner){
                iA2 = new IABeginner();
            }
            else if(player2 instanceof IAMedium){
                iA2 = new IAMedium();
            }
            else{
                iA2 = new IAHardcore();
            }

            iA2.createFleet();

            while (iA1.getScore() < nbShip && iA2.getScore() < nbShip) {

                String shot = iA1.getShot();
                boolean hit = iA2.isHit(shot);
                if(hit){
                    iA2.editShipHit(shot);
                    Coordonnee newShot = new Coordonnee(shot);
                    newShot.setHit();
                    iA1.addShot(newShot);
                    iA1.setScore(iA2.getCountDestroyed());
                } else {
                    Coordonnee newShot = new Coordonnee(shot);
                    iA1.addShot(newShot);
                }



                String shot2 = iA2.getShot();
                boolean hit2 = iA1.isHit(shot2);
                if (hit2) {
                    iA1.editShipHit(shot2);
                    Coordonnee newShot2 = new Coordonnee(shot2);
                    newShot2.setHit();
                    iA2.addShot(newShot2);
                    iA2.setScore(iA1.getCountDestroyed());
                } else {
                    Coordonnee newShot2 = new Coordonnee(shot2);
                    iA2.addShot(newShot2);
                }

            }
            if (iA1.getScore() == nbShip && iA2.getScore() < nbShip) {
                scoreiA1++;
            } else {
                scoreiA2++;
            }
        }
        setScoreIA1(scoreiA1);
        setScoreIA2(scoreiA2);
        return "Score de l'IA 1: "+scoreiA1+"\n Score de l'IA2: "+scoreiA2;

    }

    public static void setScoreIA1(int score){
        scoreIA1 = score;
    }

    public static void setScoreIA2(int score){
        scoreIA2 = score;
    }

}
