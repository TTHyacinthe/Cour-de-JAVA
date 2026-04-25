package GestionDuPersonnel.Deplacement;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeplacementTest {

    @Test
    void testRembousement(){
        Deplacement d = new Deplacement(
                LocalDate.now(),
                "Namur",
                "Bruxelles",
                100
        );
        assertEquals(
                35,
                d.calculerRemboursement(),
                0.01
        );
    }

    @Test
    void testDistanceInvalide(){
        assertThrows(IllegalArgumentException.class, () -> new Deplacement(
                LocalDate.now(),
                "Namur",
                "Bruxelles",
                0
        ));
    }
}