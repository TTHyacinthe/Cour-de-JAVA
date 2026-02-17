package interfaces;

import java.util.ArrayList;
import java.util.function.*;

public class TestInterfaceFonctionnelle {
    public static void main(String[] args) {
        MonInterfaces test = new MonInterfaces(){
            @Override
            public void affiche() {
                System.out.println("salut");
            }
        };
        MonInterfaces test2 = new MonInterfaces(){
            @Override
            public void affiche() {
                System.out.println("coucou");
            }
        };
        MonInterfaces test3 = () -> System.out.println("test3");

        MonInterfaces test4 = () -> {
            System.out.println("test4");
            var i = 2 * 3;
            System.out.printf("%d%n",i);
        };
        Lambda1Param l1 = s -> System.out.println(s);
        Lambda2Params l2 = (s1, s2) -> {
            System.out.println(s1);
            System.out.println(s2);

        };
        LambdaReturn lr1 = new LambdaReturn() {
            @Override
            public long retour() {
                return System.currentTimeMillis();
            }
        };
        LambdaReturn lr2 = () -> System.currentTimeMillis();
        LambdaReturn lr3 = () -> {
            long a = 15;
            return a * 100;
        };
        test.affiche();
        test2.affiche();
        test3.affiche();
        test4.affiche();
        l1.affiche("1 paramètre");
        l2.affiche("p1", "p2");
        System.out.println(lr2.retour());
        System.out.println(lr3.retour());
        System.out.println("********************************************************");
        Afficher(test);
        Afficher(test2);
        Afficher(test3);
        Afficher(test4);
        Afficher(() -> System.out.println("test"));
        Afficher(() -> {
            var x = getx();
            var y = gety();
            System.out.printf("La somme est égale à %d%n", x + y);
        });
        Executer(s1 -> {
            var x = getx();
            var y = gety();
            System.out.printf("La forme %s à une surface de %d%n", s1, y * x );
        }, "carré");

        Predicate<String> commenceParA = (String s) -> s.startsWith("A");
        System.out.println(commenceParA.test("Bonjour"));
        System.out.println(commenceParA.test("Attention"));
        BiPredicate<Integer, Integer> estPlusPetitQue = (x1, x2) -> x1 < x2;
        System.out.printf("3 < 5 ? %b%n", estPlusPetitQue.test(3, 5));
        System.out.printf("3 < 1 ? %b%n", estPlusPetitQue.test(3, 1));
        Consumer<String> consumer = x -> System.out.println(x);
        ArrayList<String> al = new ArrayList<>();
        al.add("1");
        al.add("2");
        al.add("3");
        al.forEach(consumer);
        for(String s : al) {
            System.out.println(s);
        }
        Supplier<String> supplier = () -> "test";
        Function<String, Integer> function = x -> Integer.parseInt(x);
        System.out.println(function.apply("13"));
    }
    public static void Afficher(MonInterfaces test) {
        test.affiche();
    }
    public static int getx() {return 4;}
    public static int gety() {return 5;}
    public static void Executer(Lambda1Param l1, String s){
        l1.affiche(s);
    }

}
