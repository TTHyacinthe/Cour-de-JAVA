package Collections;

public class Voiture implements Comparable<Voiture> {
    @Override
    public int compareTo(Voiture o) {
        return o.anneeConstruction - anneeConstruction;
    }

    private String marque;
    private String modele;
    private String plaque;
    private int anneeConstruction;

    Voiture(String marque, String modele, String plaque, int anneeConstruction) {
        this.marque = marque;
        this.modele = modele;
        this.plaque = plaque;
        this.anneeConstruction = anneeConstruction;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", plaque='" + plaque + '\'' +
                ", anneeConstruction=" + anneeConstruction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Voiture voiture = (Voiture) o;
        return anneeConstruction == voiture.anneeConstruction && marque.equals(voiture.marque) && modele.equals(voiture.modele) && plaque.equals(voiture.plaque);
    }

    @Override
    public int hashCode() {
        int result = marque.hashCode();
        result = 31 * result + modele.hashCode();
        result = 31* result + plaque.hashCode();
        result = 31 * result + anneeConstruction;
        return result;
    }

    public int getAnnee() {
        return anneeConstruction;
    }
}
