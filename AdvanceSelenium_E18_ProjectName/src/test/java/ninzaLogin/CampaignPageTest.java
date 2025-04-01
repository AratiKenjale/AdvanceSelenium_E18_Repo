package ninzaLogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import dev.failsafe.internal.util.Assert;

public class CampaignPageTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://49.249.28.218:8098/");
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		driver.findElement(By.name("campaignName")).sendKeys("CAM911");
		 WebElement targetSize = driver.findElement(By.name("targetSize"));
		 targetSize.sendKeys(Keys.BACK_SPACE);
		 targetSize.sendKeys("250");
		 
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		if(msg.contains("CAM911"))
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