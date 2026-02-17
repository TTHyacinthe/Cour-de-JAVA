package Taf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MotDePasseCorrectTest {

    @Test
    void motDePasseNull() {
        assertFalse(MotDePasseCorrect.valide(null));
    }

    @Test
    void motDePasseVide() {
        assertFalse(MotDePasseCorrect.valide("   "));
    }

    @Test
    void motDePasseTropCourt() {
        assertFalse(MotDePasseCorrect.valide("Ab!12"));
    }

    @Test
    void sansLettreMinuscule() {
        assertFalse(MotDePasseCorrect.valide("ABCDEFGHI!1"));
    }

    @Test
    void sansLettreMajuscule() {
        assertFalse(MotDePasseCorrect.valide("abcdefghi!1"));
    }

    @Test
    void sansCaractereSpecial() {
        assertFalse(MotDePasseCorrect.valide("Abcdefghij"));
    }

    @Test
    void motDePasseValide() {
        assertTrue(MotDePasseCorrect.valide("Abcdefg!12"));
    }
}
