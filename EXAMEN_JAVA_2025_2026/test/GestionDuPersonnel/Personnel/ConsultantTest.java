package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConsultantTest {

    @Test
    void testCalculSalaire() {
        Consultant c = new Consultant(
                3, "C001", "Lannister", "Tyrion",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        assertTrue(c.calculerSalaire() >= 10000);

    }

    @Test
    void testConges() {
        Consultant c = new Consultant(
                3, "C001", "Lannister", "Tyrion",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        assertEquals(20, c.calculerJoursConges());

    }

    @Test
    void testAugmentation() {
        Consultant c = new Consultant(
                3, "C001", "Lannister", "Tyrion",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        double salaire = c.appliquerAugmentation(10000);

        assertEquals(10000, salaire);
    }
}
