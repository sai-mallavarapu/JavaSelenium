package parabank;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class ParabankTest {
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

	@Test(groups = { "smoke" }, priority = 1, dataProvider = "registerData") // (priority = 1)
	public void RegisterNewUser(String firstname, String lastname, String streetname, String city, String state,
			String zipcode, String phonenumber, String ssn, String username, String password, String repeatedpassword) throws InterruptedException {
		driver.get("https://parabank.parasoft.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='register.htm']"))).click();

		System.out.println(driver.getTitle());

//		driver.findElement(By.id("FirstName")).sendKeys("Prabal");//approch no.1, it given instance result
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.firstName"))).sendKeys(firstname);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.lastName"))).sendKeys(lastname);
		// approch no.2, and it is a better approch bcz it is giving stability
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

	@Test(groups = { "regression" }, priority = 2, dataProvider = "logInTestData")
	public void Login(String userName, String password) throws InterruptedException {

		driver.get("https://parabank.parasoft.com/");
		System.out.println("performing Login");
		System.out.println("Running Login with username: " + userName + " and password: " + password);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Log In']"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
	}

	@Ignore
	@Test(groups = { "smoke" })
	public void CustomerCare() {
		driver.get("https://parabank.parasoft.com/");
		// System.out.println("Opening CustomerCare");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='contact.htm']"))).click();
		System.out.println("Opening CustomerCare");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys("sai");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("email"))).sendKeys("sai@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).sendKeys("1234567890");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("message"))).sendKeys("Hello");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Send to Customer Care']")))
				.click();
	}

	@AfterClass(groups = { "smoke" })
	// Reset to login page
	public void afterClass() throws InterruptedException {
		System.out.println("Closing the browser after done");
		if (driver != null) {
			Thread.sleep(5000);
			driver.quit();
		}
	}

	@DataProvider(name = "logInTestData")
	public Object[][] loginData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "saiuser12";
		data[0][1] = "sai@123";
		data[1][0] = "prabaluser21";
		data[1][1] = "prabal@123";
		return data;
	}

	@DataProvider(name = "registerData")
	public Object[][] registerdata() {
		return new Object[][] {
				{ "Sai", "Mallavarapu", "Kandukur Street", "Kandukur", "AP", "523105", "9876543210", "123456789",
						"saiuser12", "sai@123", "sai@123" },
				{ "Prabal", "Kumar", "MG Road", "Bangalore", "KA", "560001", "9123456780", "987654321", "prabaluser21",
						"prabal@123", "prabal@123" } };
	}

}
