package Design_Patterns.Observer;

import java.util.HashSet;
import java.util.Set;

public class Bourse implements IObservable{
    private Set<IObserver> observers = new HashSet<>();
    public void inscrire(IObserver iObserver) {
        observers.add(iObserver);

    }
    public void desinscrire(IObserver iObserver) {
        observers.remove(iObserver);
    }
    private void notifier(){
        for (IObserver iObserver : observers) {
            iObserver.notifier();
        }
    }
    public void modifier(){
        notifier();
    }
}
