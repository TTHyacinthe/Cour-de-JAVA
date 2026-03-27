package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonnelsTest {

    @Test
    void testPolymorphismeSalaire() {

        List<Personnels> liste = new ArrayList<>();

        liste.add(new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15, 160));

        liste.add(new Employe(
                2, "E001", "Smith", "Anna",
                LocalDate.of(2020, 1, 1),
                new Contrat(TypeContrat.CDI, LocalDate.of(2020,1,1), null),
                3000, "Senior"));

        liste.add(new Consultant(
                3, "C001", "Brown", "Lucas",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20));

        double total = 0;

        for (Personnels p : liste) {
            total += p.calculerSalaire();
        }

        assertTrue(total > 0);
    }
}
