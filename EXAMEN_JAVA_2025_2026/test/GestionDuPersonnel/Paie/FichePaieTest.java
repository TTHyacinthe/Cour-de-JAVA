package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Personnel.Employe;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FichePaieTest {
    @Test
    void testSalaireAvecPrimeFixe() {

        Contrat contrat = new Contrat("CDI",
                LocalDate.now(),
                null);

        Employe e = new Employe(
                1,
                "E001",
                "Tyrion",
                "Robert",
                LocalDate.now(),
                contrat,
                3000
        );

        PrimeFixe prime = new PrimeFixe(500);

        FichePaie fiche = new FichePaie(e, prime);

        double total = fiche.calculerSalaireTotale();

        assertEquals(3500, total, 0.001);
    }

}