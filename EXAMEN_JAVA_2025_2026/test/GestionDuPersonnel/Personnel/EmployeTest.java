package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    void testSalaireFixe() {

        Employe e = new Employe(
                2,
                "Michel",
                "Egon",
                LocalDate.of(2020, 1, 1),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2020,1,1),
                        null
                ),
                BaremeFonction.SENIOR
        );

        assertTrue(
                e.calculerSalaire() >= 3000
        );
    }

    @Test
    void testCongesAvecAnciennete() {

        Employe e = new Employe(
                2,
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2018,1,1),
                        null
                ),
                BaremeFonction.SENIOR
        );

        assertTrue(
                e.calculerJoursConges() >= 20
        );
    }

    @Test
    void testAugmentation() {

        Employe e = new Employe(
                2,
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2018,1,1),
                        null
                ),
                BaremeFonction.SENIOR
        );

        double salaire =
                e.appliquerAugmentation(3000);

        assertTrue(
                salaire > 3000
        );
    }
}