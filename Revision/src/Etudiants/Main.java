package Etudiants;

public class Main {
    public static void main(String[] args) {
        Etudiant e1 = new Etudiant("Alice", 15.5, 20); // création
        Etudiant e2 = new Etudiant("Bob"); //utilse le constructeur surchargé

        e1.afficherInfos();
        e2.afficherInfos();

        System.out.println("Alice a reussi ? " + e1.aReussi());
        System.out.println("Bob a reussi ? " + e2.aReussi());
        System.out.println("Nombre d'étudiants : " + Etudiant.getCompteur());
    }
}
