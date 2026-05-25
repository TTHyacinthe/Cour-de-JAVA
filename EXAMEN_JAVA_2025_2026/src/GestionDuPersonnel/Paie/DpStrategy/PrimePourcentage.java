package GestionDuPersonnel.Paie.DpStrategy;

/**
 * Cette classe permet de calculer une prime proportionnelle au salaire de base du personnel
 * Auteur : Hyacinthe TAMO
 */

public class PrimePourcentage   implements PrimeStrategy {

    private double pourcentage;

    /**
     * Crée une prime basée sur un pourcentage
     * @param pourcentage pourcentage appliqué au salaire de base
     */
    public PrimePourcentage(double pourcentage)
    {
        if (pourcentage < 0) {

            throw new IllegalArgumentException("Pourcentage invalide");
        }

        this.pourcentage = pourcentage;
    }

    /**
     * Calcule la prime à partir du salaire de base
     * La prime correspond à un pourcentage du salaire de base.
     * @param salaireDeBase salaire de base du personnel
     * @return montant de la prime calculée
     */
    @Override
    public double calculerPrime(double salaireDeBase)
    {

        return salaireDeBase * pourcentage / 100;
    }
}
