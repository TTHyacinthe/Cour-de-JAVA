package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;

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

    @Override
    public double calculerSalaire(){
        return tauxHoraire * heuresTravaillees;
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
