package parameters;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProvider2 {
	WebDriver driver;

	@Parameters({ "browserName", "url" })
	@BeforeClass(alwaysRun = true)
	public void LaunchBrowser(String browserName, String url) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			break;
		}
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test( priority = 1, dataProvider = "registerData")
	public void RegisterNewUser(String firstname, String lastname, String streetname, String city, String state,
			String zipcode, String phonenumber, String ssn, String username, String password, String repeatedpassword)
			throws InterruptedException {

		driver.get("https://parabank.parasoft.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='register.htm']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.firstName"))).sendKeys(firstname);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.lastName"))).sendKeys(lastname);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.street"))).sendKeys(streetname);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.city"))).sendKeys(city);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.state"))).sendKeys(state);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.zipCode"))).sendKeys(zipcode);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.phoneNumber"))).sendKeys(phonenumber);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.ssn"))).sendKeys(ssn);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.username"))).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.password"))).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("repeatedPassword"))).sendKeys(repeatedpassword);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Register']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
	}

	@Test( priority = 2, dataProvider = "loginData")
	public void Login(String userName, String password) throws InterruptedException {
		driver.get("https://parabank.parasoft.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Log In']"))).click();

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}

	@DataProvider(name = "registerData")
	public Object[][] getRegisterData() throws IOException {
	    return ExcelUtils.getSheetData("src/test/resources/RegistrationParabank.xlsx", "Sheet1");
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
	    return ExcelUtils.getSheetData("src/test/resources/RegistrationParabank.xlsx", "Sheet2");
	}



}
