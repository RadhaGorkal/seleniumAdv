package ninzaCrm_TestNg;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import genericUtility.Excelfileutility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.CreateCampaignPage;
import objectRepository.HomePage;

public class CreateCampaignWithCloseDateTest extends BaseClass
{
	@Test(groups = "RegressionTestCase")
	public void createCampWithDateTest() throws IOException, InterruptedException {
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		Excelfileutility eutil = new Excelfileutility();
		// Actual Test script
		// Navigate to Campaign Module
		HomePage hp = new HomePage(driver);
		hp.getCampModule().click();

		// Create Campaign Button
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampBtn().click();

		// generate RandomNumber
		int ranNum = jutil.GenerateRandomNumber();

		String campName = eutil.ReadDataFromExcel("Campaigns", 1, 2) + ranNum;
		String targetSize = eutil.ReadDataFromExcel("Campaigns", 1, 3);

		// Enter mandatory Details
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampName().sendKeys(campName);
		ccp.getTargetSize().sendKeys(targetSize);
		// Date
		String reqDate = jutil.GetRequiredDate(5);
		ccp.getCloseDate().sendKeys(reqDate);
		// save
		ccp.getCreateCampSaveBtn().click();

		//Verification
		WebElement message = cp.getCampSuccessMsg();
		wutil.waitForVisibilityOfElement(driver, message);
		String msg = message.getText();
		if (msg.contains(campName)) {
			Reporter.log("Successfully Created the campaign " + campName, true);
		} else {
			Reporter.log("Failed to Create the campaign " + campName, true);
		}
		Thread.sleep(2000);

	}

}


