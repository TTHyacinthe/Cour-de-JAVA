package GestionDuPersonnel.Contrat;

import java.time.LocalDate;

/**
 * classe représentant un contrat de travail d'un personnel
 */

public class Contrat {


    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Contrat(TypeContrat typeContrat,
                   LocalDate dateDebut,
                   LocalDate dateFin) {
        // un contrat ne peut pas exister sans avoir un type et une date de debut
        if (typeContrat == null || dateDebut == null) {
            throw new IllegalArgumentException("Paramètres invalides");
        }

        /**
         *  une date de fin peut être  null ce qui signifie que le contrat
         *  est en cours et cette date ne doit pas être antérieure à la date de debut
         */

        if (dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Date de fin invalide");
        }
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
}
