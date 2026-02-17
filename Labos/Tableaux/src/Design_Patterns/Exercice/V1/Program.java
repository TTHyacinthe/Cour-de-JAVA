package Design_Patterns.Exercice.V1;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        var random = new Random();

        var usbStick = new Article_Amazon("USB Stick 256 GB", 40, 20);
        usbStick.setPrix(random.nextInt(21));

        while (usbStick.getPrix() > 35){
            usbStick.setPrix(random.nextInt(30, 50))
        }
        if (usbStick.achete(10))
            System.out.println("10 articles achetés au prix de : " + usbStick.getPrix());
        else System.out.println("On essaie plus tard");
    }
}
