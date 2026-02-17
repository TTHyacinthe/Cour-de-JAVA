package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Paie.DpStrategy.PrimeStrategy;
import GestionDuPersonnel.Personnel.Personnels;

public class FichePaie {

    private Personnels personnel;
    private PrimeStrategy primeStrategy;

    public FichePaie(Personnels personnel,
                     PrimeStrategy primeStrategy) {
        this.personnel = personnel;
        this.primeStrategy = primeStrategy;
    }

    public double calculerSalaireTotale(){
        double salaireDeBase = personnel.calculerSalaire();
        double prime = primeStrategy.calculerPrime(salaireDeBase);
        return salaireDeBase + prime;
    }
}
