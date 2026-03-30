package GestionDuPersonnel.Formation;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Personnel.Ouvrier;
import GestionDuPersonnel.Presence.Presence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FormationTest {

    @Test
    void testAugmentationOuvrier() {

        Ouvrier o = new Ouvrier(
                1, "O001", "Jon", "Snow",
                LocalDate.now(),
                new Contrat(TypeContrat.CDD, LocalDate.now(), null),
                10
        );

        // Formations réparties sur plusieurs années
        o.ajouterFormation(new Formation("première formation",
                LocalDate.of(2023,1,1),
                LocalDate.of(2023,1,4)));

        o.ajouterFormation(new Formation("Deuxième formation",
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,4)));

        o.ajouterFormation(new Formation("Troisième formation",
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,2)));

        for (int i = 0; i < 13; i++) {
            o.ajouterPresence(
                    new Presence(LocalDate.now().minusDays(i), 8)
            );
        }
        double salaire = o.calculerSalaire();

        assertTrue(salaire > 1040); // +5%
    }

    @Test
    void testLimiteEmploye() {

        GestionDuPersonnel.Personnel.Employe e =
                new GestionDuPersonnel.Personnel.Employe(
                        1, "E001", "Stark", "Robb",
                        LocalDate.now(),
                        new Contrat(TypeContrat.CDI, LocalDate.now(), null),
                        3000,
                        "Senior"
                );

        assertThrows(IllegalArgumentException.class, () -> {
            e.ajouterFormation(new Formation("Formation",
                    LocalDate.of(2024,1,1),
                    LocalDate.of(2024,1,5))); // 5 jours → dépasse 3
        });
    }
}
