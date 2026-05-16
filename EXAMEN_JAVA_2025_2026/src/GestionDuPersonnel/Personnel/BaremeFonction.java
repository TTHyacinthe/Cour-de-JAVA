package GestionDuPersonnel.Personnel;

public enum BaremeFonction {
    JUNIOR(1.0),
    SENIOR(1.3),
    CHEF_DE_PROJET(1.6);

    private final double bareme;

    BaremeFonction(double bareme) {
        this.bareme = bareme;
    }

    public double getBareme() {
        return bareme;
    }
}
