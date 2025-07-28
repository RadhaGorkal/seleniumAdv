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
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithDDT {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		FileInputStream fis=new FileInputStream("E:\\AdvanceSelenium\\commonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String BROWSER = pro.getProperty("Browser");
		String URL = pro.getProperty("Url");
		String UNAME = pro.getProperty("Username");
		String PASSWORD = pro.getProperty("Password");
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("fire fox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).submit();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
	   Random ran=new Random();
	  int ranNum = ran.nextInt(3000);
	  
	  FileInputStream efis=new FileInputStream("E:\\AdvanceSelenium\\createCampaign.xlsx");
	  Workbook wb = WorkbookFactory.create(efis);
	  String campaignName = wb.getSheet("campaigns").getRow(1).getCell(2).getStringCellValue()+ranNum;
	  String target = wb.getSheet("campaigns").getRow(1).getCell(3).getStringCellValue();
	  driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	  driver.findElement(By.name("campaignName")).sendKeys(campaignName);
	  driver.findElement(By.name("targetSize")).sendKeys(target);
	  driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
	  Thread.sleep(2000);
		WebElement verMsg = driver.findElement(By.xpath("//div[contains(text(),'Campaign')]"));
		String message = verMsg.getText();
		System.out.println(message);
		if(message.contains("Campaign"))
		{
			System.out.println("campaign created successfully");
		}
		else
		{
			System.out.println("campaign not created successfully");
		}
		WebElement icon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(icon));
		Actions act=new Actions(driver);
		act.moveToElement(icon).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		driver.quit();
		
	  
	  
	  
	  
	  
	}
}
