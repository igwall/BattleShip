package fr.battleship;

import goncalves.lucas.Battleship;
import goncalves.lucas.Elements.Coord;
import goncalves.lucas.Player.IPlaying;
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
        IPlaying iA1;
        IPlaying iA2;

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


    private static String iaFighter(IPlaying player1, IPlaying player2){
        int scoreiA1 = 0;
        int scoreiA2 = 0;
        int nbShip = Battleship.getNbShip();
        int CountOfGames = 0;
        IPlaying currentIA;
        IPlaying oppositeIA;
        for (int i = 0; i < 100; i++) {
        		IPlaying iA1;
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

            IPlaying iA2;
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

            if(CountOfGames%2 == 0){
                currentIA = iA1;
                oppositeIA = iA2;
            }else{
                currentIA = iA2;
                oppositeIA = iA1;
            }


            while (iA1.getScore() < nbShip && iA2.getScore() < nbShip) {

                String shot = currentIA.getShot();
                boolean hit = oppositeIA.isHit(shot);
                if(hit){
                    oppositeIA.editShipHit(shot);
                    Coord newShot = new Coord(shot);
                    newShot.setHit();
                    currentIA.addShot(newShot);
                    currentIA.setScore(oppositeIA.getCountDestroyed());
                } else {
                    Coord newShot = new Coord(shot);
                    currentIA.addShot(newShot);
                }

                if(currentIA.equals(iA1)){
                    currentIA = iA2;
                    oppositeIA = iA1;
                }else{
                    currentIA = iA1;
                    oppositeIA = iA2;
                }

            }
            if (iA1.getScore() == nbShip && iA2.getScore() < nbShip) {
                scoreiA1++;
            } else {
                scoreiA2++;
            }
            CountOfGames ++;
        }
        setScoreIA1(scoreiA1);
        setScoreIA2(scoreiA2);
        return "Score de l'IA 1: "+scoreiA1+"\nScore de l'IA2: "+scoreiA2;

    }

    public static void setScoreIA1(int score){
        scoreIA1 = score;
    }

    public static void setScoreIA2(int score){
        scoreIA2 = score;
    }

}
