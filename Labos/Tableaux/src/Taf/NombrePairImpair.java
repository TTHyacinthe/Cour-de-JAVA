package Taf;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NombrePairImpair {
    public static void main(String[] args) {
        int [] nombres = new int [50];
        // Remplissage aléatoire du tableau
        Random random = new Random();
        for(int i = 0; i < nombres.length; i++){
            nombres[i] = random.nextInt(100);
        }
        Predicate<Integer> estpair = n -> n % 2 == 0;
        // affichage
        Consumer<Integer> afficherPairOuImpair = n -> {
            if(estpair.test(n)){
                System.out.println( n + " -> pair");
            }else {
                System.out.println( n + " -> impair");
            }
        };
        // parcours et affiche
        for(int n : nombres){
            afficherPairOuImpair.accept(n);
        }
    }

}
