package GestionDuPersonnel.Presence;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.Ouvrier;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PresenceTest {

    @Test
    void testPresenceValide() {
        Presence p = new Presence(LocalDate.now(), 8);
        assertEquals(8, p.getHeuresTravaillees());
    }

    @Test
    void testPresenceInvalide() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Presence(LocalDate.now(), 30);
        });
    }

}
