package Bases;

/**
 *classe pour la gestion d'une pile
 * Auteur : Hyacinthe
 * Date : 14/10/2025
 * IA :
 */
public class MaClassePile {
    private int [] tableau = new int [2];
    private int taille = 0;
    private final int step = 2;

    /**
     * cette methode permet d'ajouter une valeur dans la pile
     * @param valeur valeur à empiler
     */
    public void empilerUneValeur(int valeur){
        if(taille == tableau.length){
            int[] nouveauTableau = new int[tableau.length + step];
            for(int i = 0; i < tableau.length; i++){
                nouveauTableau[i] = tableau[i];
            }
            tableau = nouveauTableau;

        }
        tableau[taille++] = valeur;
    }

    /**
     *
     * @return true si la valeur est ajouter et false sinon
     */
    public boolean depilerUneValeur() {
        if (taille > 0) {
            System.out.println(tableau[--taille]);
            if (taille < tableau.length / 2) {
                int nouvelleTaille = (int) (tableau.length * 0.75 );
                if (nouvelleTaille < 2) {
                    nouvelleTaille = 2;
                }
                int[] nouvelleTableau = new int[nouvelleTaille];
                for(int i = 0; i < taille; i++){
                    nouvelleTableau[i] = tableau[i];
                }
                tableau = nouvelleTableau;

            }
            return true;

        }else {
            System.out.println("La pile est vide !");
            return false;
        }
    }

}
