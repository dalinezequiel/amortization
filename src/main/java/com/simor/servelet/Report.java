package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Date;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class Report
 */
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Document document = null;
	private PdfPTable header, body, bodyData, body1, bodyData1, header1 = null;
	private PdfPCell simor, sir, user, usrDate, finance, space = null;
	private PdfPCell sistema, valor, taxa, prazo, prestacao = null;
	private PdfPCell vencimento, prestaca,juro,amortizacao,saldoDevedor=null;

	private float[] columnSize = { 190, 120, 100, 100, 160 };
	private static Font normalFont12, boldTitleFont18, normalFont14 = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		this.report(request, response);
		// new Reporteia().report(request, response);
	}

	public void report(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		document = new Document(PageSize.A4, 50f, 50f, 50f, 50f);
		normalFont12 = new Font(Font.FontFamily.UNDEFINED, 12, Font.NORMAL);
		boldTitleFont18 = new Font(Font.FontFamily.UNDEFINED, 18, Font.BOLD);
		normalFont14 = new Font(Font.FontFamily.UNDEFINED, 14, Font.NORMAL);

		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Dispostion", "inline; filename=amortizacao.pdf");

			// HEADER
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			header = new PdfPTable(1);
			header.setWidthPercentage(100);
			header.setHorizontalAlignment(Element.ALIGN_LEFT);
			header.setSpacingBefore(0f);
			header.setSpacingAfter(0f);

			simor = new PdfPCell(new Paragraph("SIMOR", boldTitleFont18));
			simor.setHorizontalAlignment(Element.ALIGN_LEFT);
			simor.setBorderWidth(0.0f);
			simor.setPaddingLeft(0);

			sir = new PdfPCell(new Paragraph("Sistema de Amortização"));
			sir.setHorizontalAlignment(Element.ALIGN_LEFT);
			sir.setBorderWidth(0f);
			sir.setPaddingLeft(0);

			user = new PdfPCell(new Paragraph("User", normalFont12));
			user.setHorizontalAlignment(Element.ALIGN_LEFT);
			user.setBorderWidth(0.0f);
			user.setPaddingLeft(0);

			usrDate = new PdfPCell(new Paragraph(String.valueOf(Date.valueOf(LocalDate.now())), normalFont12));
			usrDate.setHorizontalAlignment(Element.ALIGN_LEFT);
			usrDate.setBorderWidth(0.0f);
			usrDate.setPaddingLeft(0);
			usrDate.setPaddingBottom(10);

			finance = new PdfPCell(new Paragraph("Dados do Emprestímo ou Finânciamento", normalFont14));
			finance.setHorizontalAlignment(Element.ALIGN_LEFT);
			finance.setBorderWidth(0.0f);
			finance.setPaddingBottom(6);
			finance.setPaddingLeft(0);

			space = new PdfPCell(new Paragraph(" "));
			space.setBorderWidth(0f);

			header.addCell(simor);
			header.addCell(sir);
			header.addCell(space);
			header.addCell(user);
			header.addCell(usrDate);
			header.addCell(finance);
			document.add(header);

			// BODY
			body = new PdfPTable(5);
			body.setTotalWidth(columnSize);
			body.setWidthPercentage(100);
			body.setSpacingBefore(0f);
			body.setSpacingAfter(0f);

			// BODY STRUCTURE
			sistema = new PdfPCell(new Paragraph("Sistema", normalFont12));
			valor = new PdfPCell(new Paragraph("Valor", normalFont12));
			taxa = new PdfPCell(new Paragraph("Taxa", normalFont12));
			prazo = new PdfPCell(new Paragraph("Prazo", normalFont12));
			prestacao = new PdfPCell(new Paragraph("Primeira Prestação", normalFont12));

			sistema.setPadding(8.0f);
			valor.setPadding(8.0f);
			taxa.setPadding(8.0f);
			prazo.setPadding(8.0f);
			prestacao.setPadding(8.0f);

			sistema.setBorderWidth(0.01f);
			valor.setBorderWidth(0.01f);
			taxa.setBorderWidth(0.01f);
			prazo.setBorderWidth(0.01f);
			prestacao.setBorderWidth(0.01f);

			sistema.setBorderColor(BaseColor.GRAY);
			valor.setBorderColor(BaseColor.GRAY);
			taxa.setBorderColor(BaseColor.GRAY);
			prazo.setBorderColor(BaseColor.GRAY);
			prestacao.setBorderColor(BaseColor.GRAY);

			sistema.setBackgroundColor(BaseColor.LIGHT_GRAY);
			valor.setBackgroundColor(BaseColor.LIGHT_GRAY);
			taxa.setBackgroundColor(BaseColor.LIGHT_GRAY);
			prazo.setBackgroundColor(BaseColor.LIGHT_GRAY);
			prestacao.setBackgroundColor(BaseColor.LIGHT_GRAY);

			sistema.setHorizontalAlignment(Element.ALIGN_LEFT);
			valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
			taxa.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prazo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prestacao.setHorizontalAlignment(Element.ALIGN_RIGHT);

			body.addCell(sistema);
			body.addCell(valor);
			body.addCell(taxa);
			body.addCell(prazo);
			body.addCell(prestacao);
			document.add(body);

			bodyData = new PdfPTable(5);
			bodyData.setTotalWidth(columnSize);
			bodyData.setWidthPercentage(100);
			bodyData.setSpacingBefore(0f);
			bodyData.setSpacingAfter(0f);

			// BODY DATA
			sistema = new PdfPCell(new Paragraph("MAJS/ Hamburgues", normalFont12));
			valor = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));
			taxa = new PdfPCell(new Paragraph("1,00%", normalFont12));
			prazo = new PdfPCell(new Paragraph("12,00", normalFont12));
			prestacao = new PdfPCell(new Paragraph("12/05/2023", normalFont12));

			sistema.setPadding(8.0f);
			valor.setPadding(8.0f);
			taxa.setPadding(8.0f);
			prazo.setPadding(8.0f);
			prestacao.setPadding(8.0f);

			sistema.setBorderColor(BaseColor.GRAY);
			valor.setBorderColor(BaseColor.GRAY);
			taxa.setBorderColor(BaseColor.GRAY);
			prazo.setBorderColor(BaseColor.GRAY);
			prestacao.setBorderColor(BaseColor.GRAY);

			sistema.setHorizontalAlignment(Element.ALIGN_LEFT);
			valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
			taxa.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prazo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prestacao.setHorizontalAlignment(Element.ALIGN_RIGHT);

			bodyData.addCell(sistema);
			bodyData.addCell(valor);
			bodyData.addCell(taxa);
			bodyData.addCell(prazo);
			bodyData.addCell(prestacao);
			document.add(bodyData);
			
			
			
			// BODY
			header1 = new PdfPTable(1);
			header1.setWidthPercentage(100);
			header1.setHorizontalAlignment(Element.ALIGN_LEFT);
			header1.setSpacingBefore(0f);
			header1.setSpacingAfter(0f);
			
			finance = new PdfPCell(new Paragraph("Dados do Resultantes", normalFont14));
			finance.setHorizontalAlignment(Element.ALIGN_LEFT);
			finance.setBorderWidth(0.0f);
			finance.setPaddingTop(20);
			finance.setPaddingBottom(6);
			finance.setPaddingLeft(0);
			header1.addCell(finance);
			document.add(header1);
						body1 = new PdfPTable(5);
						columnSize= new float[5];
						columnSize[0]=130;
						columnSize[1]=130;
						columnSize[2]=120;
						columnSize[3]=130;
						columnSize[4]=160;
						body1.setTotalWidth(columnSize);
						body1.setWidthPercentage(100);
						body1.setSpacingBefore(0f);
						body1.setSpacingAfter(0f);

						// BODY STRUCTURE
						vencimento = new PdfPCell(new Paragraph("Vencimento", normalFont12));
						prestaca = new PdfPCell(new Paragraph("Prestação", normalFont12));
						juro = new PdfPCell(new Paragraph("Juros", normalFont12));
						amortizacao = new PdfPCell(new Paragraph("Amortização", normalFont12));
						saldoDevedor = new PdfPCell(new Paragraph("Saldo Devedor", normalFont12));

						vencimento.setPadding(8.0f);
						prestaca.setPadding(8.0f);
						juro.setPadding(8.0f);
						amortizacao.setPadding(8.0f);
						saldoDevedor.setPadding(8.0f);

						vencimento.setBorderWidth(0.01f);
						prestaca.setBorderWidth(0.01f);
						juro.setBorderWidth(0.01f);
						amortizacao.setBorderWidth(0.01f);
						saldoDevedor.setBorderWidth(0.01f);

						vencimento.setBorderColor(BaseColor.GRAY);
						prestaca.setBorderColor(BaseColor.GRAY);
						juro.setBorderColor(BaseColor.GRAY);
						amortizacao.setBorderColor(BaseColor.GRAY);
						saldoDevedor.setBorderColor(BaseColor.GRAY);

						vencimento.setBackgroundColor(BaseColor.LIGHT_GRAY);
						prestaca.setBackgroundColor(BaseColor.LIGHT_GRAY);
						juro.setBackgroundColor(BaseColor.LIGHT_GRAY);
						amortizacao.setBackgroundColor(BaseColor.LIGHT_GRAY);
						saldoDevedor.setBackgroundColor(BaseColor.LIGHT_GRAY);

						vencimento.setHorizontalAlignment(Element.ALIGN_LEFT);
						prestaca.setHorizontalAlignment(Element.ALIGN_RIGHT);
						juro.setHorizontalAlignment(Element.ALIGN_RIGHT);
						amortizacao.setHorizontalAlignment(Element.ALIGN_RIGHT);
						saldoDevedor.setHorizontalAlignment(Element.ALIGN_RIGHT);

						body1.addCell(vencimento);
						body1.addCell(prestaca);
						body1.addCell(juro);
						body1.addCell(amortizacao);
						body1.addCell(saldoDevedor);
						document.add(body1);

						bodyData1 = new PdfPTable(5);
						bodyData1.setTotalWidth(columnSize);
						bodyData1.setWidthPercentage(100);
						bodyData1.setSpacingBefore(0f);
						bodyData1.setSpacingAfter(0f);

						// BODY DATA
						sistema = new PdfPCell(new Paragraph("12/05/2023", normalFont12));
						valor = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));
						taxa = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));
						prazo = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));
						prestacao = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));

						sistema.setPadding(8.0f);
						valor.setPadding(8.0f);
						taxa.setPadding(8.0f);
						prazo.setPadding(8.0f);
						prestacao.setPadding(8.0f);
						
						sistema.setBorderWidthLeft(0f);
						sistema.setBorderWidthTop(0f);
						sistema.setBorderWidthBottom(0f);
						
						prestacao.setBorderWidthRight(0f);
						prestacao.setBorderWidthTop(0f);
						prestacao.setBorderWidthBottom(0f);
						
						valor.setBorderWidthBottom(0f);
						taxa.setBorderWidthBottom(0f);
						prazo.setBorderWidthBottom(0f);

						sistema.setBorderColor(BaseColor.GRAY);
						valor.setBorderColor(BaseColor.GRAY);
						taxa.setBorderColor(BaseColor.GRAY);
						prazo.setBorderColor(BaseColor.GRAY);
						prestacao.setBorderColor(BaseColor.GRAY);

						sistema.setHorizontalAlignment(Element.ALIGN_LEFT);
						valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
						taxa.setHorizontalAlignment(Element.ALIGN_RIGHT);
						prazo.setHorizontalAlignment(Element.ALIGN_RIGHT);
						prestacao.setHorizontalAlignment(Element.ALIGN_RIGHT);

						bodyData1.addCell(sistema);
						bodyData1.addCell(valor);
						bodyData1.addCell(taxa);
						bodyData1.addCell(prazo);
						bodyData1.addCell(prestacao);
						document.add(bodyData1);

//						document.add(body1);
//						document.add(bodyData1);
			
			//this.prestacao(document, request, response);

			// FOOTER
			this.onEndPage(writer, document);
//			document.add(body);
//			document.add(bodyData);
//			document.add(body1);
//			document.add(bodyData1);
			document.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			document.close();
		}
	}
	
	public void prestacao(Document document, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		document = new Document(PageSize.A4, 50f, 50f, 50f, 50f);
		normalFont12 = new Font(Font.FontFamily.UNDEFINED, 12, Font.NORMAL);
		boldTitleFont18 = new Font(Font.FontFamily.UNDEFINED, 18, Font.BOLD);
		normalFont14 = new Font(Font.FontFamily.UNDEFINED, 14, Font.NORMAL);

		try {
//			response.setContentType("application/pdf");
//			response.addHeader("Content-Dispostion", "inline; filename=amortizacao.pdf");
//
//			// HEADER
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			// BODY
			body1 = new PdfPTable(5);
			body1.setTotalWidth(columnSize);
			body1.setWidthPercentage(100);
			body1.setSpacingBefore(0f);
			body1.setSpacingAfter(0f);

			// BODY STRUCTURE
			vencimento = new PdfPCell(new Paragraph("Vencimento", normalFont12));
			prestaca = new PdfPCell(new Paragraph("Prestação", normalFont12));
			juro = new PdfPCell(new Paragraph("Juros", normalFont12));
			amortizacao = new PdfPCell(new Paragraph("Amortização", normalFont12));
			saldoDevedor = new PdfPCell(new Paragraph("Saldo Devedor", normalFont12));

			vencimento.setPadding(8.0f);
			prestaca.setPadding(8.0f);
			juro.setPadding(8.0f);
			amortizacao.setPadding(8.0f);
			saldoDevedor.setPadding(8.0f);

			vencimento.setBorderWidth(0.01f);
			prestaca.setBorderWidth(0.01f);
			juro.setBorderWidth(0.01f);
			amortizacao.setBorderWidth(0.01f);
			saldoDevedor.setBorderWidth(0.01f);

			vencimento.setBorderColor(BaseColor.GRAY);
			prestaca.setBorderColor(BaseColor.GRAY);
			juro.setBorderColor(BaseColor.GRAY);
			amortizacao.setBorderColor(BaseColor.GRAY);
			saldoDevedor.setBorderColor(BaseColor.GRAY);

			vencimento.setBackgroundColor(BaseColor.LIGHT_GRAY);
			prestaca.setBackgroundColor(BaseColor.LIGHT_GRAY);
			juro.setBackgroundColor(BaseColor.LIGHT_GRAY);
			amortizacao.setBackgroundColor(BaseColor.LIGHT_GRAY);
			saldoDevedor.setBackgroundColor(BaseColor.LIGHT_GRAY);

			vencimento.setHorizontalAlignment(Element.ALIGN_LEFT);
			prestaca.setHorizontalAlignment(Element.ALIGN_RIGHT);
			juro.setHorizontalAlignment(Element.ALIGN_RIGHT);
			amortizacao.setHorizontalAlignment(Element.ALIGN_RIGHT);
			saldoDevedor.setHorizontalAlignment(Element.ALIGN_RIGHT);

			body1.addCell(vencimento);
			body1.addCell(prestaca);
			body1.addCell(juro);
			body1.addCell(amortizacao);
			body1.addCell(saldoDevedor);

			bodyData1 = new PdfPTable(5);
			bodyData1.setTotalWidth(columnSize);
			bodyData1.setWidthPercentage(100);
			bodyData1.setSpacingBefore(0f);
			bodyData1.setSpacingAfter(0f);

			// BODY DATA
			sistema = new PdfPCell(new Paragraph("MAJS/ Hamburgues", normalFont12));
			valor = new PdfPCell(new Paragraph("R$ 1.000,00", normalFont12));
			taxa = new PdfPCell(new Paragraph("1,00%", normalFont12));
			prazo = new PdfPCell(new Paragraph("12,00", normalFont12));
			prestacao = new PdfPCell(new Paragraph("12/05/2023", normalFont12));

			sistema.setPadding(8.0f);
			valor.setPadding(8.0f);
			taxa.setPadding(8.0f);
			prazo.setPadding(8.0f);
			prestacao.setPadding(8.0f);

			sistema.setBorderColor(BaseColor.GRAY);
			valor.setBorderColor(BaseColor.GRAY);
			taxa.setBorderColor(BaseColor.GRAY);
			prazo.setBorderColor(BaseColor.GRAY);
			prestacao.setBorderColor(BaseColor.GRAY);

			sistema.setHorizontalAlignment(Element.ALIGN_LEFT);
			valor.setHorizontalAlignment(Element.ALIGN_RIGHT);
			taxa.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prazo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			prestacao.setHorizontalAlignment(Element.ALIGN_RIGHT);

			bodyData1.addCell(sistema);
			bodyData1.addCell(valor);
			bodyData1.addCell(taxa);
			bodyData1.addCell(prazo);
			bodyData1.addCell(prestacao);

			document.add(body1);
			document.add(bodyData1);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("https://universoadministracao.com/"), 140, 30, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("page " + document.getPageNumber()), 530, 30, 0);
	}
}
