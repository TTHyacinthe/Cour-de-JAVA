package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

/**
 * Cette représente un employé de l'entreprise
 */

public class Employe extends Personnels {

    private static int compteurEmploye = 1;
    private static  final double salaireBase = 3000;
    private BaremeFonction fonction;

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

    // Génère automatiquement un matricule
    private static String genererMatricule(){

        int annee = LocalDate.now().getYear();

        return String.format(
                "EMP-%d-%03d",
                annee,
                compteurEmploye++
        );
    }

    public BaremeFonction getFonction() {

        return fonction;
    }

    // Employé payable si ses absences <= 30 jours
    @Override
    public boolean estPayable(){

        return calculerTotalAbsences() <= 30;
    }

    // calcul du salaire de l'employé
    @Override
    public double calculerSalaire(){

        if (! estPayable() ) return 0;

        double salaireMensuel = salaireBase * fonction.getBareme();

        return appliquerAugmentation(salaireMensuel);
    }

    // Max 3 jours/ an
    @Override
    public void ajouterFormation(Formation formation) {

        // Nombre de jours déjà suivis cette année
        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 3) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Le nombre de formation Max pour un employé est de 3 jours/an");
        }
    }

    // promotion si la formation est >= 5 "sur plusieurs années"
    public boolean peutEtrePromu() {

        return calculerTotalFormation() >= 5;
    }
    // faire +1 jour tous les 3 ans
    @Override
    public int calculerJoursConges(){

        return 20 + (getAnciennete() / 3);
    }


}