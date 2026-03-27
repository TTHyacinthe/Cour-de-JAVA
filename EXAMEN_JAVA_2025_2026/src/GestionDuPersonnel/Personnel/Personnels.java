package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Absence.Absence;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Interfaces.IAugmentation;
import GestionDuPersonnel.Interfaces.ICalculSalire;
import GestionDuPersonnel.Interfaces.IGestionConges;
import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Presence.Presence;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public abstract class Personnels implements ICalculSalire, IGestionConges, IAugmentation {

    protected int id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected LocalDate dateEntree;
    private Contrat contrat;

    // Liste des absences (COMPOSITION)
    protected List<Absence> absences = new ArrayList<>();
    // Liste des présences
    protected List<Presence> presences = new ArrayList<>();
    // Liste des formations
    protected List<Formation> formations = new ArrayList<>();




    public Personnels(int id,
                      String matricule,
                      String nom,
                      String prenom,
                      LocalDate dateEntree,
                      Contrat contrat) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
        this.contrat = contrat;
    }

    /**
     * Ajouter une absence
     */
    public void ajouterAbsence(Absence absence) {
        absences.add(absence);
    }

    /**
     * Calcul du total des jours d'absence
     */
    public long calculerTotalAbsences() {
        return absences.stream()
                .mapToLong(Absence::getNombreJours)
                .sum();
    }

    /**
     * Méthode abstraite (POLYMORPHISME)
     * Chaque type de personnel définit sa règle
     */
    public abstract boolean estPayable();

    /**
     * Calcul du salaire (abstrait)
     */
    public abstract double calculerSalaire();

    /**
     * Ajouter une présence
     */
    public void ajouterPresence(Presence presence) {
        presences.add(presence);
    }

    /**
     * Calculer le total des heures travaillées
     */
    public int calculerTotalHeures() {
        return presences.stream()
                .mapToInt(Presence::getHeuresTravaillees)
                .sum();
    }

    /**
     * Ajouter une formation
     */
    public void ajouterFormation(Formation formation) {
        formations.add(formation);
    }

    /**
     * Total jours de formation pour une année donnée
     */
    public long calculerFormationAnnuelle(int annee) {
        return formations.stream()
                .filter(f -> f.getAnnee() == annee)
                .mapToLong(Formation::getNombreJours)
                .sum();
    }




    public int getId() {
        return id;
    }
    public String getMatricule() {
        return matricule;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public LocalDate getDateEntree() {
        return dateEntree;
    }
    public Contrat getContrat() {
        return contrat;
    }
}
