package goncalves.lucas.Player.IA;

import goncalves.lucas.Elements.Coordonnee;

public interface IiA {

    public String getShot();

    public int getScore();

    public boolean isHit(String shot);

    public void editShipHit(String shot);

    public void addShot(Coordonnee coord);

    public void setScore(int score);

    public int getCountDestroyed();

    public void createFleet();


}

