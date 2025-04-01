package contactPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CampaignsPage;
import objectRepository.ContactsPage;
import objectRepository.CreateCampaignPage;
import objectRepository.CreateContactPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;

public class CreateContactTest2 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
	PropertiesFileUtility propUtil= new PropertiesFileUtility();
	String Browser = propUtil.readingDataFromPropFile("browser");
	String URL = propUtil.readingDataFromPropFile("url");
	String UN = propUtil.readingDataFromPropFile("username");
	String PWD = propUtil.readingDataFromPropFile("password");
	
	javaUtility jUtil= new javaUtility();
	int randomNum = jUtil.getRandomNum(2000);
	
	excelFileUtility exeUtility=new excelFileUtility();
	String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+randomNum;
	String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
	String contactname = exeUtility.readingDataFromExcel("ContactData", 1, 0)+randomNum;
	String organization = exeUtility.readingDataFromExcel("ContactData", 1, 1)+randomNum;
	String mobileNumber = exeUtility.readingDataFromExcel("ContactData", 1, 2);
	String title = exeUtility.readingDataFromExcel("ContactData", 1, 3)+randomNum;
	
	
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
	LoginPage lp= new LoginPage(driver);
	lp.login(UN, PWD);
	
	CampaignsPage cp= new CampaignsPage(driver);
	cp.getCreateCampaignBtn().click();
	
	CreateCampaignPage ccp= new CreateCampaignPage(driver);
	ccp.createCampaignWithMandatoryFields(campaign, target);
	
	Thread.sleep(2000);
	String msg = ccp.getConfMsg().getText();
	if(msg.contains(campaign))
	{
		System.out.println("Campaign"+campaign+" added successsfully ");
	}
	else
	{
		System.out.println("Campaign not added");
	}
	Thread.sleep(5000);
	//create contact
	
		WebDriverUtility wUtil=new WebDriverUtility();
		DashboardPage dp= new DashboardPage(driver);

		WebElement contactLink = dp.getContactsLink();
		wUtil.waitForElementToBeClickable(driver, contactLink);
		contactLink.click();
		ContactsPage conpage=new ContactsPage(driver);
		conpage.addContact();
		
		CreateContactPage ccontpage= new CreateContactPage(driver);
		ccontpage.createContact(contactname, organization, mobileNumber, title, "selectCampaign", "create-contact", campaign);
		
		Thread.sleep(2000);
		String msg1 = ccontpage.getConfMsg().getText();
		if(msg1.contains(contactname))
		{
			System.out.println("Contact "+contactname+" created successfully");
		}
		else
		{
			System.out.println("Contact not created");
		}
		Thread.sleep(4000);
		
		dp.logout();

		driver.quit();

	}
}
