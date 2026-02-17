package Design_Patterns.Observer;

public class program {
    public static void main(String[] args) {
        var bourse = new Bourse();
        var observateur1 = new Observateur("OB 1");
        var observateur2 = new Observateur("OB 2");

        observateur1.inscrire(bourse);
    }
}
