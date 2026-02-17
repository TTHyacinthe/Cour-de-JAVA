package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConsultantTest {

    @Test
    void testCalculSalaire() {
        Contrat contrat = new Contrat(
                "Mission",
                LocalDate.of(2023,1,1),
                null);
        Consultant c = new Consultant(
                3,
                "C001",
                "Lannister",
                "Baratheon",
                LocalDate.now(),
                contrat,
                500,
                20
        );

        assertEquals(10000, c.calculerSalaire());
    }

    @Test
    void testPasDeConges() {
        Contrat contrat = new Contrat(
                "Mission",
                LocalDate.of(2023,1,1),
                null);
        Consultant c = new Consultant(
                3,
                "C001",
                "Lannister",
                "Baratheon",
                LocalDate.now(),
                contrat,
                500,
                20
        );

        assertEquals(0, c.calculerJoursConges());
    }

    @Test
    void testPasAugmentationAutomatique() {
        Contrat contrat = new Contrat(
                "Mission",
                LocalDate.of(2023,1,1),
                null);
        Consultant c = new Consultant(
                3,
                "C001",
                "Lannister",
                "Baratheon",
                LocalDate.now(),
                contrat,
                500,
                20
        );

        assertEquals(5000, c.appliquerAugmentation(5000));
    }
}