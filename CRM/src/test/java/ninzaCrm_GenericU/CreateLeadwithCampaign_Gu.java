package ninzaCrm_GenericU;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateLeadwithCampaign_Gu {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		//Reading Data from properties
	       PropertiesFileUtiliy pUtill = new PropertiesFileUtiliy();
	       Excelfileutility eUtill= new Excelfileutility();
	       WebDriverUtility  wUtill = new WebDriverUtility ();
	       JavaUtility jUtill = new JavaUtility();
	        String BROWSER = pUtill.ReadDatafromProFile("Browser");
	      String URL = pUtill.ReadDatafromProFile("Url");
	     String USERNAME = pUtill.ReadDatafromProFile("Username");
	    String PASSWORD = pUtill.ReadDatafromProFile("Password");
	       WebDriver driver = null;
				// Cross Browser testing and Launching Browser
				if (BROWSER.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				} else if (BROWSER.equalsIgnoreCase("edge")) {
					driver = new EdgeDriver();
				} else {
					driver = new FirefoxDriver();
				}

				// maximize
				driver.manage().window().maximize();
				System.out.println("Successfully launched Browser and maximized");
				wUtill.waitForPageToLoad(driver);
				// step 2:Navigate to URL
				driver.get(URL);
				System.out.println("Successfully navigated to URL");
                 // Step 3: Login
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				System.out.println("Successfully logged in");
                // Step 4: Navigate to Campaign module
				driver.findElement(By.linkText("Campaigns")).click();
				// Generate Random Number
				int ranNum = jUtill.GenerateRandomNumber();
				// Read test script data from excel
				String campaignName	=eUtill.ReadDataFromExcel("Lead", 1, 2)+ranNum;
				String target =eUtill.ReadDataFromExcel("Lead", 1, 3)+ranNum;
				String lName=eUtill.ReadDataFromExcel("Lead", 1, 4)+ranNum;
				String companyName =eUtill.ReadDataFromExcel("Lead", 1, 5)+ranNum;
				String lSource =eUtill.ReadDataFromExcel("Lead", 1, 6)+ranNum;
				String lIndustry =eUtill.ReadDataFromExcel("Lead", 1, 7)+ranNum;
				String phoneNum =eUtill.ReadDataFromExcel("Lead", 1, 8)+ranNum;
				String lStatus =eUtill.ReadDataFromExcel("Lead", 1, 9)+ranNum;
				String lRating =eUtill.ReadDataFromExcel("Lead", 1, 10)+ranNum;
				// Step 5: Click on "Create Campaign Button"
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
                 // Step 6: Enter the mandatory details
				driver.findElement(By.name("campaignName")).sendKeys(campaignName);
				driver.findElement(By.name("targetSize")).sendKeys(target);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				// Step 7: Verify the message
				Thread.sleep(2000);
				String message = driver.findElement(By.xpath("//div[contains(text(),'Campaign')]")).getText();
				Thread.sleep(2000);
				if (message.contains(campaignName)) {
					System.out.println("Successfully created the campaign");
				} else {
					System.out.println("Failed to create the campaign");
				}
				Thread.sleep(3000);
				//Step 8: Navigate to Lead Module
				driver.findElement(By.linkText("Leads")).click();
				driver.findElement(By.xpath("//span[text()='Create Lead']")).click();
				String parentId = driver.getWindowHandle();
				//Stpe 9: enter mandatory Details and save the lead
				driver.findElement(By.name("name")).sendKeys(lName);
				driver.findElement(By.name("company")).sendKeys(companyName);
				driver.findElement(By.name("leadSource")).sendKeys(lSource);
				driver.findElement(By.name("industry")).sendKeys(lIndustry);
				driver.findElement(By.name("phone")).sendKeys(phoneNum);
				driver.findElement(By.name("leadStatus")).sendKeys(lStatus);
				driver.findElement(By.name("rating")).sendKeys(lRating);
				driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
				Thread.sleep(3000);
				Set<String> allWinIds = driver.getWindowHandles();//Both Parent and Child
				allWinIds.remove(parentId);
				for(String winId: allWinIds) {				
						driver.switchTo().window(winId);
				}
				WebElement campDD = driver.findElement(By.id("search-criteria"));
				Select sel= new Select(campDD);
				sel.selectByValue("campaignName");
				driver.findElement(By.id("search-input")).sendKeys(campaignName);
				driver.findElement(By.xpath("//td[text()='"+campaignName+"']/..//button[text()='Select']")).click();
				driver.switchTo().window(parentId);
				driver.findElement(By.xpath("//button[text()='Create Lead']")).click();
				//Step 10: Verify the lead
				Thread.sleep(2000);
				String leadMsg = driver.findElement(By.xpath("//div[contains(text(),'Lead ')]")).getText();
				Thread.sleep(2000);
				if (leadMsg.contains(lName)) {
					System.out.println("Successfully created the Lead");
				} else {
					System.out.println("Failed to create the Lead");
				}
				Thread.sleep(3000);
				// Step 8: Log out
				WebElement userIcon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
				// Explicit wait
				wUtill.waitForVisibilityOfElement(driver, userIcon);
				wUtill.moveToElement(driver, userIcon);
				Actions act = new Actions(driver);
				driver.findElement(By.xpath("//div[text()='Logout ']")).click();
				// Step 9: Close the Browser
				Thread.sleep(5000);
				driver.quit();

	}

}