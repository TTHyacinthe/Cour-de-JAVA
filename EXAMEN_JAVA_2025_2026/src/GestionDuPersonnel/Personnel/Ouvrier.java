package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;

import java.time.LocalDate;

public class Ouvrier extends Personnels {

    private double tauxHoraire;
    private int heuresTravaillees;

    public Ouvrier (int id,
                    String matricule,
                    String nom,
                    String prenom,
                    LocalDate dateEntree,
                    Contrat contrat,
                    double tauxHoraire,
                    int heuresTravaillees) {
        super(id, matricule, nom, prenom, dateEntree, contrat);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravaillees = heuresTravaillees;
    }

    /**
     * Règle métier :
     * Non payé si absences > 14 jours
     */
    @Override
    public boolean estPayable() {
        return calculerTotalAbsences() <= 14;
    }

    /**
     * Calcul du salaire
     */
    @Override
    public double calculerSalaire() {
        if (!estPayable()) {
            return 0;
        }

        int totalHeures = calculerTotalHeures();
        double salaire = tauxHoraire * totalHeures;

        // Règle formation : +5% si >= 10 jours
        long totalFormation = formations.stream()
                .mapToLong(f -> f.getNombreJours())
                .sum();

        if (totalFormation >= 10) {
            salaire *= 1.05;
        }

        return salaire;
    }

    @Override
    public void ajouterFormation(Formation formation) {
        int annee = formation.getAnnee();
        long total = calculerFormationAnnuelle(annee);

        if (total + formation.getNombreJours() <= 4) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Limite de formation dépassée pour un ouvrier");
        }
    }




    @Override
    public int calculerJoursConges(){
        return (heuresTravaillees / 160) * 2;
    }

    @Override
    public double appliquerAugmentation(double salaireActuel) {
        return salaireActuel * 1.05;
    }
}
