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

        List<personnels> liste = new ArrayList<>();

        liste.add(new Ouvrier(
                1, "O001", "Stark", "Arya",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                15));

        liste.add(new Employe(
                2, "E001", "Barathéon", "Joffrey",
                LocalDate.of(2020, 1, 1),
                new Contrat(TypeContrat.CDI, LocalDate.of(2020,1,1), null),
                3000, "Senior"));

        liste.add(new Consultant(
                3, "C001", "Ramsey", "Snow",
                LocalDate.now(),
                new Contrat(TypeContrat.MISSION, LocalDate.now(), null),
                500, 20));

        double total = 0;

        for (personnels p : liste) {
            total += p.calculerSalaire();
        }

        assertTrue(total > 0);
    }
}
