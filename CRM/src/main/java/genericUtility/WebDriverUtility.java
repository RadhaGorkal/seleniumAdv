package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	
	// implicit wait
		public void waitForPageToLoad(WebDriver driver) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}

		// Explicit wait
		public void waitForElementToBeClickable(WebDriver driver, WebElement ele) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}

		public void waitForVisibilityOfElement(WebDriver driver, WebElement ele) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}

		// Switching into frames
		public void switchToFrame(WebDriver driver, int index) {
			driver.switchTo().frame(index);
		}

		public void switchToFrame(WebDriver driver, String nameOrId) {
			driver.switchTo().frame(nameOrId);
		}

		public void switchToFrame(WebDriver driver, WebElement ele) {
			driver.switchTo().frame(ele);
		}

		// Handling Drop Downs
		public void selectOption(WebElement ele, int index) {
			Select sel = new Select(ele);
			sel.selectByIndex(index);
		}

		public void selectOption(WebElement ele, String value) {
			Select sel = new Select(ele);
			sel.selectByValue(value);
		}

		public void selectOption(String text, WebElement ele) {
			Select sel = new Select(ele);
			sel.selectByVisibleText(text);
		}
		
		// SwitchBackFromFrame
			//Switch to alerts
			public void switchToAlertAccept(WebDriver driver) {
				driver.switchTo().alert().accept();
			}
			public void switchToAlertDismiss(WebDriver driver) {
				driver.switchTo().alert().dismiss();
			}

		//Actions class
			public void moveToElement(WebDriver driver, WebElement ele) {
				Actions act= new Actions(driver);
				act.moveToElement(ele).click().perform();		
			}
			public void doubleClick(WebDriver driver, WebElement ele) {
				Actions act= new Actions(driver);
				act.doubleClick(ele).perform();
			}
			public void dragAndDrop(WebDriver driver, WebElement src, WebElement target) {
				Actions act= new Actions(driver);
				act.dragAndDrop(src, target).perform();
			}

			//JSE
			public void toScrollWindow(WebDriver driver, WebElement ele) {
				JavascriptExecutor jse= (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true)", ele);
				
			}
			
		//Switch to window
			public void toSwitchToWindow(WebDriver driver, String partialExpTitle) {
				Set<String> allIds = driver.getWindowHandles();
				for(String winId: allIds) {
					String windowTitle= driver.switchTo().window(winId).getTitle();
					if(windowTitle.contains(partialExpTitle)) {
						break;
					}
				}
			}
		
			//TKS
			public void takeScreenshotOfWebPage(WebDriver driver, String ssName) throws IOException {
				TakesScreenshot tks= (TakesScreenshot) driver;
				File src = tks.getScreenshotAs(OutputType.FILE);
				File dest= new File("./errorShots/"+ssName+".png");
				FileHandler.copy(src, dest);
			}
		


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

