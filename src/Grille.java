public class Grille {
    protected static int size;
    protected static int[] grille[];


    public Grille(){
        grille = new int[10][10];
        size = 10;
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                grille[x][y] = 0;
            }
        }
    }


    public Grille(int size){
        grille = new int[size][size];
        for(int x = 0; x < grille.length; x++){
            for(int y = 0; y < grille[x].length; y++){
                grille[x][y] = 0;
                this.size = size;
            }
        }
    }

    protected int coordConverter(char x){
        // Car valeur de A = 10 et on l'indice à 0
        int coordConverted = Character.getNumericValue(x) - 10;
        return coordConverted;
    }


    public boolean coordControl(Coordonnee coord){
        boolean coordValide;
        if(coordConverter(coord.getX()) >= this.size || coord.getY() >= this.size ){
            System.out.println("Taille de la grille: "+this.size);
            System.out.println("Value Y: "+coord.getY());
            System.out.println("Valeur X: "+coordConverter(coord.getX()));
            coordValide = false;
        }
        else{
            System.out.println("Taille de la grille: "+this.size);
            System.out.println("Value Y: "+coord.getY());
            System.out.println("Valeur X: "+coordConverter(coord.getX()));
            coordValide = true;
        }
        return coordValide;
    }


}
