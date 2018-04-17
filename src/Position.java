public class Position {
    private Coordonnee[] emplacement;
    private boolean vertical;
    private int length;

    public Position(Coordonnee startCoord, Coordonnee endCoord){
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
                this.emplacement = tableGeneratorH(startX,endX, startY);
            } else if (endX > startX) {
                this.emplacement = tableGeneratorH(endX, startX, startY);
            } else {
                // SHOW HOW TO RAISE ERRORS
            }

        }

        //Si les chiffres sont dfferents et que les lettres sont identiques (vertical)
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


    private Coordonnee[] tableGeneratorH(char x1, char x2, int y){
        int j=0;
        int length = (int) x1 - (int) x2 +1 ;
        this.length = length;
        int k = y;
        Coordonnee[] tab = new Coordonnee[length];

        // ON genère le tableau de coordonnees horizontales
        for(int i= 0; i<tab.length; i++){
            String coord = x2 + Integer.toString(y);
            tab[i] = new Coordonnee(coord);
            k++;
        }
        return tab;
    }


    private Coordonnee[] tableGeneratorV(int y1, int y2, char x){
        int j=0;
        int length = y1 - y2 +1 ;
        this.length = length;
        int k = y2;
        Coordonnee[] tab = new Coordonnee[length];
        for(int i= 0; i<tab.length; i++){
            String valeur = x+Integer.toString(k);
            k++;
        }
        return tab;
    }

    public Coordonnee getCoordonnee(int x){
        return emplacement[x];
    }


    public boolean isIn(Coordonnee coord){
        boolean in = false;
        int i = 0;
        while( i<emplacement.length && !in){
            if(coord.getValue() == emplacement[i].getValue()){
                emplacement[i].setHit();
                in = true;
            }
            else{
                in = false;
            }
        }
        return in;
    }

    public boolean allHit(){
        boolean allHit = true;
        int i = 0;
        while(i < emplacement.length && allHit){
            if(emplacement[i].isHit()){
                allHit = true;
            }
            else{
                allHit = false;
            }
        }
        return allHit;
    }

    public int getLength(){
        return this.length;
    }


    }