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
            String nom,
            String prenom,
            LocalDate dateEntree,
            Contrat contrat,
            FrequencePaiement frequencePaiement) {

        if (nom == null || prenom == null || dateEntree == null || frequencePaiement == null) {

            throw new IllegalArgumentException("Paramètres obligatoires manquants");
        }

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
        this.contrat = contrat;
        this.frequencePaiement = frequencePaiement;

    }

    // Définit automatiquement le matricule
    protected void setMatricule(String matricule) {

        this.matricule = matricule;
    }

    // Ajouter une absence au personnel
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

    // Ajoute une présence au personnel
    public void ajouterPresence(Presence presence) {

        if (presence == null) {

            throw new IllegalArgumentException("Presence invalide");
        }

        presences.add(presence);
    }

    // Calculer le total des heures traavaillées
    public int calculerTotalPresence(){

        return presences.stream()
                .mapToInt(Presence::getHeuresTravaillees)
                .sum();
    }

    //Ajouter une formations au personnel
    public void ajouterFormation(Formation formation) {

        if (formation == null) {

            throw new IllegalArgumentException("Formation invalide");
        }

        formations.add(formation);
    }

    //Calculer le total des jours de formations sur une année
    public int calculerFormationAnnuelle(int annee) {
        return formations.stream()
                .filter(f -> f.getAnnee() == annee)
                .mapToInt(f -> (int) f.getNombreJours())
                .sum();
    }

    //     //Calculer le total des jours de formations sur une plusieurs années
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

    // Calcul du Salaire selon la fréquence de paiement
    public double calculerSalaireParPeriode() {

        double salaire = calculerSalaire();
        if (frequencePaiement == FrequencePaiement.BIMENSUEL) {

            return salaire / 2;
        }
        return salaire;
    }

    // retourne le nombre de Congés restant
    public int getSoldeConges() {

        return calculerJoursConges() -  congesPris;
    }

    // Permettre au personnel de prendre congés
    public void prendreConges(int jours) {

        if (jours <= 0) {

            throw new IllegalArgumentException("Nombre de jours invalide");
        }
        if (jours > getSoldeConges()) {

            throw new IllegalArgumentException("Jours de congés épuisé");
        }
        congesPris += jours;
    }

    // Ajouter une mission au personnel
    public void ajouterMission(Mission mission) {

        missions.add(mission);
    }

    // Calculer l'Anciennete du personnel
    public int getAnciennete(){

        return Period.between(dateEntree, LocalDate.now()).getYears();
    }

    public FrequencePaiement getFrequencePaiement() {

        return frequencePaiement;
    }

    public List<Mission> getMissions() {

        return missions;
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

}