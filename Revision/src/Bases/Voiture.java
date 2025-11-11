package Bases;
// Une classe est un modèle qui décrits les caractéristiques ou les comportements d'un type d'objet
// EXP :  Une voiture a une marque, une vitesse, et peut accélérer ou freiner
public class Voiture {
    String marque;
    int vitesse;

    void accelerer(int ajout) {
        vitesse += ajout;
    }
    // Un objet est une instance concrete d'une classe (une voiture réelle construite à partir du plan)
    public static void voiture() {
        Voiture voiture = new Voiture();
        voiture.marque = "Toyota";
        voiture.vitesse = 0;
        voiture.accelerer(100);
    }
    // Le constructeur est une methode spéciale qui sert à initialiser les objets quand on les crée
    public Voiture() {
        this.marque = marque;
        this.vitesse = vitesse;
    }
}
