package ninzaCrm_DDT;

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

public class CreateLeadwithCampaign_DDt {

	

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		//Reading Data from properties
				FileInputStream pfis = new FileInputStream(
						"E:\\AdvanceSelenium\\commonData.properties");
				Properties prop = new Properties();
				prop.load(pfis);
				String BROWSER = prop.getProperty("Browser");
				String URL = prop.getProperty("Url");
				String UNAME = prop.getProperty("Username");
				String PWD = prop.getProperty("Password");
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
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				// step 2:Navigate to URL
				driver.get(URL);
				System.out.println("Successfully navigated to URL");

				// Step 3: Login
				driver.findElement(By.id("username")).sendKeys(UNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PWD);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				System.out.println("Successfully logged in");

				// Step 4: Navigate to Campaign module
				driver.findElement(By.linkText("Campaigns")).click();

				// Generate Random Number
				Random ran = new Random();
				int ranNum = ran.nextInt(3000);

				// Read test script data from excel
				FileInputStream efis = new FileInputStream(
						"E:\\AdvanceSelenium\\createCampaign.xlsx");
				Workbook wb = WorkbookFactory.create(efis);
				String campaignName = wb.getSheet("Lead").getRow(1).getCell(2).getStringCellValue() + ranNum;
				String target = wb.getSheet("Lead").getRow(1).getCell(3).getStringCellValue();
				
				String lName= wb.getSheet("Lead").getRow(1).getCell(4).getStringCellValue()+ranNum;
				String companyName= wb.getSheet("Lead").getRow(1).getCell(5).getStringCellValue()+ranNum;
				String lSource= wb.getSheet("Lead").getRow(1).getCell(6).getStringCellValue();
				String lIndustry= wb.getSheet("Lead").getRow(1).getCell(7).getStringCellValue();
				String phoneNum= wb.getSheet("Lead").getRow(1).getCell(8).getStringCellValue();
				String lStatus= wb.getSheet("Lead").getRow(1).getCell(9).getStringCellValue();
				String lRating= wb.getSheet("Lead").getRow(1).getCell(10).getStringCellValue();
				
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
//					String currURL = driver.getCurrentUrl();
//					if(currURL.contains("selectCampaign")) {
						driver.switchTo().window(winId);
//						System.out.println("Switched");
					
					//}
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
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOf(userIcon));
				// Thread.sleep(8000);
				Actions act = new Actions(driver);
				act.moveToElement(userIcon).click().perform();
				driver.findElement(By.xpath("//div[text()='Logout ']")).click();

				// Step 9: Close the Browser
				Thread.sleep(5000);
				driver.quit();

	}

}