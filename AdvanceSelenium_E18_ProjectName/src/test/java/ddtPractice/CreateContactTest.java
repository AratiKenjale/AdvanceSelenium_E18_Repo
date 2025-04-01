package ddtPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateContactTest 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
	FileInputStream fs=new FileInputStream("E:\\\\Workspace1\\\\AdvanceSelenium_E18_ProjectName\\\\src\\\\test\\\\resources\\\\ninza.properties");
	Properties pr= new Properties();
	pr.load(fs);
	
	Random ran= new Random();
	int randomNumber = ran.nextInt(1000);
	
	FileInputStream file= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\CreateCampaignTestData.xlsx");
	Workbook book= WorkbookFactory.create(file);
	
	String BROWSER=pr.getProperty("browser");
	String URL = pr.getProperty("url");
	String UN = pr.getProperty("username");
	String PWD = pr.getProperty("password");
	
	WebDriver driver=null;
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else if(BROWSER.equalsIgnoreCase("edge"))
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
	
	//create campaign
	driver.findElement(By.linkText("Campaigns")).click();
	driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	String campaign1 = book.getSheet("CampaignData").getRow(1).getCell(0).getStringCellValue()+randomNumber;
	
	driver.findElement(By.name("campaignName")).sendKeys(campaign1);
	
	String target = book.getSheet("CampaignData").getRow(1).getCell(1).getStringCellValue();
	WebElement targetData=driver.findElement(By.name("targetSize"));
	targetData.sendKeys(Keys.BACK_SPACE);
	targetData.sendKeys(target);
	
	driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
	
	Thread.sleep(2000);
	String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	if(msg.contains(campaign1))
	{
		System.out.println("Campaign added successsfully ");
	}
	else
	{
		System.out.println("Campaign not added");
	}
	 
	Thread.sleep(2000);
	
	//create contact
	String name = book.getSheet("ContactData").getRow(1).getCell(0).getStringCellValue()+randomNumber;
	String organization = book.getSheet("ContactData").getRow(1).getCell(1).getStringCellValue()+randomNumber;
	 String mobileNumber = book.getSheet("ContactData").getRow(1).getCell(2).getStringCellValue();
	String title = book.getSheet("ContactData").getRow(1).getCell(3).getStringCellValue()+randomNumber;
	
	driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
			
	driver.findElement(By.name("contactName")).sendKeys(name);
	driver.findElement(By.name("organizationName")).sendKeys(organization);
	driver.findElement(By.name("mobile")).sendKeys(mobileNumber);
	driver.findElement(By.name("title")).sendKeys(title);
	
	
	WebElement campaignButton = driver.findElement(By.xpath("//button[@style='white-space: nowrap; margin: 5px 10px 16px 0px; padding: 8px; background-color: green; font-size: 12px; display: flex; align-items: center; justify-content: center;']"));
	campaignButton.click();
	Thread.sleep(2000);
	Set<String> allWindowHandles = driver.getWindowHandles();
	for (String window : allWindowHandles) 
	{
	        // Switch to the new window context
	        driver.switchTo().window(window);
	        String actURL = driver.getCurrentUrl();
	        if(actURL.contains("selectCampaign"))
	        {
	        	break;
	        }
	    }
	
	
	
	WebElement campaign = driver.findElement(By.xpath("//select[@id='search-criteria']"));
	campaign.click();

	
	Select s= new Select(campaign);
	Thread.sleep(2000);
	s.selectByValue("campaignName");
	driver.findElement(By.id("search-input")).sendKeys(campaign1);
	driver.findElement(By.xpath("//button[text()='Select']")).click();
	
	for (String window : allWindowHandles) 
	{
	        // Switch to the parent window context
	        driver.switchTo().window(window);
	        String actURL = driver.getCurrentUrl();
	        if(actURL.contains("create-contact"))
	        {
	        	break;
	        }
	    }
	
	driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
	Thread.sleep(2000);
	String msg1 = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	if(msg1.contains(name))
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
