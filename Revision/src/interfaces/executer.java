package interfaces;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class executer {
    public static void main(String[] args) {
        Operation Addition  = (a,b) -> {
            System.out.println("a+b : ");
            return a+b;
        };
        Operation Soustraction = (a,b) -> {
            System.out.println("a-b : ");
            return a-b;
        };
        predicate<Integer> estPositif = new predicate<Integer>(){
            @Override
            public boolean test(Integer n) {
                return n > 0;

            }
        };
        predicate<Integer> estPair = n -> {
            return n % 2 == 0;
        };
        /**predicate<Integer> estPositifEtPair = estPositif.and(estPair);*/
        List<String> noms = List.of("TAMO", "FOKENG", "Lionel");

        Consumer<String> afficher = nom -> System.out.println(nom);
        noms.forEach(afficher);

        /**Function<String, Integer> longueur = s -> s.length();
        System.out.println(longueur.apply("informatique"));*/

        List<String> mots = List.of("java", "poo", "lambda");
        Function<String, String> maj = s -> s.toUpperCase();
        mots.forEach(s -> System.out.println(maj.apply(s)));


        System.out.println(Soustraction.calculer(10,20));
        System.out.println(Addition.calculer(10,20));
        System.out.println(estPair.test(2));
        System.out.println(estPair.test(5));
        System.out.println(estPositif.test(-2));
    }
}
