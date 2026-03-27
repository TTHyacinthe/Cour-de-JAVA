package GestionDuPersonnel.Contrat;

import java.time.LocalDate;

public class Contrat {

    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Contrat(TypeContrat typeContrat,
                   LocalDate dateDebut,
                   LocalDate dateFin) {
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
