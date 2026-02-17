package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;

import java.time.LocalDate;
import java.time.Period;

public class Employe extends Personnels{
    private double salaireMensuel;

    public Employe (int id,
                    String matricule,
                    String nom,
                    String prenom,
                    LocalDate dateEntree,
                    Contrat contrat,
                    double salaireMensuel) {
        super(id, matricule, nom, prenom, dateEntree, contrat);
        this.salaireMensuel = salaireMensuel;
    }

    @Override
    public double calculerSalaire() {
        return salaireMensuel;
    }

    @Override
    public int calculerJoursConges(){
        int anciennete = Period.between(dateEntree, LocalDate.now()).getYears();
        int joursSupplementaire = anciennete / 3;
        return 20 + joursSupplementaire;
    }

    @Override
    public double appliquerAugmentation(double salaireActuel){
        int anciennete = Period.between(dateEntree, LocalDate.now()).getYears();
        int tranches = anciennete / 2;
        return salaireActuel * (1 + 0.02 * tranches);
    }
}
