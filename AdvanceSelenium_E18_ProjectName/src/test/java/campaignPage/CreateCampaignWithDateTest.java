package campaignPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
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

public class CreateCampaignWithDateTest
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		FileInputStream fis= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\ninza.properties");
		Properties pr= new Properties();
		pr.load(fis);
		
		String Browser = pr.getProperty("browser");
		String URL = pr.getProperty("url");
		String UN = pr.getProperty("username");
		String PWD = pr.getProperty("password");
		
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("dd-MM-YYYY");
		String todaydate = simple.format(dateObj);
		System.out.println(todaydate);
		
		Calendar cal= simple.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String closeDate = simple.format(cal.getTime());
		System.out.println(closeDate);
		
		
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
		
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		driver.findElement(By.name("campaignName")).sendKeys("CAM1567");
		 WebElement targetSize = driver.findElement(By.name("targetSize"));
		 targetSize.sendKeys(Keys.BACK_SPACE);
		 targetSize.sendKeys("250");
		 driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		 
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		if(msg.contains("CAM1567"))
		{
			System.out.println("Campaign added successsfully ");
		}
		else
		{
			System.out.println("Campaign not added");
		}
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions act= new Actions(driver);
		act.moveToElement(logout).perform();
		driver.close();
		
		
	}

	

}
