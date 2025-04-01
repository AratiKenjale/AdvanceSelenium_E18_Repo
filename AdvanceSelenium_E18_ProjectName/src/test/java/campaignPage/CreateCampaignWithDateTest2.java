package campaignPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CampaignsPage;
import objectRepository.CreateCampaignPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;

public class CreateCampaignWithDateTest2
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		String Browser = propUtil.readingDataFromPropFile("browser");
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
		
		Thread.sleep(2000);
		CampaignsPage cp= new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaignWithClosedDate(campaign, target, closeDate);
//		ccp.getExpectedCloseDate().sendKeys(closeDate);
//		ccp.getCampaignName().sendKeys(campaign);
//		ccp.getTargetSize().sendKeys(Keys.BACK_SPACE);
//		ccp.getTargetSize().sendKeys(target);
//		ccp.getCreateCampaignButton().click();
		
		Thread.sleep(2000);
		String msg =ccp.getConfMsg().getText();
		if(msg.contains(campaign))
		{
			System.out.println("Campaign " +campaign+" added successsfully ");
		}
		else
		{
			System.out.println("Campaign not added");
		}
		Thread.sleep(5000);
		
		DashboardPage dp= new DashboardPage(driver);
		dp.logout();
		driver.quit();
		
		
	}

	

}
