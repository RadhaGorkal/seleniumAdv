package advanceseleniumPrctice;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Howtoreaddatafromjson {

	public static void main(String[] args) throws IOException, ParseException
	{
		FileReader fr=new FileReader("E:\\AdvanceSelenium\\commonTestData.json");
		JSONParser parse=new JSONParser();
		Object javaobj = parse.parse(fr);
		JSONObject jsonobj=(JSONObject) javaobj;
		Object BROWSER = jsonobj.get("Browser");
		Object URL = jsonobj.get("Url");
		Object PASSWORD = jsonobj.get("Password");
		Object USERNAME = jsonobj.get("Username");
		System.out.println("BROWSER IS :" +jsonobj.get("Browser")); 
		System.out.println("URL IS :" +jsonobj.get("Url"));
		System.out.println("USERNAME IS :" +jsonobj.get("Username"));
        System.out.println("PASSWORD IS :" +jsonobj.get("Password"));
        
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get((String)URL);
        driver.findElement(By.id("username")).sendKeys((String)USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys((String)PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).submit();
		

	}

}
