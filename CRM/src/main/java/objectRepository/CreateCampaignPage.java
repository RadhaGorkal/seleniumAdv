package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage 
{

	WebDriver driver;
	public CreateCampaignPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "campaignName")
	private WebElement campName;
	
	@FindBy(name = "targetSize")
	private WebElement targetSize;
	
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement createCampSaveBtn;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement closeDate;

	public WebElement getCampName() {
		return campName;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}

	public WebElement getCreateCampSaveBtn() {
		return createCampSaveBtn;
	}

	public WebElement getCloseDate() {
		return closeDate;
	}
	
	

}
