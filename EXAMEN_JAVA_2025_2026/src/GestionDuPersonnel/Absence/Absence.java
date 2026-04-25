package GestionDuPersonnel.Absence;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe métier représentant une absence d’un personnel
 */
public class Absence {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;

    public Absence(LocalDate dateDebut, LocalDate dateFin, String motif) {
        // Aucune date ne doit être null
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates ne peuvent pas être null");
        }
        // La date de fin ne doit pas être antérieure à la date de debut
        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.motif = motif;
    }

    /**
     * Calcule le nombre de jours d'absence
     * between est utilisé pour faire une différence entre les deux dates
     * "+1" pour inclure le jour du début de l'absence
     */
    public long getNombreJours() {
        return ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
    }

    /**
     * Vérifie si un certificat médical est obligatoire
     * Règle : obligatoire si absence > 1 jour
     */
    public boolean certificatObligatoire() {
        return getNombreJours() > 1;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public String getMotif() {
        return motif;
    }
}
