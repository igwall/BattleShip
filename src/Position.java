public class Position {
    private Coordonnee[] emplacement;
    private boolean vertical;
    private int length;

    public Position(String coord1, String coord2) {
        Coordonnee startCoord = new Coordonnee(coord1);
        Coordonnee endCoord = new Coordonnee(coord2);
        int lenght = 0;
        // On decompose les coordonnees
        char startX = startCoord.getX();
        int startY = startCoord.getY();
        char endX = endCoord.getX();
        int endY = endCoord.getY();

        //Si les chiffres sont identiques et que les lettres sont differentes (horizontal)
        if (startX != endX && startY == endY) {
            this.vertical = false;
            if (startX > endX) {
                this.emplacement = tableGeneratorH(startX, endX, startY);

            } else {
                this.emplacement = tableGeneratorH(endX, startX, startY);
            }

        }

        //Si les chiffres sont differents et que les lettres sont identiques (vertical)
        else if (startY != endY && startX == endX) {
            this.vertical = true;
            if (startY > endY) {
                this.emplacement = tableGeneratorV(startY, endY, startX);
            } else {
                this.emplacement = tableGeneratorV(endY, startY, startX);
            }

        }


    }


    private Coordonnee[] tableGeneratorH(char x1, char x2, int y) {
        int j = 0;
        int length = (int) x1 - (int) x2 + 1;
        this.length = length;
        Coordonnee[] tab = new Coordonnee[length];

        // ON genère le tableau de coordonnees horizontales
        for (int i = 0; i < tab.length; i++) {
            String coord = x2 + Integer.toString(y);
            tab[i] = new Coordonnee(coord);
            x2++;
        }

        return tab;
    }


    private Coordonnee[] tableGeneratorV(int y1, int y2, char x) {
        int j = 0;
        int length = y1 - y2 + 1;
        this.length = length;
        int k = y2;
        Coordonnee[] tab = new Coordonnee[length];
        for (int i = 0; i < tab.length; i++) {
            String valeur = x + Integer.toString(k);
            Coordonnee coord = new Coordonnee(valeur);
            tab[i] = coord;
            k++;
        }
        return tab;
    }


    public boolean isUsed(Coordonnee value) {
        boolean in = false;
        int i = 0;
        while (i < emplacement.length && !in) {
            if (emplacement[i].equals(value)) {
                in = true;
            } else {
                in = false;
                i++;
            }
        }
        return in;
    }

    public boolean isHit(String value) {
        boolean hit = false;
        int i = 0;
        while (i < emplacement.length && !hit) {
            if (emplacement[i].equals(value)) {
                if(emplacement[i].getHit()){
                    hit = false;
                }
                else {
                    hit = true;
                }
            }
            else{
                i++;
            }

        }
        return hit;
    }

    public boolean allHit() {
        boolean allHit = true;
        int i = 0;
        while (i < emplacement.length && allHit) {
            if (emplacement[i].getHit()) {
                allHit = true;
            } else {
                allHit = false;
            }
            i++;
        }
        return allHit;
    }

    public void editHit(String shot){
        for(Coordonnee c : emplacement){
            if(c.equals(shot)){
                c.setHit();
            }
        }
    }

    public Coordonnee getCoordonnee(int x){
        return emplacement[x];
    }

    public int getLength() {
        return this.length;
    }

}