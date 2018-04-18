public class GrilleTir extends Grille {

    public void affichageGrilleTir() {
        char index = 'A';
        int number = 1;
        System.out.println("Affichage de vos tirs: ");
        String element = "    " + Character.toString(index);
        for (int i = 0; i < size-1; i++) {
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);


        for (int i = 0; i < grille.length; i++) {
            String ligne;
            if(number < 10) {ligne = " " + Integer.toString(number) + "  "; }

            else{ligne =Integer.toString(number) + "  ";}

            for (int j = 0; j < grille[i].length; j++) {
                if(grille[j][i] == 1){
                    ligne += "X  ";
                }
                else if(grille[j][i] == 0){
                    ligne += "•  ";
                }
                else{
                    ligne += "O  ";
                }
            }
            System.out.println(ligne);
            number++;
        }
    }

    public void addHit(Coordonnee coord){
        int x = coordConverter(coord.getX());
        int y = coord.getY()-1;
        grille[x][y] = 1;
    }

    public void miss(Coordonnee coord){
        int x = coordConverter(coord.getX());
        int y = coord.getY()-1;
        grille[x][y] = -1;
    }

}
