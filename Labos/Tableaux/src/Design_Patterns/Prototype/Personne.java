package Design_Patterns.Prototype;

public class Personne {
    private String nom;
    private String prenom;
    private Adresse adresse;

    public Personne(String nom, String prenom, Adresse adresse) {
        // Agrégation
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Personne(String nom, String prenom, String ville, String libelle, int numero, int codepostal, String pays) {
        // Composition
        this.adresse = new Adresse(ville, numero, libelle, codepostal, pays);
        this.nom = nom;
        this.prenom = prenom;
    }

    public static Personne copyOf(Personne personne) {
        var p = new Personne(personne.nom, personne.prenom, personne.adresse);
        return p;
    }

    public Personne shallowCopy() {
        var p = new Personne(nom, prenom, adresse);
        return p;
    }

    public Personne deepCopy() {
        var a = new Adresse(adresse.getVille(), adresse.getNumero(), adresse.getLibellerue(),  adresse.getCodepostal(), adresse.getPays());
        var p = new Personne(nom, prenom, a);
        return p;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    // 1) shallow copy
    // 2) deep copy
}
