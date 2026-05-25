package GestionDuPersonnel.Mission;

import GestionDuPersonnel.Deplacement.Deplacement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant une mission
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion des doublons
 */
public class Mission {

    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private List<Deplacement> deplacements = new ArrayList<>();

    /**
     * Créer une nouvelle mission
     * @param titre titre de la mission
     * @param dateDebut date de début de la mission
     * @param dateFin date de fin de la mission
     */
    public Mission(
                   String titre,
                   LocalDate dateDebut,
                   LocalDate dateFin){

        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Titre invalide et dois être remplis");
        }

        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Dates invalides");
        }

        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Date fin invalide");
        }

        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    /**
     * Ajoute un déplacement à la mission
     * @param deplacement déplacement à ajouter
     */    public void ajouterDeplacement(Deplacement deplacement){

        deplacements.add(deplacement);
    }

    /**
     * Calcule le montant total des remboursements liés aux déplacements de la mission
     * @return total des remboursements
     */
    public double calculerTotalRemboursement(){

        return deplacements.stream().mapToDouble(Deplacement::calculerRemboursement).sum();
    }

    /**
     * Retourne le titre de la mission
     * @return titre de la mission
     */
    public String getTitre() {

        return titre;
    }

    /**
     * Retourne la date de début de la mission
     * @return date de début
     */
    public LocalDate getDateDebut() {

        return dateDebut;
    }

    /**
     * Retourne la date de fin de la mission
     * @return date de fin
     */
    public LocalDate getDateFin() {

        return dateFin;
    }

    /**
     * Compare deux missions
     * Deux missions sont considérées identiques lorsqu’elles possèdent le même titre et la même date de début
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        return titre.equals(mission.titre)
                && dateDebut.equals(mission.dateDebut);
    }

    /**
     * Génère le code de hachage de la mission
     * @return code de hachage
     */
    @Override
    public int hashCode() {

        return Objects.hash(titre, dateDebut);
    }

}
