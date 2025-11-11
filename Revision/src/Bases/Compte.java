package Bases;

// L'encapsulation consiste à protéger les données d'un objet en les rendant innaccessibles directements depuis l'exterieur
public class Compte {
    private double solde; // protégé

    public Compte(double s){
        solde = s;
    }
    public double getSolde() {
        return solde;
    }
    public void setSolde(double montant) {
        solde += montant;
    }
}
