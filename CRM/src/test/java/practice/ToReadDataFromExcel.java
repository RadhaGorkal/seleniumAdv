package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ToReadDataFromExcel {

	public static void main(String[] args) throws IOException 
	{
		
			
	       
		FileInputStream fis=new FileInputStream("C:\\Users\\Dell\\OneDrive\\Desktop\\Campaigns.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sh = wb.getSheet("Campaigns");
//		Row r = sh.getRow(1);
//		Cell c = r.getCell(2);
//		String campaignName = c.getStringCellValue();
		String campaignName = wb.getSheet("Campaigns").getRow(1).getCell(0).getStringCellValue();
	    String target = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();
        wb.close();
        FileInputStream pfis = new FileInputStream("E:\\AdvanceSelenium\\Campaign.properties");
        Properties pro = new Properties();
        pro.load(pfis);
       String URL = pro.getProperty("Url");
       String BROWSER = pro.getProperty("Browser");
       String USERNAME = pro.getProperty("Username");
       String PASSWORD = pro.getProperty("Password");
       
       
       
       WebDriver driver=new FirefoxDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
       driver.get(URL);
       driver.findElement(By.id("username")).sendKeys(USERNAME);
       driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
       driver.findElement(By.xpath("//button[text()='Sign In']")).click();
       driver.findElement(By.linkText("Campaigns")).click();
       driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
       driver.findElement(By.name("campaignName")).sendKeys(campaignName);
		driver.findElement(By.name("targetSize")).sendKeys(target);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
       
        
        
	}

}
