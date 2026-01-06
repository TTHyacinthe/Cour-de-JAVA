package interfaces;

import java.util.OptionalInt;

public interface ContratFilePile {
    void push(int element);
    OptionalInt pop();
    void clear();
    int size();
    boolean estpleine();
    boolean estVide();
}
