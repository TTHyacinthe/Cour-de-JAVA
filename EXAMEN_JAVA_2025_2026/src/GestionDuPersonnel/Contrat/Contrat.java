package GestionDuPersonnel.Contrat;

import java.time.LocalDate;

public class Contrat {

    private String typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Contrat(String typeContrat,
                   LocalDate dateDebut,
                   LocalDate dateFin) {
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getTypeContrat() {
        return typeContrat;
    }
    public LocalDate getDateDebut() {
            return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
}
