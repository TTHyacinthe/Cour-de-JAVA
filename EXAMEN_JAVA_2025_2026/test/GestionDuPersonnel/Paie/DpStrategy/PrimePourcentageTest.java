package GestionDuPersonnel.Paie.DpStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la stratégie PrimePourcentage
 */
class PrimePourcentageTest {

    @Test
    void testPrimePourcentage() {
        PrimePourcentage prime = new PrimePourcentage(10);

        double resultat = prime.calculerPrime(2000);

        assertEquals(200, resultat);
    }
}
