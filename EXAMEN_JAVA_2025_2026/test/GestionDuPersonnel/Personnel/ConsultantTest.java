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

        c.ajouterMission(
                new Mission(
                        "Audit",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5)
                )
        );

        assertEquals(
                10000,
                c.calculerSalaire(),
                0.01
        );
    }

    @Test
    void testConsultantSansMission() {

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
                0,
                c.calculerSalaire(),
                0.01
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

        double salaire =
                c.appliquerAugmentation(10000);

        assertEquals(
                10400,
                salaire,
                0.01
        );
    }

    @Test
    void testTarifJournalierInvalide() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Consultant(
                        1,
                        "Jon",
                        "Snow",
                        LocalDate.now(),
                        new Contrat(
                                TypeContrat.INTERVENTION,
                                LocalDate.now(),
                                null
                        ),
                        -1,
                        20
                )
        );
    }

    @Test
    void testJourFactureInvalide() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Consultant(
                        1,
                        "Jon",
                        "Snow",
                        LocalDate.now(),
                        new Contrat(
                                TypeContrat.INTERVENTION,
                                LocalDate.now(),
                                null
                        ),
                        500,
                        -2
                )
        );
    }
}