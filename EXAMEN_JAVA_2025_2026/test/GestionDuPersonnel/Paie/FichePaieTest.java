package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.DpStrategy.PrimePourcentage;
import GestionDuPersonnel.Personnel.BaremeFonction;
import GestionDuPersonnel.Personnel.Employe;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FichePaieTest {

    private Employe creerEmploye() {

        return new Employe(
                1,
                "Lannister",
                "Tyrion",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );
    }

    @Test
    void testSansPrime() {

        Employe e = creerEmploye();

        FichePaie f =
                new FichePaie(
                        e,
                        new PasDePrime()
                );

        assertEquals(
                3900,
                f.calculerSalaireTotale(),
                0.01
        );
    }

    @Test
    void testPrimeFixe() {

        Employe e = creerEmploye();

        FichePaie f =
                new FichePaie(
                        e,
                        new PrimeFixe(500)
                );

        assertEquals(
                4400,
                f.calculerSalaireTotale(),
                0.01
        );
    }

    @Test
    void testPrimePourcentage() {

        Employe e = creerEmploye();

        FichePaie f =
                new FichePaie(
                        e,
                        new PrimePourcentage(10)
                );

        assertEquals(
                4290,
                f.calculerSalaireTotale(),
                0.01
        );
    }

    @Test
    void testCalculONSS() {

        Employe e = creerEmploye();

        FichePaie f =
                new FichePaie(
                        e,
                        new PasDePrime()
                );

        assertEquals(
                507,
                f.calculerONSS(),
                0.01
        );
    }

    @Test
    void testCalculImpot() {

        Employe e = creerEmploye();

        FichePaie f =
                new FichePaie(
                        e,
                        new PasDePrime()
                );

        assertEquals(
                702,
                f.calculerImpot(),
                0.01
        );
    }

    @Test
    void testPersonnelNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new FichePaie(
                        null,
                        new PasDePrime()
                )
        );
    }

    @Test
    void testPrimeStrategyNull() {

        Employe e = creerEmploye();

        assertThrows(
                IllegalArgumentException.class,
                () -> new FichePaie(
                        e,
                        null
                )
        );
    }
}