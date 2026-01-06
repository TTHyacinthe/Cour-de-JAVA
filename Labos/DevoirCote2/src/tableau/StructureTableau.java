package tableau;

import interfaces.IFilePile;
import java.util.Optional;

/**
 * Classe abstraite pour les structures basées sur un tableau dynamique
 */
public abstract class StructureTableau<T> implements IFilePile<T> {

    private T[] tableau;
    private int size;
    private int capacite;

    @SuppressWarnings("unchecked")
    public StructureTableau() {
        capacite = 4;
        tableau = (T[]) new Object[capacite];
        size = 0;
    }

    protected void grow() {
        capacite *= 2;
        T[] nouveau = (T[]) new Object[capacite];
        System.arraycopy(tableau, 0, nouveau, 0, size);
        tableau = nouveau;
    }

    protected void shrink() {
        if (capacite > 4 && size <= capacite / 4) {
            capacite /= 2;
            T[] nouveau = (T[]) new Object[capacite];
            System.arraycopy(tableau, 0, nouveau, 0, size);
            tableau = nouveau;
        }
    }

    protected T get(int index) {
        return tableau[index];
    }

    protected void set(int index, T element) {
        tableau[index] = element;
    }

    protected int internalSize() {
        return size;
    }

    protected void incSize() {
        size++;
    }

    protected void decSize() {
        size--;
        shrink();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        capacite = 4;
        tableau = (T[]) new Object[capacite];
    }

    @Override
    public Optional<T> pop() {
        if (size == 0) {
            return Optional.empty();
        }
        return removeElement();
    }

    protected abstract Optional<T> removeElement();
}
