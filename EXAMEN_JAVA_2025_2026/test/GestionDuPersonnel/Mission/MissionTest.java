package GestionDuPersonnel.Mission;

import GestionDuPersonnel.Deplacement.Deplacement;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MissionTest {

    @Test
    void testTotalRemboursement() {

        Mission mission = new Mission(
                "Installation client",
                LocalDate.now(),
                LocalDate.now()
        );

        mission.ajouterDeplacement(
                new Deplacement(
                        LocalDate.now(),
                        "Namur",
                        "Bruxelles",
                        100
                )
        );

        mission.ajouterDeplacement(
                new Deplacement(
                        LocalDate.now(),
                        "Bruxelles",
                        "Liège",
                        50
                )
        );

        assertEquals(
                52.5,
                mission.calculerTotalRemboursement(),
                0.01
        );
    }
}
