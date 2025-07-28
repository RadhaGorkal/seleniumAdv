package ninzaCrm_GenericU;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.Excelfileutility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtiliy;
import genericUtility.WebDriverUtility;

public class CreateProductwith_Gu {

	public static void main(String[] args) throws InterruptedException, IOException
	{
		PropertiesFileUtiliy pUtill = new PropertiesFileUtiliy();
		Excelfileutility eUtill = new Excelfileutility();
		JavaUtility jUtill = new JavaUtility();
		WebDriverUtility wUtill = new WebDriverUtility();
		String BROWSER = pUtill.ReadDatafromProFile("Browser");
		String URL = pUtill.ReadDatafromProFile("Url");
		String PASSWORD = pUtill.ReadDatafromProFile("Password");
		String USERNAME = pUtill.ReadDatafromProFile("Username");
		
		//cross Browser Testing
		WebDriver driver=null; 
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}else {
			driver= new ChromeDriver();
		}
		//Login	
		driver.manage().window().maximize();
		wUtill.waitForPageToLoad(driver);		
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		//go to products
		driver.findElement(By.linkText("Products")).click();
		//create product
		driver.findElement(By.xpath("//span[text()= 'Add Product']")).click();
		//generate RandomNumber
         int ranNum=jUtill.GenerateRandomNumber();
         String prodName =eUtill.ReadDataFromExcel("Product", 1, 2)+ranNum;
         String category =eUtill.ReadDataFromExcel("Product", 1, 3)+ranNum;
         String vendor =eUtill.ReadDataFromExcel("Product", 1, 4)+ranNum;
         String quantity =eUtill.ReadDataFromExcel("Product", 1, 5)+ranNum;
         String price =eUtill.ReadDataFromExcel("Product", 1, 6)+ranNum;
		driver.findElement(By.name("productName")).sendKeys(prodName);
		driver .findElement(By.name("productCategory")).sendKeys(category);
		driver.findElement(By.name("quantity")).sendKeys(quantity);
		driver.findElement(By.name("price")).sendKeys(price);
		driver.findElement(By.name("vendorId")).sendKeys(vendor);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Verification
		WebElement message =driver.findElement(By.xpath("//div[contains(text(), 'Product')]"));
		wUtill.waitForPageToLoad(driver);
		wUtill.waitForVisibilityOfElement(driver, message);
		String msg = message.getText();
		 if(msg.contains(prodName)) {
			 System.out.println("Successfully Created the campaign "+prodName);	 
		 }else {
			 System.out.println("Failed to Create the campaign "+prodName);
		 }
		 //log out
		 WebElement userIcon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
		 wUtill.moveToElement(driver, userIcon);
		 driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		 Thread.sleep(7000);
		 driver.quit();

		

}

}


