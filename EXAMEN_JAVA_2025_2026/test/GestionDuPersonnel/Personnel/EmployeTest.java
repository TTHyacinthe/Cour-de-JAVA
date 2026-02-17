package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    void testSalaireFixe() {
        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);
        Employe e = new Employe(
                2,
                "E001",
                "Michel",
                "Egon",
                LocalDate.of(2020, 1, 1),
                contrat,
                3000
        );

        assertEquals(3000, e.calculerSalaire());
    }

    @Test
    void testCongesAvecAnciennete() {
        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);
        Employe e = new Employe(
                2,
                "E001",
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                contrat,
                3000
        );

        int conges = e.calculerJoursConges();

        assertTrue(conges >= 20);
    }

    @Test
    void testAugmentation() {
        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);
        Employe e = new Employe(
                2,
                "E001",
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                contrat,
                3000
        );

        double nouveauSalaire = e.appliquerAugmentation(3000);

        assertTrue(nouveauSalaire >= 3000);
    }
}