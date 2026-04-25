package GestionDuPersonnel.Paie;

import GestionDuPersonnel.Paie.DpStrategy.Export.ExportFichePaie;
import GestionDuPersonnel.Paie.FichePaie;
import GestionDuPersonnel.Personnel.Personnels;

import com.itextpdf.kernel.colors.ColorConstants;
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

public class FichePaiePDF implements ExportFichePaie {

    private static final DeviceRgb BLEU = new DeviceRgb(30, 70, 140);
    private static final DeviceRgb GRIS = new DeviceRgb(240,240,240);
    private static final DeviceRgb VERT = new DeviceRgb(0,150,70);

    public void exporter(FichePaie fiche, String chemin) {

        try {

            Personnels p = fiche.getPersonnel();

            PdfWriter writer = new PdfWriter(chemin);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf, PageSize.A4);

            doc.setMargins(30,30,30,30);

            // ===========================
            // HEADER
            // ===========================

            Paragraph titre = new Paragraph("FICHE DE PAIE")
                    .setBold()
                    .setFontSize(24)
                    .setFontColor(BLEU)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph societe = new Paragraph("GESTION DU PERSONNEL")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph date = new Paragraph(
                    "Date : " +
                            LocalDate.now().format(
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            ).setTextAlignment(TextAlignment.RIGHT);

            doc.add(societe);
            doc.add(titre);
            doc.add(date);

            doc.add(new LineSeparator(new SolidLine()));
            doc.add(new Paragraph("\n"));

            // ===========================
            // INFOS PERSONNEL
            // ===========================

            doc.add(new Paragraph("INFORMATIONS")
                    .setBold()
                    .setFontColor(BLEU));

            Table info = new Table(2)
                    .useAllAvailableWidth();

            info.addCell(cellTitre("Nom"));
            info.addCell(cellValeur(p.getNom()));

            info.addCell(cellTitre("Prénom"));
            info.addCell(cellValeur(p.getPrenom()));

            info.addCell(cellTitre("Matricule"));
            info.addCell(cellValeur(p.getMatricule()));

            info.addCell(cellTitre("Ancienneté"));
            info.addCell(cellValeur(
                    p.getAnciennete() + " ans"));

            info.addCell(cellTitre("Absences"));
            info.addCell(cellValeur(
                    p.calculerTotalAbsences() + " jours"));

            info.addCell(cellTitre("Formations"));
            info.addCell(cellValeur(
                    p.calculerTotalFormation() + " jours"));

            info.addCell(cellTitre("Congés restants"));
            info.addCell(cellValeur(
                    p.getSoldeConges() + " jours"));

            doc.add(info);
            doc.add(new Paragraph("\n"));

            // ===========================
            // SALAIRE
            // ===========================

            double base = p.calculerSalaire();
            double total = fiche.calculerSalaireTotale();
            double prime = total - base;

            doc.add(new Paragraph("RÉMUNÉRATION")
                    .setBold()
                    .setFontColor(BLEU));

            Table sal = new Table(2)
                    .useAllAvailableWidth();

            sal.addCell(cellTitre("Salaire de base"));
            sal.addCell(cellValeur(format(base)));

            sal.addCell(cellTitre("Prime"));
            sal.addCell(cellValeur(format(prime)));

            sal.addCell(cellTitre("Salaire Net"));
            sal.addCell(
                    new Cell()
                            .add(new Paragraph(format(total))
                                    .setBold()
                                    .setFontColor(VERT))
                            .setBackgroundColor(GRIS)
            );

            doc.add(sal);
            doc.add(new Paragraph("\n"));

            // ===========================
            // OBSERVATION
            // ===========================

            String statut = p.estPayable()
                    ? "Employé payable"
                    : "Non payable selon règlement";

            doc.add(new Paragraph("OBSERVATION")
                    .setBold()
                    .setFontColor(BLEU));

            doc.add(new Paragraph(statut));

            doc.add(new Paragraph("\n\n"));

            // ===========================
            // SIGNATURES
            // ===========================

            Table sign = new Table(2)
                    .useAllAvailableWidth();

            sign.addCell(new Cell()
                    .add(new Paragraph("Signature Employé"))
                    .setHeight(60));

            sign.addCell(new Cell()
                    .add(new Paragraph("Signature RH"))
                    .setHeight(60));

            doc.add(sign);

            doc.add(new Paragraph(
                    "\nDocument généré automatiquement.")
                    .setFontSize(9)
                    .setTextAlignment(TextAlignment.CENTER));

            doc.close();

            System.out.println("PDF généré : " + chemin);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Cell cellTitre(String txt){
        return new Cell()
                .add(new Paragraph(txt).setBold())
                .setBackgroundColor(GRIS);
    }

    private Cell cellValeur(String txt){
        return new Cell()
                .add(new Paragraph(txt));
    }

    private String format(double m){
        return String.format("%.2f €", m);
    }
}