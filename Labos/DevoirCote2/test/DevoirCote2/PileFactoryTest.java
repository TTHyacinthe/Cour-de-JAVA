package DevoirCote2;

import factory.Pile;
import factory.TypeStructure;
import interfaces.IFilePile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PileFactoryTest {

    @Test
    void testPileWithTableau() {
        IFilePile<Integer> pile = new Pile<>(TypeStructure.TABLEAU);

        pile.push(1);
        pile.push(2);

        assertEquals(2, pile.pop().get());
    }

    @Test
    void testPileWithListe() {
        IFilePile<String> pile = new Pile<>(TypeStructure.LISTE);

        pile.push("A");
        pile.push("B");

        assertEquals("B", pile.pop().get());
    }
}
