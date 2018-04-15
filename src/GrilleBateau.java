public class GrilleBateau extends Grille {
    private int[] grille[];

    public GrilleBateau(){
        for(int x = 0; x < grille.length; x++){
            for(int y = 0; y < grille[x].length; y++){
                grille[x][y] = 0;
            }
        }
    }

    public boolean getOccupe(int x, int y){
        boolean occupe;
        if (this.grille[x][y] == 1){
            occupe = true;
        }
        else{
            occupe = false;
        }
        return occupe;
    }

}
