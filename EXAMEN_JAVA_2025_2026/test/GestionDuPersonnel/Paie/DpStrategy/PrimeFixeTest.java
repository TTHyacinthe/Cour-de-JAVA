package GestionDuPersonnel.Paie.DpStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la stratégie PrimeFixe
 */
class PrimeFixeTest {

    @Test
    void testPrimeFixe() {
        PrimeFixe prime = new PrimeFixe(500);

        double resultat = prime.calculerPrime(3000);

        assertEquals(500, resultat);
    }
}
