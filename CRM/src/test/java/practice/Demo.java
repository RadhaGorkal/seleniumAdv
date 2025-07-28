package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException 
	{
	    
	        // Load Properties
	        Properties prop = new Properties();
	        FileInputStream fis = new FileInputStream("E:\\AdvanceSelenium\\saucedemo.properties");
	        prop.load(fis);

	        String url = prop.getProperty("Url");
	        String username = prop.getProperty("UserName");
	        String password = prop.getProperty("Password");

	        // Setup Selenium
	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get(url);

	        // Login
	        driver.findElement(By.id("user-name")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("login-button")).click();

	        // Store first product name in Excel
	        WebElement firstProduct = driver.findElement(By.className("inventory_item_name"));
	        String productName = firstProduct.getText();
	        System.out.println("Product saved: " + productName);

	        FileOutputStream fileOut = new FileOutputStream("E:\\AdvanceSelenium\\Demo3.xlsx");
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Products");
	        Row row = sheet.createRow(0);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(productName);
	        workbook.write(fileOut);
	        workbook.close();
	        fileOut.close();

	        // Logout before next phase
	        driver.findElement(By.id("react-burger-menu-btn")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.id("logout_sidebar_link")).click();

	        // Login again
	        driver.get(url);
	        driver.findElement(By.id("user-name")).sendKeys(username);
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("login-button")).click();

	        // Read product name from Excel
	        FileInputStream excelFile = new FileInputStream("ProductData.xlsx");
	        Workbook wb = new XSSFWorkbook(excelFile);
	        Sheet sh = wb.getSheet("Products");
	        String productToAdd = sh.getRow(0).getCell(0).getStringCellValue();
	        wb.close();
	        excelFile.close();

	        System.out.println("Adding to cart: " + productToAdd);

	        // Add product to cart
	        WebElement productElement = driver.findElement(By.xpath("//div[text()='" + productToAdd + "']/ancestor::div[@class='inventory_item']//button"));
	        productElement.click();

	        // Logout
	        driver.findElement(By.id("react-burger-menu-btn")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.id("logout_sidebar_link")).click();

	        driver.quit();
	        System.out.println("Test completed successfully.");
	    }
	

		
	       
	       
		

	}


