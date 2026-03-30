package GestionDuPersonnel.Paie.DpStrategy;

public class PrimePourcentage   implements PrimeStrategy {
    private double pourcentage;

    public PrimePourcentage(double pourcentage)
    {
        this.pourcentage = pourcentage;
    }

    @Override
    public double calculerPrime(double salaireDeBase)
    {
        return salaireDeBase * pourcentage / 100;
    }
}
