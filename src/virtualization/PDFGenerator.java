package virtualization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;


import pojos.Appointment;

public class PDFGenerator {
	
	public void generateAppointmentPDF(Appointment app, File file) throws DocumentException, MalformedURLException, IOException {
		Document document  = new Document();
		FileOutputStream fs = new FileOutputStream(file);
		PdfWriter writer = PdfWriter.getInstance(document, fs);
		
		document.open();

		/*Image image = Image.getInstance(".//images//Logo.jpg");
		image.setAbsolutePosition(100f, 550f);
		image.scaleAbsolute(200, 200);
		document.add(image);*/
		
		Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, Font.BOLD, new CMYKColor(255,0,0,0));
		Paragraph title = new Paragraph("Appointment Details", titleFont);
		Chapter chapter1 = new Chapter(title, 1);
		document.add(chapter1);
		
		document.add(new Paragraph(""));
		document.add(new Paragraph(""));
		
		document.add(new Paragraph("Reason of the appointment: " + app.getReason()));
		document.add(new Paragraph("Date of the appointment: " + app.getDate().toString()));
		document.add(new Paragraph("Hour of the appointment: " + app.getHour()));
		
		document.close();
		writer.close();
	}
}
