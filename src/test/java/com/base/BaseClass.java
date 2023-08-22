package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static WebDriver launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;

	}

	public static void launchUrl(String uRL) {
		driver.get(uRL);
		driver.manage().window().maximize();
	}

	public static void enterText(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void Click(WebElement element) {
		element.click();
	}

	public static void implicitWait(long seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		
	}
	public String getData(String name,int rownum,int cellnum) throws IOException {
		String value= null;
		File f = new File("C:\\Users\\Novfoukz\\eclipse-workspace\\eclipse2022\\TechmentProject\\ExcelSheets\\Book1.xlsx");
		FileInputStream stream = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet(name);
		Row r = s.getRow(rownum);
		Cell c = r.getCell(cellnum);
		CellType type = c.getCellType();
		switch (type) {
		case STRING:
			 value = c.getStringCellValue();
			
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(c)) {
				Date d = c.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 value = dateFormat.format(d);
			} else {
				double numericCellValue = c.getNumericCellValue();
				long l = (long)numericCellValue;
				value = String.valueOf(l);

			}

		default:
			break;
		}
		return value;
	}
	
}
