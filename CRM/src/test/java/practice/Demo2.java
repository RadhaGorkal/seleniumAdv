package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo2 {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		FileInputStream fis=new FileInputStream("E:\\AdvanceSelenium\\saucedemo.properties");
        Properties pro=new Properties();
        pro.load(fis);
        String BROWSER = pro.getProperty("Browser");
        String URL = pro.getProperty("Url");
        String USERNAME = pro.getProperty("UserName");
        String PASSWORD = pro.getProperty("Password");
        
        WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(URL);
        driver.findElement(By.id("user-name")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();
        
        WebElement firstProduct = driver.findElement(By.id("item_4_title_link"));
        String productName1 = firstProduct.getText();
        System.out.println(productName1);
     
       
        FileInputStream fos=new FileInputStream("E:\\AdvanceSelenium\\Demo3.xlsx");
        Workbook wb = WorkbookFactory.create(fos);
        Sheet sh = wb.getSheet("Products");
        Row row = sh.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(productName1);
        
        FileOutputStream foi=new FileOutputStream("E:\\AdvanceSelenium\\Demo3.xlsx");
        wb.write(foi);
        wb.close();
        foi.close();
        System.out.println("successfully enterde the data");
        
        FileInputStream excel=new FileInputStream("E:\\AdvanceSelenium\\Demo3.xlsx");
        Workbook wb1 = WorkbookFactory.create(excel);
        Sheet she = wb.getSheet("Products");
      String productToAdd = she.getRow(0).getCell(0).getStringCellValue();
      wb1.close();
      excel.close();
      System.out.println("Adding to cart: " + productToAdd);
       WebElement productElement = driver.findElement(By.xpath("//div[text()='" + productToAdd + "']/ancestor::div[@class='inventory_item']//button"));
       productElement.click();
       driver.findElement(By.id("react-burger-menu-btn")).click();
       Thread.sleep(1000);
       driver.findElement(By.id("logout_sidebar_link")).click();

	}

}
