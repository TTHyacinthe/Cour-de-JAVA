package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Paie.DpStrategy.Export.ExportFichePaie;
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

    /**
     * Calcul du salaire total
     */
    public double calculerSalaireTotale() {

        double salaireBase =
                personnel.calculerSalaire();

        double prime =
                primeStrategy.calculerPrime(salaireBase);

        return salaireBase + prime;
    }

    /**
     * Export du document
     */
    public void exporter(ExportFichePaie exportStrategy,
                         String chemin) {

        exportStrategy.exporter(this, chemin);
    }

    public Personnels getPersonnel() {
        return personnel;
    }
}
