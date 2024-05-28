package nilseleniuminc.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import nilseleniuminc.pageobjects.CartPage;
import nilseleniuminc.pageobjects.CheckOutPage;
import nilseleniuminc.pageobjects.ConfirmationPage;
import nilseleniuminc.pageobjects.ProductCatalogue;
import nilseleniuminc.testcomponents.BaseTest;
import nilseleniuminc.testcomponents.RetryListeners;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"errorHandlings"}, retryAnalyzer=RetryListeners.class)
	public void loginError() throws IOException, InterruptedException {

		landingPage.loginApplication("anshika@gmail.com", "aaa");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrorMsg());

	}

	@Test
	public void verifyProductInCart() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
