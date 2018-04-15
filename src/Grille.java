public class Grille {
    private int size;
    private int[] grille[] = new int[size][size];


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
}
