package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;

import java.time.LocalDate;

public class Consultant extends Personnels{
    private double tarifJournalier;
    private int facturesJours;

    public Consultant(int id,
                      String matricuel,
                      String nom,
                      String prenom,
                      LocalDate dateEntree,
                      Contrat contrat,
                      double tarifJournalier,
                      int facturesJours) {
        super(id, matricuel, nom, prenom, dateEntree, contrat);
        this.tarifJournalier = tarifJournalier;
        this.facturesJours = facturesJours;
    }

    @Override
    public double calculerSalaire(){
        return tarifJournalier * facturesJours;
    }

    @Override
    public int calculerJoursConges(){
        return 0; // pas de congés payés
    }

    @Override
    public double appliquerAugmentation(double salaireActuel){
        return salaireActuel; // pas d'augmentation automatique
    }
}
