package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Tableaux {
    public static void main(String[] args) {
        ArrayList<Integer> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        liste.add(5);
        System.out.println(liste.size());
        System.out.println(liste.get(3));
       // parcourir la liste de manière plus rapide
        for (Integer i : liste) {
            System.out.println(i);
        }
        // Moins rapide
        for (int j =  0; j < liste.size(); j++) {
            System.out.println(liste.get(j));

        }

        Iterator itr = liste.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        ListIterator<Integer> listIterator = liste.listIterator();




    }
}
