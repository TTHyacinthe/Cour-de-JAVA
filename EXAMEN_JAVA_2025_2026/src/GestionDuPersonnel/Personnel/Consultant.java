package  GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

public class Consultant extends personnels{

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
    public boolean estPayable(){
        return calculerTotalAbsences() == 0;
    }

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