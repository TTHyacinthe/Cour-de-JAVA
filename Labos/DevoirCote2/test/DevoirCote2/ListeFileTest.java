package DevoirCote2;

import interfaces.IFilePile;
import liste.ListeFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListeFileTest {

    @Test
    void testFileOrder() {
        IFilePile<Integer> file = new ListeFile<>();

        file.push(1);
        file.push(2);
        file.push(3);

        assertEquals(1, file.pop().get());
        assertEquals(2, file.pop().get());
        assertEquals(3, file.pop().get());
        assertTrue(file.pop().isEmpty());
    }
}
