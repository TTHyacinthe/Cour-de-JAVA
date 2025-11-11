package Banque;

public class CompteEpargne extends CompteBancaire{
    private double tauxInteret;   // pourcentage annuel

    // Constructeur
    public CompteEpargne(String titulaire, double soldeInitial, double tauxInteret){
        super(titulaire, soldeInitial);    // appel du constructeur parent
        this.tauxInteret = tauxInteret;
    }
    public double getTauxInteret() {
        return tauxInteret;
    }
    public void setTauxInteret(double tauxInteret) {
        if (tauxInteret >=  0){
            this.tauxInteret = tauxInteret;
        }
    }

    // Appliquer les interêts sur le solde
    public void AppliquerInterets(){
        double interet = getSolde() * (tauxInteret / 100);
        deposer(interet); // on réutilise la méthode du parent
        System.out.println("Interêts de " + interet + "€ appliqués à " + getNumeroCompte());
    }

    // rédéfinition de la méthode d'affichage
    @Override
    public void afficherInfos() {
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return  "CompteEpargne [id = " + getNumeroCompte() +
                ", titulaire = " + getTitulaire() +
                ", solde = " + getSolde() +
                ", tauxInteret = " + tauxInteret + "%]";
    }
}
