package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Presence.Presence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OuvrierTest {

    @Test
    void testCalculSalaire() {

        Ouvrier o = new Ouvrier(
                1,
                "Tyrell",
                "Margaery",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        for (int i = 0; i < 20; i++) {

            o.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        assertEquals(
                2400,
                o.calculerSalaire(),
                0.01
        );
    }

    @Test
    void testConges() {

        Ouvrier o = new Ouvrier(
                1,
                "Tyrell",
                "Margaery",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        for (int i = 0; i < 20; i++) {

            o.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        assertEquals(
                2,
                o.calculerJoursConges()
        );
    }

    @Test
    void testAugmentation() {

        Ouvrier o = new Ouvrier(
                1,
                "Tyrell",
                "Margaery",
                LocalDate.now().minusYears(4),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        double salaire =
                o.appliquerAugmentation(2000);

        assertEquals(
                2080,
                salaire,
                0.01
        );
    }

    @Test
    void testTauxHoraireInvalide() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Ouvrier(
                        1,
                        "Jon",
                        "Snow",
                        LocalDate.now(),
                        new Contrat(
                                TypeContrat.CDD,
                                LocalDate.now(),
                                null
                        ),
                        -5
                )
        );
    }

    @Test
    void testFormationMaxDepassee() {

        Ouvrier o = new Ouvrier(
                1,
                "Jon",
                "Snow",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        o.ajouterFormation(
                new Formation(
                        "Formation 1",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,2)
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterFormation(
                        new Formation(
                                "Formation 2",
                                LocalDate.of(2025,2,1),
                                LocalDate.of(2025,2,4)
                        )
                )
        );
    }
}