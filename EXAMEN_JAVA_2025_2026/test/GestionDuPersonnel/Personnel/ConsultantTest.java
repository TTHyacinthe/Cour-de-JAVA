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
                3, "C001", "Brown", "Lucas",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        assertEquals(10000, c.calculerSalaire());
    }

    @Test
    void testConges() {
        Consultant c = new Consultant(
                3, "C001", "Brown", "Lucas",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        assertEquals(0, c.calculerJoursConges());
    }

    @Test
    void testAugmentation() {
        Consultant c = new Consultant(
                3, "C001", "Brown", "Lucas",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20
        );

        double salaire = c.appliquerAugmentation(10000);

        assertEquals(10000, salaire);
    }
}
