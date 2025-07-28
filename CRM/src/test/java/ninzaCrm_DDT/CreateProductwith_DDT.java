package ninzaCrm_DDT;

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

public class CreateProductwith_DDT {

	public static void main(String[] args) throws InterruptedException, IOException
	{
		//Step 1: create object FIS
		FileInputStream fis= new FileInputStream("E:\\AdvanceSelenium\\commonData.properties");
		
		//step 2: Create obj of property file
		Properties prop= new Properties();
		
		//Step 3: Load all the keys
		prop.load(fis);
		
		//Step 4: Read the data 
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String UNAME = prop.getProperty("Username");
		String PWD = prop.getProperty("Password");
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//go to products
		driver.findElement(By.linkText("Products")).click();
		
		//create product
		driver.findElement(By.xpath("//span[text()= 'Add Product']")).click();
		
		//generate RandomNumber

		Random ran= new Random();
		int ranNum= ran.nextInt(1000);
		
		//read the test script data from excel
		
		//step 1: Create obj of FIS
		FileInputStream efis= new FileInputStream("E:\\AdvanceSelenium\\createCampaign.xlsx");
		
		//Step 2: Open the workbook in read mode
		Workbook wb = WorkbookFactory.create(efis);
		
		//step 3: Get the control of sheet, row, cell and read the data
		
		String prodName = wb.getSheet("Product").getRow(1).getCell(2).getStringCellValue()+ranNum;
	  String category = wb.getSheet("Product").getRow(1).getCell(3).getStringCellValue();
	  String vendor = wb.getSheet("Product").getRow(1).getCell(4).getStringCellValue();
	  String quantity=  wb.getSheet("Product").getRow(1).getCell(5).getStringCellValue();
	  String price= wb.getSheet("Product").getRow(1).getCell(6).getStringCellValue();
		//step 4: close the workbook
		wb.close();
		
		driver.findElement(By.name("productName")).sendKeys(prodName);
		//drop downs
		WebElement prodCategory = driver.findElement(By.name("productCategory"));
		Select catSel= new Select(prodCategory);
		catSel.selectByValue(category);
		
		WebElement vendorName = driver.findElement(By.name("vendorId"));
		Select vendSel= new Select(vendorName);
		vendSel.selectByValue(vendor);
		
		driver.findElement(By.name("quantity")).sendKeys(quantity);
		driver.findElement(By.name("price")).sendKeys(price);
		
		//save
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		//Verification
		WebElement message =driver.findElement(By.xpath("//div[contains(text(), 'Product')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(message));
		
		String msg = message.getText();
		 if(msg.contains(prodName)) {
			 System.out.println("Successfully Created the campaign "+prodName);	 
		 }else {
			 System.out.println("Failed to Create the campaign "+prodName);
		 }
		 
		 //log out
		 WebElement userIcon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
		 Actions act = new Actions(driver);
		 act.moveToElement(userIcon).click().perform();
		 driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		 Thread.sleep(7000);
		 driver.quit();

		

}

}


