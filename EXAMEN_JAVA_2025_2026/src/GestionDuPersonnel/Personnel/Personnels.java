package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Absence.Absence;
import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Interfaces.IAugmentation;
import GestionDuPersonnel.Interfaces.ICalculSalire;
import GestionDuPersonnel.Interfaces.IGestionConges;
import GestionDuPersonnel.Mission.Mission;
import GestionDuPersonnel.Paie.FrequencePaiement;
import GestionDuPersonnel.Presence.Presence;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un membre du personnel
 */

public abstract class Personnels implements ICalculSalire, IGestionConges, IAugmentation {

    protected int id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected LocalDate dateEntree;

    protected Contrat contrat;
    protected FrequencePaiement frequencePaiement;

    protected List<Absence> absences = new ArrayList<>();
    protected List<Presence> presences = new ArrayList<>();
    protected List<Formation> formations = new ArrayList<>();
    protected List<Mission> missions = new ArrayList<>();

    protected int congesPris = 0;

    public Personnels(
            int id,
            String matricule,
            String nom,
            String prenom,
            LocalDate dateEntree,
            Contrat contrat,
            FrequencePaiement frequencePaiement) {

        if (matricule == null || nom == null || prenom == null || dateEntree == null) {
            throw new IllegalArgumentException("Paramètres obligatoires manquants");
        }

        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
        this.contrat = contrat;
        this.frequencePaiement = frequencePaiement;

    }

    // Ajouter une absence
    public void ajouterAbsence(Absence absence) {
        if (absence == null) {
            throw new IllegalArgumentException("Absence invalide");
        }

        absences.add(absence);
    }

    // Calcule le nombre total de jours d'absence du personnel
    public int calculerTotalAbsences() {
        return absences.stream()
                .mapToInt(a -> (int) a.getNombreJours())
                .sum();
    }

    // Presences
    public void ajouterPresence(Presence presence) {

        if (presence == null) {
            throw new IllegalArgumentException("Presence invalide");
        }

        presences.add(presence);
    }
    public int calculerTotalPresence(){
        return presences.stream()
                .mapToInt(Presence::getHeuresTravaillees)
                .sum();
    }

    //Formations
    public void ajouterFormation(Formation formation) {

        if (formation == null) {
            throw new IllegalArgumentException("Formation invalide");
        }

        formations.add(formation);
    }
    public int calculerFormationAnnuelle(int annee) {
        return formations.stream()
                .filter(f -> f.getAnnee() == annee)
                .mapToInt(f -> (int) f.getNombreJours())
                .sum();
    }

    public int calculerTotalFormation() {
        return formations.stream()
                .mapToInt(f -> (int) f.getNombreJours())
                .sum();
    }

    // Augmentation (règles globale "2% tous les 2 ans révolus")
    public double appliquerAugmentation(double salaire){
        int tranches = getAnciennete() / 2;
        return salaire * (1 + 0.02 * tranches);
    }

    // Salaire par période
    public double calculerSalaireParPeriode() {
        double salaire = calculerSalaire();

        if (frequencePaiement == FrequencePaiement.BIMENSUEL) {
            return salaire / 2;
        }
        return salaire;
    }

    // Conges
    public int getSoldeConges() {
        return calculerJoursConges() -  congesPris;
    }
    public void prendreConges(int jours) {
        if (jours <= 0) {
            throw new IllegalArgumentException("Nombre de jours invalide");
        }

        if (jours > getSoldeConges()) {
            throw new IllegalArgumentException("Pas assez de congés");
        }
        congesPris += jours;
    }
    public void ajouterMission(Mission mission) {
        missions.add(mission);
    }


    // Anciennete
    public int getAnciennete(){
        return Period.between(dateEntree, LocalDate.now()).getYears();
    }

    //Abstrait
    public abstract boolean estPayable();
    public abstract double calculerSalaire();
    public abstract int calculerJoursConges();

    //Getters
    public String getNom()
    {
        return nom;
    }
    public String getPrenom()
    {
        return prenom;
    }
    public String getMatricule()
    {
        return matricule;
    }
    public Contrat getContrat()
    {
        return contrat;
    }
    public abstract String getFonction();
    public abstract double getBareme();


}