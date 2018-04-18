public class Position {
    private Coordonnee[] emplacement;
    private boolean vertical;
    private int length;

    public Position(Coordonnee startCoord, Coordonnee endCoord) {
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
            } else if (endX > startX) {
                this.emplacement = tableGeneratorH(endX, startX, startY);
            } else {
                // SHOW HOW TO RAISE ERRORS
            }

        }

        //Si les chiffres sont differents et que les lettres sont identiques (vertical)
        else if (startY != endY && startX == endX) {
            this.vertical = true;
            if (startY > endY) {
                this.emplacement = tableGeneratorV(startY, endY, startX);
            } else if (endY > startY) {
                this.emplacement = tableGeneratorV(endY, startY, startX);
            } else {
                // SHOW HOW TO RAISE ERRORS
            }

        }


    }


    private Coordonnee[] tableGeneratorH(char x1, char x2, int y) {
        System.out.println("Entrée tableau H");
        int j = 0;
        int length = (int) x1 - (int) x2 + 1;
        this.length = length;
        Coordonnee[] tab = new Coordonnee[length];

        // ON genère le tableau de coordonnees horizontales
        for (int i = 0; i < tab.length; i++) {
            String coord = x2 + Integer.toString(y);
            tab[i] = new Coordonnee(coord);
            System.out.println(tab[i].getValue());
            x2++;
        }

        return tab;
    }


    private Coordonnee[] tableGeneratorV(int y1, int y2, char x) {
        System.out.println("Entrée tableau V");
        int j = 0;
        int length = y1 - y2 + 1;
        this.length = length;
        int k = y2;
        Coordonnee[] tab = new Coordonnee[length];
        for (int i = 0; i < tab.length; i++) {
            String valeur = x + Integer.toString(k);
            System.out.println(valeur);
            Coordonnee coord = new Coordonnee(valeur);
            tab[i] = coord;
            k++;
        }
        return tab;
    }

    public boolean isIn(Coordonnee coord) {
        System.out.println("Entrée dans le isIn de Position");
        boolean in = false;
        int i = 0;
        while (i < emplacement.length && !in) {
            if (coord.getValue().equals(emplacement[i].getValue())) {
                emplacement[i].setHit();
                in = true;
                System.out.println("Touché");
            } else {
                in = false;
                i++;
            }

        }
        if (in) {
            System.out.println("Le bateau est touché");
        } else {
            System.out.println("Le bateau n'est pas touché");
        }
        System.out.println("Sortie du isIn de position");
        return in;
    }

    public boolean allHit() {
        boolean allHit = true;
        int i = 0;
        while (i < emplacement.length && allHit) {
            if (emplacement[i].isHit()) {
                allHit = true;
            } else {
                allHit = false;
            }
            i++;
        }
        return allHit;
    }

    public int getLength() {
        return this.length;
    }

}