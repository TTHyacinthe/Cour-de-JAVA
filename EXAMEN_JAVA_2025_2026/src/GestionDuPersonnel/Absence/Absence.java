package GestionDuPersonnel.Absence;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Classe représentant une absence d’un personnel
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion des doublons
 *
 */
public class Absence {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private boolean certifatFourni = false;

    /**
     * créer une nouvelle absence
     * @param dateDebut
     * @param dateFin
     * @param motif
     */
    public Absence(LocalDate dateDebut, LocalDate dateFin, String motif) {

        // Aucune date ne doit être null
        if (dateDebut == null || dateFin == null) {

            throw new IllegalArgumentException("Les dates ne peuvent pas être null");
        }

        // La date de fin ne doit pas être antérieure à la date de debut
        if (dateFin.isBefore(dateDebut)) {

            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }

        if (motif == null || motif.isBlank()) {

            throw new IllegalArgumentException("Le motif dois être valide");
        }

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.motif = motif;
    }

    /**
     * calculer le nombre total de jours d'absence
     * @return le nombre de jours d'absence
     */
    public long getNombreJours() {

        return ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
    }

    /**
     * Vérifie si un certificat médical est obligatoire
     * Règle : obligatoire si absence > 1 jour
     * @return true si le certificat est obligatoire et sinmon false
     */
    public boolean certificatObligatoire() {

        return getNombreJours() > 1;
    }

    /**
     * Marque le certificat médical comme fourni
     */
    public void fournirUncerticat() {

        this.certifatFourni = true;
    }

    /**
     * Vérifie si le certificat médical a été fourni
     * @return true si le certificat est fourni, sinon false
     */
    public boolean isCertificatFourni() {

        return certifatFourni;
    }

    /**
     * Vérifie si l’absence est justifiée
     * Une absence est justifiée : si aucun certificat n’est obligatoire ou si le certificat demandé a été fourni
     * @return true si l’absence est justifiée, sinon false
     */
    public boolean absenceJustifier(){

        if(!certificatObligatoire()){
            return true;
        }
        return certifatFourni;
    }

    /**
     * Retourne la date de début de l’absence
     * @return date de début
     */
    public LocalDate getDateDebut() {

        return dateDebut;
    }

    /**
     * Retourne la date de fin de l’absence
     *
     * @return date de fin
     */
    public LocalDate getDateFin() {

        return dateFin;
    }

    /**
     * Retourne le motif de l’absence
     * @return motif de l’absence
     */
    public String getMotif() {

        return motif;
    }

    /**
     * Compare deux absences.
     * Deux absences sont considérées identiques lorsqu’elles possèdent les mêmes dates de début et de fin.
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Absence absence = (Absence) o;
        return dateDebut.equals(absence.dateDebut) && dateFin.equals(absence.dateFin);
    }

    /**
     * Génère le code de hachage de l’absence.
     * @return code de hachage
    */
    @Override
    public int hashCode() {

        return Objects.hash(dateDebut, dateFin);
    }
}
