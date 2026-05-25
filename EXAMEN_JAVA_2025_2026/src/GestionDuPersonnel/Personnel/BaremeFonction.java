package GestionDuPersonnel.Personnel;

/**
 * Réprésentation des différent barèmes de fonction d'un employé
 * Auteur : Hyacinthe TAMO
 */

public enum BaremeFonction {
    JUNIOR(1.0),
    SENIOR(1.3),
    CHEF_DE_PROJET(1.6);

    private final double bareme;

    /**
     * Crée un barème de fonction
     * @param bareme coefficient associé au niveau de fonction
     */
    BaremeFonction(double bareme) {
        this.bareme = bareme;
    }

    /**
     * Retourne le coefficient du barème
     * @return coefficient du barème
     */
    public double getBareme() {
        return bareme;
    }
}
