package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Paie.DpStrategy.Export.ExportFichePaie;
import GestionDuPersonnel.Paie.DpStrategy.PrimeStrategy;
import GestionDuPersonnel.Personnel.Personnels;

/**
 * Classe réprésentant une fiche de paie associée à un membre du personnel
 * Utilisation du pattern strategy pour gérer les primes de salaires et l'exportations
 * Auteur : Hyacinthe TAMO
 */

public class FichePaie {

    private Personnels personnel;
    private PrimeStrategy primeStrategy;

    /**
     * Créer une nouvelle fiche de paie
     * @param personnel personnel concerné
     * @param primeStrategy stratégie de calcul de la prime
     */
    public FichePaie(Personnels personnel,
                     PrimeStrategy primeStrategy) {

        if (personnel == null || primeStrategy == null) {

            throw new IllegalArgumentException(
                    "Paramètres invalides"
            );
        }

        this.personnel = personnel;
        this.primeStrategy = primeStrategy;
    }

    /**
     * Calcule le salaire total du personnel
     * @return salaire total
     */
    public double calculerSalaireTotale() {

        double salaireBase = personnel.calculerSalaire();

        double prime = primeStrategy.calculerPrime(salaireBase);

        return salaireBase + prime;
    }

    /**
     * Exporte la fiche de paie
     * @param exportStrategy stratégie utilisée pour l’export
     * @param chemin chemin de destination du fichier exporté
     */
    public void exporter(ExportFichePaie exportStrategy, String chemin) {

        exportStrategy.exporter(this, chemin);
    }

    /**
     * Retourne le personnel associé à la fiche de paie
     * @return personnel concerné
     */
    public Personnels getPersonnel() {

        return personnel;
    }

    /**
     * Calcule le salaire brut
     * @return salaire brut
     */
    public double calculerSalaireBrut(){

        return calculerSalaireTotale();
    }

    /**
     * Calcule la cotisation ONSS
     * Le calcul est effectué selon un taux fixe de 13%.
     * @return montant de la cotisation ONSS
     */
    public double calculerONSS(){

        return calculerSalaireBrut() * 0.13;
    }

    /**
     * Calcule l’impôt appliqué au salaire brut
     * Le calcul est effectué selon un taux fixe de 18%.
     * @return montant de l’impôt
     */
    public double calculerImpot(){

        return calculerSalaireBrut() * 0.18;
    }

}
