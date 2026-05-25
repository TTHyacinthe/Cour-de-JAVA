package GestionDuPersonnel.Presence;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.Ouvrier;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PresenceTest {

    @Test
    void testPresenceValide() {

        Presence p = new Presence(
                LocalDate.now(),
                8
        );

        assertEquals(
                8,
                p.getHeuresTravaillees()
        );
    }

    @Test
    void testHeuresInvalidesSuperieur24() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Presence(
                        LocalDate.now(),
                        30
                )
        );
    }

    @Test
    void testHeuresInvalidesNegatif() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Presence(
                        LocalDate.now(),
                        -5
                )
        );
    }

    @Test
    void testDateNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Presence(
                        null,
                        8
                )
        );
    }

    @Test
    void testDoublonPresence() {

        Ouvrier o = new Ouvrier(
                1,
                "Snow",
                "John",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        Presence p1 = new Presence(
                LocalDate.now(),
                8
        );

        Presence p2 = new Presence(
                LocalDate.now(),
                10
        );

        o.ajouterPresence(p1);

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterPresence(p2)
        );
    }
}