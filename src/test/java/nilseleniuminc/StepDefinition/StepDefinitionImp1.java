package nilseleniuminc.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nilseleniuminc.pageobjects.CartPage;
import nilseleniuminc.pageobjects.CheckOutPage;
import nilseleniuminc.pageobjects.ConfirmationPage;
import nilseleniuminc.pageobjects.LandingPage;
import nilseleniuminc.pageobjects.ProductCatalogue;
import nilseleniuminc.testcomponents.BaseTest;

public class StepDefinitionImp1 extends BaseTest {
	public LandingPage landingPage;  
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password){
		landingPage.loginApplication(username, password);
	}
	
	@When("^User add product (.+) to Cart$")
	public void User_add_product_to_Cart(String productname) {
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(productname);
		productCatalogue.goToCartPage();
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productname) {
		CartPage cartPage = new CartPage(driver);
		//Boolean match = cartPage.verifyProductDisplay(productName1);
		Boolean match = cartPage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		cartPage.goToCheckOutPage();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.selectCountry("Ind");
		checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmatinPage")
	public void message_is_displayed_on_ConfirmatinPage(String string) {
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} error message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getLoginErrorMsg());
		driver.close();
	}
}
