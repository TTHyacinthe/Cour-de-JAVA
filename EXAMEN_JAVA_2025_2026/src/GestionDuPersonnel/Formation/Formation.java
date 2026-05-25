package GestionDuPersonnel.Formation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Classe représentant une formation suivie par un personnel
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion des doublons
 */
public class Formation {

    private String intitule;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    /**
     * Créer une nouvelle formation
     * @param intitule intitulé de la formation
     * @param dateDebut date de début de la formation
     * @param dateFin date de fin de la formation
     */
    public Formation(String intitule, LocalDate dateDebut, LocalDate dateFin) {

        if (intitule == null || intitule.isBlank()) {

            throw new IllegalArgumentException("L'intitule est invalide, et dois être rempli");
        }

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
     * Calcule le nombre total de jours de formation
     * Le calcul inclut le premier jour de la formation
     * @return nombre de jours de formation
     */
    public long getNombreJours() {

        return ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
    }

    /**
     * Retourne l’année de la formation
     * L’année correspond à celle de la date de début
     * @return année de la formation
     */
    public int getAnnee() {

        return dateDebut.getYear();
    }

    /**
     * Retourne l’intitulé de la formation
     * @return intitulé de la formation
     */
    public String getIntitule() {

        return intitule;
    }

    /**
     * Compare deux formations
     * Deux formations sont considérées identiques lorsqu’elles possèdent le même intitulé et la même date de début
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Formation that = (Formation) o;

        return intitule.equals(that.intitule)
                && dateDebut.equals(that.dateDebut);
    }

    /**
     * Génère le code de hachage de la formation
     * @return code de hachage
     */
    @Override
    public int hashCode() {

        return Objects.hash(intitule, dateDebut);
    }
}
