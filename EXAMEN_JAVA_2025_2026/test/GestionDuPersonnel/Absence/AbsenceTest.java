package GestionDuPersonnel.Absence;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AbsenceTest {

    @Test
    void testNombreJours() {
        Absence a = new Absence(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 3),
                "Maladie"
        );

        assertEquals(3, a.getNombreJours());
    }

    @Test
    void testCertificat() {
        Absence a = new Absence(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2),
                "Maladie"
        );

        assertTrue(a.certificatObligatoire());
    }
}
