package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Cette classe représente un employé de l'entreprise
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion automatique des matricules
 */

public class Employe extends Personnels {

    private static int compteurEmploye = 1;
    private static  final double salaireBase = 3000;
    private BaremeFonction fonction;

    /**
     * Crée  un nouvel employé
     * @param id identifiant de l’employé
     * @param nom nom de l’employé
     * @param prenom prénom de l’employé
     * @param dateEntree date d’entrée dans l’entreprise
     * @param contrat contrat de travail
     * @param fonction fonction occupée par l’employé
     */
    public Employe(
                   int id,
                   String nom,
                   String prenom,
                   LocalDate dateEntree,
                   Contrat contrat,
                   BaremeFonction fonction){

        super(id, nom,prenom,dateEntree,contrat, FrequencePaiement.MENSUEL);

        this.fonction = fonction;

        setMatricule(genererMatricule());
    }

    /**
     * Génère automatiquement un matricule pour l’employé
     * @return matricule généré automatiquement
     */
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "EMP-%d-%03d",
                annee,
                compteurEmploye++
        );
    }

    /**
     * Retourne la fonction de l’employé
     * @return fonction de l’employé
     */
    public BaremeFonction getFonction() {

        return fonction;
    }

    /**
     * Vérifie si l’employé est payable
     * Un employé est payable uniquement si le total de ses absences ne dépasse pas 30 jours
     * @return true si l’employé est payable, sinon false
     */
    @Override
    public boolean estPayable(){

        return calculerTotalAbsences() <= 30;
    }

    /**
     * Calcule le salaire de l’employé
     * @return salaire calculé de l’employé
     */
    @Override
    public double calculerSalaire(){

        if (! estPayable() ) return 0;

        double salaireMensuel = salaireBase * fonction.getBareme();

        return appliquerAugmentation(salaireMensuel);
    }

    /**
     * Ajoute une formation à l’employé
     * Un employé ne peut pas dépasser 3 jours de formation par année
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

        // Nombre de jours déjà suivis cette année
        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 3) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Le nombre de formation Max pour un employé est de 3 jours/an");
        }
    }

    /**
     * Vérifie si l’employé peut être promu
     * Un employé est promouvable lorsque le total de ses formations atteint au moins 5 jours
     * @return true si l’employé peut être promu, sinon false
     */
    public boolean peutEtrePromu() {

        return calculerTotalFormation() >= 5;
    }

    /**
     * Calcule le nombre de jours de congés accordés à l’employé
     * @return nombre de jours de congés
     */
    @Override
    public int calculerJoursConges(){

        return 20 + (getAnciennete() / 3);
    }


}