package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Paie.FichePaie;

import java.io.FileWriter;
import java.io.IOException;

public class FichePaiePDF implements ExportFichePaie {

    @Override
    public void exporter(FichePaie fiche, String chemin)
    {
        try (FileWriter writer = new FileWriter(chemin))
        {
            writer.write("========== FICHE DE PAIE ==========\n");

            writer.write("Nom : " + fiche.getPersonnel().getNom() + "\n");
            writer.write("Prénom : " + fiche.getPersonnel().getPrenom() + "\n");
            writer.write("Matricule : " + fiche.getPersonnel().getMatricule() + "\n");

            double salaireBase = fiche.getPersonnel().calculerSalaire();
            double total = fiche.calculerSalaireTotale();

            writer.write("Salaire de Base : " + salaireBase + "\n");
            writer.write("Salaire total : " + total + "\n");

            writer.write("=====================================\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
