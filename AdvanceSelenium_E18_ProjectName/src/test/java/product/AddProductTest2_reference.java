package product;

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

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CreateProductPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class AddProductTest2_reference 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
	PropertiesFileUtility propUtil= new PropertiesFileUtility();
	String Browser = propUtil.readingDataFromPropFile("browser");
	String URL = propUtil.readingDataFromPropFile("url");
	String UN = propUtil.readingDataFromPropFile("username");
	String PWD = propUtil.readingDataFromPropFile("password");
	
	javaUtility jUtil= new javaUtility();
	int randomNum = jUtil.getRandomNum(2000);
	
	WebDriverUtility wUtil= new WebDriverUtility();
	
	WebDriver driver=null;
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
	
	excelFileUtility exeUtility=new excelFileUtility();
	String quantity = exeUtility.readingDataFromExcel("Product", 1, 0)+randomNum;
	String product = exeUtility.readingDataFromExcel("Product", 1, 1)+randomNum;
	String price = exeUtility.readingDataFromExcel("Product", 1, 2)+randomNum;
	
	DashboardPage dp= new DashboardPage(driver);
	
	WebElement clickProduct = dp.getProductsLink();
	wUtil.waitForElementToBeClickable(driver, clickProduct);
	clickProduct.click();
	
	ProductPage pp= new ProductPage(driver);
	
	WebElement addProductBtn = pp.getAddProductBtn();
	wUtil.waitForElementToBeClickable(driver, addProductBtn);
	addProductBtn.click();
	
	CreateProductPage cpp= new CreateProductPage(driver);
	cpp.addProduct(quantity, product, price);
	driver.findElement(By.xpath("//span[text()='Add Product']")).click();
	WebElement Qty = driver.findElement(By.name("quantity"));
	Qty.sendKeys(Keys.BACK_SPACE);
	Qty.sendKeys(quantity);
	
	driver.findElement(By.name("productName")).sendKeys(product);
	WebElement Price = driver.findElement(By.name("price"));
	Price.sendKeys(Keys.BACK_SPACE);
	Price.sendKeys(price);
	
	WebElement ProductCat = driver.findElement(By.name("productCategory"));

	wUtil.select(ProductCat, "Furniture");
	
	WebElement VendorID = driver.findElement(By.name("vendorId"));
	wUtil.select(VendorID, "VID_058");

	driver.findElement(By.xpath("//button[text()='Add']")).click();

	Thread.sleep(2000);
	String msg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	if(msg.contains(product))
	{
		System.out.println("Product"+ product+" added successfully");
	}
	else
	{
		System.out.println("Product not added");
	}
	
	Thread.sleep(4000);

	
	dp.logout();
	driver.close();

}
}
