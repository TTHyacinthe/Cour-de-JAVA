package DevoirCote1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class FileTest {
    final File file = new File(10);
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void clear() {
        file.push(1);
        file.push(2);
        file.clear();
        assertEquals(0,file.size());
    }

    @Test
    void size() {
        assertEquals(0,file.size());
        file.clear();
        assertEquals(0,file.size());
        file.push(1);
        file.push(2);
        file.clear();
    }

    @Test
    void testToString() {
    }

    @Test 
    void push() {
        file.push(1);
        file.push(2);
        assertEquals(2, file.size());
    }

    @Test
    void pop() {
    }
}