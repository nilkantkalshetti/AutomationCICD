package nilseleniuminc.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import nilseleniuminc.pageobjects.CartPage;
import nilseleniuminc.pageobjects.CheckOutPage;
import nilseleniuminc.pageobjects.ConfirmationPage;
import nilseleniuminc.pageobjects.OrderPage;
import nilseleniuminc.pageobjects.ProductCatalogue;
import nilseleniuminc.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void submitOrder(String userName, String passWord, String productName1) throws IOException, InterruptedException {
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		//landingPage.loginApplication(userName, passWord);
		landingPage.loginApplication(input.get("email"), input.get("password"));

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		//productCatalogue.addProductToCart(productName1);
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		//Boolean match = cartPage.verifyProductDisplay(productName1);
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		cartPage.goToCheckOutPage();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.selectCountry("Ind");
		checkOutPage.submitOrder();

		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory() {
		landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\nilka\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\nilseleniuminc\\testdata\\PurchaseOrderTestData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"nilkal@gmail.com",
	 * "Iamking@000","ADIDAS ORIGINAL"}}; }
	 */
	/*
	 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
	 * "anshika@gmail.com"); map.put("password", "Iamking@000"); map.put("product",
	 * "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "nilkal@gmail.com"); map1.put("password", "Iamking@000");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 */	
	

}
