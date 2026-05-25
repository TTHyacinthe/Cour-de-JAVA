package  GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Classe représentant un consultant de l'entreprise
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion automatique des matricules
 */
public class Consultant extends Personnels {

    private static int compteurConsultant = 1;
    private  double tarifJournalier;
    private int joursFactures;

    /**
     * Créer un nouveau consultant
     * @param id identifiant du consultant
     * @param nom nom du consultant
     * @param prenom prénom du consultant
     * @param dateEntree date d’entrée dans l’entreprise
     * @param contrat contrat du consultant
     * @param tarifJournalier tarif journalier appliqué
     * @param joursFactures nombre de jours facturés
     */
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

    /**
     * Génère automatiquement un matricule pour le consultant
     * @return matricule généré automatiquement
     */
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "CON-%d-%03d",
                annee,
                compteurConsultant++
        );
    }

    /**
     * Vérifie si le consultant est payable
     * Un consultant est payable uniquement lorsqu’il possède au moins une mission.
     * @return true si le consultant est payable, sinon false
     */
    @Override
    public boolean estPayable(){

        return !getMissions().isEmpty();
    }

    /**
     * Calcule le salaire du consultant
     * @return salaire calculé du consultant
     */
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

    /**
     * Calcule le nombre de jours de congés accordés au consultant
     * @return nombre de jours de congés
     */
    @Override
    public int calculerJoursConges(){

        return 20;
    }

}