package tableau;

import java.util.Optional;

/**
 * Pile (LIFO) implémentée avec un tableau
 */
public class TableauPile<T> extends StructureTableau<T> {

    @Override
    public void push(T element) {
        if (internalSize() == size()) {
            grow();
        }
        set(size(), element);
        incSize();
    }

    @Override
    protected Optional<T> removeElement() {
        T element = get(size() - 1);
        decSize();
        return Optional.of(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pile : ");
        for (int i = size() - 1; i >= 0; i--) {
            sb.append(get(i)).append(" ");
        }
        return sb.toString();
    }
}
