package GestionDuPersonnel.Deplacement;

import java.time.LocalDate;

/**
 * Classe représentant un déplacement
 * Auteur : Hyacinthe TAMO
 */
public class Deplacement {

    private LocalDate date;
    private String VilleDepart;
    private String VilleArrivee;
    private double distanceKm;

    /**
     * créer un nouveau deplacement
     * @param date
     * @param villeDepart
     * @param villeArrivee
     * @param distanceKm
     */
    public Deplacement(
                        LocalDate date,
                        String villeDepart,
                        String villeArrivee,
                        double distanceKm
                        ) {

        if (date == null) {

            throw new IllegalArgumentException("Date invalide");
        }

        if (villeDepart == null || villeDepart.isBlank()) {

            throw new IllegalArgumentException("Ville départ invalide");
        }

        if (villeArrivee == null || villeArrivee.isBlank()) {

            throw new IllegalArgumentException("Ville arrivée invalide");
        }

        if (distanceKm <= 0){

            throw new IllegalArgumentException("La Distance ne doit pas être négative ou égale à 0");
        }

        this.date = date;
        this.VilleDepart = villeDepart;
        this.VilleArrivee = villeArrivee;
        this.distanceKm = distanceKm;
    }
    /**
     * Calcule le remboursement du déplacement
     * Le remboursement est calculé selon un tarif fixe de 0.35 par kilomètre
     * @return montant du remboursement
     */
    public double calculerRemboursement(){

        return distanceKm * 0.35;
    }

    /**
     * Retourne la distance parcourue
     * @return distance en kilomètres
     */
    public double getDistanceKm() {

        return distanceKm;
    }

    /**
     * Retourne la ville de départ
     * @return ville de départ
     */
    public String getVilleDepart() {

        return VilleDepart;
    }

    /**
     * Retourne la ville d’arrivée
     * @return ville d’arrivée
     */
    public String getVilleArrivee() {

        return VilleArrivee;
    }

    /**
     * Retourne la date du déplacement
     * @return date du déplacement
     */
    public LocalDate getDate() {
        return date;
    }
}
