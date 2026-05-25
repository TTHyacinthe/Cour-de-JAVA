package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Formation.Formation;
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

        assertEquals(
                4134,
                e.calculerSalaire(),
                0.01
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

    @Test
    void testFormationMaxDepassee() {

        Employe e = new Employe(
                1,
                "Robb",
                "Stark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.JUNIOR
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterFormation(
                        new Formation(
                                "Formation",
                                LocalDate.of(2025,1,1),
                                LocalDate.of(2025,1,5)
                        )
                )
        );
    }

    @Test
    void testPromotionPossible() {

        Employe e = new Employe(
                1,
                "Robb",
                "Stark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.JUNIOR
        );

        e.ajouterFormation(
                new Formation(
                        "Formation 1",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,3)
                )
        );

        e.ajouterFormation(
                new Formation(
                        "Formation 2",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,3)
                )
        );

        assertTrue(
                e.peutEtrePromu()
        );
    }
}