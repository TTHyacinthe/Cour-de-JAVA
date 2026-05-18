package GestionDuPersonnel.Presence;

import java.time.LocalDate;

/**
 * Représente une présence d'un membre du personnel,
 * qui doit contenir une date de présence et le nombre d'heures travaillées ce jour-là
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Presence presence = (Presence) o;
        return date.equals(presence.date);
    }

    @Override
    public int hashCode() {

        return date.hashCode();
    }

}
