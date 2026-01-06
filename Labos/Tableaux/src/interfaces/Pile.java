package interfaces;

import java.util.OptionalInt;

public class Pile implements ContratFilePile{
    @Override
    public void push(int element) {

    }

    @Override
    public OptionalInt pop() {
        return OptionalInt.empty();
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean estpleine() {
        return false;
    }

    @Override
    public boolean estVide() {
        return false;
    }
}
