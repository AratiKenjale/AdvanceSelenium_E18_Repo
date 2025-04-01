package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import genericUtility.WebDriverUtility;

public class CreateContactPage
{
	WebDriver driver;

	public CreateContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "contactName")
	private WebElement contactName;
	@FindBy(name = "organizationName")
	private WebElement organizationName;
	@FindBy(name = "mobile")
	private WebElement mobile;
	@FindBy(name = "title")
	private WebElement title;
	@FindBy(name = "email")
	private WebElement email;
	@FindBy(xpath="//button[@style='white-space: nowrap; margin: 5px 10px 16px 0px; padding: 8px; background-color: green; font-size: 12px; display: flex; align-items: center; justify-content: center;']")
	private WebElement addCampaignBtn;
	@FindBy(xpath="//select[@id='search-criteria']")
	private WebElement searchDropdown;
	@FindBy(id="search-input")
	private WebElement searchInput;
	@FindBy(xpath="//button[text()='Select']")
	private WebElement selectBtn;
	@FindBy(xpath="//button[text()='Create Contact']")
	private WebElement createContactBtn;
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;

	public WebElement getContactName() {
		return contactName;
	}
	public WebElement getOrganizationName() {
		return organizationName;
	}
	public WebElement getMobile() {
		return mobile;
	}
	public WebElement getTitle() {
		return title;
	}
	public WebElement getEmail() {
		return email;
	}
	public WebElement getAddCampaignBtn() {
		return addCampaignBtn;
	}
	public WebElement getConfMsg() {
		return confMsg;
	}
	public WebElement getSearchDropdown() {
		return searchDropdown;
	}
	public WebElement getSearchInput() {
		return searchInput;
	}
	public WebElement getSelectBtn() {
		return selectBtn;
	}
	public WebElement getCreateContactBtn() {
		return createContactBtn;
	}
	
	public void createContact(String contName, String organization,String mob, String titleName,String partialUrl,String parentUrl, String campaignName)
	{
		contactName.sendKeys(contName);
		organizationName.sendKeys(organization);
		mobile.sendKeys(mob);
		title.sendKeys(titleName);
		addCampaignBtn.click();
		WebDriverUtility wUtil= new WebDriverUtility();
		wUtil.switchToWindow(driver, partialUrl);
		wUtil.select(searchDropdown, "campaignName");
		searchInput.sendKeys(campaignName);
		selectBtn.click();
		wUtil.switchToWindow(driver, parentUrl);
		createContactBtn.click();
	
		
	}
	
	
	
}
