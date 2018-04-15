import static java.lang.Character.isLetter;

// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)
public class Ship {

    //Déclaration des variables de la classe:
    private String startCoord, endCoord;
    private String[] coordBateau; // On y stockera toute les coordonnées des bateaux grâce à la fonction traitement
    private boolean vertical;

    public Ship(String startCoord, String endCoord){ // Constructeur d'un bateau
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.coordBateau = traitement(startCoord, endCoord);
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

    // Met à jour le tableau de coordonnées du tableau
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



    // Zone des getters et des setters :

    public int getLenght() {
        return this.lenght;
    }

    //Print de controle des coordonnées générées par le créateur du tableau.
    public String printCoordBateau() {
        String tab ="[";
        for(int i=0; i<this.coordBateau.length; i++){
            tab = tab + coordBateau[i]+",";
        }
        tab+="]";
        return tab;
    }

}
