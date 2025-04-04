package campaignPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
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
import objectRepository.CreateCampaignPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;

//@Listeners(ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass
{
	@Test
	public void createCampaign() throws InterruptedException, IOException 
	{
		javaUtility jUtil= new javaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		excelFileUtility exeUtility=new excelFileUtility();
		String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+randomNum;
		String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
		
		//WebDriver driver= new ChromeDriver();
		String expectedURL="http://49.249.28.218:8098/dashboard";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		DashboardPage dp= new DashboardPage(driver);
		dp.getCampaignLink().click();
		
		CampaignsPage cp= new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaignWithMandatoryFields(campaign, target);
		
		Thread.sleep(2000);
		String msg = ccp.getConfMsg().getText();
		boolean status = msg.contains(campaign);
		Assert.assertEquals(status, true, "campaign not added");	
		Thread.sleep(5000);
		
}
	@Test
	public void createCampaignWithDateTest() throws IOException, InterruptedException
	{	
		javaUtility jUtil= new javaUtility();
		int randomNum= jUtil.getRandomNum(2000);
		
		excelFileUtility exeUtility=new excelFileUtility();
		String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+ randomNum;
		String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
	
		String closeDate = jUtil.generateReqDate(30);
		WebDriverUtility wUtil= new WebDriverUtility();
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignLink().click();
		
		Thread.sleep(2000);
		CampaignsPage cp= new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaignWithClosedDate(campaign, target, closeDate);

		Thread.sleep(2000);
		String msg =ccp.getConfMsg().getText();
		boolean status = msg.contains(campaign);
		Assert.assertEquals(status, true, "campaign not added");
		
	
	}

}