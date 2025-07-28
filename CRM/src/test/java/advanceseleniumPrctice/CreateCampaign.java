package advanceseleniumPrctice;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaign {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/ ");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).submit();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("l2");
		driver.findElement(By.name("targetSize")).sendKeys("3");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		Thread.sleep(2000);
		WebElement verMsg = driver.findElement(By.xpath("//div[contains(text(),'Successfully Added')]"));
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



