package ninzaCrm_DDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateCampaignwithdate_DDT {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Reading Data from properties
				FileInputStream pfis = new FileInputStream("E:\\AdvanceSelenium\\commonData.properties");
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
				String campaignName = wb.getSheet("Campaigns").getRow(1).getCell(2).getStringCellValue() + ranNum;
				String target = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();

				// Step 5: Click on "Create Campaign Button"
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();

				// Step 6: Enter the mandatory details
				driver.findElement(By.name("campaignName")).sendKeys(campaignName);
				driver.findElement(By.name("targetSize")).sendKeys(target);

				// Select the date after 5 days
				Date d = new Date();
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
				String currentDate = simple.format(d);
				Calendar cal = simple.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH, 5);
				String reqDate = simple.format(cal.getTime());
				System.out.println(reqDate);
				Thread.sleep(2000);
				driver.findElement(By.name("expectedCloseDate")).sendKeys(reqDate);
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