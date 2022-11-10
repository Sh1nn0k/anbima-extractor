package com.anbima;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.anbima.models.DataModel;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException{
				
		System.out.println("\r\n"
				+ "  ___       _      _                 _                                       /\\/|       \r\n"
				+ " |_ _|_ __ (_) ___(_) __ _ _ __   __| | ___     _____  _____  ___ _   _  ___|/\\/_  ___  \r\n"
				+ "  | || '_ \\| |/ __| |/ _` | '_ \\ / _` |/ _ \\   / _ \\ \\/ / _ \\/ __| | | |/ __/ _` |/ _ \\ \r\n"
				+ "  | || | | | | (__| | (_| | | | | (_| | (_) | |  __/>  <  __/ (__| |_| | (_| (_| | (_) |\r\n"
				+ " |___|_| |_|_|\\___|_|\\__,_|_| |_|\\__,_|\\___/   \\___/_/\\_\\___|\\___|\\__,_|\\___\\__,_|\\___/ \r\n"
				+ "                                                                         )_)            \r\n"
				+ "");
		
		ArrayList<DataModel> data = new ArrayList<DataModel>();
		
		ExcelExport excelExport = new ExcelExport();
		
		File dir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
		
		File file = new File(dir+"\\chromedriver.exe");
				
		InputStream is = Main.class.getClassLoader().getResourceAsStream("com/anbima/driver/chromedriver.exe");
		
		if(!file.exists() || Files.size(file.toPath())==0) {
		IOUtils.copy(is, new FileOutputStream(file));
		}
		 
		System.setProperty("webdriver.chrome.driver", file.toString());
				
		 ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless");
	        options.addArguments("--window-size=1920,1080");	        
		
		WebDriver driver = new ChromeDriver(options);
				
		driver.get("https://data.anbima.com.br/fundos?page=1&size=100&");
		
		Thread.sleep(1000);
		
		System.out.println("\r\n"
				+ "  _____      _             _           _                         _           _           \r\n"
				+ " | ____|_  _| |_ _ __ __ _(_)_ __   __| | ___     ___  ___    __| | __ _  __| | ___  ___ \r\n"
				+ " |  _| \\ \\/ / __| '__/ _` | | '_ \\ / _` |/ _ \\   / _ \\/ __|  / _` |/ _` |/ _` |/ _ \\/ __|\r\n"
				+ " | |___ >  <| |_| | | (_| | | | | | (_| | (_) | | (_) \\__ \\ | (_| | (_| | (_| | (_) \\__ \\\r\n"
				+ " |_____/_/\\_\\\\__|_|  \\__,_|_|_| |_|\\__,_|\\___/   \\___/|___/  \\__,_|\\__,_|\\__,_|\\___/|___/\r\n"
				+ "                                                                                         \r\n"
				+ "");
		
		int lastPage = Integer.parseInt(driver.findElement(By.xpath("//a[@class='anbima-ui-pagination__label__last-page']")).getText());
		
		ProgressBarBuilder pbb = new ProgressBarBuilder().setStyle(ProgressBarStyle.ASCII).setTaskName("Extraindo...");
		
		ProgressBar.wrap(IntStream.rangeClosed(1,lastPage), pbb).forEach(i -> {
			
			for(int j=0; j<=99; j++) {
			
			try {
				
				DataModel dataModel = new DataModel();
				
				dataModel.setNomeFundo(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//a[@id='item-title-" + j + "']")).getText());
				dataModel.setLinkFundo(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//a[@id='item-title-" + j + "']")).getAttribute("href"));
				dataModel.setCnpjFundo(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//span[@id='cnpj-" + j + "']")).getText().substring(0, 18));
				dataModel.setClasseAnbima(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//span[@id='classeAnbima-" + j + "']")).getText());
				try {
				dataModel.setGestorFundo(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='gestor-" + j + "']/dd")).getText());
				}catch(Exception e){
					dataModel.setGestorFundo("");
				}
				dataModel.setAplicacaoMin(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='aplicacaoInicialMinima-" + j + "']/dd")).getText());
				dataModel.setPatrimonioLiquido(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='patrimonioLiquido-" + j + "']/dd")).getText());
				dataModel.setAdministradorFundo(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='administrador-" + j + "']/dd")).getText());
				dataModel.setCaracteristicaInvestidor(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='caracteristicaInvestidor-" + j + "']/dd")).getText());
				dataModel.setTaxaAdministracaoMax(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='taxaAdministracaoMaxima-" + j + "']/dd")).getText());
				dataModel.setRentabilidade(driver.findElement(By.xpath("//li[@class='col-xs-12 list-item__container col-no-padding']//dl[@id='rentabilidade-" + j + "']/dd")).getText());
				
				data.add(new DataModel(dataModel.getNomeFundo(),
						dataModel.getLinkFundo(), 
						dataModel.getCnpjFundo(),
						dataModel.getClasseAnbima(),
						dataModel.getGestorFundo(), 
						dataModel.getAplicacaoMin(), 
						dataModel.getPatrimonioLiquido(),
						dataModel.getAdministradorFundo(),
						dataModel.getCaracteristicaInvestidor(), 
						dataModel.getTaxaAdministracaoMax(), 
						dataModel.getRentabilidade()));
				
			}catch(NoSuchElementException e){
				
			break;
				
			}

		}
		
		driver.get("https://data.anbima.com.br/fundos?page=" + (i+1) + "&size=100&");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		});

		driver.quit();
		excelExport.export(data);
		
		System.out.println("\r\n"
				+ "  _____                          /\\/|          __ _             _ _              _       \r\n"
				+ " | ____|_  _____  ___ _   _  ___|/\\/_  ___    / _(_)_ __   __ _| (_)______ _  __| | __ _ \r\n"
				+ " |  _| \\ \\/ / _ \\/ __| | | |/ __/ _` |/ _ \\  | |_| | '_ \\ / _` | | |_  / _` |/ _` |/ _` |\r\n"
				+ " | |___ >  <  __/ (__| |_| | (_| (_| | (_) | |  _| | | | | (_| | | |/ / (_| | (_| | (_| |\r\n"
				+ " |_____/_/\\_\\___|\\___|\\__,_|\\___\\__,_|\\___/  |_| |_|_| |_|\\__,_|_|_/___\\__,_|\\__,_|\\__,_|\r\n"
				+ "                             )_)                                                         \r\n"
				+ "");
		
		System.out.println("\r\n"
				+ "                                                                                            \r\n"
				+ "                                                                                            \r\n"
				+ "  _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ \r\n"
				+ " |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|\r\n"
				+ "                                                                                            \r\n"
				+ "                                                                                            \r\n"
				+ "");
		
		System.out.println("\r\n"
				+ "     _                    _                                       _                         __                       _        _             _           _ _                 _                           __       _       \r\n"
				+ "    / \\   _ __ __ _ _   _(_)_   _____     __ _  ___ _ __ __ _  __| | ___    _ __   __ _    /_/_ _ __ ___  __ _    __| | ___  | |_ _ __ __ _| |__   __ _| | |__   ___     __| | ___    _   _ ___ _   _  /_/_ _ __(_) ___  \r\n"
				+ "   / _ \\ | '__/ _` | | | | \\ \\ / / _ \\   / _` |/ _ \\ '__/ _` |/ _` |/ _ \\  | '_ \\ / _` |  / _` | '__/ _ \\/ _` |  / _` |/ _ \\ | __| '__/ _` | '_ \\ / _` | | '_ \\ / _ \\   / _` |/ _ \\  | | | / __| | | |/ _` | '__| |/ _ \\ \r\n"
				+ "  / ___ \\| | | (_| | |_| | |\\ V / (_) | | (_| |  __/ | | (_| | (_| | (_) | | | | | (_| | | (_| | | |  __/ (_| | | (_| |  __/ | |_| | | (_| | |_) | (_| | | | | | (_) | | (_| | (_) | | |_| \\__ \\ |_| | (_| | |  | | (_) |\r\n"
				+ " /_/   \\_\\_|  \\__, |\\__,_|_| \\_/ \\___/   \\__, |\\___|_|  \\__,_|\\__,_|\\___/  |_| |_|\\__,_|  \\__,_|_|  \\___|\\__,_|  \\__,_|\\___|  \\__|_|  \\__,_|_.__/ \\__,_|_|_| |_|\\___/   \\__,_|\\___/   \\__,_|___/\\__,_|\\__,_|_|  |_|\\___/ \r\n"
				+ "                 |_|                     |___/                                                                                                                                                                           \r\n"
				+ "");

	}

}
