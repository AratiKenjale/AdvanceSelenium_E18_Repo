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



public class LoginTest1 
{
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		FileInputStream fs= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\ninza.properties");
		Properties prop= new Properties();
		prop.load(fs);
		
		String Browser = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("username");
		String PWD = prop.getProperty("password");
		
		System.out.println(Browser);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);
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
		
		driver.get("http://49.249.28.218:8098/");
		
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
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
		
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions act= new Actions(driver);
		act.moveToElement(logout).perform();
		driver.close();
		
		

	}
}
