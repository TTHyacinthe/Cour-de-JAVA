package liste;

import java.util.Optional;

/**
 * Implémentation d'une file avec une liste chaînée
 * @param <T> type des éléments
 */
public class ListeFile<T> extends StructureListe<T> {

    public ListeFile() {
        super();
    }

    @Override
    public void push(T element) {
        addLast(element);
    }

    @Override
    public Optional<T> pop() {
        return removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("File : ");
        ListeElement<T> current = tete;
        while (current != null) {
            sb.append(current.valeur).append(" ");
            current = current.suivant;
        }
        return sb.toString();
    }
}
