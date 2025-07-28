package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeadPage 
{
	WebDriver driver;
	public CreateLeadPage(WebDriver driver) {
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}

	@FindBy(name = "name")
	private WebElement leadName;

	@FindBy(name = "company")
	private WebElement companyName;

	@FindBy(name = "leadSource")
	private WebElement source;

	@FindBy(name = "industry")
	private WebElement industry;

	@FindBy(name = "phone")
	private WebElement phone;

	@FindBy(name = "leadStatus")
	private WebElement status;

	@FindBy(name = "rating")
	private WebElement rating;

	@FindBy(xpath = "//*[name()='svg' and @data-icon='plus']")
	private WebElement addCampBtn;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement createLeadSaveBtn;

	public WebElement getLeadName() {
		return leadName;
	}

	public WebElement getCompanyName() {
		return companyName;
	}

	public WebElement getSource() {
		return source;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getPhone() {
		return phone;
	}

	public WebElement getStatus() {
		return status;
	}

	public WebElement getRating() {
		return rating;
	}

	public WebElement getAddCampBtn() {
		return addCampBtn;
	}

	public WebElement getCreateLeadSaveBtn() {
		return createLeadSaveBtn;
	}



	}



