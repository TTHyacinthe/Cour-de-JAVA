package liste;

import interfaces.IFilePile;
import java.util.Optional;

/**
 * Classe abstraite pour une structure basée sur une liste chaînée
 * @param <T> type des éléments
 */
public abstract class StructureListe<T> implements IFilePile<T> {

    private static StructureListe<?> instance;

    protected ListeElement<T> tete;
    protected ListeElement<T> queue;
    protected int taille;

    protected StructureListe() {
        tete = null;
        queue = null;
        taille = 0;
    }

    @SuppressWarnings("unchecked")
    protected static <T> StructureListe<T> getInstance(StructureListe<T> structure) {
        if (instance == null) {
            instance = structure;
        }
        return (StructureListe<T>) instance;
    }

    protected void addLast(T element) {
        ListeElement<T> nouveau = new ListeElement<>(element);

        if (tete == null) {
            tete = queue = nouveau;
        } else {
            queue.suivant = nouveau;
            queue = nouveau;
        }
        taille++;
    }

    protected Optional<T> removeFirst() {
        if (tete == null) return Optional.empty();

        T value = tete.valeur;
        tete = tete.suivant;
        taille--;

        if (tete == null) queue = null;

        return Optional.of(value);
    }

    protected Optional<T> removeLast() {
        if (tete == null) return Optional.empty();
        if (tete == queue) {
            T value = tete.valeur;
            tete = queue = null;
            taille = 0;
            return Optional.of(value);
        }

        ListeElement<T> current = tete;
        while (current.suivant != queue) {
            current = current.suivant;
        }

        T value = queue.valeur;
        queue = current;
        queue.suivant = null;
        taille--;

        return Optional.of(value);
    }

    @Override
    public int size() {
        return taille;
    }

    @Override
    public void clear() {
        tete = null;
        queue = null;
        taille = 0;
    }
}
