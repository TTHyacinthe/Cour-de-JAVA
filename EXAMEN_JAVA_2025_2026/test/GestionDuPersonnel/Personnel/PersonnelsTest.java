package GestionDuPersonnel.Personnel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonnelsTest {
    @Test
    void testPolymorphismeSalaire() {

        List<Personnels> personnel = new ArrayList<>();

        personnel.add(new Ouvrier(
                1, "O001", "Doe", "John",
                LocalDate.now(), "CDI",15, 160));

        personnel.add(new Employe(
                2, "E001", "Smith", "Anna",
                LocalDate.of(2020, 1, 1), "CDI", 3000));

        personnel.add(new Consultant(
                3, "C001", "Brown", "Lucas",
                LocalDate.now(), "Mission",500, 20));

        double total = 0;

        for (Personnels p : personnel) {
            total += p.calculerSalaire();
        }

        assertTrue(total > 0);
    }

}