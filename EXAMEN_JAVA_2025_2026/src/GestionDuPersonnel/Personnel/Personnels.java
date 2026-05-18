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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    protected Set<Presence> presences = new HashSet<>();
    protected Set<Absence> absences = new HashSet<>();
    protected Set<Formation> formations = new HashSet<>();
    protected Set<Mission> missions = new HashSet<>();

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

        if (!absences.add(absence)){

            throw new IllegalArgumentException("Absence déjà enregistrée");
        }
        if (absence == null) {

            throw new IllegalArgumentException("Absence invalide");
        }
    }

    // Calcule le nombre total de jours d'absence du personnel
    public int calculerTotalAbsences() {

        return absences.stream()
                .mapToInt(a -> (int) a.getNombreJours())
                .sum();
    }

    // Ajoute une présence au personnel
    public void ajouterPresence(Presence presence) {

        if (!presences.add(presence)) {

            throw new IllegalArgumentException("Presence déjà enregistrée pour cette date");
        }

        if (presence == null) {

            throw new IllegalArgumentException("Presence invalide");
        }

    }

    // Calculer le total des heures traavaillées
    public int calculerTotalPresence(){

        return presences.stream()
                .mapToInt(Presence::getHeuresTravaillees)
                .sum();
    }

    //Ajouter une formations au personnel
    public void ajouterFormation(Formation formation) {

        if (!formations.add(formation)) {

            throw new IllegalArgumentException("Formation déjà enregistrée");
        }

        if (formation == null) {

            throw new IllegalArgumentException("Formation invalide");
        }
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

        if (!missions.add(mission)) {

            throw new IllegalArgumentException("Mission déjà enregistrée");
        }

    }

    // Calculer l'Anciennete du personnel
    public int getAnciennete(){

        return Period.between(dateEntree, LocalDate.now()).getYears();
    }

    public FrequencePaiement getFrequencePaiement() {

        return frequencePaiement;
    }

    public Set<Mission> getMissions() {

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