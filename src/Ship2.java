/*
// Le bateau possède une coordonnée X (Lettre + Chiffre)  && une coordonnée Y (Lettre + Chiffre)
public class Ship {

    //Déclaration des variables de la classe:
    private String startCoord, endCoord;
    int lenght = 0;
    private String[] coordBateau; // On y stockera toute les coordonnées des bateaux grâce à la fonction traitement
    private boolean vertical;


    public Ship(String startCoord, String endCoord){ // Constructeur d'un bateau
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.coordBateau = traitement(startCoord, endCoord);
    }


    // La fonction de traitement permet de récupérer un ensemble de coordonnées pour notre bateau
    private String[] traitement(String startCoord, String endCoord){

        // L'ensemble des coordonnées sont enregistrées de la forme "Lettre + Chiffre : B3, F3, etc..."

        // On s'assure que les coordonnées soient de la forme Letre+Chiffre
        char StartX = arrange(startCoord);
        String coord2 = arrange(endCoord);
        String[] tab;
        //Si les lettre sont identiques et que les chiffres sont différents
        if (coord1.charAt(0) == coord2.charAt(0) && coord1.charAt(1) != coord2.charAt(1)) {
            if (coord1.charAt(1) > coord2.charAt(1)) {
                tab = tableGenerator(coord2, coord1);
            } else if (coord2.charAt(1) > coord1.charAt(1)) {
                tab = tableGenerator(coord1, coord2);
            } else {
                System.out.println("Les coordonnées sont incorrectes, bateau d'une seule case");
            }
        }
        //Si les chiffres sont identiques et que les lettres sont différentes
        else if (coord1.charAt(0) != coord2.charAt(0) && coord1.charAt(1) == coord2.charAt(1)){
            if(coord1.charAt(0) > coord2.charAt(0)){

            }
        }



        //Si les chiffres sont identiques et que les lettres sont différentes
        else if (coord1.charAt(0) != coord2.charAt(0) && coord1.charAt(1) == coord2.charAt(1)){
            if(coord1.charAt(0) > coord2.charAt(0)){
                int j=0;
                int length = Character.getNumericValue(coord1.charAt(0)) - Character.getNumericValue(coord2.charAt(0)) +1 ;
                System.out.println(Integer.toString(lenght));
                tab = new String[length];
                for(int i=0; i<=tab.length; i++){
                    tab[j] = Integer.toString(i)+Character.toString(coord2.charAt(1));
                    System.out.println(tab[j]);
                    j++;
                }
            }else{
                int j=0;
                int length = Character.getNumericValue(coord2.charAt(0)) - Character.getNumericValue(coord1.charAt(0)) +1 ;
                System.out.println(Integer.toString(lenght));
                tab = new String[length];
                for(int i=0; i<=tab.length+1; i++) {
                    tab[j] = Integer.toString(i)+Character.toString(coord2.charAt(1));
                    j++;
                }
            }
            // Chiffres et lettres identiques, ou le bateau est en diagonale.
        }else{
            System.out.println("Coordonnées invalides");
            tab = new String[5];
            for(int i=0; i<=tab.length; i++) {
                tab[i] = "X";
            }
        }
        return tab;
    }


    // Fonction de récupération du chiffre de la coordonnée
    private char getX(String coord){
        return coord.charAt(1);
    }


    // Fonction de récupération de la lettre de la coordonnée
    private char getY(String coord){
        return coord.charAt(1);
    }

    // Vérifie si le bateau est touché ou pas
    public boolean isHit(String coord) {

        // On arrange ce qu'on veut récupérer
        coord = arrange(coord);
        boolean hit = false;
        if (this.vertical) {
            if (this.getX(startCoord) == coord.charAt(1)) {

                hit = ((this.getY(startCoord) <= coord.charAt(0)) && (this.getY(endCoord) >= coord.charAt(0)));
                System.out.println("Valeur de coord.chartAt(1) ="+Character.toString(coord.charAt(1)));
            } else {
                if (this.getY(startCoord) == coord.charAt(0)) {
                    hit = ((this.getX(startCoord) <= coord.charAt(1)) && (this.getX(endCoord) >= coord.charAt(1)));
                } else {
                    hit = false;
                }
            }
        }
        return hit;
    }


    public  void editShipCoord(char coord){
        int i = 0;
        boolean end = false;
        while(i < this.coordBateau.length || !end){
            if (coordBateau[i].equals(String.valueOf(coord))){
                coordBateau[i] = "X";
                end = true;
            }
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

    //Fonction qui adapte la coordonnée saisie au "normes" de notre implémentation ("Lettre + Chiffre")

    private String arrange(String coord){
        String coordValide;
        char coord0 = coord.charAt(0);
        char coord1 = coord.charAt(1);
        if(Character.getNumericValue(coord0) > 10  && Character.getNumericValue(coord1) <= 10 ){
            coordValide = coord;
            System.out.println(coordValide);
        }else if(Character.getNumericValue(coord1) > 10 && Character.getNumericValue(coord0) <= 10 ){
            coordValide = String.valueOf(coord1);
            coordValide += String.valueOf(coord0);
            System.out.println(coordValide);
        }else{
            System.out.println("La coordonnée que vous avez entré n'est pas correcte");
            coordValide = coord;
        }
        return coordValide;
    }

    // Zone des getters et des setters :

    //Cette fonction génère le tableau de coordonnées pour le bateau
    public String[] tableGenerator(String coord1, String coord2){
        int j=0;
        System.out.println(String.valueOf(coord1.charAt(1)));
        int length = (Character.getNumericValue(coord2.charAt(1)) - Character.getNumericValue(coord1.charAt(1))) +1 ;
        int k = Character.getNumericValue(coord1.charAt(1));
        String[] tab = new String[length];
        for(int i= 0; i<tab.length; i++){
            tab[i] = Character.toString(coord2.charAt(0))+Integer.toString(i);
            System.out.println(tab[i]);
            k++;
        }
        return tab;
    }



    public int getLenght() {
        int i =0;
        while(coordBateau[i] != null){
            i++;
        }
        return i;
    }

    public String printCoordBateau() {
        String tab ="[";
        for(int i=0; i<this.coordBateau.length; i++){
            tab = tab + coordBateau[i]+",";
        }
        tab+="]";
        return tab;
    }

}
*/