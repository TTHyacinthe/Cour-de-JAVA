package GestionDuPersonnel.Formation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Classe représentant une formation suivie par un personnel
 */
public class Formation {

    private String intitule;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Formation(String intitule, LocalDate dateDebut, LocalDate dateFin) {

        // une formation a toujours une durée bien déterminé
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("les dates  sont invalides");
        }

        // cohérence entre la date de fin et la date de debut
        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Date de fin invalide");
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Formation that = (Formation) o;

        return intitule.equals(that.intitule)
                && dateDebut.equals(that.dateDebut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(intitule, dateDebut);
    }
}
