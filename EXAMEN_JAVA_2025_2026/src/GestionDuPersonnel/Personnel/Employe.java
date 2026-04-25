package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Cette représente un employé de l'entreprise
 */

public class Employe extends Personnels {

    private double salaireMensuel;
    private String fonction;

    public Employe(
                   int id,
                   String matricule,
                   String nom,
                   String prenom,
                   LocalDate dateEntree,
                   Contrat contrat,
                   double salaireMensuel,
                   String fonction){
        super(id,matricule,nom,prenom,dateEntree,contrat, FrequencePaiement.MENSUEL);
        this.salaireMensuel = salaireMensuel;
        this.fonction = fonction;
    }

    @Override
    public String getFonction() {
        return "Employé";
    }
    @Override
    public double getBareme() {
        return salaireMensuel;
    }


    // Employé payable si ses absences <= 30 jours
    @Override
    public boolean estPayable(){
        return calculerTotalAbsences() <= 30;
    }
    // calcul du salaire de l'employé
    @Override
    public double calculerSalaire(){
        if (! estPayable() ) return 0;
        return appliquerAugmentation(salaireMensuel);
    }

    // Max 3 jours/ an
    @Override
    public void ajouterFormation(Formation formation) {
        // Nombre de jours déjà suivis cette année
        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 3) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Max 3 jours/an");
        }
    }

    // promotion si la formation est >= 5 "sur plusieurs années"
    public boolean peutEtrePromu() {
        return calculerTotalFormation() >= 5;
    }
    // faire +1 jour tous les 3 ans
    @Override
    public int calculerJoursConges(){
        return 20 + (getAnciennete() / 3);
    }


}