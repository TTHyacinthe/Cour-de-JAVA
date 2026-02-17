package Design_Patterns.Exercice.V1;

public class Article_Amazon {
    private String nom;
    private int prix;
    private int stock;

    public Article_Amazon(String nom, int prix, int stock){
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public String getNom() {
        return nom;
    }

    public int  getPrix() {
        return prix;
    }

    public int getStock() {
        return stock;
    }

    public void achete (int quantite){
        if(quantite > 0 && quantite <= stock){
            stock -= quantite;
            return true;
        }
        System.out.println("pas assez d'article en stock");
        return false;
    }
    public void ajouter (int quantite){
        += quantite;

    }
    public void setPrix(int prix){
        this.prix = prix;
    }
}
