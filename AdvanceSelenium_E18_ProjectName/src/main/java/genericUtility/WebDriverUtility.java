package genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;

import com.google.protobuf.Duration;

public class WebDriverUtility 
{
	
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void switchToWindow(WebDriver driver,String partialURL)
	{
		Set<String> allWindowIds = driver.getWindowHandles();
		
		for(String Window:allWindowIds)
		{
			driver.switchTo().window(Window);
			String actURL = driver.getCurrentUrl();
			if(actURL.contains(partialURL))
			{
				break;
			}
		}
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void select(WebElement element,String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);	
	}
	public void select(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);	
	}
	public void select(String visibleText,WebElement element)
	{
		Select s=new Select(element);
		s.selectByVisibleText(visibleText);
	}
	
	public void moveToElement(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.doubleClick(element).perform();
	}
	public void dragAndDrop(WebDriver driver, WebElement source,WebElement target)
	{
		Actions act= new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}
	public void contextClick(WebDriver driver,WebElement target)
	{
		Actions act= new Actions(driver);
		act.contextClick(target).perform();
	}
	public void takeScreenshotOfWebPage(WebDriver driver, String filename) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest= new File("./screenshots/"+filename+".png");
		FileHandler.copy(temp, dest);
	}
	public void takeScreenshotOfWebElement(WebElement element, String filename) throws IOException
	{
		File temp = element.getScreenshotAs(OutputType.FILE);
		File dest= new File("./screenshots/"+filename+".png");
		FileHandler.copy(temp, dest);
		
	}
}

