package base;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import genericUtility.PropertiesFileUtiliy;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {
	
	PropertiesFileUtiliy putil= new PropertiesFileUtiliy(); 
		 public WebDriver driver=null;  
		  
		 @BeforeSuite 
		 public void configBS() { 
		   
		 Reporter.log("Establish DB Connectivity", 
		true); 
		  
		 } 
		  
		 @AfterSuite 
		 public void configAS() { 
		  
		  Reporter.log("Close the DB Connectivity", 
		true); 
		 } 
		  
		 @BeforeTest 
		 public void configBT() { 
		   
		  Reporter.log("Pre-condition", true); 
		 } 
		  
		 @AfterMethod 
		 public void configAM() { 
		   //log out 
		  HomePage hp= new HomePage(driver); 
		   hp.logOutOfNinzaCRM(); 
		  Reporter.log("Logged Out", true); 
		 } 
		  
		 @AfterClass 
		 public void configAC() throws 
		InterruptedException { 
		  Thread.sleep(7000); 
		  driver.quit(); 
		  Reporter.log("Closed the browser", true); 
		 } 
		  
		 @AfterTest 
		 public void configAT() { 
		   
		  Reporter.log("Post-condition", true); 
		 } 
		  
		 @BeforeClass 
		 public void configBC() throws IOException { 
		   
		  String BROWSER = 
		putil.ReadDatafromProFile("Browser"); 
		  String URL = 
		putil.ReadDatafromProFile("Url"); 
		  //cross Browser Testing 
		     
		   
		 if(BROWSER.equalsIgnoreCase("chrome"))
		 { 
		  driver= new ChromeDriver(); 
		    }
		 else if(BROWSER.equalsIgnoreCase("firefox")) 
		 { 
		     driver= new FirefoxDriver(); 
		    }else 
		if(BROWSER.equalsIgnoreCase("edge"))
		{ 
		     driver= new EdgeDriver(); 
		    }
		else 
		    { 
		     driver= new ChromeDriver(); 
		} 
		Reporter.log("Launch the browser", true); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 
		driver.get(URL); 
		} 
		@BeforeMethod 
		public void configBM() throws IOException { 
		//Login  
		String UNAME = 
		putil.ReadDatafromProFile("Username"); 
		String PWD = 
		putil.ReadDatafromProFile("Password"); 
		LoginPage lp= new LoginPage(driver); 
		lp.toLoginToNinzaCRM(UNAME, PWD); 
		Reporter.log("Logged in", true);

}
}
