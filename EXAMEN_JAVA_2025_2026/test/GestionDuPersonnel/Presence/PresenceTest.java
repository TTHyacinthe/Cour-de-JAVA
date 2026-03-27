package GestionDuPersonnel.Presence;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.Ouvrier;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PresenceTest {

    @Test
    void testCalculHeuresOuvrier() {

        Ouvrier o = new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                10, 0 // ⚠️ on ignore heures ici
        );

        // Ajout des présences
        o.ajouterPresence(new Presence(LocalDate.now(), 8));
        o.ajouterPresence(new Presence(LocalDate.now(), 8));

        // 16 heures * 10€
        assertEquals(160, o.calculerSalaire());
    }
}
