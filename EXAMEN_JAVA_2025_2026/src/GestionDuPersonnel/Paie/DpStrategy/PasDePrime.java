package GestionDuPersonnel.Paie.DpStrategy;

/**
 * Classe permettant de spécifié que le personnel ne bénéficie d’aucun bonus supplémentaire sur son salaire
 * Utiisation du Design Pattern Strategy pour manipuler les différentes politiques de prime
 * Auteur :  Hyacinthe TAMO
 */

public class PasDePrime implements PrimeStrategy {

    /**
     * Retourne une prime nulle
     * @param salaireDeBase salaire de base du personnel
     * @return 0 car aucune prime n’est appliquée
     */
    @Override
    public double calculerPrime(double salaireDeBase)
    {

        return 0;
    }
}
