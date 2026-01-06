package factory;

/**
 * Enumération représentant le type de structure interne à utiliser.
 * Elle permet de choisir entre une implémentation basée sur :
 * - un tableau
 * - une liste chaînée
 *
 * Cette enum est utilisée par les classes Factory (Pile et File)
 * pour instancier la bonne implémentation.
 */
public enum TypeStructure {

    /**
     * Utilisation d'une structure basée sur un tableau
     */
    TABLEAU,

    /**
     * Utilisation d'une structure basée sur une liste chaînée
     */
    LISTE
}
