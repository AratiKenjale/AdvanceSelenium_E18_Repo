package ddtPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AddProductTest 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
	FileInputStream file= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\ninza.properties");
	Properties pr= new Properties();
	pr.load(file);
	
	Random ran= new Random();
	int randomNum = ran.nextInt(1000);
	
	String BROWSER=pr.getProperty("browser");
	String URL = pr.getProperty("url");
	String UN = pr.getProperty("username");
	String PWD = pr.getProperty("password");
	
	WebDriver driver=null;
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else if(BROWSER.equalsIgnoreCase("edge"))
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
	
	FileInputStream file2= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\CreateCampaignTestData.xlsx");
	Workbook book = WorkbookFactory.create(file2);
	String quantity = book.getSheet("Product").getRow(1).getCell(0).getStringCellValue()+randomNum;
	String product = book.getSheet("Product").getRow(1).getCell(1).getStringCellValue()+randomNum;
	String price = book.getSheet("Product").getRow(1).getCell(2).getStringCellValue()+randomNum;
	
	driver.findElement(By.linkText("Products")).click();
	driver.findElement(By.xpath("//span[text()='Add Product']")).click();
	WebElement Qty = driver.findElement(By.name("quantity"));
	Qty.sendKeys(Keys.BACK_SPACE);
	Qty.sendKeys(quantity);
	
	driver.findElement(By.name("productName")).sendKeys(product);
	WebElement Price = driver.findElement(By.name("price"));
	Price.sendKeys(Keys.BACK_SPACE);
	Price.sendKeys(price);
	
	WebElement ProductCat = driver.findElement(By.name("productCategory"));
	
	Select s1= new Select(ProductCat);
	s1.selectByValue("Furniture");
	
	WebElement VendorID = driver.findElement(By.name("vendorId"));
	Select s2= new Select(VendorID);
	s2.selectByValue("VID_058");

	driver.findElement(By.xpath("//button[text()='Add']")).click();

	Thread.sleep(2000);
	String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	if(msg.contains(product))
	{
		System.out.println("Product added successfully");
	}
	else
	{
		System.out.println("Product not added");
	}
	
	Thread.sleep(4000);
	

	
	driver.findElement(By.xpath("//div[@class='user-icon']")).click();
	WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
	Actions act= new Actions(driver);
	act.moveToElement(logout).perform();
	driver.close();

}
}
