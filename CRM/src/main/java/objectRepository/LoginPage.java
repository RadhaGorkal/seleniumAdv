package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{

WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement usernameTF;
	
	@FindBy(id = "inputPassword")
	private WebElement passwordTF;
	
	@FindAll({@FindBy(xpath = "//button[@type='submit']"), @FindBy(xpath = "//button[text()='Sign In']")})
	private WebElement signinButton;

	public WebElement getUsernameTF() {
		return usernameTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getSigninButton() {
		return signinButton;
	}
	
	//Business logic/ library
	public void toLoginToNinzaCRM(String Uname, String pswd) {
		usernameTF.sendKeys(Uname);
		passwordTF.sendKeys(pswd);
		signinButton.click();
	}
	
}
