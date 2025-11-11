package DevoirCote1;

import java.util.OptionalInt;

public class File extends FilePile{
    public File(int capacite){
        super(capacite);
    }

    /**
     * Ajoute un élément à la fin de la fille
     * @param element élément à enfiler
     */
    @Override
    public void push(int element){
        if (taille == tableau.length){
            agrandir();
        }
        tableau[taille++] = element;
    }

    /**
     * Rétire le premier élément ajouté à la file
     * @return OptionalInt contenant la valeur défilée, ou vide si la file est vide
     */
    @Override
    public OptionalInt pop(){
        if (taille == 0){
            return OptionalInt.empty();
        }
        int valeur = tableau[0];
        // Decalage à gauche
        for (int i = 0; i < taille - 1; i++){
            tableau[i] = tableau[i + 1];
        }
        taille--;
        return OptionalInt.of(valeur);
    }

    /**
     * Agrandit le tableau si la file est pleine
     */
    private void agrandir(){
        int [] nouveau = new int[tableau.length + 3];
        System.arraycopy(tableau, 0, nouveau, 0, tableau.length);
        tableau = nouveau;
    }
}
