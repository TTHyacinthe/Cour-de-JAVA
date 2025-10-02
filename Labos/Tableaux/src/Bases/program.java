package Bases;

public class program {
    public static void main(String[] args) {
        for ( ; ; ){
            break;
        }
        int[] monTableau = {1,2,3,4,5};
        System.out.println(monTableau[0]);

        System.out.println(monTableau.length);

        int[] maTable = new int[10];
        maTable = new int[12]; // on ne change pas la taille du tableau mais la variable pointe vers un autre tableau et l'ancien tableau reste inactif en mémoire
        maTable[9] = 1;
        System.out.println(maTable.length);
        System.out.println(maTable[10]);

        // string

        System.out.println();
        System.out.println("---------------------------------");

        String msg = "Bonjour";
        System.out.println(msg);
        String texte = "Bonjour";
        System.out.println(texte);
        System.out.println(texte.length());
        System.out.println(msg == texte);
        texte = "coucou";
        System.out.println("texte = " + texte);
        System.out.println("msg = " + msg);
        System.out.println(msg == texte);
        var msg2 = "Bon";
        var msg3 = "jour";
        texte = msg2 + msg3;
        System.out.println(texte);
        System.out.println(texte == msg);

        System.out.println(texte.substring(3)); // selectionne une partie du string
        System.out.println(texte.substring(3, 5));
        System.out.println(texte.toUpperCase()); // Majuscule
        System.out.println(texte.toLowerCase()); // Minuscule
        var essaie = texte.substring(0, 1).toUpperCase() + texte.substring(1);
        System.out.println(essaie);
        System.out.println(essaie.equals("Bonjour"));
    }
}