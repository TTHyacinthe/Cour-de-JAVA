package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Personnel.Employe;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FichePaiePDFTest {

    @Test
    void genererPdfEmploye() {


        Contrat contrat = new Contrat(
                TypeContrat.CDI,
                LocalDate.of(2020, 1, 10),
                null
        );

        Employe emp = new Employe(
                1,
                "E001",
                "Tom",
                "Jerry",
                LocalDate.of(2020, 1, 10),
                contrat,
                2500,
                "Employé"
        );

        FichePaie fiche = new FichePaie(
                emp,
                new PrimeFixe(300)
        );

        GestionDuPersonnel.Paie.FichePaiePDF pdf = new GestionDuPersonnel.Paie.FichePaiePDF();

        String chemin = "PDF/TestEmploye.pdf";

        pdf.exporter(fiche, chemin);

        File file = new File(chemin);

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}