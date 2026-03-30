package GestionDuPersonnel.Paie.DpStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de la stratégie PasDePrime
 */
class PasDePrimeTest {

    @Test
    void testPasDePrime() {
        PasDePrime prime = new PasDePrime();

        double resultat = prime.calculerPrime(3000);

        assertEquals(0, resultat);
    }
}
