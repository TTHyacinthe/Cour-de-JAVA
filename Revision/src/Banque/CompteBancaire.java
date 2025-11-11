package Banque;

public class CompteBancaire {

    // Attributs (encapsulation)
    private final String numeroCompte;    // identifiant unique, immuable
    private String titulaire;
    private double solde;
    private static int compteur = 0;  // compteur partagé (static)

    // Constructeur
    public CompteBancaire(String titulaire, double soldeInitial) {
        compteur++;
        this.numeroCompte = "C-" + compteur;    // génère un identifiant unique
        this.titulaire = titulaire;
        if (soldeInitial < 0) {
            this.solde = 0;                     // ne pas avoir de solde négatif
        } else {
            this.solde = soldeInitial;
        }
    }

    // Getters et Setters
    public String getNumeroCompte() {
        return numeroCompte;
    }
    public String getTitulaire() {
        return titulaire;
    }
    public void setTitulaire(String titulaire) {
        if (titulaire != null && !titulaire.isBlank()) {
            this.titulaire = titulaire;
        }
    }
    public double getSolde() {
        return solde;
    }

    // Méthodes de gestion
    public void deposer (double montant) {
        if (montant > 0) {
            solde += montant;
            System.out.println(montant + " € déposés sur le compte " + numeroCompte);
        }else {
            System.out.println("Montant invalide pour le depôt ! ");
        }
    }
    public boolean retirer (double montant) {
        if (montant <= 0) {
            System.out.println("Montant invalide pour le retirer ! ");
            return false;
        }
        if (montant > solde) {
            System.out.println("Retrait impossible : solde insuffisant pour le compte " +  numeroCompte);
            return false;
        }
        solde -= montant;
        System.out.println(montant + " € rétirés du compte " + numeroCompte);
        return true;
    }
    public void afficherInfos() {
        System.out.println(toString());
    }

    // Redefinition de tostring()
    @Override
    public String toString() {
        return "  compte [ id = " + numeroCompte +
               ", titulaire = " +  titulaire +
               ", solde = " + solde + " ]";
    }

    // Méthode static
    public static int getCompteur() {
        return compteur;
    }
}
