package GestionDuPersonnel.Formation;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.BaremeFonction;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Personnel.Ouvrier;
import GestionDuPersonnel.Presence.Presence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FormationTest {

    @Test
    void testNombreJoursFormation() {

        Formation f = new Formation(
                "Java",
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,3)
        );

        assertEquals(
                3,
                f.getNombreJours()
        );
    }

    @Test
    void testAnneeFormation() {

        Formation f = new Formation(
                "Spring",
                LocalDate.of(2024,5,10),
                LocalDate.of(2024,5,12)
        );

        assertEquals(
                2024,
                f.getAnnee()
        );
    }

    @Test
    void testDateNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Formation(
                        "Java",
                        null,
                        LocalDate.now()
                )
        );
    }

    @Test
    void testDateFinAvantDateDebut() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Formation(
                        "Java",
                        LocalDate.of(2025,1,10),
                        LocalDate.of(2025,1,1)
                )
        );
    }

    @Test
    void testAugmentationSalaireOuvrier() {

        Ouvrier o = new Ouvrier(
                1,
                "John",
                "Snow",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                10
        );

        /*
         * Total formations = 10 jours
         */

        o.ajouterFormation(
                new Formation(
                        "Formation 1",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,4)
                )
        );

        o.ajouterFormation(
                new Formation(
                        "Formation 2",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,4)
                )
        );

        o.ajouterFormation(
                new Formation(
                        "Formation 3",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,2)
                )
        );

        /*
         * 13 jours × 8h × 10€
         */

        for (int i = 0; i < 13; i++) {

            o.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        double salaire = o.calculerSalaire();

        /*
         * Salaire normal = 1040
         * Avec bonus formation +5%
         */

        assertTrue(
                salaire > 1040
        );
    }

    @Test
    void testLimiteFormationEmploye() {

        Employe e = new Employe(
                1,
                "Stark",
                "Robb",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterFormation(
                        new Formation(
                                "Formation longue",
                                LocalDate.of(2025,1,1),
                                LocalDate.of(2025,1,5)
                        )
                )
        );
    }

    @Test
    void testDoublonFormation() {

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

        Formation f1 = new Formation(
                "Java",
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,3)
        );

        Formation f2 = new Formation(
                "Java",
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,5)
        );

        o.ajouterFormation(f1);

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterFormation(f2)
        );
    }

    @Test
    void testFormationEmployePromouvable() {

        Employe e = new Employe(
                1,
                "Emily",
                "Clark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        e.ajouterFormation(
                new Formation(
                        "Formation 1",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,3)
                )
        );

        e.ajouterFormation(
                new Formation(
                        "Formation 2",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,2)
                )
        );

        assertTrue(
                e.peutEtrePromu()
        );
    }

    @Test
    void testFormationEmployeNonPromouvable() {

        Employe e = new Employe(
                1,
                "Emily",
                "Clark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        e.ajouterFormation(
                new Formation(
                        "Formation courte",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,2)
                )
        );

        assertFalse(
                e.peutEtrePromu()
        );
    }
}