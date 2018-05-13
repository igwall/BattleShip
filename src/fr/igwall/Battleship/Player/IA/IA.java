package fr.igwall.Battleship.Player.IA;

import fr.igwall.Battleship.Coordonnee;

public interface IA {

    public String getShot();

    public int getScore();

    public boolean isHit(String shot);

    public void editShipHit(String shot);

    public void addShot(Coordonnee coord);

    public void setScore(int score);

    public int getCountDestroyed();

    public void createFleet();


}

