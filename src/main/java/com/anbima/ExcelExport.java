package com.anbima;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anbima.models.DataModel;

public class ExcelExport {
	
	public static final String filePath = System.getProperty("user.home") + "\\Desktop" + "\\anbima.xlsx";
	
	private int i = 0;
	
	File file = new File(filePath);
	
	public void export(ArrayList<DataModel> data) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Anbima");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Nome do Fundo");
		header.createCell(1).setCellValue("Link do Fundo");
		header.createCell(2).setCellValue("CNPJ do Fundo");
		header.createCell(3).setCellValue("Classe da Anbima");
		header.createCell(4).setCellValue("Gestor do Fundo");
		header.createCell(5).setCellValue("Aplicação Mínima");
		header.createCell(6).setCellValue("Patrimonio de Líquido");
		header.createCell(7).setCellValue("Administrador do Fundo");
		header.createCell(8).setCellValue("Caracteristica do Investidor");
		header.createCell(9).setCellValue("Taxa de Administração Maxima");
		header.createCell(10).setCellValue("Rentabilidade");
		
		for(int i=0; i<data.size(); i++) {
			
			Row row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(data.get(i).getNomeFundo());
				row.createCell(1).setCellValue(data.get(i).getLinkFundo());
				row.createCell(2).setCellValue(data.get(i).getCnpjFundo());
				row.createCell(3).setCellValue(data.get(i).getClasseAnbima());
				row.createCell(4).setCellValue(data.get(i).getGestorFundo());
				row.createCell(5).setCellValue(data.get(i).getAplicacaoMin());
				row.createCell(6).setCellValue(data.get(i).getPatrimonioLiquido());
				row.createCell(7).setCellValue(data.get(i).getAdministradorFundo());
				row.createCell(8).setCellValue(data.get(i).getCaracteristicaInvestidor());
				row.createCell(9).setCellValue(data.get(i).getTaxaAdministracaoMax());
				row.createCell(10).setCellValue(data.get(i).getRentabilidade());
			
		}

		while(file.exists()) {
			
			i++;
			file = new File(System.getProperty("user.home") + "\\Desktop" + "\\anbima(" + i + ").xlsx");
			
		}
		
		FileOutputStream outputStream = new FileOutputStream(file);
			
		workbook.write(outputStream);
		
	
	}
}
