package objectRepository; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage 
{
WebDriver driver;

public CreateCampaignPage(WebDriver driver) 
{
	this.driver = driver;
	PageFactory.initElements(driver,this);
}
@FindBy(name = "expectedCloseDate")
private WebElement expectedCloseDate;
@FindBy(name = "campaignName")
private WebElement campaignName;
@FindBy(name = "targetSize")
private WebElement targetSize;
@FindBy(xpath="//button[text()='Create Campaign']")
private WebElement createCampaignButton;
@FindBy(xpath="//div[@role='alert']")
private WebElement confMsg;

public WebElement getExpectedCloseDate() 
{
	return expectedCloseDate;
}
public WebElement getCampaignName() 
{
	return campaignName;
}
public WebElement getTargetSize() 
{
	return targetSize;
}
public WebElement getCreateCampaignButton()
{
	return createCampaignButton;
}
public WebElement getConfMsg() 
{
	return confMsg;
}
public void createCampaignWithMandatoryFields(String campName,String targSize)
{
	campaignName.sendKeys(campName);
	targetSize.clear();
	targetSize.sendKeys(targSize);
	createCampaignButton.click();
}
public void createCampaignWithClosedDate(String campName,String targSize,String closedDate)
{
	expectedCloseDate.sendKeys(closedDate);
	campaignName.sendKeys(campName);
	targetSize.clear();
	targetSize.sendKeys(targSize);
	createCampaignButton.click();
}





}

