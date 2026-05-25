package GestionDuPersonnel.Paie.DpStrategy;

/**
 * Classe permettant d'attribuer un montant de prime constant
 * Utiisation du Design Pattern Strategy pour manipuler les différentes politiques de prime
 * Auteur :  Hyacinthe TAMO
 */

public class PrimeFixe implements PrimeStrategy {

    private double montant;


    /**
     * Créer un montant de prime fixe
     * @param montant montant fixe de la prime
     */
    public PrimeFixe(double montant)
    {
        if (montant < 0) {

            throw new IllegalArgumentException("Montant invalide");
        }

        this.montant = montant;
    }

    /**
     * Calcule la prime appliquée au salaire
     * Cette implémentation retourne toujours le montant fixe défini.
     * @param salaireDeBase salaire de base du personnel
     * @return montant fixe de la prime
     */
    @Override
    public double calculerPrime(double salaireDeBase)
    {

        return montant;
    }
}
