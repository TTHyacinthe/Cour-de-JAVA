package GestionDuPersonnel.Contrat;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ContratTest {

    @Test
    void testContratValide() {

        Contrat c = new Contrat(
                TypeContrat.CDI,
                LocalDate.now(),
                null
        );

        assertEquals(
                TypeContrat.CDI,
                c.getTypeContrat()
        );
    }

    @Test
    void testTypeNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Contrat(
                        null,
                        LocalDate.now(),
                        null
                )
        );
    }

    @Test
    void testDateDebutNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Contrat(
                        TypeContrat.CDI,
                        null,
                        null
                )
        );
    }

    @Test
    void testDateFinAvantDebut() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2025,1,10),
                        LocalDate.of(2025,1,1)
                )
        );
    }
}