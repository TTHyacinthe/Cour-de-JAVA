package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Fonction.Fonction;
import GestionDuPersonnel.Fonction.TypeFonction;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.DpStrategy.PrimePourcentage;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FichePaieTest {
    Fonction fonctionSenior = new Fonction(
            TypeFonction.SENIOR,
            3000
    );

    @Test
    void testSansPrime() {
        Employe e = new Employe(
                1, "E001", "Test", "User",
                LocalDate.now(),
                new Contrat(TypeContrat.CDI, LocalDate.now(), null),
                3000,
                "Senior"
        );

        FichePaie f = new FichePaie(e, new PasDePrime());

        assertEquals(3000, f.calculerSalaireTotale());
    }

    @Test
    void testPrimeFixe() {
        Employe e = new Employe(
                1, "E001", "Khal", "Drogo",
                LocalDate.now(),
                new Contrat(TypeContrat.CDI, LocalDate.now(), null),
                3000,
                "Senior"
        );

        FichePaie f = new FichePaie(e, new PrimeFixe(500));

        assertEquals(3500, f.calculerSalaireTotale());
    }

    @Test
    void testPrimePourcentage() {
        Employe e = new Employe(
                1, "E001", "Baelish", "Petyr",
                LocalDate.now(),
                new Contrat(TypeContrat.CDI, LocalDate.now(), null),
                3000,
                "Senior"
        );

        FichePaie f = new FichePaie(e, new PrimePourcentage(10));

        assertEquals(3300, f.calculerSalaireTotale());
    }
}
