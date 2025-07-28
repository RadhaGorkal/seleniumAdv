package advanceseleniumPrctice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProduct {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/ ");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).submit();
        driver.findElement(By.linkText("Products")).click();
        driver.findElement(By.xpath("//span[text()='Add Product']")).click();
        driver.findElement(By.name("productName")).sendKeys("Computers5");
        WebElement cata = driver.findElement(By.name("productCategory"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("productCategory")));
        Select sel = new Select(driver.findElement(By.name("productCategory")));
       
        sel.selectByVisibleText("Electronics");
        driver.findElement(By.name("quantity")).sendKeys("4");
        driver.findElement(By.name("price")).sendKeys("1.23");
        WebElement ven = driver.findElement(By.name("vendorId"));
        
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.elementToBeClickable(By.name("vendorId")));
        Select sel1 = new Select(driver.findElement(By.name("vendorId")));
        sel1.selectByValue("VID_008");
        driver.findElement(By.xpath("//button[text()='Add']")).click();
        
       
		
	}

        
	}


