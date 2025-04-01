package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="username")
	private WebElement username;
	@FindBy(id="inputPassword")
	private WebElement password;
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement signIn;
	@FindBy(linkText = "Forgot password?")
	private WebElement forgotPassword;
	@FindBy(linkText = "Create Account")
	private WebElement createAcc;

	public WebElement getUsername() 
	{
		return username;
	}
	public WebElement getPassword() 
	{
		return password;
	}
	public WebElement getSignIn() 
	{
		return signIn;
	}
	public WebElement getForgotPassword() 
	{
		return forgotPassword;
	}
	public WebElement getCreateAcc() 
	{
		return createAcc;
	}
	
	public void login(String uname,String pwd)
	{
		username.sendKeys(uname);
		password.sendKeys(pwd);
		signIn.click();
	}
	
	
	
	
	
}
