package Listes;

public class Program {
    static void main() {
        ListeFile<Integer> liste = new ListeFile<>();
        System.out.printf("Nombre d'éléments : %d%n", liste.size());

        liste.push(10);
        System.out.printf("Nombre d'éléments : %d%n", liste.size());

        liste.push(20);
        liste.push(30);
        System.out.printf("Nombre d'éléments : %d%n", liste.size());

        liste.pop();
        liste.push(40);

        while (true) {
            var temp = liste.pop();
            if (!temp.isPresent()) break;

            System.out.printf("Valeur retournée : %d%n",temp.get());
        }
        System.out.println("La liste est vide");
    }
}
