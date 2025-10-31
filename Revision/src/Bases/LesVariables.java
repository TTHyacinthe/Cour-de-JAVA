package Bases;

public class LesVariables {
    public static void main(String[] args) {
       int  n = 4;
       int nCarre;
       final double VITESSE_DE_LA_LUMIERE = 299792458.0;
       nCarre = n*n;
      //  VITESSE_DE_LA_LUMIERE = 100;  impossible de modifier
       System.out.println("La variable n contient " + n);
       System.out.println("Le carre de " + n + "est " + nCarre);
        System.out.println("Le double de n est " + 2 * n );
    }
}
