package  GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Classe représentant un consultant de l'netreprise
 */

public class Consultant extends Personnels {

    private static int compteurConsultant = 1;
    private  double tarifJournalier;
    private int joursFactures;

    public Consultant(
                        int id,
                        String nom,
                        String prenom,
                        LocalDate dateEntree,
                        Contrat contrat,
                        double tarifJournalier,
                        int joursFactures){

        super(id, nom,prenom,dateEntree,contrat, FrequencePaiement.MENSUEL);

        if (tarifJournalier <= 0) {

            throw new IllegalArgumentException(
                    "Le Tarif journalier invalide ne dois pas être négatif"
            );
        }
        if (joursFactures < 0) {

            throw new IllegalArgumentException(
                    "Le Nombre de jours facturés ne dois pas être négatif"
            );
        }
        this.tarifJournalier = tarifJournalier;
        this.joursFactures = joursFactures;

        setMatricule(genererMatricule());
    }

    // Génère automatiquement un matricule
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "CON-%d-%03d",
                annee,
                compteurConsultant++
        );
    }

    // le consultant est payé s'il n'a aucune absence
    @Override
    public boolean estPayable(){

        return !getMissions().isEmpty();
    }

    // calcul salaire du consultant
    @Override
    public double calculerSalaire(){

        if (!estPayable()) return 0;

        int joursPayes = joursFactures - congesPris;

        // sécurité si trop de congés
        if (joursPayes < 0) {
            joursPayes = 0;
        }

        double salaire = tarifJournalier * joursPayes;

        return appliquerAugmentation(salaire);
    }

    @Override
    public int calculerJoursConges(){

        return 20;
    }

}