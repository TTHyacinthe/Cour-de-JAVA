package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

public class Ouvrier extends personnels {

    private double tauxHoraire;

    public Ouvrier(
                    int id,
                    String matricule,
                    String nom,
                    String prenom,
                    LocalDate dateEntree,
                    Contrat contrat,
                    double tauxHoraire ) {
        super(id, matricule,nom,prenom,dateEntree,contrat, FrequencePaiement.BIMENSUEL);
        this.tauxHoraire = tauxHoraire;
    }

    @Override
    public boolean estPayable(){
        return calculerTotalAbsences() <= 14;
    }

    @Override
    public double calculerSalaire(){
        if (!estPayable()) return 0;

        double salaire = tauxHoraire * calculerTotalPresence();

        // Augmentation +5% si la formation >= 10 jours
        if (calculerTotalFormation() >= 10) {
            salaire *= 1.05;
        }

        return appliquerAugmentation(salaire);
    }
    // Max 4 jours/an
    @Override
    public void ajouterFormation(Formation formation) {
        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 4) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Max 4 jours/an");
        }
    }


    @Override
    public int calculerJoursConges(){
        return (calculerTotalPresence() / 160) * 2;
    }

}