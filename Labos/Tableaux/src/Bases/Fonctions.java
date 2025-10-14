package Bases;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

public class Fonctions {
    public static void main(String[] args) {
        System.out.println(somme(1, 2));
        System.out.printf("estPaire(11) = %b%n", estPaire(11));

    }

    public static int somme(int a, int b){
        return a+b;
    }

    public static boolean estPaire(int a){
        return a%2==0;
    }

    public static void affiche(int a){
        // void ne permet pas de réutiliser la fonction dans le code
        System.out.println(a);
    }
}
