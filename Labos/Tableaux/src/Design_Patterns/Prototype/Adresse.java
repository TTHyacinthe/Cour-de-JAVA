package Design_Patterns.Prototype;

public class Adresse {
    private String ville;
    private int codepostal;
    private String libellerue;
    private int numero;
    private String pays;

    public Adresse(String ville, int codepostal, String libellerue, int numero, String pays) {
        this.ville = ville;
        this.codepostal = codepostal;
        this.libellerue = libellerue;
        this.numero = numero;
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public int  getCodepostal() {
        return codepostal;
    }

    public String getLibellerue() {
        return libellerue;
    }

    public int getNumero() {
        return numero;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "ville='" + ville + '\'' +
                ", codepostal=" + codepostal +
                ", libellerue='" + libellerue + '\'' +
                ", numero=" + numero +
                ", pays='" + pays + '\'' +
                '}';
    }
}
