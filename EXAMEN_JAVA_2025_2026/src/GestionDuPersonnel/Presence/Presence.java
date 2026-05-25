package GestionDuPersonnel.Presence;

import java.time.LocalDate;

/**
 * Représente une présence d'un membre du personnel, qui doit contenir une date de présence et le nombre d'heures travaillées ce jour-là
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free pour la gestion des doublons
 */
public class Presence {

    private LocalDate date;
    private int heuresTravaillees;

    /**
     * Crée une nouvelle présence
     * @param date date de présence
     * @param heuresTravaillees nombre d’heures travaillées
     */
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

    /**
     * Retourne le nombre d’heures travaillées
     * @return nombre d’heures travaillées
     */
    public int getHeuresTravaillees() {
        return heuresTravaillees;
    }

    /**
     * Compare deux présences
     * Deux présences sont considérées identiques lorsqu’elles possèdent la même date
     * @param o objet à comparer
     * @return true si les objets sont égaux, sinon false
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Presence presence = (Presence) o;
        return date.equals(presence.date);
    }

    /**
     * Génère le code de hachage de la présence
     * @return code de hachage
     */
    @Override
    public int hashCode() {

        return date.hashCode();
    }

}
