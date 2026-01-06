package DevoirCote2;

import factory.File;
import factory.TypeStructure;
import interfaces.IFilePile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileFactoryTest {

    @Test
    void testFileWithTableau() {
        IFilePile<Integer> file = new File<>(TypeStructure.TABLEAU);

        file.push(1);
        file.push(2);

        assertEquals(1, file.pop().get());
    }

    @Test
    void testFileWithListe() {
        IFilePile<String> file = new File<>(TypeStructure.LISTE);

        file.push("A");
        file.push("B");

        assertEquals("A", file.pop().get());
    }
}
