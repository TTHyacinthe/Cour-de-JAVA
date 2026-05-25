package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Deplacement.Deplacement;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Mission.Mission;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Personnel.BaremeFonction;
import GestionDuPersonnel.Personnel.Consultant;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Personnel.Ouvrier;
import GestionDuPersonnel.Presence.Presence;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires complets pour la génération PDF
 */
class FichePaiePDFTest {

    /**
     * Vérifie qu'un PDF simple employé est bien généré
     */
    @Test
    void testGenerationPdfEmploye() {

        Employe employe = new Employe(
                1,
                "Tom",
                "Jerry",
                LocalDate.of(2020, 1, 10),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2020, 1, 10),
                        null
                ),
                BaremeFonction.JUNIOR
        );

        FichePaie fiche = new FichePaie(
                employe,
                new PrimeFixe(300)
        );

        FichePaiePDF pdf = new FichePaiePDF();

        String chemin = "PDF/test_employe.pdf";

        pdf.exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);
    }

    /**
     * Vérifie la génération PDF sans prime
     */
    @Test
    void testGenerationPdfSansPrime() {

        Employe employe = new Employe(
                2,
                "Arya",
                "Stark",
                LocalDate.of(2021, 5, 1),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.of(2021, 5, 1),
                        null
                ),
                BaremeFonction.SENIOR
        );

        FichePaie fiche = new FichePaie(
                employe,
                new PasDePrime()
        );

        FichePaiePDF pdf = new FichePaiePDF();

        String chemin = "PDF/test_sans_prime.pdf";

        pdf.exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);
    }

    /**
     * Vérifie la génération PDF avec ouvrier
     */
    @Test
    void testGenerationPdfOuvrier() {

        Ouvrier ouvrier = new Ouvrier(
                3,
                "John",
                "Snow",
                LocalDate.now().minusYears(2),
                new Contrat(
                        TypeContrat.CDD,
                        LocalDate.now().minusYears(2),
                        null
                ),
                15
        );

        for (int i = 0; i < 20; i++) {

            ouvrier.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        FichePaie fiche = new FichePaie(
                ouvrier,
                new PrimeFixe(200)
        );

        FichePaiePDF pdf = new FichePaiePDF();

        String chemin = "PDF/test_ouvrier.pdf";

        pdf.exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);
    }

    /**
     * Vérifie la génération PDF consultant avec mission
     * et remboursement kilométrique
     */
    @Test
    void testGenerationPdfConsultantAvecMission() {

        Consultant consultant = new Consultant(
                4,
                "Tyrion",
                "Lannister",
                LocalDate.now().minusYears(3),
                new Contrat(
                        TypeContrat.INTERVENTION,
                        LocalDate.now().minusYears(3),
                        null
                ),
                500,
                20
        );

        Mission mission = new Mission(
                "Audit client",
                LocalDate.now(),
                LocalDate.now().plusDays(5)
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

        consultant.ajouterMission(mission);

        FichePaie fiche = new FichePaie(
                consultant,
                new PrimeFixe(1000)
        );

        FichePaiePDF pdf = new FichePaiePDF();

        String chemin = "PDF/test_consultant_mission.pdf";

        pdf.exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);
    }

    /**
     * Vérifie la génération PDF avec formations
     */
    @Test
    void testGenerationPdfAvecFormation() {

        Employe employe = new Employe(
                5,
                "Robb",
                "Stark",
                LocalDate.now().minusYears(5),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now().minusYears(5),
                        null
                ),
                BaremeFonction.SENIOR
        );

        employe.ajouterFormation(
                new Formation(
                        "Java",
                        LocalDate.of(2023, 1, 1),
                        LocalDate.of(2023, 1, 2)
                )
        );

        employe.ajouterFormation(
                new Formation(
                        "Spring Boot",
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 3)
                )
        );

        FichePaie fiche = new FichePaie(
                employe,
                new PrimeFixe(400)
        );

        FichePaiePDF pdf = new FichePaiePDF();

        String chemin = "PDF/test_formation.pdf";

        pdf.exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);
    }

    /**
     * Vérifie qu'aucune exception n'est levée
     * lorsqu'un employé n'a aucune mission
     */
    @Test
    void testGenerationSansMission() {

        Employe employe = new Employe(
                6,
                "Sansa",
                "Stark",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.JUNIOR
        );

        FichePaie fiche = new FichePaie(
                employe,
                new PasDePrime()
        );

        FichePaiePDF pdf = new FichePaiePDF();

        assertDoesNotThrow(() -> {

            pdf.exporter(
                    fiche,
                    "PDF/test_sans_mission.pdf"
            );
        });
    }

    /**
     * Vérifie qu'un fichier PDF vide n'est jamais généré
     */
    @Test
    void testFichierPdfNonVide() {

        Employe employe = new Employe(
                7,
                "Daenerys",
                "Targaryen",
                LocalDate.now(),
                new Contrat(
                        TypeContrat.CDI,
                        LocalDate.now(),
                        null
                ),
                BaremeFonction.SENIOR
        );

        FichePaie fiche = new FichePaie(
                employe,
                new PrimeFixe(500)
        );

        String chemin = "PDF/test_non_vide.pdf";

        new FichePaiePDF().exporter(fiche, chemin);

        File fichier = new File(chemin);

        assertTrue(fichier.exists());
        assertNotEquals(0, fichier.length());
    }
}