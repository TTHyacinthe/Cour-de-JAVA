package GestionDuPersonnel.Presence;

import java.time.LocalDate;

/**
 * Classe représentant une présence d’un employé
 */
public class Presence {

    private LocalDate date;
    private int heuresTravaillees;

    public Presence(LocalDate date, int heuresTravaillees) {
        this.date = date;
        this.heuresTravaillees = heuresTravaillees;
    }

    public int getHeuresTravaillees() {
        return heuresTravaillees;
    }

    public LocalDate getDate() {
        return date;
    }
}
