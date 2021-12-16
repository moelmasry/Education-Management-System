package com.edumans.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.edumans.dto.CourseDto;
import com.edumans.dto.StudentDto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * this class utility to build the PDF file on runtime based on the actual data
 * this class developed using librePDF-OpenPDF
 * 
 * @author mohamed.elmasry
 *
 */
public class PDFBuilder {

	private List<CourseDto> coursesList;
	private StudentDto studentDto;

	public PDFBuilder(List<CourseDto> coursesList) {
		this.coursesList = coursesList;
	}

	public PDFBuilder(List<CourseDto> coursesList, StudentDto studentDto) {
		this.coursesList = coursesList;
		this.studentDto = studentDto;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Course ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Course Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Course Description", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (CourseDto course : coursesList) {
			table.addCell(String.valueOf(course.getId()));
			table.addCell(course.getName());
			table.addCell(course.getDescription());
		}
	}

	public ByteArrayInputStream build() throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, bos);

		document.open();
		// add styling
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		String documentHeader = "List of Courses";
		if (this.studentDto != null) {
			documentHeader = "List of Courses for <" + studentDto.getFirstName() + " " + studentDto.getLastName() + ">";
		}

		Paragraph p = new Paragraph(documentHeader, font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

		return new ByteArrayInputStream(bos.toByteArray());
	}

}
