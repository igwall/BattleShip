public class GrilleBateau extends Grille {

    public GrilleBateau(){
        size = 10;
        grille = new int[10][10];
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                grille[x][y] = 0;
            }
        }
    }

    public boolean positionValide(Position position){
        boolean occupe = false;
        int i =0;
        while(i < position.getLength() && !occupe){
            occupe = getOccupeCoord(position.getCoordonnee(i));
            i++;
        }
        return !occupe;
    }


    public boolean getOccupeCoord(Coordonnee coord){
        boolean occupe;
        int x = coordConverter(coord.getX());
        System.out.println(coordConverter(coord.getX()));
        int y = coord.getY();
        System.out.println("valeur de grille x, y: "+Integer.toString(grille[x][y]));
        if (grille[x][y] == 1){
            occupe = true;
        }
        else{
            occupe = false;
        }
        return occupe;
    }


    public void updateGrille(Position position){
        boolean valide = true;
        for(int i = 0; i < position.getLength() ; i++){
            Coordonnee coord = position.getCoordonnee(i);
            int x = coordConverter(coord.getX());
            int y = coord.getY();
            this.grille[x][y] = 1;
        }
    }

    public void affichageGrilleBateau() {
        char index = 'A';
        int number = 1;
        System.out.println("Affichage de vos bateaux: ");
        String element = "    " + Character.toString(index);
        for (int i = 0; i < size; i++) {
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);


        for (int i = 1; i < grille.length; i++) {
            String ligne;
            if(number < 10) {ligne = " " + Integer.toString(number) + "  "; }

            else{ligne =Integer.toString(number) + "  ";}

            for (int j = 0; j < grille[i].length; j++) {
                if(grille[j][i] == 1){
                    ligne += "•  ";
                }
                else{
                    ligne += "   ";
                }
            }
            System.out.println(ligne);
            number++;
        }
    }



}
