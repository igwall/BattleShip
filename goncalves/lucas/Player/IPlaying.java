package goncalves.lucas.Player;

import goncalves.lucas.Elements.Coord;

public interface IPlaying {

    public void createFleet();

    public String getShot();

    public String getName();

    public int getScore();

    public String displayGridShip();

    public String displayGridShot();

    public void editShipHit(String shot);

    public boolean isHit(String shot);

    public void addShot(Coord coord);

    public void setScore(int score);

    public int getCountDestroyed();



}
