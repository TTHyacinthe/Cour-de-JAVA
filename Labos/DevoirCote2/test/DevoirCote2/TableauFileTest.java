package DevoirCote2;

import interfaces.IFilePile;
import tableau.TableauFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableauFileTest {

    @Test
    void testPushPopOrder() {
        IFilePile<Integer> file = new TableauFile<>();

        file.push(1);
        file.push(2);
        file.push(3);

        assertEquals(1, file.pop().get());
        assertEquals(2, file.pop().get());
        assertEquals(3, file.pop().get());
        assertTrue(file.pop().isEmpty());
    }
}
