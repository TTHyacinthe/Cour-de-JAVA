package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Presence.Presence;
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
                15
        );

        for (int i = 0; i < 20; i++) {
            o.ajouterPresence(
                    new Presence(LocalDate.now().minusDays(i), 8)
            );
        }

        assertEquals(2400, o.calculerSalaire(), 0.01);
    }


    @Test
    void testConges() {
        Ouvrier o = new Ouvrier(
                1, "O001", "Marta", "Pierette",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15
        );

        for (int i = 0; i < 20; i++) {
            o.ajouterPresence(
                    new Presence(LocalDate.now().minusDays(i), 8)
            );
        }

        assertTrue(o.calculerJoursConges() > 0);
    }



    @Test
    void testAugmentation() {
        Ouvrier o = new Ouvrier(
                1, "O001", "Tyrell", "Margaery",
                LocalDate.now().minusYears(4),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15
        );

        double salaire = o.appliquerAugmentation(2000);

        assertEquals(2080, salaire, 0.01);
    }


}
