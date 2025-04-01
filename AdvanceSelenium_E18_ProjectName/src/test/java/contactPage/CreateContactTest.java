package contactPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtility.PropertiesFileUtility;

public class CreateContactTest 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
	
	FileInputStream fs=new FileInputStream("E:\\\\Workspace1\\\\AdvanceSelenium_E18_ProjectName\\\\src\\\\test\\\\resources\\\\ninza.properties");
	Properties pr= new Properties();
	pr.load(fs);
	
	String Browser=pr.getProperty("browser");
	String URL = pr.getProperty("url");
	String UN = pr.getProperty("username");
	String PWD = pr.getProperty("password");
	
	WebDriver driver=null;
	if(Browser.equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if(Browser.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else if(Browser.equalsIgnoreCase("edge"))
	{
		driver=new EdgeDriver();
	}
	else
	{
		driver=new ChromeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	driver.get(URL);
	driver.findElement(By.id("username")).sendKeys(UN);
	driver.findElement(By.id("inputPassword")).sendKeys(PWD);
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	
	//driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
			
	driver.findElement(By.name("contactName")).sendKeys("Karan Shah");
	driver.findElement(By.name("organizationName")).sendKeys("KJ Company");
	driver.findElement(By.name("mobile")).sendKeys("8976567833");
	driver.findElement(By.name("title")).sendKeys("ABC");
	
	String parentWindowHandle = driver.getWindowHandle();
	WebElement campaignButton = driver.findElement(By.xpath("//button[@style='white-space: nowrap; margin: 5px 10px 16px 0px; padding: 8px; background-color: green; font-size: 12px; display: flex; align-items: center; justify-content: center;']"));
	campaignButton.click();
	Thread.sleep(2000);
	Set<String> allWindowHandles = driver.getWindowHandles();
	for (String handle : allWindowHandles) 
	{
	    // Confirm if the handle does not match the parent window
	    if (!handle.equals(parentWindowHandle)) 
	    {
	        // Switch to the new window context
	        driver.switchTo().window(handle);
	    }
	}
	
	
	WebElement campaign = driver.findElement(By.xpath("//select[@id='search-criteria']"));
	campaign.click();

	
	Select s= new Select(campaign);
	Thread.sleep(2000);
	s.selectByValue("campaignName");
	driver.findElement(By.id("search-input")).sendKeys("CAM1000");
	driver.findElement(By.xpath("//button[text()='Select']")).click();
	driver.switchTo().window(parentWindowHandle);
	driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
	Thread.sleep(2000);
	String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	if(msg.contains("Karan Shah"))
	{
		System.out.println("Contact created successfully");
	}
	else
	{
		System.out.println("Contact not created");
	}
	Thread.sleep(4000);
	driver.findElement(By.xpath("//div[@class='user-icon']")).click();
	WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
	Actions act= new Actions(driver);
	act.moveToElement(logout).perform();
	driver.close();
	
	
	

}
}
