package GestionDuPersonnel.Paie.DpStrategy;

public class PrimeFixe implements PrimeStrategy {
    private double montant;

    public PrimeFixe(double montant)
    {

        this.montant = montant;
    }
    @Override
    public double calculerPrime(double salaireDeBase)
    {

        return montant;
    }
}
