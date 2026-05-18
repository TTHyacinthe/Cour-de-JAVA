package GestionDuPersonnel.Mission;

import GestionDuPersonnel.Deplacement.Deplacement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant une mission
 */
public class Mission {

    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private List<Deplacement> deplacements = new ArrayList<>();

    public Mission(
                   String titre,
                   LocalDate dateDebut,
                   LocalDate dateFin){
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Ajouter un déplacement
    public void ajouterDeplacement(Deplacement deplacement){

        deplacements.add(deplacement);
    }

    //Calcul total du remboursement
    public double calculerTotalRemboursement(){

        return deplacements.stream().mapToDouble(Deplacement::calculerRemboursement).sum();
    }

    public String getTitre() {

        return titre;
    }

    public LocalDate getDateDebut() {

        return dateDebut;
    }

    public LocalDate getDateFin() {

        return dateFin;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        return titre.equals(mission.titre)
                && dateDebut.equals(mission.dateDebut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titre, dateDebut);
    }

}
