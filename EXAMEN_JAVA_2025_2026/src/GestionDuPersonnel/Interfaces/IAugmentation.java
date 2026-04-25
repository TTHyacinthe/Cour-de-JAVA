package GestionDuPersonnel.Interfaces;

/**
 * Interface définissant l'augmentation d'un salaire et toute classe implémentant cette
 * interface doit fournir une méthode permettant d'aplliquer une augmentation à un salaire précisé
 */

public interface IAugmentation {
    double appliquerAugmentation(double salaireActuel);
}
