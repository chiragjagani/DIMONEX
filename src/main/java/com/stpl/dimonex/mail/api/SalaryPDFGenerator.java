package com.stpl.dimonex.mail.api;


import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.ManagerSalary;

public class SalaryPDFGenerator {

    public static byte[] generateManagerSalarySlip( ManagerSalary salary) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Title
        Paragraph title = new Paragraph("Manager Salary Slip", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); // spacer

        
        // Salary Table
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);

        table.addCell(new PdfPCell(new Phrase("Month")));
        table.addCell(new PdfPCell(new Phrase(salary.getMonth())));

        table.addCell(new PdfPCell(new Phrase("Year")));
        table.addCell(new PdfPCell(new Phrase(String.valueOf(salary.getYear()))));

        table.addCell(new PdfPCell(new Phrase("Amount")));
        table.addCell(new PdfPCell(new Phrase(String.valueOf(salary.getAmount()))));

        table.addCell(new PdfPCell(new Phrase("Status")));
        table.addCell(new PdfPCell(new Phrase(salary.isPaid() ? "Paid" : "Unpaid")));

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

}