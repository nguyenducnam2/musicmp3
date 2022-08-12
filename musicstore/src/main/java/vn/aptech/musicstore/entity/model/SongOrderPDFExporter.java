/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.entity.model;

import com.lowagie.text.Document;
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
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
        cell.setPhrase(new Phrase("Username", font));
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
        for (SongOrder item : list) {
            table.addCell(item.getAccount().getUsername());
            table.addCell(item.getStatus());
            table.addCell(String.valueOf(item.getTotal()));
            table.addCell(item.getPayment());
            table.addCell(new String(item.getDate().toString()).substring(0, 10));
        }
    }
    
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(new Paragraph("List Song Order"));
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{4.0f, 1.5f, 2.0f, 1.5f, 2.4f});
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
