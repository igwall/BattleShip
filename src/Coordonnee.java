import static java.lang.Character.isLetter;

public class Coordonnee {
    private char x;
    private int y;
    private boolean hit;
    private String value;


    public Coordonnee(String coord) {
        this.x = calcX(coord);
        this.y = calcY(coord);
        this.hit = false;
        this.value = this.x+Integer.toString(this.y);
    }

    private int calcY(String coord) {
        StringBuilder y = new StringBuilder(coord);
        String newCoord = "";
        for (int i = 0; i < coord.length(); i++) {
            if (isLetter(coord.charAt(i))) {
                y.deleteCharAt(i); //On retire la lettre de la coordonee
                newCoord = y.toString();
            }
        }
        int resultat = Integer.parseInt(newCoord);
        return resultat;
    }

    // Fonction de recuperation de la lettre de la coordonnee
    private char calcX(String coord) {
        //Par defaut, la valeur sera A.
        char x ='A'; //Valeur par defaut obligatoire...
        for (int i = 0; i < coord.length(); i++) {
            if (isLetter(coord.charAt(i))) {
                x = coord.charAt(i);
                x = Character.toUpperCase(x);
            }
        }
        return x;
    }



    public boolean equals(String value) {
        return this.getValue().equals(value);
    }

    public boolean equals(Coordonnee coord){
        return this.getValue().equals(coord.getValue());
    }

    public void setHit(){
        this.hit = true;
    }


    public char getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private String getValue(){
        return this.value;
    }

    public boolean getHit(){
        return this.hit;
    }

}
