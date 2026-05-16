package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Mission.Mission;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConsultantTest {

    @Test
    void testCalculSalaire() {

        Consultant c = new Consultant(
                3,
                "Lannister",
                "Tyrion",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now(),
                        null
                ),
                500,
                20
        );

        // Ajout d'une mission obligatoire
        c.ajouterMission(
                new Mission(
                        "Audit",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5)
                )
        );

        /*
         * 500 × 20 = 10000
         */
        assertTrue(
                c.calculerSalaire() >= 10000
        );
    }

    @Test
    void testConges() {

        Consultant c = new Consultant(
                3,
                "Lannister",
                "Tyrion",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now(),
                        null
                ),
                500,
                20
        );

        assertEquals(
                20,
                c.calculerJoursConges()
        );
    }

    @Test
    void testAugmentation() {

        Consultant c = new Consultant(
                3,
                "Lannister",
                "Tyrion",
                LocalDate.now().minusYears(4),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now(),
                        null
                ),
                500,
                20
        );

        /*
         * 4 ans = +4%
         */
        double salaire =
                c.appliquerAugmentation(10000);

        assertEquals(
                10400,
                salaire,
                0.01
        );
    }
}