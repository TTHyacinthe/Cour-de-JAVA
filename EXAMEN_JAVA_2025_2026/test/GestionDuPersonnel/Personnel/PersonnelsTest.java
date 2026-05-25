package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Absence.Absence;
import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Formation.Formation;
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
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
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

    @Test
    void testAjoutPresenceNull() {

        Ouvrier o = creerOuvrier();

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterPresence(null)
        );
    }

    @Test
    void testDoublonPresence() {

        Ouvrier o = creerOuvrier();

        Presence p = new Presence(
                LocalDate.now(),
                8
        );

        o.ajouterPresence(p);

        assertThrows(
                IllegalArgumentException.class,
                () -> o.ajouterPresence(p)
        );
    }

    @Test
    void testCalculTotalPresence() {

        Ouvrier o = creerOuvrier();

        o.ajouterPresence(
                new Presence(LocalDate.now(), 8)
        );

        o.ajouterPresence(
                new Presence(LocalDate.now().minusDays(1), 7)
        );

        assertEquals(
                15,
                o.calculerTotalPresence()
        );
    }

    @Test
    void testAjoutFormationNull() {

        Employe e = creerEmploye();

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterFormation(null)
        );
    }

    @Test
    void testDoublonFormation() {

        Employe e = creerEmploye();

        Formation f = new Formation(
                "Java",
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,2)
        );

        e.ajouterFormation(f);

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterFormation(f)
        );
    }

    @Test
    void testCalculFormationAnnuelle() {

        Employe e = creerEmploye();

        e.ajouterFormation(
                new Formation(
                        "Java",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,2)
                )
        );

        e.ajouterFormation(
                new Formation(
                        "Spring",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,3)
                )
        );

        assertEquals(
                3,
                e.calculerFormationAnnuelle(2025)
        );
    }

    @Test
    void testCalculTotalFormation() {

        Employe e = creerEmploye();

        e.ajouterFormation(
                new Formation(
                        "Java",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,2)
                )
        );

        e.ajouterFormation(
                new Formation(
                        "Spring",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,3)
                )
        );

        assertEquals(
                5,
                e.calculerTotalFormation()
        );
    }

    @Test
    void testAjoutMissionNull() {

        Consultant c = creerConsultant();

        assertThrows(
                IllegalArgumentException.class,
                () -> c.ajouterMission(null)
        );
    }

    @Test
    void testDoublonMission() {

        Consultant c = creerConsultant();

        Mission mission = new Mission(
                "Audit",
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        c.ajouterMission(mission);

        assertThrows(
                IllegalArgumentException.class,
                () -> c.ajouterMission(mission)
        );
    }

    @Test
    void testAjoutAbsenceNull() {

        Employe e = creerEmploye();

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterAbsence(null)
        );
    }

    @Test
    void testDoublonAbsence() {

        Employe e = creerEmploye();

        Absence absence = new Absence(
                LocalDate.of(2025,1,1),
                LocalDate.of(2025,1,2),
                "Maladie"
        );

        e.ajouterAbsence(absence);

        assertThrows(
                IllegalArgumentException.class,
                () -> e.ajouterAbsence(absence)
        );
    }

    @Test
    void testCalculTotalAbsence() {

        Employe e = creerEmploye();

        e.ajouterAbsence(
                new Absence(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,3),
                        "Maladie"
                )
        );

        assertEquals(
                3,
                e.calculerTotalAbsences()
        );
    }

    @Test
    void testSalaireParPeriodeBimensuel() {

        Ouvrier o = creerOuvrier();

        for (int i = 0; i < 20; i++) {

            o.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        assertEquals(
                1200,
                o.calculerSalaireParPeriode(),
                0.01
        );
    }

    @Test
    void testSalaireParPeriodeMensuel() {

        Employe e = creerEmploye();

        assertEquals(
                e.calculerSalaire(),
                e.calculerSalaireParPeriode(),
                0.01
        );
    }

    @Test
    void testPrendreCongesInvalide() {

        Employe e = creerEmploye();

        assertThrows(
                IllegalArgumentException.class,
                () -> e.prendreConges(0)
        );
    }

    @Test
    void testPrendreTropConges() {

        Employe e = creerEmploye();

        assertThrows(
                IllegalArgumentException.class,
                () -> e.prendreConges(50)
        );
    }

    @Test
    void testSoldeConges() {

        Employe e = creerEmploye();

        int avant = e.getSoldeConges();

        e.prendreConges(5);

        assertEquals(
                avant - 5,
                e.getSoldeConges()
        );
    }

    private Ouvrier creerOuvrier() {

        return new Ouvrier(
                1,
                "Jon",
                "Snow",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now(),
                        null
                ),
                15
        );
    }

    private Employe creerEmploye() {

        return new Employe(
                1,
                "Robb",
                "Stark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.JUNIOR
        );
    }

    private Consultant creerConsultant() {

        return new Consultant(
                1,
                "Tyrion",
                "Lannister",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now(),
                        null
                ),
                500,
                20
        );
    }
}