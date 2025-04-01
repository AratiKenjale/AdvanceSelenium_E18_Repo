package ninzaLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.PropertiesFileUtility;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;



public class LoginTest2 
{
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		String Browser = propUtil.readingDataFromPropFile("browser");
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
		WebDriver driver=null;
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
//		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
		lp.login(UN, PWD);
	
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		
		if(url.contains("dashboard"))
		{
			System.out.println("Dashboard is displayed");
		}
		else
		{
			
			System.out.println("Dashboard is not displayed");
		}
		Thread.sleep(2000);
		
		DashboardPage dp= new DashboardPage(driver);
		dp.logout();
		driver.quit();
		
		

	}
}
