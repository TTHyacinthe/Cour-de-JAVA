package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Paie.DpStrategy.Export.ExportFichePaie;
import GestionDuPersonnel.Paie.DpStrategy.PrimeStrategy;
import GestionDuPersonnel.Personnel.personnels;
import GestionDuPersonnel.Personnel.personnels;

/**
 * classe représentant une fiche de paie
 */

public class FichePaie {

    private personnels personnel;
    private PrimeStrategy primeStrategy;

    public FichePaie(personnels personnel,
                     PrimeStrategy primeStrategy) {
        this.personnel = personnel;
        this.primeStrategy = primeStrategy;
    }

    /**
     * calcul du salaire total
     * @return salaire total
     */

    public double calculerSalaireTotale(){
        double salaireDeBase = personnel.calculerSalaire();
        double prime = primeStrategy.calculerPrime(salaireDeBase);
        return salaireDeBase + prime;
    }

    /**
     * Export
     */
    public void exporter(ExportFichePaie exporStrategy, String chemin)
    {
        exporStrategy.exporter(this, chemin);
    }
    public personnels getPersonnel() {
        return personnel;
    }
}
