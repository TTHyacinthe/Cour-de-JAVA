package Heritage;

public class Vehicule {
   private   String marque;
   private   String couleur;
   private   String statut;
   private   int roues;
   protected    int kilometrage;
    public Vehicule(String marque, String couleur, int roues){
        this.marque = marque;
        this.couleur = couleur;
        this.roues = roues;
        this.kilometrage = 0;
        this.statut = "A l'arrêt";

    }

    void avance() {
        System.out.println("Avance");
        kilometrage++;
        statut = "En marche";
    }

    void arrete() {
        System.out.println("Arreter");
        statut = "A l'arrêt";
    }

    String getStatus(){
        return statut;
    }

    int getKilometrage(){
        return kilometrage;
    }
}
