public class GrilleBateau extends Grille {

    public boolean positionValide(Position position){
        boolean valide = true;
        int i =0;
        while(i < position.getLength() && valide){
            valide = getOccupeCoord(position.getCoordonnee(i));
            i++;
        }
        return valide;
    }


    public boolean getOccupeCoord(Coordonnee coord){
        boolean occupe;
        int x = coordConverter(coord.getX());
        int y = coord.getY();
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



}
