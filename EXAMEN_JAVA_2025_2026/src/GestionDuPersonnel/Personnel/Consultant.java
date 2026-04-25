package  GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Classe représentant un consultant de l'netreprise
 */

public class Consultant extends Personnels {

    private  double tarifJournalier;
    private int joursFactures;

    public Consultant(
                        int id,
                        String matricule,
                        String nom,
                        String prenom,
                        LocalDate dateEntree,
                        Contrat contrat,
                        double tarifJournalier,
                        int joursFactures){
        super(id,matricule,nom,prenom,dateEntree,contrat, FrequencePaiement.MENSUEL);
        this.tarifJournalier = tarifJournalier;
        this.joursFactures = joursFactures;
    }

    @Override
    public String getFonction() {
        return "Consultant";
    }
    @Override
    public double getBareme() {
        return tarifJournalier;
    }


    // le consultant est payé s'il n'a aucune absence
    @Override
    public boolean estPayable(){
        return calculerTotalAbsences() == 0;
    }

    // calcul salaire du consultant
    @Override
    public double calculerSalaire(){
        if (!estPayable()) return 0;
        return appliquerAugmentation(tarifJournalier * joursFactures);
    }

    @Override
    public int calculerJoursConges(){
        return 20;
    }

}