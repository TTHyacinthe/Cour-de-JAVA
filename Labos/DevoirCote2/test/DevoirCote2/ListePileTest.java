package DevoirCote2;

import interfaces.IFilePile;
import liste.ListePile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListePileTest {

    @Test
    void testPileOrder() {
        IFilePile<Integer> pile = new ListePile<>();

        pile.push(1);
        pile.push(2);
        pile.push(3);

        assertEquals(3, pile.pop().get());
        assertEquals(2, pile.pop().get());
        assertEquals(1, pile.pop().get());
        assertTrue(pile.pop().isEmpty());
    }
}
