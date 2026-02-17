package Collections;

import java.util.*;
import java.util.stream.Stream;

public class CollectionVoiture {
    public static void main(String[] args) {
        List<Voiture> voitures = new ArrayList<Voiture>();
        Voiture a;

        voitures.add(a = new Voiture("MA", "DA", "AAAAAAAAAAAAA", 2020));
        voitures.add(new Voiture("MB", "DB", "BBBBBBBBBBBBB", 2021));
        voitures.add(new Voiture("MC", "DC", "CCCCCCCCCCCCC", 2022));
        voitures.add(new Voiture("MD", "DD", "DDDDDDDDDDDDD", 2023));
        voitures.add(new Voiture("ME", "DE", "EEEEEEEEEEEEE", 2021));

        for(Voiture voiture : voitures){
            System.out.println(voiture);
        }

        var vexiste = voitures.contains(a);
        System.out.println("Voiture AAAAAAA existe ? " + vexiste);
        System.out.println("***********************************");

        Set<Voiture> voitures2 = new HashSet<>(voitures);


        Set<Voiture> voitures3 = new TreeSet<>(voitures);

        for(Voiture voiture : voitures2){
            System.out.println(voiture);

        }
        System.out.println("***********************************");
        for(Voiture voiture : voitures3){
            System.out.println(voiture);

        }

        Map<Voiture, Integer> map = new HashMap<>();
        map.put(a, a.hashCode());
        System.out.println("***********************************");

        Stream<Voiture> sVoitures = voitures.stream();
        List<Voiture> lVoitures = sVoitures
                .filter(v -> v.getAnnee() == 2021)
                .limit(1)
                .toList();
        for (Voiture voiture : lVoitures) {
            System.out.println(voiture);
        }
    }



}
