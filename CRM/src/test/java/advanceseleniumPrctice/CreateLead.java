package advanceseleniumPrctice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateLead {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. Login to Ninza CRM
        driver.get("http://49.249.28.218:8098/");
        driver.findElement(By.id("username")).sendKeys("rmgyantra");
        driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        // 3. Create a Campaign (assuming there's a section for it)
        driver.findElement(By.linkText("Campaigns")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();

        driver.findElement(By.name("campaign_name")).sendKeys("Test Campaign");
        driver.findElement(By.name("close_date")).sendKeys("2025-06-26"); // Example: 5 days from today
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        // Wait and verify campaign creation
        Thread.sleep(3000); // Better to use explicit wait here

        // 4. Create a Lead
        driver.findElement(By.linkText("Leads")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Create Lead')]")).click();

        driver.findElement(By.name("first_name")).sendKeys("Radha");
        driver.findElement(By.name("last_name")).sendKeys("Kalyani");
        driver.findElement(By.name("email")).sendKeys("radha@example.com");
        driver.findElement(By.name("phone")).sendKeys("9876543210");

        // Select Campaign
        WebElement campaignDropdown = driver.findElement(By.name("campaign_id"));
        Select select = new Select(campaignDropdown);
        select.selectByVisibleText("Test Campaign");  // Match the Campaign name

        // Submit Lead
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        // 5. Verify lead creation (e.g., Toast message or redirection)
        WebElement toast = driver.findElement(By.xpath("//div[contains(@class, 'Toastify__toast') and contains(text(),'Successfully')]"));
        System.out.println("Lead creation status: " + toast.getText());

        // 6. Close browser
        driver.quit();
    }
	}


