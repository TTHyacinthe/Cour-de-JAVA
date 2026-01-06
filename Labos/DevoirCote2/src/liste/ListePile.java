package liste;

import java.util.Optional;

/**
 * Implémentation d'une pile avec une liste chaînée
 * @param <T> type des éléments
 */
public class ListePile<T> extends StructureListe<T> {

    public ListePile() {
        super();
    }

    @Override
    public void push(T element) {
        addLast(element);
    }

    @Override
    public Optional<T> pop() {
        return removeLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pile : ");
        ListeElement<T> current = tete;
        while (current != null) {
            sb.append(current.valeur).append(" ");
            current = current.suivant;
        }
        return sb.toString();
    }
}
