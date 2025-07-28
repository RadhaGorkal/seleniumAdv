package ninzaCrm_Pom;

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
import objectRepository.CampaignPage;
import objectRepository.CreateCampaignPage;
import objectRepository.CreateLeadPage;
import objectRepository.HomePage;
import objectRepository.LeadPage;
import objectRepository.LeadwithCampaignPage;
import objectRepository.LoginPage;

public class CreateLeadwithCampaign_Pom {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		//Reading Data from properties
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
				
				//Navigate to Campaign Module
				HomePage hp= new HomePage(driver);
				hp.getCampModule().click();
				//Create Campaign Button
				CampaignPage cp= new CampaignPage(driver);
				cp.getCreateCampBtn().click();
				
				//generate RandomNumber
				int ranNum = jutil.GenerateRandomNumber();
				
           //read the test script data from excel
				String lName =eutil.ReadDataFromExcel("Lead", 1, 2)+ranNum;
				String cName = eutil.ReadDataFromExcel("Lead", 1, 3)+ranNum;
				String leadSrc =eutil.ReadDataFromExcel("Lead", 1, 4);
				String industry=  eutil.ReadDataFromExcel("Lead", 1, 5);
				String phNum= eutil.ReadDataFromExcel("Lead", 1, 6);
				String status= eutil.ReadDataFromExcel("Lead", 1, 7);
				String CampInLead= eutil.ReadDataFromExcel("Lead", 1, 8)+ranNum;
				String targetSize = eutil.ReadDataFromExcel("Lead", 1, 9);
				String ratings = eutil.ReadDataFromExcel("Lead", 1, 10);
				
				//Enter mandatory Details
				CreateCampaignPage ccp= new CreateCampaignPage(driver);
				ccp.getCampName().sendKeys(CampInLead);
				ccp.getTargetSize().sendKeys(targetSize);
				//save
				ccp.getCreateCampSaveBtn().click();
				
				//Verification
				WebElement message =cp.getCampSuccessMsg();
				wutil.waitForVisibilityOfElement(driver, message);
								
				String msg = message.getText();
				 if(msg.contains(CampInLead)) {
					 System.out.println("Successfully Created the campaign "+CampInLead);	 
				 }else {
					 System.out.println("Failed to Create the campaign "+CampInLead);
				 }
				
				
				//Lead module
				WebElement leadModule= hp.getLeadModule();
				 leadModule.click();
				Thread.sleep(5000);
				//Create Lead
				LeadPage leadp= new LeadPage(driver);
				WebElement createLeadBtn = leadp.getCreateLeadBtn();
				wutil.waitForElementToBeClickable(driver, createLeadBtn);
				createLeadBtn.click();
			
				//details
				CreateLeadPage clp= new CreateLeadPage(driver);
				clp.getLeadName().sendKeys(lName);
				clp.getCompanyName().sendKeys(cName);
				clp.getSource().sendKeys(leadSrc);
				clp.getIndustry().sendKeys(industry);
				clp.getPhone().sendKeys(phNum);
				clp.getStatus().sendKeys(status);
				clp.getRating().sendKeys(ratings);
							
				WebElement addCampBtn =clp.getAddCampBtn(); 
				addCampBtn.click();
				String parentID = driver.getWindowHandle();
				wutil.toSwitchToWindow(driver, "Campaign");
				//dropdown
				LeadwithCampaignPage lwcp= new LeadwithCampaignPage(driver);
				WebElement categoryDD = lwcp.getSearchCriteria();
				wutil.selectOption(categoryDD, "campaignName");
				lwcp.getSearchTF().sendKeys(CampInLead);
				//dynamic xpath
				driver.findElement(By.xpath("//td[text()='"+CampInLead+"']/..//button[@class='select-btn']")).click();
				driver.switchTo().window(parentID);
				
				//save
				clp.getCreateLeadSaveBtn().click();
				//Verification
				WebElement message1 =leadp.getLeadSuccessMsg();
				wutil.waitForVisibilityOfElement(driver, message1);
								
				String msg1 = message.getText();
				 if(msg1.contains(lName)) {
					 System.out.println("Successfully Created the campaign "+lName);	 
				 }else {
					 System.out.println("Failed to Create the campaign "+lName);
				 }
				 Thread.sleep(5000);
				 //log out
				 hp.logOutOfNinzaCRM();
				 Thread.sleep(7000);
				 driver.quit();
 			
	}
}
