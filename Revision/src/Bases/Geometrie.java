package Bases;

public class Geometrie {
    public static void main(String[] args) {
        double largeur = 3.0;
        double hauteur = 4.0;
        System.out.println("surface du rectangle : " + surface(largeur, hauteur));
    }
    static double surface(double largeur, double hauteur) {
        return largeur * hauteur;
    }
}
