package goncalves.lucas.Elements;

import static java.lang.Character.isLetter;

// Le bateau possède une coordonnee X (Lettre + Chiffre)  && une coordonnee Y (Lettre + Chiffre)
public class Ship {
    private Coord[] position;
    private boolean destroyed = false;

    public Ship(String startCoord, String endCoord){
        destroyed = false;
        Coord coord1 = new Coord(startCoord);
        Coord coord2 = new Coord(endCoord);
        int lenght = 0;


        // On decompose les coordonnees
        char startX = coord1.getX();
        int startY = coord1.getY();
        char endX = coord2.getX();
        int endY = coord2.getY();

        //Si les chiffres sont identiques et que les lettres sont differentes (horizontal)
        if (startX != endX && startY == endY) {
            if (startX > endX) {
                this.position = tableGeneratorH(startX, endX, startY);

            } else {
                this.position = tableGeneratorH(endX, startX, startY);
            }

        }

        //Si les chiffres sont differents et que les lettres sont identiques (vertical)
        else {
            if (startY > endY) {
                this.position = tableGeneratorV(startY, endY, startX);
            } else {
                this.position = tableGeneratorV(endY, startY, startX);
            }
        }

    }


    private Coord[] tableGeneratorH(char x1, char x2, int y) {
        int j = 0;
        int length = (int) x1 - (int) x2 + 1;
        Coord[] tab = new Coord[length];

        // ON genère le tableau de coordonnees horizontales
        for (int i = 0; i < tab.length; i++) {
            String coord = x2 + Integer.toString(y);
            tab[i] = new Coord(coord);
            x2++;
        }

        return tab;
    }


    private Coord[] tableGeneratorV(int y1, int y2, char x) {
        int j = 0;
        int length = y1 - y2 + 1;
        int k = y2;
        Coord[] tab = new Coord[length];
        for (int i = 0; i < tab.length; i++) {
            String valeur = x + Integer.toString(k);
            Coord coord = new Coord(valeur);
            tab[i] = coord;
            k++;
        }
        return tab;
    }


    public boolean isHit(String value) {
        boolean hit = false;
        int i = 0;
        while (i < position.length && !hit) {
            if (position[i].equals(value)) {
                hit = true;
            } else {
                hit = false;
                i++;
            }
        }
        return hit;
    }

    public void editHit(String shot){
        for(Coord c : position){
            if(c.equals(shot)){
                c.setHit();
            }
        }
        boolean allHit = this.allHit();
        if(allHit){
            this.destroyed = true;
        }
    }

    public boolean allHit() {
        boolean allHit = true;
        int i = 0;
        while (i < position.length && allHit) {
            if (!position[i].getHit()) {
                allHit = false;
            }

            i++;
        }
        return allHit;
    }

    public boolean isDestroyed(){
        return this.destroyed;
    }

    public boolean isDammaged(String coord){
        boolean dammaged = false;
        for(Coord c : position){
            if(c.equals(coord)){
                if(c.getHit()){
                    dammaged = true;
                }
                else{
                    dammaged = false;
                }
            }
        }
        return dammaged;
    }

    public void getCoord(){
        String line ="";
        for(Coord c : position){
            line+= c.getValue()+", ";

        }
        System.out.println(line);
    }

}
