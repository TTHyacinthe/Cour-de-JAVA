package GestionDuPersonnel.Deplacement;

import java.time.LocalDate;

/**
 * Classe représentant un déplacement
 */
public class Deplacement {

    private LocalDate date;
    private String VilleDepart;
    private String VilleArrivee;
    private double distanceKm;

    public Deplacement(
                        LocalDate date,
                        String villeDepart,
                        String villeArrivee,
                        double distanceKm
                        ) {
        if (distanceKm <= 0){
            throw new IllegalArgumentException("Distance invalide");
        }
        this.date = date;
        this.VilleDepart = villeDepart;
        this.VilleArrivee = villeArrivee;
        this.distanceKm = distanceKm;
    }
    /**
     * Calcul du remboursement pour 0.35/km
     */
    public double calculerRemboursement(){
        return distanceKm * 0.35;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public String getVilleDepart() {
        return VilleDepart;
    }

    public String getVilleArrivee() {
        return VilleArrivee;
    }
}
