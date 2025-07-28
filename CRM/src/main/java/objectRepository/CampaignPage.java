package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage
{
	WebDriver driver;

	public CampaignPage(WebDriver driver) {
	this.driver= driver;
	PageFactory.initElements(driver, this);
}

	public WebDriver getDriver() 
	{
		return driver;
	}

	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement createCampBtn;
	
	@FindBy(xpath = "//div[contains(text(), 'Campaign')]")
	private WebElement campSuccessMsg;

	public WebElement getCreateCampBtn() {
		return createCampBtn;
	}

	public WebElement getCampSuccessMsg() {
		return campSuccessMsg;
	}
	

	
	
	
}


