package ninzaLogin;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import genericBaseclassUtility.BaseClass;
import genericListenerUtility.ListenerImplementation;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import genericUtility.PropertiesFileUtility;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;


//@Listeners(ListenerImplementation.class)
public class LoginTest extends BaseClass
{

@Test(retryAnalyzer = genericListenerUtility.RetryListenerImp.class)
	public void loginTest() throws InterruptedException, IOException 
	{
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		Thread.sleep(2000);
		String expectedURL="http://49.249.28.218:8098/dashboard";
		String actUrl = driver.getCurrentUrl();
		Thread.sleep(2000);
		Assert.assertEquals(actUrl, expectedURL,"Validation is failed");
	

	}
}
