package ninzaCrm_Pom;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.Excelfileutility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtiliy;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.CreateCampaignPage;
import objectRepository.CreateProductPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class CreateCampaignWithMandatoryFiled_Pom {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil= new JavaUtility();
		WebDriverUtility wutil= new WebDriverUtility();
		Excelfileutility eutil= new Excelfileutility();
		PropertiesFileUtiliy putil= new PropertiesFileUtiliy();
		
				String BROWSER = putil.ReadDatafromProFile("Browser");
				String URL = putil.ReadDatafromProFile("Url");
				String UNAME = putil.ReadDatafromProFile("Username");
				String PWD = putil.ReadDatafromProFile("Password");
				
				//cross Browser Testing
				WebDriver driver=null; 
				if(BROWSER.equalsIgnoreCase("chrome")) {
					driver= new ChromeDriver();
				}else if(BROWSER.equalsIgnoreCase("firefox")) {
					driver= new FirefoxDriver();
				}else if(BROWSER.equalsIgnoreCase("edge")) {
					driver= new EdgeDriver();
				}else {
					driver= new ChromeDriver();
				}
				//Login	
				driver.manage().window().maximize();
				wutil.waitForPageToLoad(driver);
				driver.get(URL);
				LoginPage lp = new LoginPage(driver);
				lp.toLoginToNinzaCRM(UNAME, PWD);
								
				//go to products
				HomePage hp= new HomePage(driver);
				hp.getProductModule().click();
							
				//create product
				ProductPage pp= new ProductPage(driver);
				pp.getCreateProdBtn().click();
				
				//generate RandomNumber
				int ranNum = jutil.GenerateRandomNumber();
				
				//read the test script data from excel
			  String prodName = eutil.ReadDataFromExcel("Product", 1, 2)+ranNum;
			  String category = eutil.ReadDataFromExcel("Product", 1, 3);
			  String vendor = eutil.ReadDataFromExcel("Product", 1, 4);
			  String quantity= eutil.ReadDataFromExcel("Product", 1, 5);
			  String price= eutil.ReadDataFromExcel("Product", 1, 6);
					
			  CreateProductPage cpp= new CreateProductPage(driver);
			  cpp.getProdName().sendKeys(prodName);
			
				//drop downs
				WebElement prodCategory = cpp.getProdCategory();
				wutil.selectOption(prodCategory, category);
			
				WebElement vendorName = cpp.getVendorId();
				wutil.selectOption(vendorName, vendor);
				cpp.getQuantity().sendKeys(quantity);
				cpp.getPrice().sendKeys(price);
							
				//save
				cpp.getCreateProdSaveBtn().click();
			
				//Verification
				WebElement message = pp.getProdSuccessMsg();
//				wutil.waitForVisibilityOfElement(driver, message);
				
				String msg =  message.getText();
				 if(msg.contains(prodName)) {
					 System.out.println("Successfully Created the campaign "+prodName);	 
				 }else {
					 System.out.println("Failed to Create the campaign "+prodName);
				 }
				 
				 //log out
				hp.logOutOfNinzaCRM();
						
				 Thread.sleep(7000);
				 driver.quit();
	}

}

