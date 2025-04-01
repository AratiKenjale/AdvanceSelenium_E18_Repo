package contactPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import genericBaseclassUtility.BaseClass;
import genericListenerUtility.ListenerImplementation;
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

//@Listeners(ListenerImplementation.class)
public class CreateContactWithCampaignTest_testNG extends BaseClass
{
	//@Parameters("browser")
	@Test
public void createContactWithCampaignTest() throws IOException, InterruptedException 
{	
	javaUtility jUtil= new javaUtility();
	int randomNum = jUtil.getRandomNum(2000);
	
	excelFileUtility exeUtility=new excelFileUtility();
	String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+randomNum;
	String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
	String contactname = exeUtility.readingDataFromExcel("ContactData", 1, 0)+randomNum;
	String organization = exeUtility.readingDataFromExcel("ContactData", 1, 1)+randomNum;
	String mobileNumber = exeUtility.readingDataFromExcel("ContactData", 1, 2);
	String title = exeUtility.readingDataFromExcel("ContactData", 1, 3)+randomNum;
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	   DashboardPage dp=new DashboardPage(driver);
			Thread.sleep(2000);
			dp.getCampaignLink().click();
	
	CampaignsPage cp= new CampaignsPage(driver);
	cp.getCreateCampaignBtn().click();
	
	CreateCampaignPage ccp= new CreateCampaignPage(driver);
	ccp.createCampaignWithMandatoryFields(campaign, target);
	
	Thread.sleep(2000);
	String msg = ccp.getConfMsg().getText();
	if(msg.contains(campaign))
	{
		Reporter.log("Campaign"+campaign+" added successsfully ");
	}
	else
	{
		Reporter.log("Campaign not added");
	}
	Thread.sleep(5000);
	//create contact
	
		WebDriverUtility wUtil=new WebDriverUtility();
		WebElement contactLink = dp.getContactsLink();
		wUtil.waitForElementToBeClickable(driver, contactLink);
		contactLink.click();
		ContactsPage conpage=new ContactsPage(driver);
		conpage.addContact();
		
		CreateContactPage ccontpage= new CreateContactPage(driver);
		ccontpage.createContact(contactname, organization, mobileNumber, title, "selectCampaign", "create-contact", campaign);
		
		Thread.sleep(2000);
		String msg1 = ccontpage.getConfMsg().getText();
		boolean status = msg1.contains(contactname);
	       Assert.assertTrue(status);
		

	}
}
