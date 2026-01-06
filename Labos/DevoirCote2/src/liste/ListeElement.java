package liste;

/**
 * Représente un élément d'une liste chaînée
 * @param <T> type de la valeur stockée
 */
class ListeElement<T> {

    T valeur;
    ListeElement<T> suivant;

    ListeElement(T valeur) {
        this.valeur = valeur;
        this.suivant = null;
    }
}
