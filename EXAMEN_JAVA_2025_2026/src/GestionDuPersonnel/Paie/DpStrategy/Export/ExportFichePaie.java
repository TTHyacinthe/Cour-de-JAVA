package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Paie.FichePaie;

/**
 * Interface définissant le comportement d’exportation d’une fiche de paie.
 */
public interface ExportFichePaie
{
    void exporter(FichePaie fiche, String chemin);
}
