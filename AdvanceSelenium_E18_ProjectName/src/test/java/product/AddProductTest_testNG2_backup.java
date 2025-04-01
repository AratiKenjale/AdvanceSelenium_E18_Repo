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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CreateProductPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class AddProductTest_testNG2_backup 
{
	@Parameters("browser")
	@Test(groups = {"IntegrationTest"})
public void createProduct(String browser) throws IOException, InterruptedException 
{
	PropertiesFileUtility propUtil= new PropertiesFileUtility();
	//String Browser = propUtil.readingDataFromPropFile("browser");
	String Browser=browser;
	String URL = propUtil.readingDataFromPropFile("url");
	String UN = propUtil.readingDataFromPropFile("username");
	String PWD = propUtil.readingDataFromPropFile("password");
	
	javaUtility jUtil= new javaUtility();
	int randomNum = jUtil.getRandomNum(2000);
	
	WebDriverUtility wUtil= new WebDriverUtility();
	
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
	
	Thread.sleep(2000);
	String msg = cpp.getConfMsg().getText();
	if(msg.contains(product))
	{
		Reporter.log("Product"+ product+" added successfully",true);
	}
	else
	{
		Reporter.log("Product not added",true);
	}
	
	Thread.sleep(4000);	
	dp.logout();
	driver.quit();
}
}
