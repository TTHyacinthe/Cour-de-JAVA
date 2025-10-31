package Heritage;

public class Velo extends Vehicule{
    int plateauAvant = 1;
    int plateauArriere = 1;

    Velo(String marque, String couleur){
        super(marque, couleur, 2);
    }

    void changerMultiplicateur(int multiplicateur){
        this.plateauAvant = multiplicateur;
    }
    void changerVitesse(int vitesse){
        this.plateauArriere = vitesse;
    }

    void avance(){
        var tempsKilometrage = kilometrage;
        super.avance();
        kilometrage =  tempsKilometrage + 1;

    }
}
