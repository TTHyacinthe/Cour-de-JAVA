package GestionDuPersonnel.Mission;

import GestionDuPersonnel.Deplacement.Deplacement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


}
