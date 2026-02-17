package Collections;

import java.util.*;

public class LesListes {
    static void main() {
        arraylist();
        System.out.println("=============================================");
        linkedlist();
    }

    static void arraylist() {
        /*
            ArrayList = Tableau géré comme une liste
            Interface List
            Avantages :
                Rapide en lecture pour récupérer un élément précis
                Rapide à la création si on connait à l'avance le nombre de valeurs qu'il contiendra
            Inconvénients :
                Pas trié
                Peut contenir plusieurs fois la même valeur
                Pas conseillé si on doit souvent ajouter ou insérer des valeurs
            Utilisation : si on doit souvent consulter une liste non ordonnée et très peu ajouter/retirer des éléments
         */

        // Déclaration
        List<Integer> list = new ArrayList<>();
        // Création d'un tableau qui contiendra 20 valeurs
        List<Integer> list20 = new ArrayList<>(20);

        // Ajout de 8 valeurs, les doublons sont ajoutés comme toute autre valeur
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(4);

        // Création d'un tableau qui est une copie d'un autre
        List<Integer> listCopy = new ArrayList<>(list);

        // Insertion de l'élément '7' après les 4 premiers
        list.add(4, 7);

        // Ajout en tête de liste
        list.addFirst(15);

        // Ajout en fin de liste -> idem que add()
        list.addLast(12);

        // Ajout d'une liste d'éléments en un bloc
        list20.addAll(list);

        // Affichage du nombre d'éléments dans le tableau
        System.out.printf("Taille : %d%n", list.size());

        // Affichage d'un élément particulier, celui après les 3 premiers
        System.out.printf("Affiche du 4è élément : %d%n", list.get(3));

        // Retrait d'un élément de la liste et retourne true s'il existe sinon false (n'efface pas les doublons s'il y en a)
        list.remove((Integer)3);
        // Retrait du 4è élément, indice 3 dans le tableau
        list.remove(3);

        // Boucle for-each qui va parcourir toute la liste, 1 élément après l'autre depuis le début jusqu'au dernier élément
        System.out.println("-------------------------------");
        for (Integer i : list) {
            System.out.println(i);
        }

        // Boucle identique en utilisant les méthodes fournies : pas pertinent sauf si on veut juste une partie de la liste
        System.out.println("-------------------------------");
        for (int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j));
        }

        // Iterator : curseur (pointeur) qui permet de parcourir la liste du 1er élément au dernier élément
        // hasNext() retourne 'true' s'il y a encore des éléments après la position actuelle du curseur
        // next() fait avancer le curseur sur l'élément suivant et renvoie celui-ci
        Iterator itr = list.iterator();
        System.out.println("-------------------------------");
        while (itr.hasNext()) {
            var result = itr.next();
            System.out.println(result);
            // Si l'élément actuel à la valeur 5, on l'efface de la liste
            if (result.equals(5)) {
                itr.remove(); // retire la valeur
            }
        }

        // ListIterator : curseur qui permet de parcourir la liste dans les 2 sens
        ListIterator<Integer> listIterator = list.listIterator();
        // Il est possible de créer un ListIterator qui soit positionné à une place précise, par exemple après le dernier élément
        ListIterator<Integer> listIterator2 = list.listIterator(list.size());

        // Parcours de la liste en sens inverse
        // hasPrevious() retourne 'true' s'il y a encore un élément qui précéde le curseur
        // previous() fait reculer le curseur d'une place et renvoie l'élément qui s'y trouve
        System.out.println("-------------------------------");
        while (listIterator2.hasPrevious()) {
            var result = listIterator2.previous();
            System.out.println(result);
            // Si l'élément retourné est '3', remplace sa valeur dans la liste par '21'
            if (result.equals(3)) {
                listIterator2.set(21); // le set change la valeur de l'élément actuelle
            }
        }

        // Vide le tableau
        list.clear();
    }

    static void linkedlist() {
        /*
            LinkedList = liste chaînée
            Interface List
            Avantages :
                Rapide à l'insertion / ajout d'un élément
                Rapide pour retirer un élément de la liste
            Inconvénients :
                Pas trié
                Peut contenir plusieurs fois la même valeur
                Pas conseillé si on doit souvent récupérer un élément à une position donnée
            Utilisation : si on doit souvent ajouter/insérter/retirer un ou des éléments dans une liste non ordonnée
         */

        // Déclaration
        List<Integer> list = new LinkedList<>();

        // Ajout de 8 valeurs, les doublons sont ajoutés comme toute autre valeur
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(4);

        // Création d'une liste qui est une copie d'une autre
        List<Integer> listCopy = new LinkedList<>(list);

        // Insertion de l'élément '7' après les 4 premiers
        list.add(4, 7);

        // Ajout en tête de liste
        list.addFirst(15);

        // Ajout en fin de liste -> idem que add()
        list.addLast(12);

        // Ajout d'une liste d'éléments en un bloc
        listCopy.addAll(list);

        // Affichage du nombre d'éléments dans la liste
        System.out.printf("Taille : %d%n", list.size());

        // Affichage d'un élément particulier, celui après les 3 premiers
        System.out.printf("Affiche du 4è élément : %d%n", list.get(3));

        // Retrait d'un élément de la liste et retourne true s'il existe sinon false (n'efface pas les doublons s'il y en a)
        list.remove((Integer)3);
        // Retrait du 4è élément, indice 3 dans le tableau
        list.remove(3);

        // Boucle for-each qui va parcourir toute la liste, 1 élément après l'autre depuis le début jusqu'au dernier élément
        System.out.println("-------------------------------");
        for (Integer i : list) {
            System.out.println(i);
        }

        // Boucle identique en utilisant les méthodes fournies : pas pertinent sauf si on veut juste une partie de la liste
        System.out.println("-------------------------------");
        for (int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j));
        }

        // Iterator : curseur (pointeur) qui permet de parcourir la liste du 1er élément au dernier élément
        // hasNext() retourne 'true' s'il y a encore des éléments après la position actuelle du curseur
        // next() fait avancer le curseur sur l'élément suivant et renvoie celui-ci
        Iterator itr = list.iterator();
        System.out.println("-------------------------------");
        while (itr.hasNext()) {
            var result = itr.next();
            System.out.println(result);
            // Si l'élément actuel à la valeur 5, on l'efface de la liste
            if (result.equals(5)) {
                itr.remove();
            }
        }

        // ListIterator : curseur qui permet de parcourir la liste dans les 2 sens
        ListIterator<Integer> listIterator = list.listIterator();
        // Il est possible de créer un ListIterator qui soit positionné à une place précise, par exemple après le dernier élément
        ListIterator<Integer> listIterator2 = list.listIterator(list.size());

        // Parcours de la liste en sens inverse
        // hasPrevious() retourne 'true' s'il y a encore un élément qui précéde le curseur
        // previous() fait reculer le curseur d'une place et renvoie l'élément qui s'y trouve
        System.out.println("-------------------------------");
        while (listIterator2.hasPrevious()) {
            var result = listIterator2.previous();
            System.out.println(result);
            // Si l'élément retourné est '3', remplace sa valeur dans la liste par '21'
            if (result.equals(3)) {
                listIterator2.set(21);
            }
        }

        // Vide la liste
        list.clear();
    }
}
