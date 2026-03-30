package GestionDuPersonnel.Presence;

import java.time.LocalDate;

/**
 * Classe représentant une présence d’un employé
 */
public class Presence {

    private LocalDate date;
    private int heuresTravaillees;

    public Presence(LocalDate date, int heuresTravaillees) {

        if (date == null){
            throw new IllegalArgumentException("La date ne peut pas être null");
        }
        if (heuresTravaillees < 0 || heuresTravaillees > 24){
            throw  new IllegalArgumentException("Nombre d'heures invalide");
        }
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
