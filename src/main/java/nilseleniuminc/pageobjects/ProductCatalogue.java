package nilseleniuminc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nilseleniuminc.AbstarctComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	By toastMsgDisappear = By.cssSelector(".ng-animating");
	public List<WebElement> getProductLists() {
		waitForElementToApprear(productsBy);
		return productList;
	}

	public WebElement getProductByName(String productName1) {
		
		WebElement productName = getProductLists().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return productName;
		
	}
	
	public void addProductToCart(String productName1) {
		WebElement productName = getProductByName(productName1);
		//productName.findElement(addToCart).click();
		driver.findElement(By.xpath("//h5/b[text()='"+productName1+"']/ancestor-or-self::h5//following-sibling::button[2]")).click();
		////b[text()='ADIDAS ORIGINAL']/ancestor-or-self::h5//following-sibling::button[2]
		waitForElementToApprear(toastMsg);
		waitForElementToDisappear(toastMsgDisappear);
	}
	
	
}
