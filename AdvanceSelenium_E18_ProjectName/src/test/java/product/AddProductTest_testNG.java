package product;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import genericBaseclassUtility.BaseClass;
import genericListenerUtility.ListenerImplementation;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import genericUtility.excelFileUtility;
import genericUtility.javaUtility;
import objectRepository.CreateProductPage;
import objectRepository.DashboardPage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

//@Listeners(ListenerImplementation.class)
public class AddProductTest_testNG extends BaseClass
{

	@Test
public void addProductTest() throws IOException, InterruptedException 
{
	
	javaUtility jUtil= new javaUtility();
	int randomNum = jUtil.getRandomNum(2000);
	
	WebDriverUtility wUtil= new WebDriverUtility();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
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
	boolean status = msg.contains(product);
	org.testng.Assert.assertTrue(status);
	
	
}
}
