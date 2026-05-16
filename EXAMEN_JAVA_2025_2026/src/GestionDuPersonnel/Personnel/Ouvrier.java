package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Cette représente un ouvrier de l'entreprise
 */

public class Ouvrier extends Personnels {

    private double tauxHoraire;

    private static int compteurOuvrier = 1;

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

    // Génère automatiquement un matricule
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "OUV-%d-%03d",
                annee,
                compteurOuvrier++
        );
    }

    // Ouvrier payable si ses absences <= 14 jours
    @Override
    public boolean estPayable(){

        return calculerTotalAbsences() <= 14;
    }

    // calcul salaire ouvrier
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

            throw new IllegalArgumentException("Le nombre de formation Max pour un ouvrier est de 4 jours/an");
        }
    }

    // tous les 160h travaillées on a 2 jours de congé qui s'ajoute
    @Override
    public int calculerJoursConges(){

        return (calculerTotalPresence() / 160) * 2;
    }



}