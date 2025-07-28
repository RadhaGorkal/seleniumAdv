package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class HomePage 
{
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
		



@FindBy(xpath = "//*[name()='svg' and @data-icon='user']")
private WebElement userIcon;

@FindBy(xpath = "//div[text()='Logout ']")
private WebElement logOutLink;

@FindBy(linkText = "Campaigns")
private WebElement campModule;

@FindBy(linkText = "Products")
private WebElement productModule;

@FindBy(linkText = "Leads")
private WebElement leadModule;


public WebElement getUserIcon() {
	return userIcon;
}


public void setUserIcon(WebElement userIcon) {
	this.userIcon = userIcon;
}


public WebElement getLogOutLink() {
	return logOutLink;
}


public void setLogOutLink(WebElement logOutLink) {
	this.logOutLink = logOutLink;
}


public WebElement getCampModule() {
	return campModule;
}


public void setCampModule(WebElement campModule) {
	this.campModule = campModule;
}


public WebElement getProductModule() {
	return productModule;
}


public void setProductModule(WebElement productModule) {
	this.productModule = productModule;
}


public WebElement getLeadModule() {
	return leadModule;
}


public void setLeadModule(WebElement leadModule) {
	this.leadModule = leadModule;
}


//Business Logic
 public void logOutOfNinzaCRM() {
	 WebDriverUtility wutil= new WebDriverUtility();
	 wutil.moveToElement(driver, userIcon);
	 logOutLink.click();
 }


}
