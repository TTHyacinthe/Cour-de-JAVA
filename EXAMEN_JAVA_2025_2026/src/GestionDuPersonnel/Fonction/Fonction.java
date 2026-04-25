package GestionDuPersonnel.Fonction;

/**
 * Représentation d'une fonction dans l'entreprise
 */

public class Fonction {

    private TypeFonction typeFonction;
    private double bareme;

    public Fonction (TypeFonction typeFonction, double bareme) {

        this.typeFonction = typeFonction;
        this.bareme = bareme;
    }

    public TypeFonction getTypeFonction() {
        return typeFonction;
    }

    public double getBareme() {
        return bareme;
    }

    public void setTypeFonction(TypeFonction typeFonction) {
        this.typeFonction = typeFonction;
    }

    public void setBareme(double bareme) {
        this.bareme = bareme;
    }

    @Override
    public String toString() {

        return typeFonction + " - " + bareme + " €";
    }

}
