package nilseleniuminc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import nilseleniuminc.AbstarctComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	//Actions placeOrderBtnactions = new Actions(driver);
	
	public CheckOutPage(WebDriver driver) {
		//Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='form-group']/input")
	WebElement countryInput;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countryList;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']/i")
	WebElement countryListVisible;
	
	
	public void selectCountry(String countryName1) {
		
		countryInput.sendKeys(countryName1);
		waitForWebElementToApprear(countryListVisible);	
		Actions placeOrderBtnactions = new Actions(driver);
		placeOrderBtnactions.moveToElement(countryList.get(1)).click().build().perform();
		//countryList.get(1).click();
	}
	
	public void submitOrder() {
		waitForWebElementToApprear(countryInput);
		waitForWebElementToApprear(placeOrderBtn);	
		Actions placeOrderBtnactions = new Actions(driver);
		placeOrderBtnactions.moveToElement(placeOrderBtn).click().build().perform();

	}
}
