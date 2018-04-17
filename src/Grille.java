public class Grille {
    private static int size;
    protected static int[] grille[];


    public Grille(){
        grille = new int[10][10];
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
            }
        }
    }

    protected int coordConverter(char x){
        // Car valeur de A = 10 et on l'indice à 0
        int coordConverted = Character.getNumericValue(x) - 10;
        return coordConverted;
    }

    public void affichageGrille(){

        char index = 'A';
        char number = '1';
        String element = "  " + Character.toString(index);
        for (int i=0; i<=size; i++){
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);


        for (int i=0;i<= grille.length;i++){
            String ligne = Character.toString(number);
            for(int j=0; j<= grille[i].length; j++){
                ligne += " " + grille[i][j];
            }
            System.out.println(ligne);
            number++;
        }

    }

    public boolean coordControl(Coordonnee coord){
        boolean coordValide;
        if(coordConverter(coord.getX()) > this.size || coord.getY() > this.size ){
            coordValide = false;
        }
        else{
            coordValide = true;
        }
        return coordValide;
    }


}
