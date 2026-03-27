package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OuvrierTest {

    @Test
    void testCalculSalaire() {
        Ouvrier o = new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15, 160 // inutile ici
        );

        o.ajouterPresence(
                new GestionDuPersonnel.Presence.Presence(LocalDate.now(), 160)
        );

        assertEquals(2400, o.calculerSalaire(), 0.01);
    }

    @Test
    void testConges() {
        Ouvrier o = new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15, 160
        );

        assertTrue(o.calculerJoursConges() > 0);
    }

    @Test
    void testAugmentation() {
        Ouvrier o = new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15, 160
        );

        double salaire = o.appliquerAugmentation(2000);

        assertTrue(salaire > 2000);
    }
}
