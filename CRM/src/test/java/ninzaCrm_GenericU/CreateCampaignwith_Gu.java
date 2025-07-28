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
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.Excelfileutility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtiliy;
import genericUtility.WebDriverUtility;

public class CreateCampaignwith_Gu {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		PropertiesFileUtiliy pUtill = new PropertiesFileUtiliy();
		Excelfileutility eUtill = new Excelfileutility();
		JavaUtility jUtill = new JavaUtility();
		WebDriverUtility wUtill = new WebDriverUtility();
	    String BROWSER = pUtill.ReadDatafromProFile("Browser");
	    String URL = pUtill.ReadDatafromProFile("Url");
	    String USERNAME = pUtill.ReadDatafromProFile("Username");
	    String PASSWORD = pUtill.ReadDatafromProFile("Password");
	    
		
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
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).submit();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		int ranNum = jUtill.GenerateRandomNumber();
		String campaignName=eUtill.ReadDataFromExcel("campaigns", 1, 2)+ranNum;
		String target=eUtill.ReadDataFromExcel("campaigns", 1, 3)+ranNum;
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
		wUtill.waitForPageToLoad(driver);
		wUtill.waitForVisibilityOfElement(driver, icon);
		wUtill.moveToElement(driver, icon);
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		driver.quit();  
	}
}
