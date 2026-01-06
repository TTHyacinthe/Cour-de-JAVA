package DevoirCote2;

import interfaces.IFilePile;
import org.junit.jupiter.api.Test;
import tableau.TableauPile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableauPileTest {

    @Test
    void testPushPopOrder() {
        IFilePile<Integer> pile = new TableauPile<>();

        pile.push(1);
        pile.push(2);
        pile.push(3);

        assertEquals(3, pile.pop().get());
        assertEquals(2, pile.pop().get());
        assertEquals(1, pile.pop().get());
        assertTrue(pile.pop().isEmpty());
    }

    @Test
    void testSize() {
        IFilePile<String> pile = new TableauPile<>();

        pile.push("A");
        pile.push("B");

        assertEquals(2, pile.size());
    }

    @Test
    void testClear() {
        IFilePile<Integer> pile = new TableauPile<>();

        pile.push(10);
        pile.push(20);
        pile.clear();

        assertEquals(0, pile.size());
        assertTrue(pile.pop().isEmpty());
    }
}
