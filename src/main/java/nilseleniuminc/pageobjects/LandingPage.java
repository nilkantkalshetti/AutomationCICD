package nilseleniuminc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nilseleniuminc.AbstarctComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail")); //common way to define WebElements
	//We can define WebElemets using PageFactory like below
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrorMsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public void loginApplication(String useremail, String userpassword) {
		userEmail.sendKeys(useremail);
		userPassword.sendKeys(userpassword);
		submitButton.click();
	}
	
	public String getLoginErrorMsg() {
		waitForWebElementToApprear(loginErrorMsg);		
		return loginErrorMsg.getText();
		
	}
	
	
	
	
	
	
	
}
