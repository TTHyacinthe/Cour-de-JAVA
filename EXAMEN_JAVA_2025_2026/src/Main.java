import GestionDuPersonnel.Absence.Absence;
import GestionDuPersonnel.Contrat.Contrat;
import GestionDuPersonnel.Contrat.TypeContrat;
import GestionDuPersonnel.Deplacement.Deplacement;
import GestionDuPersonnel.Formation.Formation;
import GestionDuPersonnel.Mission.Mission;
import GestionDuPersonnel.Paie.DpStrategy.Export.FichePaiePDF;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Paie.DpStrategy.PasDePrime;
import GestionDuPersonnel.Paie.DpStrategy.PrimeFixe;
import GestionDuPersonnel.Paie.DpStrategy.PrimePourcentage;
import GestionDuPersonnel.Personnel.BaremeFonction;
import GestionDuPersonnel.Personnel.Consultant;
import GestionDuPersonnel.Personnel.Employe;
import GestionDuPersonnel.Personnel.Ouvrier;
import GestionDuPersonnel.Presence.Presence;
import java.time.LocalDate;

/**
 * Classe principale permettant de démontrer toutes les règles métier du projet
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free utilisée pour tous ce qui concerne la génération des PDF
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("      GESTION DU PERSONNEL");
        System.out.println("====================================");

        /*
         * =========================================================
         * 1. CREATION DES CONTRATS
         * =========================================================
         */
        System.out.println("\n===== CONTRATS =====");

        Contrat contratOuvrier = new Contrat(
                TypeContrat.CDD,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31)
        );
        System.out.println(
                "Début contrat Ouvrier : "
                        + contratOuvrier.getDateDebut()
        );
        System.out.println(
                "Date de Fin du contrat Ouvrier : "
                        + contratOuvrier.getDateFin()
        );
        System.out.println();

        Contrat contratEmploye = new Contrat(
                TypeContrat.CDI,
                LocalDate.of(2020, 1, 1),
                null
        );
        System.out.println(
                "Début contrat employé : "
                        + contratEmploye.getDateDebut()
        );
        System.out.println(
                "Date de Fin du contrat Employé : "
                        + (contratEmploye.getDateFin() == null
                        ? "Contrat sans date de fin"
                        : contratEmploye.getDateFin())
        );
        System.out.println();

        Contrat contratConsultant = new Contrat(
                TypeContrat.INTERVENTION,
                LocalDate.of(2025, 1, 1),
                null
        );
        System.out.println(
                "Début contrat employé : "
                        + contratConsultant.getDateDebut()
        );
        System.out.println(
                "Date de Fin du contrat Consultant : "
                        + (contratConsultant.getDateFin() == null
                        ? "Contrat sans date de fin"
                        : contratConsultant.getDateFin())
        );

        /*
         * =========================================================
         * 2. CREATION DES PERSONNELS
         * =========================================================
         */
        Ouvrier ouvrier = new Ouvrier(
                1,
                "Tyrell",
                "Margaery",
                LocalDate.of(2022, 1, 1),
                contratOuvrier,
                15
        );

        Employe employe = new Employe(
                2,
                "Michel",
                "Egon",
                LocalDate.of(2018, 1, 1),
                contratEmploye,
                BaremeFonction.SENIOR
        );

        System.out.println();
        System.out.println(
                "Fonction employé : "
                    + employe.getFonction()
        );

        Consultant consultant = new Consultant(
                3,
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

        for (int i = 0; i < 20; i++) {

            ouvrier.ajouterPresence(
                    new Presence(
                            LocalDate.now().minusDays(i),
                            8
                    )
            );
        }

        System.out.println(
                "Total heures prestée par l'ouvrier : "
                        + ouvrier.calculerTotalPresence()
                        + " h"
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
        Formation formation1  = new Formation(
                        "Sécurité",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,4)
        );

        Formation formation2 = new Formation(
                        "Machines",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,4)
        );

        Formation formation3 = new Formation(
                        "Technique",
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,2)
        );

        ouvrier.ajouterFormation(formation1);
        ouvrier.ajouterFormation(formation2);
        ouvrier.ajouterFormation(formation3);

        System.out.println("Différente formation suivie par l'employé : ");
        System.out.println(
                "   Formation suivie 1 : "
                    + formation1.getIntitule()
        );
        System.out.println(
                "   Formation suivie 2 : "
                        + formation2.getIntitule()
        );
        System.out.println(
                "   Formation suivie 3 : "
                        + formation3.getIntitule()
        );

        System.out.println("Formations ouvrier validée");
        System.out.println();

        /*
         * EMPLOYE :
         * max 3 jours par an
         * promotion après 5 jours cumulés
         */
        Formation formationEmploye1 = new Formation(
                        "Java",
                        LocalDate.of(2023,1,1),
                        LocalDate.of(2023,1,3)
        );

        Formation formationEmploye2 = new Formation(
                        "Spring",
                        LocalDate.of(2024,1,1),
                        LocalDate.of(2024,1,2)
        );

        employe.ajouterFormation(formationEmploye1);
        employe.ajouterFormation(formationEmploye2);

        System.out.println("Différentes formation suivie par l'employé : ");
        System.out.println(
                "   Formation employé 1 : "
                        + formationEmploye1.getIntitule()
        );
        System.out.println(
                "   Formation employé 2 : "
                        + formationEmploye2.getIntitule()
        );

        System.out.println(
                "Employé promouvable : "
                        + (employe.peutEtrePromu()
                        ? "Oui"
                        : "Non")
        );
        System.out.println();

        /**
         * Lorsque le nombre de formation par an est dépassé
         */
        try {

            employe.ajouterFormation(new Formation(
                    "Docker",
                    LocalDate.of(2024,6,1),
                    LocalDate.of(2024,6,5)
            ));
        } catch (Exception e) {

            System.out.println(
                    "Erreur formation : " + e.getMessage()
            );
        }

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
        System.out.println("Absence maladie : ");
        System.out.println(
                "   Total absences employé : "
                        + employe.calculerTotalAbsences()
                        + " jours"
        );

        /*
         * Vérification certificat médical
         */
        Absence absence = new Absence(
                LocalDate.of(2025,3,1),
                LocalDate.of(2025,3,3),
                "Maladie"
        );

        /*
         * Le personnel fournit son certificat
         */
        absence.fournirUncerticat();

        System.out.println(
                "   Certificat obligatoire : "
                    + (absence.certificatObligatoire()
                    ? "Oui"
                    : "Non")
        );
        System.out.println(
                "   Date début absence : "
                        + absence.getDateDebut()
        );
        System.out.println(
                "   Date fin absence : "
                        + absence.getDateFin()
        );
        System.out.println(
                "   Motif absence : "
                        + absence.getMotif()
        );

        System.out.println(
                "Certificat fourni : "
                    + (absence.isCertificatFourni()
                    ? "Oui"
                    : "Non")
        );

        /*
         * Absence non justifié
         */
        Absence absenceInjustifie = new Absence(
                LocalDate.of(2025,4,1),
                LocalDate.of(2025,4,10),
                "Absence injustifiée"

        );
        System.out.println();

        System.out.println("Absence injustifiée : ");
        System.out.println(
                "   Total absence injustifié : "
                    + absenceInjustifie.getNombreJours()
                    + " jours"
        );
        System.out.println(
                "   Certificat obligatoire : "
                        + (absenceInjustifie.certificatObligatoire()
                        ? "Oui"
                        : "Non")
        );
        System.out.println(
                "   Date début absence : "
                        + absenceInjustifie.getDateDebut()
        );
        System.out.println(
                "   Date fin absence : "
                        + absenceInjustifie.getDateFin()
        );
        System.out.println(
                "Certificat fourni : "
                        + (absenceInjustifie.isCertificatFourni()
                        ? "Oui"
                        : "Non")
        );



        /*
         * =========================================================
         * 6. GESTION DES CONGES
         * =========================================================
         */
        System.out.println("\n===== CONGES =====");

        /*
         * Méthode prendreConges()
         */
        employe.prendreConges(5);
        System.out.println(
                "5 jours de congés pris par l'employé"
        );

        System.out.println();
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
         * Réfus de congés
         */
        try {

            employe.prendreConges(100);
        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "Erreur congés : "
                        + e.getMessage()
            );
        }


        /*
         * =========================================================
         * 7. MISSIONS
         * =========================================================
         */
        System.out.println("\n===== MISSIONS =====");

        Mission mission1 = new Mission(
                "Audit Client",
                LocalDate.of(2025,5,10),
                LocalDate.of(2025,5,15)
        );

        Mission mission2 = new Mission(
                "Maintenance Serveur",
                LocalDate.of(2025,6,1),
                LocalDate.of(2025,6,3)
        );

        consultant.ajouterMission(mission1);
        employe.ajouterMission(mission2);

        System.out.println(
                "Mission ajoutée au consultant : "
                        + mission1.getTitre()
        );

        System.out.println(
                "Mission ajoutée à l'employé : "
                        + mission2.getTitre()
        );

        /*
         * =========================================================
         * 8. DEPLACEMENTS
         * =========================================================
         */
        System.out.println("\n===== DEPLACEMENTS =====");


        Deplacement deplacement1 = new Deplacement(
                LocalDate.of(2025,5,11),
                "Namur",
                "Bruxelles",
                90
        );
        System.out.println(
                "Date déplacement 1 : "
                        + deplacement1.getDate()
        );


        Deplacement deplacement2 = new Deplacement(
                LocalDate.of(2025,5,12),
                "Bruxelles",
                "Liège",
                100
        );



        mission1.ajouterDeplacement(deplacement1);
        mission2.ajouterDeplacement(deplacement2);

        System.out.println(
                "Trajet : "
                        + deplacement1.getVilleDepart()
                        + " -> "
                        + deplacement1.getVilleArrivee()
        );

        System.out.println(
                "Distance : "
                        + deplacement1.getDistanceKm()
                        + " km"
        );

        System.out.println(
                "Montant remboursé : "
                        + String.format("%.2f",deplacement1.calculerRemboursement())
                        + " €"
        );

        System.out.println();
        System.out.println(
                "Date déplacement 2 : "
                        + deplacement2.getDate()
        );
        System.out.println(
                "Trajet : "
                        + deplacement2.getVilleDepart()
                        + " -> "
                        + deplacement2.getVilleArrivee()
        );

        System.out.println(
                "Distance : "
                        + deplacement2.getDistanceKm()
                        + " km"
        );

        System.out.println(
                "Remboursement : "
                        + deplacement2.calculerRemboursement()
                        + " €"
        );

        /*
         * Total mission
         */
        System.out.println();

        double totalRemboursement = mission1.calculerTotalRemboursement() + mission2.calculerTotalRemboursement();
        System.out.println(
                "Remboursement total mission : "
                        +  totalRemboursement
                        + " €"
        );

        /*
         * Déplacement invalide
         */
        try {

            new Deplacement(
                    LocalDate.now(),
                    "Namur",
                    "Bruxelles",
                    -50
            );
        }  catch (Exception e) {

            System.out.println(
                    "Erreur deplacement : "
                            + e.getMessage()
            );
        }

        /*
         * =========================================================
         * 9. VALIDATIONS METIER
         * =========================================================
         */
        System.out.println("\n===== VALIDATIONS METIER =====");

        Ouvrier ouvrierAbsent = new Ouvrier(
             10,
             "Bolton",
             "Ramsey",
             LocalDate.of(2024,1,1),
             contratOuvrier,
             15
        );

        ouvrierAbsent.ajouterAbsence(
                new Absence(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,1,20),
                        "Absence injustifiée"
                )
        );

        System.out.println(
                "Ouvrier payable : "
                    + (ouvrierAbsent.estPayable()
                    ? "Oui"
                    : "Non")
        );

        System.out.println(
                "Salaire ouvrier absent : "
                    + ouvrierAbsent.calculerSalaire()
        );

        Employe employeAbsent = new Employe(
                11,
                "Greyjoy",
                "Théon",
                LocalDate.of(2020,1,1),
                contratEmploye,
                BaremeFonction.CHEF_DE_PROJET
        );

        employeAbsent.ajouterAbsence(
                new Absence(
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,2,15),
                        "Absence prolongée"
                )
        );
        System.out.println();
        System.out.println(
                "Employé payable : "
                        + (employeAbsent.estPayable()
                        ? "Oui"
                        : "Non")
        );
        System.out.println(
                "Salaire employé absent : "
                        + employeAbsent.calculerSalaire()
        );

        Consultant consultantSansMission =
                new Consultant(
                        12,
                        "Frey",
                        "Walder",
                        LocalDate.of(2025,1,1),
                        contratConsultant,
                        600,
                        15
                );
        System.out.println();
        System.out.println(
                "Consultant payable : "
                        + (consultantSansMission.estPayable()
                        ? "Oui"
                        : "Non")
        );
        System.out.println(
                "Salaire consultant : "
                        + consultantSansMission.calculerSalaire()
        );

        /*
         * Consultant avec congés non payés
         */
        Consultant consultantAvecConges = new Consultant(
                13,
                "Stark",
                "Arya",
                LocalDate.of(2023,1,1),
                contratConsultant,
                500,
                20
        );

        /*
         * Ajout d'une mission
         */
        consultantAvecConges.ajouterMission(
                new Mission(
                        "Projet Cloud",
                        LocalDate.of(2025,7,1),
                        LocalDate.of(2025,7,20)
                )
        );

        /*
         * Le consultant prend 5 jours de congés
         */
        consultantAvecConges.prendreConges(5);
        System.out.println();
        System.out.println(
                "Consultant avec mission payable : "
                        + (consultantAvecConges.estPayable()
                        ? "Oui"
                        : "Non")
        );
        System.out.println(
                "Congés consultant pris : 5 jours"
        );
        System.out.println(
                "Salaire consultant après congés non payés : "
                        + consultantAvecConges.calculerSalaire()
                        + " €"
        );



        /*
         * =========================================================
         * 10. AUGMENTATIONS
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
         * 11. CALCUL DES SALAIRES
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
         * 12. SALAIRE PAR PERIODE
         * =========================================================
         */

        System.out.println("\n===== SALAIRE PAR PERIODE =====");

        System.out.println(
                "Salaire ouvrier par periode : "
                        + ouvrier.calculerSalaireParPeriode()
        );

        System.out.println(
                "Salaire employé par periode : "
                        + employe.calculerSalaireParPeriode()
        );

        System.out.println(
                "Salaire consultant par periode : "
                        + consultant.calculerSalaireParPeriode()
        );

        /*
         * =========================================================
         * 13. FICHES DE PAIE
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
         * Ouvrier non payable
         */

        FichePaie ficheOuvrierAbsent = new FichePaie(
                ouvrierAbsent,
                new PasDePrime()
        );

        ficheOuvrierAbsent.exporter(
                new FichePaiePDF(),
                "PDF/fiche_ouvrier_absent.pdf"
        );

        /*
         * Employé non payable
         */

        FichePaie ficheEmployeAbsent = new FichePaie(
                employeAbsent,
                new PasDePrime()
        );

        ficheEmployeAbsent.exporter(
                new FichePaiePDF(),
                "PDF/fiche_employe_absent.pdf"
        );

        /*
         * Consultant non payable
         */

        FichePaie ficheConsultantAbsent = new FichePaie(
                consultantSansMission,
                new PasDePrime()
        );

        ficheConsultantAbsent.exporter(
                new FichePaiePDF(),
                "PDF/fiche_consultant_absent.pdf"
        );

        /*
         * Consultant avec congés non payés
         */

        FichePaie ficheConsultantConges = new FichePaie(
                consultantAvecConges,
                new PasDePrime()
        );

        ficheConsultantConges.exporter(
                new FichePaiePDF(),
                "PDF/fiche_consultant_conges.pdf"
        );


        /*
         * =========================================================
         * 14. FIN
         * =========================================================
         */
        System.out.println("\n====================================");
        System.out.println("        FIN DU PROGRAMME");
        System.out.println(" FICHES DE PAIE GÉNÉRÉES AVEC SUCCÈS");
        System.out.println("====================================");
    }
}
