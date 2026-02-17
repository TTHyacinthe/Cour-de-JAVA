package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;


class OuvrierTest {

    @Test
    void testCalculSalaire() {

        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);

        Ouvrier o = new Ouvrier(
                1,
                "O001",
                "Doé",
                "Marcel",
                LocalDate.of(2024, 1, 1),
                contrat,
                15.0,
                160
        );

        double salaire = o.calculerSalaire();

        assertEquals(2400.0, salaire);
    }

    @Test
    void testCalculConges() {
        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);
        Ouvrier o = new Ouvrier(
                1,
                "O001",
                "Doé",
                "Marcel",
                LocalDate.now(),
                contrat,
                15.0,
                320
        );

        int conges = o.calculerJoursConges();

        assertEquals(4, conges);
    }

    @Test
    void testAugmentation() {
        Contrat contrat = new Contrat(
                "CDI",
                LocalDate.of(2023,1,1),
                null);
        Ouvrier o = new Ouvrier(
                1,
                "O001",
                "Doé",
                "Marcel",
                LocalDate.now(),
                contrat,
                15.0,
                160
        );

        double nouveauSalaire = o.appliquerAugmentation(2000);

        assertEquals(2100, nouveauSalaire);
    }
}