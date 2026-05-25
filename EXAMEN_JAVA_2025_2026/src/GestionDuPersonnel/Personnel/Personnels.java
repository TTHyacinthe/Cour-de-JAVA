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
import java.util.Set;

/**
 * Classe abstraite représentant un membre du personnel
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free utilisé pour la gestion automatique des matricules et des doublons
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

    /**
     * Construit un nouveau membre du personnel
     * @param id identifiant du personnel
     * @param nom nom du personnel
     * @param prenom prénom du personnel
     * @param dateEntree date d’entrée dans l’entreprise
     * @param contrat contrat du personnel
     * @param frequencePaiement fréquence de paiement
     */
    public Personnels(
            int id,
            String nom,
            String prenom,
            LocalDate dateEntree,
            Contrat contrat,
            FrequencePaiement frequencePaiement) {

        if (id <= 0 || nom == null || prenom == null || dateEntree == null || contrat == null || frequencePaiement == null ) {

            throw new IllegalArgumentException("Paramètres obligatoires manquants");
        }

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
        this.contrat = contrat;
        this.frequencePaiement = frequencePaiement;

    }

    /**
     * Définit automatiquement le matricule du personnel
     * @param matricule matricule attribué
     */
    protected void setMatricule(String matricule) {

        this.matricule = matricule;
    }

    /**
     * Ajoute une absence au personnel
     * @param absence absence à ajouter
     */
    public void ajouterAbsence(Absence absence) {

        if (absence == null) {

            throw new IllegalArgumentException("Absence invalide");
        }

        if (!absences.add(absence)){

            throw new IllegalArgumentException("Absence déjà enregistrée");
        }
    }

    /**
     * Calcule le nombre total de jours d’absence du personnel
     * @return total des jours d’absence
     */
    public int calculerTotalAbsences() {

        return absences.stream()
                .mapToInt(a -> (int) a.getNombreJours())
                .sum();
    }

    /**
     * Ajoute une présence au personnel
     * @param presence présence à ajouter
     */
    public void ajouterPresence(Presence presence) {

        if (presence == null) {

            throw new IllegalArgumentException("Presence invalide");
        }

        if (!presences.add(presence)) {

            throw new IllegalArgumentException("Presence déjà enregistrée pour cette date");
        }

    }

    /**
     * Calcule le total des heures travaillées
     * @return total des heures de présence
     */
    public int calculerTotalPresence(){

        return presences.stream()
                .mapToInt(Presence::getHeuresTravaillees)
                .sum();
    }

    /**
     * Ajoute une formation au personnel
     * @param formation formation à ajouter
     */
    public void ajouterFormation(Formation formation) {

        if (formation == null) {

            throw new IllegalArgumentException("Formation invalide");
        }

        if (!formations.add(formation)) {

            throw new IllegalArgumentException("Formation déjà enregistrée");
        }
    }

    /**
     * Calcule le total des jours de formation pour une année donnée
     * @param annee année concernée
     * @return total des jours de formation
     */
    public int calculerFormationAnnuelle(int annee) {
        return formations.stream()
                .filter(f -> f.getAnnee() == annee)
                .mapToInt(f -> (int) f.getNombreJours())
                .sum();
    }

    /**
     * Calcule le total des jours de formation effectués sur plusieurs années
     * @return total des jours de formation
     */
    public int calculerTotalFormation() {
        return formations.stream()
                .mapToInt(f -> (int) f.getNombreJours())
                .sum();
    }

    /**
     * Applique une augmentation salariale
     * Règle : une augmentation de 2% est appliquée tous les 2 ans d’ancienneté
     * @param salaire salaire initial
     * @return salaire après augmentation
     */
    public double appliquerAugmentation(double salaire){

        int tranches = getAnciennete() / 2;
        return salaire * (1 + 0.02 * tranches);
    }

    /**
     * Calcule le salaire selon la fréquence de paiement
     * Pour un paiement bimensuel, le salaire est divisé en deux périodes
     * @return salaire par période
     */
    public double calculerSalaireParPeriode() {

        double salaire = calculerSalaire();
        if (frequencePaiement == FrequencePaiement.BIMENSUEL) {

            return salaire / 2;
        }
        return salaire;
    }

    /**
     * Retourne le nombre de jours de congés restants
     * @return solde des congés disponibles
     */
    public int getSoldeConges() {

        return calculerJoursConges() -  congesPris;
    }

    /**
     * Permet au personnel de prendre des congés
     * @param jours nombre de jours demandés
     */
    public void prendreConges(int jours) {

        if (jours <= 0) {

            throw new IllegalArgumentException("Nombre de jours invalide");
        }
        if (jours > getSoldeConges()) {

            throw new IllegalArgumentException("Jours de congés épuisé");
        }
        congesPris += jours;
    }

    /**
     * Ajoute une mission au personnel
     * @param mission mission à ajouter
     */
    public void ajouterMission(Mission mission) {

        if (mission == null) {

            throw new IllegalArgumentException("Mission invalide");
        }

        if (!missions.add(mission)) {

            throw new IllegalArgumentException("Mission déjà enregistrée");
        }

    }

    /**
     * Calcule l’ancienneté du personnel
     * L’ancienneté est exprimée en années
     * @return nombre d’années d’ancienneté
     */    public int getAnciennete(){

        return Period.between(dateEntree, LocalDate.now()).getYears();
    }

    /**
     * Retourne la fréquence de paiement
     * @return fréquence de paiement
     */
    public FrequencePaiement getFrequencePaiement() {

        return frequencePaiement;
    }

    /**
     * Retourne les missions du personnel
     * @return ensemble des missions
     */
    public Set<Mission> getMissions() {

        return missions;
    }

    /**
     * Vérifie si le personnel est payable
     * @return true si le personnel est payable, sinon false
     */
    public abstract boolean estPayable();

    /**
     * Calcule le salaire du personnel
     * @return salaire calculé
     */
    public abstract double calculerSalaire();

    /**
     * Calcule le nombre de jours de congés accordés au personnel
     * @return nombre de jours de congés
     */
    public abstract int calculerJoursConges();

    /**
     * Retourne le nom du personnel
     * @return nom du personnel
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * Retourne le prénom du personnel
     * @return prénom du personnel
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * Retourne le matricule du personnel
     * @return matricule du personnel
     */
    public String getMatricule()
    {
        return matricule;
    }

    /**
     * Retourne le contrat du personnel
     * @return contrat du personnel
     */
    public Contrat getContrat()
    {
        return contrat;
    }

}