package com.ticketbooking.web.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ticketbooking.model.Ticket;

public class PdfView extends AbstractITextPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Ticket> tickets = (List<Ticket>) model.get("tickets");
		
		document.add(new Paragraph("User's tickets"));
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Ticket Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User Id", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Event Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Place", font));
        table.addCell(cell);

        // write table row data
        for (Ticket ticket : tickets) {
        	table.addCell(Long.toString(ticket.getId()));
        	table.addCell(Long.toString(ticket.getUserId()));
        	table.addCell(Long.toString(ticket.getEventId()));
        	table.addCell(ticket.getCategory().name());
        	table.addCell(Integer.toString(ticket.getPlace()));
        }
        
        document.add(table);
	}

}
