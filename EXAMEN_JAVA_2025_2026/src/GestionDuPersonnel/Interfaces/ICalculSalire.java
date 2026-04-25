package GestionDuPersonnel.Interfaces;

/**
 * Interface définissant le calcul d'un salaire et toute classe enfant de la classe personnels
 * doit implémenté cette méthode selon sa propre logique de rémunération
 */

public interface ICalculSalire {
    double calculerSalaire();
}
