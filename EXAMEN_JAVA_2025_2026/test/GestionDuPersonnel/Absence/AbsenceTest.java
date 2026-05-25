package GestionDuPersonnel.Absence;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.Ouvrier;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AbsenceTest {

    @Test
    void testNombreJours() {

        Absence a = new Absence(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,3),
                "Maladie"
        );

        assertEquals(
                3,
                a.getNombreJours()
        );
    }

    @Test
    void testCertificatObligatoire() {

        Absence a = new Absence(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,2),
                "Maladie"
        );

        assertTrue(
                a.certificatObligatoire()
        );
    }

    @Test
    void testDateFinAvantDateDebut() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Absence(
                        LocalDate.of(2024,1,5),
                        LocalDate.of(2024,1,1),
                        "Maladie"
                )
        );
    }

    @Test
    void testDateNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Absence(
                        null,
                        LocalDate.now(),
                        "Maladie"
                )
        );
    }

    @Test
    void testAbsenceJustifiee() {

        Absence a = new Absence(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,3),
                "Maladie"
        );

        a.fournirUncerticat();

        assertTrue(
                a.absenceJustifier()
        );
    }

    @Test
    void testDoublonAbsence() {

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

        Absence a1 = new Absence(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,3),
                "Maladie"
        );

        Absence a2 = new Absence(
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,3),
                "Maladie"
        );

        o.ajouterAbsence(a1);

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterAbsence(a2)
        );
    }
}