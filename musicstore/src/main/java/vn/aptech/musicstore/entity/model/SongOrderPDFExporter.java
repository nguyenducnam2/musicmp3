/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.entity.model;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.SongOrder;

/**
 *
 * @author namng
 */
public class SongOrderPDFExporter {

    private List<SongOrder> list;

    public SongOrderPDFExporter() {
    }

    public SongOrderPDFExporter(List<SongOrder> list) {
        this.list = list;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("No.", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Fullname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total ($)", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Payment", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        int i = 0;
        for (SongOrder item : list) {
            table.addCell(String.valueOf(++i));
            table.addCell(item.getAccount().getFullname());
            table.addCell(item.getStatus());
            table.addCell(String.valueOf(item.getTotal()));
            table.addCell(item.getPayment());
            table.addCell(new String(item.getDate().toString()).substring(0, 10));
        }
    }

    public void export(HttpServletResponse response, String currenttime, String from, String to, Double total, Account acc) throws IOException, ParseException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Paragraph p = new Paragraph("List Song Order");
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        try {
            if ((!from.isEmpty()) && (!(to.isEmpty())) && (!(total == null))) {
                document.add(new Paragraph("From: " + from));
                document.add(new Paragraph("To: " + to));
            }
        } catch (Exception e) {

        }

        document.addTitle("Song Order Report");
        document.addCreationDate();

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{0.7f, 4.0f, 1.5f, 2.0f, 1.5f, 2.4f});
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        try {
            if ((!from.isEmpty()) && (!(to.isEmpty())) && (!(total == null)) && (!(acc == null))) {
                Paragraph p2 = new Paragraph("Summary Total: " + total + " $");
                p2.setAlignment(Element.ALIGN_RIGHT);
                document.add(p2);
                Paragraph p3 = new Paragraph("     "+currenttime.substring(0, 10) + "\n        " + acc.getLastName() + "\n" + acc.getFullname());
                p3.setAlignment(Element.ALIGN_LEFT);
                document.add(p3);

            }
        } catch (Exception e) {

        }

        document.close();
    }
}
