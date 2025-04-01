package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage 
{
WebDriver driver;

public CampaignsPage(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(xpath="//span[text()='Create Campaign']")
private WebElement createCampaignBtn;
@FindBy(xpath="//select[@class='form-control']")
private WebElement searchByCampaignIdDropdown;
@FindBy(xpath="//input[@placeholder='Search by Campaign Id']")
private WebElement campaignIdField;

public WebElement getCreateCampaignBtn()
{
	return createCampaignBtn;
}
public WebElement getSearchByCampaignIdDropdown()
{
	return searchByCampaignIdDropdown;
}
public WebElement getCampaignIdField() 
{
	return campaignIdField;
}



}
