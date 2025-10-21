package Bases;

public class TestPile {
    public static void main(String[] args) {
        MaClassePile pile = new MaClassePile();

        for (int i = 1; i <= 5; i++) {
            int valeur = i * 10;
            pile.empilerUneValeur(valeur);
            System.out.println("Empilé : " + valeur);
        }
        for (int i = 1; i <= 10; i++) {
            boolean depile = pile.depilerUneValeur();
            System.out.println("Dépilage réussi ? : " + depile);
        }

    }
}
