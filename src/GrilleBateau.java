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
        boolean free = false;
        int i =0;
        while(i < position.getLength() && !free){
            free = !getOccupeCoord(position.getCoordonnee(i));
            i++;
        }
        return free;
    }


    public boolean getOccupeCoord(Coordonnee coord){
        boolean occupe;
        int x = coordConverter(coord.getX());
        System.out.println(coordConverter(coord.getX()));
        int y = coord.getY()-1;
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
            int y = coord.getY()-1;
            this.grille[x][y] = 1;
        }
    }

    public void updatePrintHit(Coordonnee coord){
        int x = coordConverter(coord.getX());
        int y = coord.getY()-1;
        this.grille[x][y] = -1;
    }

    public void printOwnShip() {
        char index = 'A';

        System.out.println("Affichage de vos bateaux: ");
        String element = "    " + Character.toString(index);
        for (int i = 1; i < size; i++) {
            index++;
            element += "  " + Character.toString(index);
        }
        System.out.println(element);

        int number = 1;
        for (int i = 0; i < grille.length; i++) {
            String ligne;
            if(number < 10) {ligne = " " + Integer.toString(number) + "  "; }

            else{ligne =Integer.toString(number) + "  ";}

            for (int j = 0; j < grille[i].length; j++) {
                if(grille[j][i] == 1){ ligne += "•  "; }
                else if(grille[j][i] == -1){ ligne += "X  "; }
                else{ligne+= "   ";}
            }
            System.out.println(ligne);
            number++;
        }
    }



}
