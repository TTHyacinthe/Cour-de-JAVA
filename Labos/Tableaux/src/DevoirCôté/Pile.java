package DevoirCôté;

import java.util.OptionalInt;

public class Pile extends FilePile{
    public Pile( int capacite){
        super(capacite);
    }

    /**
     * Ajoute un élément au sommet de la pile
     * @param element élément à empiler
     */
    @Override
    public void push(int element){
        if (taille == tableau.length){
            agrandir();
        }
        tableau[taille++] =  element;
    }

    /**
     * Rétire le dernier élément ajouté à la pile
     * @return OptionalInt contenant la valeur dépilée, ou vide si la pile est vide
     */
    @Override
    public OptionalInt pop(){
        if (taille == 0){
            return OptionalInt.empty();
        }
        return OptionalInt.of(tableau[--taille]);
    }

    /**
     * Agrandit le tableau si la pile est pleine
     */
    private void agrandir(){
        int [] nouveau = new int[tableau.length + 3];
        System.arraycopy(tableau, 0, nouveau, 0, tableau.length);
        tableau = nouveau;
    }
}
