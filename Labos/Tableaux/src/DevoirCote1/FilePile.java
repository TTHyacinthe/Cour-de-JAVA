package DevoirCote1;

import java.util.OptionalInt;

/**
 * classe abstraite representant la base commune d'une pile et d'une file
 * on ne peut pas creer d'objet FilePile directement
 *
 * Author       : TAMO Hyacinthe
 * Date         : 27/10/2025
 * Version      : 1.0
 * IA           : Utiliser pour "String toString" et "OptionalInt"
 *
 */

public abstract class FilePile {
    protected int[] tableau;
    protected int taille;
    protected final int TAILLE_PAR_DEFAUT = 5;
    /**
     * constructeur avec une taille personnalisée ou une taille par defaut
     * @param capacite taille initiale du tableau (si <=0, on utilise la taille par défaut)
     */
    public FilePile(int capacite) {
        if (capacite > 0) {
            tableau = new int[capacite];
        }
        else {
            tableau = new int[TAILLE_PAR_DEFAUT];
        }
        taille = 0;
    }
    public FilePile() {
        tableau = new int[TAILLE_PAR_DEFAUT];
    }

    /**
     * Retire un élément de la structure et le retourne
     * Méthode à implementé dans les classes filles
     * @return OptionalInt contenant la valeur retirée, ou vide si la structure est vide
     */
     // OptionalInt permet de retourner une valeur null ou une valeur auquel on s'attend
    public abstract OptionalInt pop();

    /**
     * vide la structure
     */
    public void clear(){
        taille = 0;
    }

    /**
     * retourne le nombre d'éléments présents dans la structure
     * @return nombre d'éléments
     */
    public int size(){
        return taille;
    }

    /**
     * Formate le contenu du tableau sous forme de chaîne de caractères
     * @return représentation textuelle du tableau
     */
    @Override
    public String toString(){
        if (taille == 0) {
            return "[structure vide]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < taille; i++) {
            sb.append(tableau[i]);
            if (i < taille - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public abstract void push(int element);

}
