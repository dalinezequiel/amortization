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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * Servlet implementation class Report
 */
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Document document = null;
	private float [] sizeHead;
	private static Font fonteNormal, fonteTitulo, masterFont, normalFont14, normalFont12, fonteSubTitulo = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		this.report(0, "", request, response);
	}
	
	public void report(int id, String paciente, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		document = new Document(PageSize.A4, 50f, 50f, 50f, 50f);
		masterFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
		fonteNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
		fonteTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		normalFont14 = new Font(Font.FontFamily.UNDEFINED, 14, Font.NORMAL);
		normalFont12 = new Font(Font.FontFamily.UNDEFINED, 10, Font.BOLD);
		fonteSubTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Dispostion", "inline; filename=relatorio_diagnostico.pdf");
			
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			//Criando tabela
			PdfPTable head = new PdfPTable(1);
			float [] sizeHead = {250};
			//head.setTotalWidth(sizeHead);
			head.setWidthPercentage(100);
			head.setHorizontalAlignment(Element.ALIGN_LEFT);
			head.setSpacingBefore(0f);
			head.setSpacingAfter(0f);
//			head.setM
			
			//Adicionando cabeçalho inicial
			PdfPCell simor = new PdfPCell(new Paragraph("SIMOR", fonteTitulo));
			simor.setHorizontalAlignment(Element.ALIGN_LEFT);
			simor.setBorderWidth(0.0f);
			simor.setPaddingLeft(0);
			
			PdfPCell sir = new PdfPCell(new Paragraph("Sistema de Amortização"));
			sir.setHorizontalAlignment(Element.ALIGN_LEFT);
			sir.setBorderWidth(0f);
			sir.setPaddingLeft(0);
			
			PdfPCell user = new PdfPCell(new Paragraph("User", fonteNormal));
			user.setHorizontalAlignment(Element.ALIGN_LEFT);
			user.setBorderWidth(0.0f);
			user.setPaddingLeft(0);
			
			PdfPCell usrDate = new PdfPCell(new Paragraph(String.valueOf(Date.valueOf(LocalDate.now())), fonteNormal));
			usrDate.setHorizontalAlignment(Element.ALIGN_LEFT);
			usrDate.setBorderWidth(0.0f);
			usrDate.setPaddingLeft(0);
			
			PdfPCell emprestFinan = new PdfPCell(new Paragraph("Dados do Emprestímo ou Finânciamento", normalFont14));
			emprestFinan.setHorizontalAlignment(Element.ALIGN_LEFT);
			emprestFinan.setBorderWidth(0.0f);
			
			PdfPCell space = new PdfPCell(new Paragraph(" "));
			space.setBorderWidth(0f);
			

			head.addCell(simor);
			head.addCell(sir);
			head.addCell(space);
			head.addCell(user);
			head.addCell(usrDate);
//			head.addCell(emprestFinan);
			
			document.add(head);
			document.add(new Paragraph("Dados do Emprestímo ou Finânciamento", normalFont14));
			document.add(new Paragraph(" "));

			//Tabela de diagnósticos
			PdfPTable table = new PdfPTable(5);
			float [] sizeHeader = {190, 120, 100, 100, 160};
			table.setTotalWidth(sizeHeader);
			table.setWidthPercentage(100);
			table.setSpacingBefore(0f);
		    table.setSpacingAfter(0f);
			
			//Cabeçalho
			PdfPCell coluna1 = new PdfPCell(new Paragraph("Sistema", normalFont12));
			PdfPCell coluna2 = new PdfPCell(new Paragraph("Valor", normalFont12));
			PdfPCell coluna3 = new PdfPCell(new Paragraph("Taxa", normalFont12));
			PdfPCell coluna4 = new PdfPCell(new Paragraph("Prazo", normalFont12));
			PdfPCell coluna5 = new PdfPCell(new Paragraph("Primeira Prestação", normalFont12));
			
			coluna1.setPadding(8.0f);
			coluna2.setPadding(8.0f);
			coluna3.setPadding(8.0f);
			coluna4.setPadding(8.0f);
			coluna5.setPadding(8.0f);
			
			coluna1.setBorderWidth(0.01f);
			coluna2.setBorderWidth(0.01f);
			coluna3.setBorderWidth(0.01f);
			coluna4.setBorderWidth(0.01f);
			coluna5.setBorderWidth(0.01f);
			
			coluna1.setBorderColor(BaseColor.GRAY);
			coluna2.setBorderColor(BaseColor.GRAY);
			coluna3.setBorderColor(BaseColor.GRAY);
			coluna4.setBorderColor(BaseColor.GRAY);
			coluna5.setBorderColor(BaseColor.GRAY);

			coluna1.setBackgroundColor(BaseColor.LIGHT_GRAY); 
			coluna2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			coluna3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			coluna4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			coluna5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			
			coluna1.setHorizontalAlignment(Element.ALIGN_LEFT);
			coluna2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			coluna3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			coluna4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			coluna5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			table.addCell(coluna1);
			table.addCell(coluna2);
			table.addCell(coluna3);
			table.addCell(coluna4);
			table.addCell(coluna5);
			
			//
			//Adicionando dados na tabela de forma dinamica
//			ArrayList<DiagnosticoModel> listaDiag = null;
//			if((id != 0) || ((paciente != null) && (!paciente.isEmpty())) ) {
//				listaDiag  = DiagnosticoDAO.listaDiagnosticoByMultipleParameters(id, paciente, "");
//			}else {
//				listaDiag  = DiagnosticoDAO.listaDiagnostico();
//			}
//			
//			PdfPCell cellCodigo = null;
//			PdfPCell cellAvalia = null;
//			PdfPCell cellDiagnostico = null;
//			PdfPCell cellResposta = null;
//			PdfPCell cellUltimaActualizacao = null;
//
//			for(int i=0; i<listaDiag.size(); i++) {
//				
//				cellCodigo = new PdfPCell(new Paragraph(String.valueOf(listaDiag.get(i).getIdDiagnostico()), fonteNormal));
//				cellCodigo.setPadding(8.0f);
//				cellCodigo.setBorderWidth(0.01f);
//				cellCodigo.setBackgroundColor(BaseColor.WHITE);
//				
//				cellAvalia = new PdfPCell(new Paragraph(String.valueOf(listaDiag.get(i).getIdAvaliacao()), fonteNormal));
//				cellAvalia.setPadding(8.0f);
//				cellAvalia.setBorderWidth(0.01f);
//				cellAvalia.setBackgroundColor(BaseColor.WHITE);
//				
//				cellDiagnostico = new PdfPCell(new Paragraph(listaDiag.get(i).getDiagnostico(), fonteNormal));
//				cellDiagnostico.setPadding(8.0f);
//				cellDiagnostico.setBorderWidth(0.01f);
//				cellDiagnostico.setBackgroundColor(BaseColor.WHITE);
//				
//				cellResposta = new PdfPCell(new Paragraph(listaDiag.get(i).getResposta(), fonteNormal));
//				cellResposta.setPadding(8.0f);
//				cellResposta.setBorderWidth(0.01f);
//				cellResposta.setBackgroundColor(BaseColor.WHITE);
//				
//				cellUltimaActualizacao = new PdfPCell(new Paragraph(listaDiag.get(i).getPaciente(), fonteNormal));
//				cellUltimaActualizacao.setPadding(8.0f);
//				cellUltimaActualizacao.setBorderWidth(0.01f);
//				cellUltimaActualizacao.setBackgroundColor(BaseColor.WHITE);
//				
//				table.addCell(cellCodigo);
//				table.addCell(cellAvalia);
//				table.addCell(cellDiagnostico);
//				table.addCell(cellResposta);
//				table.addCell(cellUltimaActualizacao);
//			}
			
			
			//Footer
			PdfPTable tableFooter = new PdfPTable(2);
			float [] sizeFooter = {630,135};
			tableFooter.setTotalWidth(sizeFooter);
			tableFooter.setWidthPercentage(100);
			tableFooter.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableFooter.setSpacingBefore(0f);
		    tableFooter.setSpacingAfter(0f);
			
			//Cabeçalho
			PdfPCell footer1 = new PdfPCell(new Paragraph("TOTAL", fonteSubTitulo));
//			PdfPCell footer2 = new PdfPCell(new Paragraph(String.valueOf(listaDiag.size()), fonteSubTitulo));

			footer1.setPadding(8.0f);
//			footer2.setPadding(8.0f);
//			footer2.setHorizontalAlignment(Element.ALIGN_CENTER);

			footer1.setBorderWidth(0.1f);
			//footer2.setBorderWidth(0.1f);
			
			//Adicionando cabeçalho a tabela
			tableFooter.addCell(footer1);
			//tableFooter.addCell(footer2);

			document.add(table);
			document.add(new Paragraph(" "));
			document.add(tableFooter);
			document.close();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			document.close();
		}
	}
}
