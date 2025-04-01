package ninzaLogin;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import genericUtility.PropertiesFileUtility;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;



public class LoginTest_testNG2_backup 
{
@Parameters("browser")
@Test(groups = {"SmokeTest"})
	public void loginTest(String browser) throws InterruptedException, IOException 
	{
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		//String Browser = propUtil.readingDataFromPropFile("browser");
		String Browser=browser;
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
		lp.login(UN, PWD);
	
		Thread.sleep(2000);
		String actUrl = driver.getCurrentUrl();
		
		
		Thread.sleep(2000);
		
		if(actUrl.contains("dashboard"))
		{
			Reporter.log("Dashboard is displayed",true);
		}
		else
		{
			
			Reporter.log("Dashboard is not displayed",true);
		}
		Thread.sleep(2000);
		
		DashboardPage dp= new DashboardPage(driver);
		dp.logout();
		driver.quit();
		
		

	}
}
