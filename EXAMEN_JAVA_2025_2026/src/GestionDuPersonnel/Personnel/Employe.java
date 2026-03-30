package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FrequencePaiement;

import java.time.LocalDate;

public class Employe extends personnels{

    private double salaireMensuel;
    private String fonction;

    public Employe(
                   int id,
                   String matricule,
                   String nom,
                   String prenom,
                   LocalDate dateEntree,
                   Contrat contrat,
                   double salaireMensuel,
                   String fonction){
        super(id,matricule,nom,prenom,dateEntree,contrat, FrequencePaiement.MENSUEL);
        this.salaireMensuel = salaireMensuel;
        this.fonction = fonction;
    }

    @Override
    public boolean estPayable(){
        return calculerTotalAbsences() <= 30;
    }

    @Override
    public double calculerSalaire(){
        if (!estPayable()) return 0;
        return appliquerAugmentation(salaireMensuel);
    }

    // Max 3 jours/ an
    @Override
    public void ajouterFormation(Formation formation) {
        int total = calculerFormationAnnuelle(formation.getAnnee());

        if (total + formation.getNombreJours() <= 3) {
            formations.add(formation);
        } else {
            throw new IllegalArgumentException("Max 3 jours/an");
        }
    }

    // promotion si la formation est >= 5 "sur plusieurs années"
    public boolean peutEtrePromu() {
        return calculerTotalFormation() >= 5;
    }

    @Override
    public int calculerJoursConges(){
        return 20 + (getAnciennete() / 3);
    }


}