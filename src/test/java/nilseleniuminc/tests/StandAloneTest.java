package nilseleniuminc.tests;

import java.awt.Dimension;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import nilseleniuminc.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prodAddCartBtn = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prodAddCartBtn.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));// its
		// slowing process
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> listCartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = listCartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);

		WebElement checkoutEle = driver.findElement(By.cssSelector(".totalRow button"));
		Actions actions = new Actions(driver);
		actions.moveToElement(checkoutEle).click().build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']/input")));
		driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']/i")));
		// List<WebElement> countryOptions =
		// driver.findElements(By.xpath("//span[@class='ng-star-inserted']/i"));
		List<WebElement> countryOptions = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		countryOptions.get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));

		WebElement placeOrderBtn = driver.findElement(By.cssSelector(".action__submit"));
		Actions placeOrderBtnactions = new Actions(driver);
		placeOrderBtnactions.moveToElement(placeOrderBtn).click().build().perform();

		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
