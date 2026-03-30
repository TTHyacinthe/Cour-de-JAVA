package GestionDuPersonnel.Contrat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContratTest {
    @Test
    void testContratValide() {
        Contrat c = new Contrat(TypeContrat.CDI, LocalDate.now(), null);
        assertEquals(TypeContrat.CDI, c.getTypeContrat());
    }


}