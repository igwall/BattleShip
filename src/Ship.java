import static java.lang.Character.isLetter;

// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)
public class Ship {

    //Déclaration des variables de la classe:
    private String startCoord, endCoord;
    private char startY, endY;
    private int startX, endX;
    int lenght = 0;
    private String[] coordBateau; // On y stockera toute les coordonnées des bateaux grâce à la fonction traitement
    private boolean vertical;



    public Ship(String startCoord, String endCoord){ // Constructeur d'un bateau
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.coordBateau = traitement(startCoord, endCoord);
    }


    // La fonction de traitement permet de récupérer un ensemble de coordonnées pour notre bateau
    private String[] traitement(String startCoord, String endCoord) {

        // L'ensemble des coordonnées sont enregistrées de la forme "Lettre + Chiffre : B3, F3, etc..."

        // On décompose les coordonnées
        startX = getX(startCoord);
        startY = getY(startCoord);
        endX = getX(endCoord);
        endY = getY(endCoord);
        String[] tab;

        //Si les lettre sont identiques et que les chiffres sont différents
        if (startX != endX && startY == endY) {
            if (this.startX > this.endX) {
                tab = tableGeneratorH(startX, endX, startY);
            } else if (endX > startX) {
                tab = tableGeneratorH(endX, startX, startY);
            } else {
                System.out.println("Les coordonnées sont incorrectes, bateau d'une seule case");
                tab = new String[5];
                vertical = false;
            }
        }
        //Si les chiffres sont identiques et que les lettres sont différentes
        else if (startY != endY && startX == endX) {
            if (startY > endY) {
                tab = tableGeneratorV(startY, endY, startX);
            } else if (endY > startY) {
                tab = tableGeneratorV(endY, startY, startX);
            } else {
                System.out.println("Les coordonnées sont incorrectes, bateau d'une seule case");
                tab = new String[5];
            }
            vertical = true;
        } else {
            System.out.println("Les coordonnées sont incorrectes, le bateau est en diagonale ou ne fait qu'une case");
            tab = new String[5];
        }
        return tab;
    }


    // Fonction de récupération du chiffre de la coordonnée
    private int getX(String coord){
        StringBuilder x = new StringBuilder(coord);
        String newCoord ="";
        for(int i = 0 ; i< coord.length(); i++){
            if (isLetter(coord.charAt(i))){
                x.deleteCharAt(i); //On retire la lettre de la coordonée
                newCoord = x.toString();
            }
        }
        int resultat = Integer.parseInt(newCoord);
        return resultat;
    }



    // Fonction de récupération de la lettre de la coordonnée
    private char getY(String coord){
        //Par défaut, la valeur sera A.
        char Y ='A';
        for(int i = 0 ; i< coord.length(); i++){
            if (isLetter(coord.charAt(i))){
                Y = coord.charAt(i);
            }
        }
        return Y;
    }

    // Vérifie si le bateau est touché ou pas
    public boolean isHit(String coord) {
        int xTir = getX(coord);
        char yTir = getY(coord);
        boolean hit;
        System.out.println(this.getStartX());
        if (!this.vertical) {

            if (this.startX > this.endX) {
                hit = (this.startX > xTir && xTir > this.endX);
                System.out.println(Integer.toString(this.startX)+","+Integer.toString(xTir)+","+Integer.toString(this.endX));
                System.out.println("Non vertical et Start > End");
            }else{
                hit = (this.getEndX() > xTir && xTir > this.getStartX());
                System.out.println(Integer.toString(this.endX)+","+Integer.toString(xTir)+","+Integer.toString(this.startX));
                System.out.println("Non vertical et End > Start");
            }
        }else{
            if (this.getStartY() > this.getEndY()) {
                hit = (this.getStartY() > yTir && yTir > this.getEndY());
            }else{
                hit = (this.getEndY() > yTir && yTir > this.getStartY());
            }
        }
        return hit;
    }


    public  void editShipCoord(String coord){
        int i = 0;
        boolean end = false;
        while(i < this.coordBateau.length || !end){
            if (coordBateau[i].equals(coord)){
                coordBateau[i] = "X";
                end = true;
            }
            i++;
        }
    }



    // Vérifie si le bateau est détruit ou pas
    // Si l'ensemble des string sont égal à X, il ne reste plus de case
    public boolean isDestroyed(){
        boolean destroyed = true;
        int i = 0;
        while(i< coordBateau.length && destroyed){
            if (coordBateau[i] != "X"){
                destroyed = false;
            }else{
                i++;
            }
        }
        return destroyed;
    }

    // Zone des getters et des setters :

    //Cette fonction génère le tableau de coordonnées pour le bateau
    // Ici x1 > x2 et y est la lettre identique;

    private String[] tableGeneratorH(int x1, int x2, char y){
        int j=0;
        int length = x1 - x2 +1 ;
        int k = x2;
        String[] tab = new String[length];
        for(int i= 0; i<tab.length; i++){
            tab[i] = y+Integer.toString(k);
            System.out.println(tab[i]);
            k++;
        }
        return tab;
    }

    // Ici, la lettre y1 est supérieure à y2 et le chiffre X est identique
    private String[] tableGeneratorV(char y1, char y2, int x){
        int j=0;
        int length = (int) y1 - (int) y2 +1 ;
        char k = y2;
        String[] tab = new String[length];
        for(int i= 0; i<tab.length; i++){
            tab[i] = k + Integer.toString(x);
            System.out.println(Integer.toString(x));
            System.out.println(tab[i]);
            k++;
        }
        return tab;
    }



    public int getLenght() {
        return this.lenght;
    }

    public String printCoordBateau() {
        String tab ="[";
        for(int i=0; i<this.coordBateau.length; i++){
            tab = tab + coordBateau[i]+",";
        }
        tab+="]";
        return tab;
    }

    private int getStartX(){
        return this.startX;
    }

    private int getEndX(){
        return this.endX;
    }

    private char getStartY(){
        return this.startY;
    }

    private char getEndY(){
        return this.endY;
    }

    public boolean isIn(String coord) { // Fonction qui vérifie que la coordonnée n'est pas déjà sur le beateu (éviter de poser au même endroit)
        int xTir = getX(coord);
        char yTir = getY(coord);
        boolean hit;
        System.out.println(this.getStartX());
        if (!this.vertical) {

            if (this.startX > this.endX) {
                hit = (this.startX > xTir && xTir > this.endX);
                System.out.println(Integer.toString(this.startX)+","+Integer.toString(xTir)+","+Integer.toString(this.endX));
                System.out.println("Non vertical et Start > End");
            }else{
                hit = (this.getEndX() > xTir && xTir > this.getStartX());
                System.out.println(Integer.toString(this.endX)+","+Integer.toString(xTir)+","+Integer.toString(this.startX));
                System.out.println("Non vertical et End > Start");
            }
        }else{
            if (this.getStartY() > this.getEndY()) {
                hit = (this.getStartY() > yTir && yTir > this.getEndY());
            }else{
                hit = (this.getEndY() > yTir && yTir > this.getStartY());
            }
        }
        return hit;
    }

}
