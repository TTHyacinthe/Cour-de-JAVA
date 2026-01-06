package factory;

import interfaces.IFilePile;
import liste.ListePile;
import tableau.TableauPile;

import java.util.Optional;

/**
 * Classe Pile utilisant le design pattern Factory.
 *
 * Cette classe masque les implémentations concrètes :
 * - TableauPile
 * - ListePile
 *
 * L'utilisateur de la classe n'a plus besoin de connaître
 * la structure interne utilisée (tableau ou liste).
 *
 * @param <T> type des éléments stockés dans la pile
 */
public class Pile<T> implements IFilePile<T> {

    /**
     * Référence vers l'implémentation réelle de la pile.
     * Elle peut être soit une pile basée sur tableau,
     * soit une pile basée sur liste chaînée.
     */
    private final IFilePile<T> implementation;

    /**
     * Constructeur de la pile.
     * Il choisit l'implémentation concrète en fonction
     * du type de structure demandé.
     *
     * @param type type de structure interne (TABLEAU ou LISTE)
     */
    public Pile(TypeStructure type) {
        switch (type) {
            case TABLEAU -> implementation = new TableauPile<>();
            case LISTE -> implementation = new ListePile<>();
            default -> throw new IllegalArgumentException(
                    "Type de structure non supporté"
            );
        }
    }

    /**
     * Ajoute un élément au sommet de la pile.
     *
     * @param element élément à ajouter
     */
    @Override
    public void push(T element) {
        implementation.push(element);
    }

    /**
     * Retire et retourne l'élément au sommet de la pile.
     *
     * @return un Optional contenant l'élément,
     * ou Optional.empty() si la pile est vide
     */
    @Override
    public Optional<T> pop() {
        return implementation.pop();
    }

    /**
     * Retourne le nombre d'éléments dans la pile.
     *
     * @return taille de la pile
     */
    @Override
    public int size() {
        return implementation.size();
    }

    /**
     * Vide complètement la pile.
     */
    @Override
    public void clear() {
        implementation.clear();
    }

    /**
     * Retourne une représentation textuelle de la pile.
     *
     * @return String représentant le contenu de la pile
     */
    @Override
    public String toString() {
        return implementation.toString();
    }
}
