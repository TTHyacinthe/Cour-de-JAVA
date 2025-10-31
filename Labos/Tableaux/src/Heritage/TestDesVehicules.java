package Heritage;

public class TestDesVehicules {
    public static void main (String[] args) {
        Velo velo = new Velo("Bosch", "Rouge");

        System.out.println(velo.getStatus());
        velo.avance();
        System.out.println(velo.getStatus());
        velo.arrete();

        velo.changerMultiplicateur(2);
        velo.changerVitesse(2);

        Vehicule vehicule = new Vehicule("BMW", "Verte", 4);

        vehicule.avance();
        vehicule.arrete();
        System.out.println(vehicule.getStatus());
        System.out.println(vehicule.getKilometrage());
    }
}
