import factory.File;
import factory.Pile;
import factory.TypeStructure;
import interfaces.IFilePile;

/**
 * Classe principale permettant de tester l'exécution du projet
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("===== TEST PILE AVEC TABLEAU =====");
        IFilePile<Integer> pileTableau = new Pile<>(TypeStructure.TABLEAU);
        pileTableau.push(10);
        pileTableau.push(20);
        pileTableau.push(30);

        System.out.println("Pop : " + pileTableau.pop().orElse(null));
        System.out.println("Taille : " + pileTableau.size());
        System.out.println();

        System.out.println("===== TEST PILE AVEC LISTE =====");
        IFilePile<String> pileListe = new Pile<>(TypeStructure.LISTE);
        pileListe.push("A");
        pileListe.push("B");
        pileListe.push("C");

        System.out.println("Pop : " + pileListe.pop().orElse(null));
        System.out.println("Taille : " + pileListe.size());
        System.out.println();

        System.out.println("===== TEST FILE AVEC TABLEAU =====");
        IFilePile<Integer> fileTableau = new File<>(TypeStructure.TABLEAU);
        fileTableau.push(1);
        fileTableau.push(2);
        fileTableau.push(3);

        System.out.println("Pop : " + fileTableau.pop().orElse(null));
        System.out.println("Taille : " + fileTableau.size());
        System.out.println();

        System.out.println("===== TEST FILE AVEC LISTE =====");
        IFilePile<String> fileListe = new File<>(TypeStructure.LISTE);
        fileListe.push("X");
        fileListe.push("Y");
        fileListe.push("Z");

        System.out.println("Pop : " + fileListe.pop().orElse(null));
        System.out.println("Taille : " + fileListe.size());
        System.out.println();

        System.out.println("===== FIN DU PROGRAMME =====");
    }
}
