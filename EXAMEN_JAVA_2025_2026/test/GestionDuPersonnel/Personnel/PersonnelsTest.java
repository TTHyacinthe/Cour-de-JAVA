package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Mission.Mission;
import GestionDuPersonnel.Presence.Presence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonnelsTest {

    @Test
    void testPolymorphismeSalaire() {

        List<Personnels> liste = new ArrayList<>();

        Ouvrier ouvrier = new Ouvrier(
                1,
                "Stark",
                "Arya",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );

        ouvrier.ajouterPresence(
                new Presence(
                        LocalDate.now(),
                        8
                )
        );

        liste.add(ouvrier);

        Employe employe = new Employe(
                2,
                "Barathéon",
                "Joffrey",
                LocalDate.of(2020, 1, 1),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2020,1,1),
                        null
                ),
                BaremeFonction.SENIOR
        );

        liste.add(employe);


        Consultant consultant = new Consultant(
                3,
                "Ramsey",
                "Snow",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now(),
                        null
                ),
                500,
                20
        );

        consultant.ajouterMission(
                new Mission(
                        "Audit",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5)
                )
        );

        liste.add(consultant);

        double total = 0;

        for (Personnels p : liste) {

            total += p.calculerSalaire();
        }

        assertTrue(total > 0);
    }
}