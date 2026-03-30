package GestionDuPersonnel.Formation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe représentant une formation suivie par un personnel
 */
public class Formation {

    private String intitule;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Formation(String intitule, LocalDate dateDebut, LocalDate dateFin) {

        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Dates invalides");
        }

        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Date fin invalide");
        }
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    /**
     * Calcul du nombre de jours de formation
     */
    public long getNombreJours() {
        return ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
    }

    public int getAnnee() {
        return dateDebut.getYear();
    }

    public String getIntitule() {
        return intitule;
    }
}
