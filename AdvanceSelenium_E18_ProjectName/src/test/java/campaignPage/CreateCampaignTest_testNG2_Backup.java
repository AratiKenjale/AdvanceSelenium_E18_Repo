package campaignPage;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CampaignsPage;
import objectRepository.CreateCampaignPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;

public class CreateCampaignTest_testNG2_Backup

{
	@Parameters("browser")
	@Test(groups = {"SmokeTest"})
	public void createCampaignTest(String browser) throws InterruptedException, IOException 
	{
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		//String Browser = propUtil.readingDataFromPropFile("browser");
		String Browser=browser;
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
		javaUtility jUtil= new javaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		excelFileUtility exeUtility=new excelFileUtility();
		String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+randomNum;
		String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
		
		WebDriver driver=null;
		ChromeOptions coption= new ChromeOptions();
		FirefoxOptions foption= new FirefoxOptions();
		EdgeOptions eoption= new EdgeOptions();
		coption.addArguments("--headless");
		foption.addArguments("--headless");
		eoption.addArguments("--headless");
		
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver(coption);
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver(foption);
		}
		else if(Browser.equals("edge"))
		{
			driver=new EdgeDriver(eoption);
		}
		else
		{
			driver=new ChromeDriver(coption);
		}
		//WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://49.249.28.218:8098/");
		LoginPage lp= new LoginPage(driver);
		lp.login(UN, PWD);
	
		DashboardPage dp= new DashboardPage(driver);
		dp.getCampaignLink().click();
		
		CampaignsPage cp= new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaignWithMandatoryFields(campaign, target);
		
		Thread.sleep(2000);
		String msg = ccp.getConfMsg().getText();
		if(msg.contains(campaign))
		{
			Reporter.log("Campaign " +campaign+" added successsfully",true);
		}
		else
		{
			Reporter.log("Campaign not added",true);
		}
		Thread.sleep(5000);
		
		dp.logout();
		driver.quit();	
}
	@Parameters("browser")
	@Test(groups = {"RegressionTest"})
	public void createCampaignWithDateTest(String browser) throws IOException, InterruptedException
	{
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		//String Browser = propUtil.readingDataFromPropFile("browser");
		String Browser=browser;
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
		javaUtility jUtil= new javaUtility();
		int randomNum= jUtil.getRandomNum(2000);
		
		excelFileUtility exeUtility=new excelFileUtility();
		String campaign = exeUtility.readingDataFromExcel("CampaignData", 1, 0)+ randomNum;
		String target=exeUtility.readingDataFromExcel("CampaignData", 1, 1);
	
		String closeDate = jUtil.generateReqDate(30);
		WebDriverUtility wUtil= new WebDriverUtility();
		
		WebDriver driver= null;
		ChromeOptions coption= new ChromeOptions();
		FirefoxOptions foption= new FirefoxOptions();
		EdgeOptions eoption= new EdgeOptions();
		coption.addArguments("--headless");
		foption.addArguments("--headless");
		eoption.addArguments("--headless");
		
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver(coption);
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver(foption);
		}
		else if(Browser.equals("edge"))
		{
			driver=new EdgeDriver(eoption);
		}
		else
		{
			driver=new ChromeDriver(coption);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
		lp.login(UN, PWD);
		
		Thread.sleep(2000);
		CampaignsPage cp= new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaignWithClosedDate(campaign, target, closeDate);

		Thread.sleep(2000);
		String msg =ccp.getConfMsg().getText();
		if(msg.contains(campaign))
		{
			Reporter.log("Campaign " +campaign+" added successsfully ",true);
		}
		else
		{
			Reporter.log("Campaign not added",true);
		}
		Thread.sleep(5000);
		
		DashboardPage dp= new DashboardPage(driver);
		dp.logout();
		driver.quit();	
	}

}