package Bases;

public class Program {
    public static void main(String[] args) {
        System.out.println("coucou");
        System.out.print("ne va pas à la ligne \n");
        System.out.printf("1ère valeur = %d %n", 12);

        int entier = 12;
        int unAutreEntier = 9;
        System.out.println(entier + unAutreEntier);

        // NOMBRE ENTIER

        byte unByte = 100; // -128 +128
        short unShort;
        int unInt; //4 bytes
        long unLong = 1; // 8 bytes

        //CARACTERE ('')

        char unChar; // caractère dans la table unicode 16 (16 bits) (aussi un npmbre entier de 0 à 65535)

        // BOOLEAN
        boolean unBool = true; // true - false

        // NOMBRE FLOTANT
        float unFloat;
        double unDouble;

        //Chaîne de caractère
        String unString; // ""

        unChar = 'a';
        unString = "chaîne";

        unChar = 'a' + 5;
        unString = "chaîne " + 2;

        // variable
        var debrouilleToi = 1l; // l signifie "Long"
        var uneC = "coucou";

        unInt = unByte;
        unByte = (byte) unLong;
        unLong = (long) unInt;

        var resultat = 12 + 7 / 5;
        resultat = resultat + 11;
        resultat += 11;
        resultat *= 2;
        resultat <<= 2;
        resultat >>= 2;

        unBool = ! unBool;

        if (unString == "Bonjour"){
            System.out.println("unString vaut Bonjour");
        }

        if (5 > unInt) {
            System.out.println("unInt < 5");
        }

        unInt = unBool == true ? 1 : 0;
        if (unBool == true)
            unInt = 1;
        else
            unInt = 0;


        // BOUCLES
        while (unInt < 10) {
            System.out.println(unInt++);
        }
                                            // tester et faire
        while ((unInt=2) < 10){
            break;
        }

        do{
            System.out.println(unInt);
        }while (unInt++ < 10);


    }


}
