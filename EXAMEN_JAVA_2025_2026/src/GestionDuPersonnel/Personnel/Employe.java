package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;

import java.time.LocalDate;
import java.time.Period;

public class Employe extends Personnels{
    private double salaireMensuel;
// Fonction de l'employé
    private String fonction;

    public Employe (int id,
                    String matricule,
                    String nom,
                    String prenom,
                    LocalDate dateEntree,
                    Contrat contrat,
                    double salaireMensuel,
                    String fonction) {
        super(id, matricule, nom, prenom, dateEntree, contrat);
        this.salaireMensuel = salaireMensuel;
        this.fonction = fonction;
    }

    /**
     * Règle métier :
     * Non payé si absences > 30 jours
     */
    @Override
    public boolean estPayable() {
        return calculerTotalAbsences() <= 30;
    }

    /**
     * Calcul du salaire
     */
    @Override
    public double calculerSalaire() {
        if (!estPayable()) {
            return 0;
        }
        return salaireMensuel;
    }

    /**
     * Ajout d'une formation avec contrainte métier :
     * max 3 jours de formation par an
     */
    @Override
    public void ajouterFormation(Formation formation) {

        int annee = formation.getAnnee();

        long total = calculerFormationAnnuelle(annee);

        // Vérifie la limite annuelle
        if (total + formation.getNombreJours() <= 3) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException(
                    "Limite de formation dépassée pour un employé (max 3 jours/an)"
            );
        }
    }

    /**
     * Vérifie si l'employé peut être promu
     * Règle : au moins 5 jours de formation
     */
    public boolean peutEtrePromu(int annee) {
        return calculerFormationAnnuelle(annee) >= 5;
    }
    public String getFonction() {
        return fonction;
    }
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public double getSalaireMensuel() {
        return salaireMensuel;
    }
    public void setSalaireMensuel(double salaireMensuel) {
        this.salaireMensuel = salaireMensuel;
    }

    @Override
    public int calculerJoursConges(){
        int anciennete = Period.between(dateEntree, LocalDate.now()).getYears();
        int joursSupplementaire = anciennete / 3;
        return 20 + joursSupplementaire;
    }

    @Override
    public double appliquerAugmentation(double salaireActuel){
        int anciennete = Period.between(dateEntree, LocalDate.now()).getYears();
        int tranches = anciennete / 2;
        return salaireActuel * (1 + 0.02 * tranches);
    }
}
