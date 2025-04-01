package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage
{
WebDriver driver;

public ContactsPage(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//select[@class='form-control']")
private WebElement searchByContactIdDropdown;
@FindBy(xpath="//input[@placeholder='Search by Contact Id']")
private WebElement searchByContactIdfield;
@FindBy(xpath="//span[text()='Create Contact']")
private WebElement addNewContactBtn;

public WebElement getSearchByContactIdDropdown() 
{
	return searchByContactIdDropdown;
}
public WebElement getSearchByContactIdfield() 
{
	return searchByContactIdfield;
}
public WebElement getAddNewContactBtn() {
	return addNewContactBtn;
}
public void addContact()
{
	addNewContactBtn.click();
}




}
