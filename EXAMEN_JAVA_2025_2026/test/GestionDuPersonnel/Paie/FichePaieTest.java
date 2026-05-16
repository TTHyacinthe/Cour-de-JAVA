package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.BaremeFonction;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.DpStrategy.PrimePourcentage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FichePaieTest {

    @Test
    void testSansPrime() {

        Employe e = new Employe(
                1,
                "Test",
                "User",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        FichePaie f = new FichePaie(e, new PasDePrime());

        assertEquals(3900, f.calculerSalaireTotale());
    }

    @Test
    void testPrimeFixe() {

        Employe e = new Employe(
                1,
                "Khal",
                "Drogo",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        FichePaie f = new FichePaie(e, new PrimeFixe(500));

        assertEquals(4400, f.calculerSalaireTotale());
    }

    @Test
    void testPrimePourcentage() {

        Employe e = new Employe(
                1,
                "Baelish",
                "Petyr",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        FichePaie f = new FichePaie(e, new PrimePourcentage(10));

        assertEquals(4290, f.calculerSalaireTotale());
    }
}