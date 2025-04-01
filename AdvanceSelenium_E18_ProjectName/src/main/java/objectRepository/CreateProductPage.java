package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class CreateProductPage
{
	WebDriver driver;

	public CreateProductPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="quantity")
	private WebElement qty;
	@FindBy(name = "productName")
	private WebElement productname;
	@FindBy(name = "price")
	private WebElement price;
	@FindBy(name = "productCategory")
	private WebElement selectCatagoryDropdown;
	@FindBy(name = "vendorId")
	private WebElement selectVendorIdDropdown;
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addBtn;
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;

	public WebElement getQty() {
		return qty;
	}
	public WebElement getProductname() {
		return productname;
	}
	public WebElement getPrice() {
		return price;
	}
	public WebElement getSelectCatagoryDropdown() {
		return selectCatagoryDropdown;
	}
	public WebElement getSelectVendorIdDropdown() {
		return selectVendorIdDropdown;
	}
	public WebElement getAddBtn() {
		return addBtn;
	}
	public WebElement getConfMsg() {
		return confMsg;
	}
	
	public void addProduct(String quantity, String prodName, String prodPrice)
	{
		qty.clear();
		qty.sendKeys(quantity);
		productname.sendKeys(prodName);
		price.clear();
		price.sendKeys(prodPrice);
		selectCatagoryDropdown.click();
		WebDriverUtility wUtil= new WebDriverUtility();
		wUtil.select(selectCatagoryDropdown, "Furniture");
		selectVendorIdDropdown.click();
		wUtil.select(selectVendorIdDropdown, "VID_008");
		addBtn.click();
		
	}
	
	
	
	
}
