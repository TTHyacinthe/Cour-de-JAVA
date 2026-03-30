package GestionDuPersonnel.Contrat;

import java.time.LocalDate;

public class Contrat {


    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Contrat(TypeContrat typeContrat,
                   LocalDate dateDebut,
                   LocalDate dateFin) {

        if (typeContrat == null || dateDebut == null) {
            throw new IllegalArgumentException("Paramètres invalides");
        }

        if (dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Date fin invalide");
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
