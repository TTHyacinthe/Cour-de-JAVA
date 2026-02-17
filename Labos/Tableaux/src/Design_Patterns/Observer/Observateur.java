package Design_Patterns.Observer;

public class Observateur implements IObserver {
    private IObserver observer;
    @Override
    public void notifier(){

    }
    public void inscrire(IObserver iObserver){
        this.observer = observer;
        observer.inscrire(this);

    }
    public void desinscrire(IObserver iObserver){
    }
}
