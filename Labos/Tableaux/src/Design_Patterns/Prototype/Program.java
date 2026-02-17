package Design_Patterns.Prototype;

public class Program {
    static void main() {
        // Composition
        var compoPersonne = new Personne("Malherbe", "Geoffroy", "Namur", "rue de l'Ange", 14, 5000, "Belgique");

        // Agrégation
        var adresse1 = new Adresse("Namur", 5000, "rue de l'ange", 14, "Belgique");
        var agregPersonne = new Personne("Malherbe", "Geoffroy", adresse1);

        var personne1 = agregPersonne; // pas une copie mais 2 variables qui référencent le même objet

        var personne2 = Personne.copyOf(agregPersonne);  // shallow copy
        var personne3 = personne2.shallowCopy();         // shallow copy
        var personne4 = personne3.deepCopy();            // deep copy

        agregPersonne.setNom("Namurois");
        adresse1.setPays("FRANCE");

        System.out.println(agregPersonne);
        System.out.println("---------------------");
        System.out.println(personne3);
        System.out.println("---------------------");
        System.out.println(personne4);
    }
}
