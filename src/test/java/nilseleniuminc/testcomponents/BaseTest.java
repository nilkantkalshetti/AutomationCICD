package nilseleniuminc.testcomponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import nilseleniuminc.pageobjects.LandingPage;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		// FileInputStream fis = new FileInputStream(
		// System.getProperty("user.dir") +
		// "//src//main//java//nilseleniuminc//Resources//GlobalData.propertie");

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\nilka\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\nilseleniuminc\\Resources\\GlobalData.properties");

		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions chromeOption = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName == "chromeheadless") {
				chromeOption.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOption);
			//driver.manage().window().setSize(new Dimension(1440,900));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {

		} else if (browserName.equalsIgnoreCase("edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json file content to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, targetFile);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
