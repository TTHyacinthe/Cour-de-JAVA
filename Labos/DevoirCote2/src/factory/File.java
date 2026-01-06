package factory;

import interfaces.IFilePile;
import liste.ListeFile;
import tableau.TableauFile;

import java.util.Optional;

/**
 * Classe File utilisant le design pattern Factory.
 *
 * Cette classe permet de manipuler une file
 * sans connaître l'implémentation concrète utilisée.
 *
 * Les implémentations possibles sont :
 * - TableauFile
 * - ListeFile
 *
 * @param <T> type des éléments stockés dans la file
 */
public class File<T> implements IFilePile<T> {

    /**
     * Implémentation réelle de la file.
     */
    private final IFilePile<T> implementation;

    /**
     * Constructeur de la file.
     * Il sélectionne l'implémentation concrète
     * en fonction du type de structure demandé.
     *
     * @param type type de structure interne (TABLEAU ou LISTE)
     */
    public File(TypeStructure type) {
        switch (type) {
            case TABLEAU -> implementation = new TableauFile<>();
            case LISTE -> implementation = new ListeFile<>();
            default -> throw new IllegalArgumentException(
                    "Type de structure non supporté"
            );
        }
    }

    /**
     * Ajoute un élément à la fin de la file.
     *
     * @param element élément à ajouter
     */
    @Override
    public void push(T element) {
        implementation.push(element);
    }

    /**
     * Retire et retourne le premier élément de la file.
     *
     * @return un Optional contenant l'élément,
     * ou Optional.empty() si la file est vide
     */
    @Override
    public Optional<T> pop() {
        return implementation.pop();
    }

    /**
     * Retourne le nombre d'éléments dans la file.
     *
     * @return taille de la file
     */
    @Override
    public int size() {
        return implementation.size();
    }

    /**
     * Vide complètement la file.
     */
    @Override
    public void clear() {
        implementation.clear();
    }

    /**
     * Retourne une représentation textuelle de la file.
     *
     * @return String représentant le contenu de la file
     */
    @Override
    public String toString() {
        return implementation.toString();
    }
}
