package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Personnel.Employe;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test de l'export PDF
 */
class FichePaiePDFTest {

    @Test
    void testExportFichier() {

        Employe e = new Employe(
                1, "E001", "Cersei", "Lannister",
                LocalDate.now(),
                new Contrat(TypeContrat.CDI, LocalDate.now(), null),
                3000,
                "Dev"
        );

        FichePaie fiche = new FichePaie(e, new PasDePrime());

        String chemin = "D:\\Esa-Namur 2\\Cour-de-JAVA\\EXAMEN_JAVA_2025_2026\\PDF\\ficheSalaire.txt";

        fiche.exporter(new FichePaiePDF(), chemin);

        File file = new File(chemin);

        assertTrue(file.exists());


    }
}
