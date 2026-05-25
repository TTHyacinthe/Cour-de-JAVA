package GestionDuPersonnel.Paie.DpStrategy.Export;

import GestionDuPersonnel.Mission.Mission;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Personnel.Personnels;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe permettant d'exporter une fiche de paie au format PDF
 * Auteur : Hyacinthe TAMO
 * IA : ChatGPT Free utilisée pour tous ce qui concerne la génération des PDF
 */
public class FichePaiePDF implements ExportFichePaie {

    /*
     * Couleurs entreprise
     */
    private static final DeviceRgb BLEU = new DeviceRgb(30, 70, 140);
    private static final DeviceRgb GRIS = new DeviceRgb(240, 240, 240);
    private static final DeviceRgb VERT = new DeviceRgb(0, 150, 70);

    /**
     * Exporte une fiche de paie au format PDF
     * @param fiche fiche de paie à exporter
     * @param chemin chemin de destination du fichier PDF
     */
    @Override
    public void exporter(
            FichePaie fiche,
            String chemin
    ) {

        try {

            /*
             * Récupération du personnel
             */
            Personnels p = fiche.getPersonnel();

            /*
             * Création PDF
             */
            PdfWriter writer = new PdfWriter(chemin);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4);
            Document doc = new Document(pdf);
            doc.setMargins(30, 30, 30, 30);

            /*
             * ==================================================
             * HEADER
             * ==================================================
             */

            Paragraph societe = new Paragraph("GESTION DU PERSONNEL")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph titre = new Paragraph("FICHE DE PAIE")
                    .setBold()
                    .setFontSize(24)
                    .setFontColor(BLEU)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph date = new Paragraph("Date : " + LocalDate.now().format(
                                    DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                            ))
            )
                    .setTextAlignment(TextAlignment.RIGHT);

            doc.add(societe);
            doc.add(titre);
            doc.add(date);

            doc.add(
                    new LineSeparator(
                            new SolidLine()
                    )
            );

            doc.add(new Paragraph("\n"));

            /*
             * ==================================================
             * INFORMATIONS PERSONNEL
             * ==================================================
             */

            doc.add(
                    new Paragraph("INFORMATIONS")
                            .setBold()
                            .setFontColor(BLEU)
                            .setFontSize(16)
            );

            Table info = new Table(2)
                    .useAllAvailableWidth();

            info.addCell(cellTitre("Nom"));
            info.addCell(cellValeur(p.getNom()));

            info.addCell(cellTitre("Prénom"));
            info.addCell(cellValeur(p.getPrenom()));

            info.addCell(cellTitre("Matricule"));
            info.addCell(cellValeur(p.getMatricule()));

            info.addCell(cellTitre("Contrat"));
            info.addCell(cellValeur(
                    p.getContrat()
                            .getTypeContrat()
                            .toString()
            ));

            info.addCell(cellTitre("Fréquence paiement"));
            info.addCell(cellValeur(
                    p.getFrequencePaiement()
                            .toString()
            ));

            info.addCell(cellTitre("Ancienneté"));
            info.addCell(cellValeur(
                    p.getAnciennete()
                            + " ans"
            ));

            info.addCell(cellTitre("Absences"));
            info.addCell(cellValeur(
                    p.calculerTotalAbsences()
                            + " jours"
            ));

            info.addCell(cellTitre("Formations"));
            info.addCell(cellValeur(
                    p.calculerTotalFormation()
                            + " jours"
            ));

            info.addCell(cellTitre("Congés restants"));
            info.addCell(cellValeur(
                    p.getSoldeConges()
                            + " jours"
            ));

            doc.add(info);

            doc.add(new Paragraph("\n"));


            /*
             * ==================================================
             * MISSIONS
             * ==================================================
             */

            doc.add(
                    new Paragraph("MISSIONS")
                            .setBold()
                            .setFontColor(BLEU)
                            .setFontSize(16)
            );

            if (p.getMissions().isEmpty()) {

                doc.add(
                        new Paragraph(
                                "Aucune mission enregistrée"
                        )
                );

            } else {

                for (Mission mission :
                        p.getMissions()) {

                    Table missionTable = new Table(2)
                                    .useAllAvailableWidth();

                    missionTable.addCell(
                            cellTitre("Mission")
                    );
                    missionTable.addCell(
                            cellValeur(
                                    mission.getTitre()
                            )
                    );

                    missionTable.addCell(
                            cellTitre("Date début")
                    );
                    missionTable.addCell(
                            cellValeur(
                                    mission.getDateDebut()
                                            .toString()
                            )
                    );

                    missionTable.addCell(
                            cellTitre("Date fin")
                    );
                    missionTable.addCell(
                            cellValeur(
                                    mission.getDateFin()
                                            .toString()
                            )
                    );

                    missionTable.addCell(
                            cellTitre(
                                    "Indemnité Kilométrique"
                            )
                    );
                    missionTable.addCell(
                            cellValeur(
                                    format(
                                            mission.calculerTotalRemboursement()
                                    )
                            )
                    );

                    doc.add(missionTable);

                    doc.add(new Paragraph("\n"));
                }
            }


            /*
             * ==================================================
             * REMUNERATION
             * ==================================================
             */

            double salaireBase = p.calculerSalaire();
            double salairePeriode = p.calculerSalaireParPeriode();
            double salaireAvecPrime = fiche.calculerSalaireTotale();

            // Rembousements des mission
            double remboursementTotal = 0;
            for (Mission mission : p.getMissions()) {

                remboursementTotal += mission.calculerTotalRemboursement();
            }

            double prime = salaireAvecPrime - salaireBase;
            double salaireBrut = salaireAvecPrime;
            double onss = fiche.calculerONSS();
            double impot = fiche.calculerImpot();
            double salaireNet = salaireBrut - onss - impot + remboursementTotal;

            doc.add(
                    new Paragraph("RÉMUNÉRATION")
                            .setBold()
                            .setFontColor(BLEU)
                            .setFontSize(16)
            );

            Table salaire = new Table(2)
                    .useAllAvailableWidth();

            salaire.addCell(
                    cellTitre("Salaire de base")
            );
            salaire.addCell(
                    cellValeur(format(salaireBase))
            );

            salaire.addCell(
                    cellTitre("Salaire par période")
            );
            salaire.addCell(
                    cellValeur(format(salairePeriode))
            );

            salaire.addCell(
                    cellTitre("Prime")
            );
            salaire.addCell(
                    cellValeur(format(prime))
            );

            salaire.addCell(
                    cellTitre("Salaire Brut")
            );
            salaire.addCell(
                    cellValeur(format(salaireBrut))
            );

            salaire.addCell(
                    cellTitre("Cotisation ONSS (13%)")
            );
            salaire.addCell(
                    cellValeur("- " + format(onss))
            );

            salaire.addCell(
                    cellTitre("Impôt (18%)")
            );
            salaire.addCell(
                    cellValeur("- " + format(impot))
            );

            salaire.addCell(
                    cellTitre("Indemnité kilométrique")
            );

            salaire.addCell(
                    cellValeur(format(remboursementTotal))
            );

            salaire.addCell(
                    cellTitre("NET À PAYER")
            );

            salaire.addCell(
                    new Cell()
                            .add(
                                    new Paragraph(
                                            format(
                                                    salaireNet
                                            )
                                    )
                                            .setBold()
                                            .setFontColor(VERT)
                            )
                            .setBackgroundColor(GRIS)
            );

            doc.add(salaire);

            doc.add(new Paragraph("\n"));



            /*
             * ==================================================
             * OBSERVATIONS
             * ==================================================
             */

            doc.add(
                    new Paragraph("OBSERVATIONS")
                            .setBold()
                            .setFontColor(BLEU)
                            .setFontSize(16)
            );

            String statut;

            if (p.estPayable()) {

                statut =
                        "Personnel payable";

            } else {

                statut =
                        "Personnel NON payable";
            }

            doc.add(new Paragraph(statut));

            /*
             * Cas employé promouvable
             */

            if (p.calculerTotalFormation() >= 5) {

                doc.add(
                        new Paragraph(
                                "Personnel promouvable"
                        )
                                .setFontColor(VERT)
                );
            }

            /*
             * ==================================================
             * SIGNATURES
             * ==================================================
             */

            doc.add(new Paragraph("\n\n"));

            Table signatures =
                    new Table(2)
                            .useAllAvailableWidth();

            signatures.addCell(
                    new Cell()
                            .add(
                                    new Paragraph(
                                            "Signature Employé"
                                    )
                            )
                            .setHeight(70)
                            .setBorder(
                                    new SolidBorder(1)
                            )
            );

            signatures.addCell(
                    new Cell()
                            .add(
                                    new Paragraph(
                                            "Signature RH"
                                    )
                            )
                            .setHeight(70)
                            .setBorder(
                                    new SolidBorder(1)
                            )
            );

            doc.add(signatures);

            /*
             * FOOTER
             */

            doc.add(
                    new Paragraph(
                            "\nDocument généré automatiquement"
                    )
                            .setFontSize(9)
                            .setTextAlignment(
                                    TextAlignment.CENTER
                            )
            );

            /*
             * Fermeture
             */
            doc.close();

            System.out.println(
                    "PDF généré avec succès : "
                            + chemin
            );

        } catch (Exception e) {

            throw new RuntimeException("Erreur lors de la génération deu PDF",
            e);
        }
    }

    /*
     * ==================================================
     * CELLULES TABLEAUX
     * ==================================================
     */

    /**
     * Crée une cellule de titre pour les tableaux PDF
     * @param texte texte affiché dans la cellule
     * @return cellule formatée pour un titre
     */
    private Cell cellTitre(String texte) {

        return new Cell()
                .add(
                        new Paragraph(texte)
                                .setBold()
                )
                .setBackgroundColor(GRIS);
    }

    /**
     * Crée une cellule contenant une valeur pour les tableaux PDF
     * @param texte texte affiché dans la cellule
     * @return cellule contenant la valeur
     */
    private Cell cellValeur(String texte) {

        return new Cell()
                .add(
                        new Paragraph(texte)
                );
    }

    /*
     * ==================================================
     * FORMAT EURO
     * ==================================================
     */

    /**
     * Formate un montant monétaire en euro
     * @param montant montant à formater
     * @return montant formaté avec deux décimales suivi du symbole euro
     */
    private String format(double montant) {

        return String.format(
                "%.2f €",
                montant
        );
    }
}
