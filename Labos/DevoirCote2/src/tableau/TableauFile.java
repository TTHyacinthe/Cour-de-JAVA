package tableau;

import java.util.Optional;

/**
 * File (FIFO) implémentée avec un tableau
 */
public class TableauFile<T> extends StructureTableau<T> {

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
        T element = get(0);

        for (int i = 1; i < size(); i++) {
            set(i - 1, get(i));
        }

        decSize();
        return Optional.of(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("File : ");
        for (int i = 0; i < size(); i++) {
            sb.append(get(i)).append(" ");
        }
        return sb.toString();
    }
}
