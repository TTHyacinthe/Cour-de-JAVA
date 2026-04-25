package GestionDuPersonnel.Interfaces;

/**
 * Interface définissant la gestion des congés et elle impose le calcul
 * du nombre de nbre de jours de congés pour chaque type de personnel
 */

public interface IGestionConges {
    int calculerJoursConges();
}
