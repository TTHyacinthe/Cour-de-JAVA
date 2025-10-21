package Bases;

public class MaClasseFile {
    private int [] tableau = new int [3];
    private int nbElement = 0;
    private int pointeurPush = 0;
    private int pointeurPop = 0;
    private final int step = 2;

    public MaClasseFile(int tailleTableau){
        if (tailleTableau > 3){
            tableau = new int[tailleTableau];
        }
    }

    /**
     * cette methode permet d'ajouter une valeur dans la file  (si le tableau est plein on essaye d'abord de décaller vers
     * la gauche pour récuperer de la place et si pas possible on agrandit le tableau)
     * @param valeur valeur à ajouter
     */
    public void enfiler(int valeur){
        // on vérifie si on peut decaler ou si l'on doit agrandir
        if (nbElement == tableau.length){
            if (pointeurPop > 0){
                decalerVersGauche();
            }else {
                agrandirTableau();
            }
        }
        // ajout de la nouvelle valeur
        tableau[pointeurPush] = valeur;
        pointeurPush++;
        nbElement++;
    }

    /**
     * cette methode permet de retirer le premier élément de la file
     * @return true si un élément à été retiré avec succes et false si aucun élément n'a été retiré
     */
    public boolean defiler(){
        if (nbElement == 0){
            System.out.println("La file est vide !");
            return false;
        }
        int valeur = tableau[pointeurPop];
        System.out.println("Défilé : " + valeur);
        pointeurPop++;
        nbElement--;
        if  (nbElement == 0){
            pointeurPop = 0;
            pointeurPush = 0;
        }
        return true;
    }

    /**
     * cette methode décale tous les élements valides vers le début du tableauaprès le décalage :
     * pointeurPop = 0
     * pointeurPush = nbElement
     */
    private void decalerVersGauche(){
        int j = 0;
        for (int i = pointeurPop; i < pointeurPush; i++){
            tableau[j] = tableau[i];
            j++;
        }
        pointeurPush = nbElement;
        pointeurPop = 0;
    }

    /**
     * cette methode permet d'agrandit le tableau
     */
    private void agrandirTableau(){
        int newtaille = tableau.length + step;
        int[] newtableau = new int[newtaille];
        for (int i = 0; i < nbElement; i++){
            newtableau[i] = tableau[pointeurPop + i];
        }
        tableau = newtableau;
        pointeurPop = 0;
        pointeurPush = nbElement;
    }

    /**
     * cette methode affiche la file sur une seule ligne
     */
    public void afficherFile(){
        System.out.println("File : ");
        for (int i = pointeurPop; i < pointeurPush; i++){
            System.out.println(tableau[i] + " ");
        }
    }

    public static void main(String [] args){
        MaClasseFile file = new MaClasseFile(3);
        for (int i = 1; i <= 6; i++){
            file.enfiler(i * 10);
            file.afficherFile();
        }
        for (int i = 1; i <= 4; i++){
            file.defiler();
            file.afficherFile();
        }
    }
}
