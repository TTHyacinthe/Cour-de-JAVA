package GestionDuPersonnel;

import GestionDuPersonnel.Absence.Absence;
import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.DpStrategy.PrimePourcentage;
import GestionDuPersonnel.Paie.FichePaiePDF;
import GestionDuPersonnel.Personnel.Consultant;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Personnel.Ouvrier;
import GestionDuPersonnel.Presence.Presence;

import java.time.LocalDate;

/**
 * Classe principale permettant de démontrer
 * toutes les règles métier du projet
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("GESTION DU PERSONNEL");
        System.out.println("====================================");

        /*
         * =========================================================
         * 1. CREATION DES CONTRATS
         * =========================================================
         */

        Contrat contratOuvrier = new Contrat(
                TypeContrat.CDD,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31)
        );

        Contrat contratEmploye = new Contrat(
                TypeContrat.CDI,
                LocalDate.of(2020, 1, 1),
                null
        );

        Contrat contratConsultant = new Contrat(
                TypeContrat.MISSION,
                LocalDate.of(2025, 1, 1),
                null
        );

        /*
         * =========================================================
         * 2. CREATION DES PERSONNELS
         * =========================================================
         */

        Ouvrier ouvrier = new Ouvrier(
                1,
                "O001",
                "Tyrell",
                "Margaery",
                LocalDate.of(2022, 1, 1),
                contratOuvrier,
                15
        );

        Employe employe = new Employe(
                2,
                "E001",
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                contratEmploye,
                3000,
                "Développeur Senior"
        );

        Consultant consultant = new Consultant(
                3,
                "C001",
                "Lannister",
                "Tyrion",
                LocalDate.of(2024, 1, 1),
                contratConsultant,
                500,
                20
        );

        /*
         * =========================================================
         * 3. GESTION DES PRESENCES
         * =========================================================
         */

        System.out.println("\n===== PRESENCES =====");

        for (int i = 0; i < 20; i++)
        {

            ouvrier.ajouterPresence(
                    new Presence(LocalDate.now().minusDays(i), 8));
        }


        System.out.println(
                "Total heures ouvrier : "
                        + ouvrier.calculerTotalPresence()
        );

        /*
         * =========================================================
         * 4. GESTION DES FORMATIONS
         * =========================================================
         */

        System.out.println("\n===== FORMATIONS =====");

        /*
         * OUVRIER :
         * max 4 jours par an
         * +5% après 10 jours cumulés
         */

        ouvrier.ajouterFormation(
                new Formation(
                        "Sécurité",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,4)
                )
        );

        ouvrier.ajouterFormation(
                new Formation(
                        "Machines",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,4)
                )
        );

        ouvrier.ajouterFormation(
                new Formation(
                        "Technique",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,2)
                )
        );

        System.out.println(
                "Formation ouvrier validée"
        );

        /*
         * EMPLOYE :
         * max 3 jours par an
         * promotion après 5 jours cumulés
         */

        employe.ajouterFormation(
                new Formation(
                        "Java",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,3)
                )
        );

        employe.ajouterFormation(
                new Formation(
                        "Spring",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,2)
                )
        );

        System.out.println(
                "Employé promouvable : "
                        + employe.peutEtrePromu()
        );

        /*
         * =========================================================
         * 5. GESTION DES ABSENCES
         * =========================================================
         */

        System.out.println("\n===== ABSENCES =====");

        employe.ajouterAbsence(
                new Absence(
                        LocalDate.of(2025,2,1),
                        LocalDate.of(2025,2,5),
                        "Maladie"
                )
        );

        System.out.println(
                "Total absences employé : "
                        + employe.calculerTotalAbsences()
        );

        /*
         * Vérification certificat médical
         */

        Absence absence = new Absence(
                LocalDate.of(2025,3,1),
                LocalDate.of(2025,3,3),
                "Maladie"
        );

        System.out.println(
                "Certificat obligatoire : "
                        + absence.certificatObligatoire()
        );

        /*
         * =========================================================
         * 6. CALCUL DES CONGES
         * =========================================================
         */

        System.out.println("\n===== CONGES =====");

        System.out.println(
                "Congés ouvrier : "
                        + ouvrier.calculerJoursConges()
        );

        System.out.println(
                "Congés employé : "
                        + employe.calculerJoursConges()
        );

        System.out.println(
                "Congés consultant : "
                        + consultant.calculerJoursConges()
        );

        /*
         * =========================================================
         * 7. AUGMENTATIONS
         * =========================================================
         */

        System.out.println("\n===== AUGMENTATIONS =====");

        double nouveauSalaireEmploye =
                employe.appliquerAugmentation(
                        employe.calculerSalaire()
                );

        System.out.println(
                "Salaire employé augmenté : "
                        + nouveauSalaireEmploye
        );

        double nouveauSalaireOuvrier =
                ouvrier.appliquerAugmentation(
                        ouvrier.calculerSalaire()
                );

        System.out.println(
                "Salaire ouvrier augmenté : "
                        + nouveauSalaireOuvrier
        );

        /*
         * =========================================================
         * 8. CALCUL DES SALAIRES
         * =========================================================
         */

        System.out.println("\n===== SALAIRES =====");

        System.out.println(
                "Salaire ouvrier : "
                        + ouvrier.calculerSalaire()
        );

        System.out.println(
                "Salaire employé : "
                        + employe.calculerSalaire()
        );

        System.out.println(
                "Salaire consultant : "
                        + consultant.calculerSalaire()
        );

        /*
         * =========================================================
         * 9. FICHES DE PAIE
         * =========================================================
         */

        System.out.println("\n===== FICHES DE PAIE =====");

        /*
         * Employé avec prime fixe
         */

        FichePaie ficheEmploye = new FichePaie(
                employe,
                new PrimeFixe(500)
        );

        ficheEmploye.exporter(
                new FichePaiePDF(),
                "PDF/fiche_employe.pdf"
        );

        /*
         * Ouvrier avec prime %
         */

        FichePaie ficheOuvrier = new FichePaie(
                ouvrier,
                new PrimePourcentage(10)
        );

        ficheOuvrier.exporter(
                new FichePaiePDF(),
                "PDF/fiche_ouvrier.pdf"
        );

        /*
         * Consultant sans prime
         */

        FichePaie ficheConsultant = new FichePaie(
                consultant,
                new PasDePrime()
        );

        ficheConsultant.exporter(
                new FichePaiePDF(),
                "PDF/fiche_consultant.pdf"
        );

        /*
         * =========================================================
         * 10. FIN
         * =========================================================
         */

        System.out.println("\n====================================");
        System.out.println("FIN DU PROGRAMME");
        System.out.println("FICHE DE PAIE GENERES AVEC SUCCES");
        System.out.println("====================================");
    }
}
