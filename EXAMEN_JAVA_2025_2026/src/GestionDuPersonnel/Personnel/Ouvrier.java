package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Cette représente un ouvrier de l'entreprise
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion automatique des matricules
 */

public class Ouvrier extends Personnels {

    private double tauxHoraire;

    private static int compteurOuvrier = 1;

    /**
     * Crée un nouvel ouvrier
     * @param id identifiant de l’ouvrier
     * @param nom nom de l’ouvrier
     * @param prenom prénom de l’ouvrier
     * @param dateEntree date d’entrée dans l’entreprise
     * @param contrat contrat de travail
     * @param tauxHoraire taux horaire appliqué
     */
    public Ouvrier(
                    int id,
                    String nom,
                    String prenom,
                    LocalDate dateEntree,
                    Contrat contrat,
                    double tauxHoraire ) {

        super(id,nom,prenom,dateEntree,contrat, FrequencePaiement.BIMENSUEL);

        if (tauxHoraire <= 0) {

            throw new IllegalArgumentException(
                    "Le Taux horaire ne dois pas être négatif"
            );
        }
        this.tauxHoraire = tauxHoraire;

        setMatricule(genererMatricule());
    }

    /**
     * Génère automatiquement un matricule pour l’ouvrier
     * @return matricule généré automatiquement
     */
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "OUV-%d-%03d",
                annee,
                compteurOuvrier++
        );
    }

    /**
     * Vérifie si l’ouvrier est payable
     * Un ouvrier est payable uniquement si le total de ses absences ne dépasse pas 14 jours.
     * @return true si l’ouvrier est payable, sinon false
     */
    @Override
    public boolean estPayable(){

        return calculerTotalAbsences() <= 14;
    }

    /**
     * Calcule le salaire de l’ouvrier
     * @return salaire calculé de l’ouvrier
     */
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

    /**
     * Ajoute une formation à l’ouvrier
     * Un ouvrier ne peut pas dépasser 4 jours de formation par année
     * @param formation formation à ajouter
     */
    @Override
    public void ajouterFormation(Formation formation) {

        if (formation == null) {

            throw new IllegalArgumentException("Formation invalide");
        }

        if (formations.contains(formation)) {

            throw new IllegalArgumentException(
                    "Formation déjà enregistrée"
            );
        }

        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 4) {

            formations.add(formation);
        } else {

            throw new IllegalArgumentException("Le nombre de formation Max pour un ouvrier est de 4 jours/an");
        }
    }

    /**
     * Calcule le nombre de jours de congés accordés à l’ouvrier
     * @return nombre de jours de congés
     */
    @Override
    public int calculerJoursConges(){

        return (calculerTotalPresence() / 160) * 2;
    }



}