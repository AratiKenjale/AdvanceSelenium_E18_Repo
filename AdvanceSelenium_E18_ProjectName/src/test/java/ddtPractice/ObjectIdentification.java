package ddtPractice;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ObjectIdentification
{
public static void main(String[] args) 
{
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://demowebshop.tricentis.com/");
	WebElement searchStore = driver.findElement(By.id("small-searchterms"));
	WebElement searchBtn = driver.findElement(By.xpath("//input[@type='submit']"));
	searchStore.sendKeys("computers");
	driver.navigate().refresh();
	searchStore.sendKeys("mobiles");
	searchBtn.click();
	
}
}
