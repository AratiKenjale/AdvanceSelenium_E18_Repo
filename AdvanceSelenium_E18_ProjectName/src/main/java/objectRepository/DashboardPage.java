package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage 
{
	WebDriver driver;
	
public DashboardPage(WebDriver driver) 
{
		this.driver = driver;
		PageFactory.initElements(driver,this);
}
@FindBy(linkText = "Campaigns")
private WebElement campaignLink;
@FindBy(linkText = "Contacts")
private WebElement contactsLink;
@FindBy(linkText = "Products")
private WebElement productsLink;
@FindBy(xpath="//div[@class='user-icon-container']")
private WebElement profileIcon;
@FindBy(xpath="//div[text()='Logout ']")
private WebElement logoutBtn;

public WebElement getCampaignLink() 
{
	return campaignLink;
}
public WebElement getContactsLink() 
{
	return contactsLink;
}
public WebElement getProductsLink() 
{
	return productsLink;
}
public WebElement getProfileIcon() 
{
	return profileIcon;
}
public WebElement getLogoutBtn() 
{
	return logoutBtn;
}

public void logout()
{
	profileIcon.click();
	Actions act= new Actions(driver);
	act.moveToElement(logoutBtn).click();
	
}



}
