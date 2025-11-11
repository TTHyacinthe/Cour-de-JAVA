package Banque;

public class TestCompteBancaireEpargne {
    public static void main(String[] args) {
        // Création de comptes
        CompteBancaire c1 = new CompteBancaire("Alice", 500);
        CompteBancaire c2 = new CompteBancaire("Bob", 100);
        CompteEpargne  e1 = new CompteEpargne ("Carlos", 775, 5);

        c1.deposer(1000);
        c1.retirer(200);
        c2.retirer(500);
        e1.AppliquerInterets();

        c1.afficherInfos();
        c2.afficherInfos();
        e1.afficherInfos();
        System.out.println("Nombre total de comptes crées : " + CompteBancaire.getCompteur());
    }
}
