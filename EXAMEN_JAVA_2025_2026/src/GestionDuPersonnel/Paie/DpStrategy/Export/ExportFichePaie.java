package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Paie.FichePaie;

public interface ExportFichePaie
{
    void exporter(FichePaie fiche, String chemin);
}
