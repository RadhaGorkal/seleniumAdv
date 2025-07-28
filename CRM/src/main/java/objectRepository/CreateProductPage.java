package objectRepository;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateProductPage 
{

      WebDriver driver;
	
	public CreateProductPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "productName")
	private WebElement prodName;
	
	@FindBy(name = "productCategory")
	private WebElement prodCategory;
	
	@FindBy(name = "vendorId")
	private WebElement vendorId;
	
	@FindBy(name = "quantity")
	private WebElement quantity;
	
	@FindBy(name = "price")
	private WebElement price;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement createProdSaveBtn;

	public WebElement getProdName() {
		return prodName;
	}

	public WebElement getProdCategory() {
		return prodCategory;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getCreateProdSaveBtn() {
		return createProdSaveBtn;
	}

	
	

}

