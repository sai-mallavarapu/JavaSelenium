package parabank;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;

public class ParabankTest {
	WebDriver driver;

	@Parameters({ "browserName", "url" })
	@BeforeClass(groups= {"smoke"})
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

	@Test(groups= {"smoke"},priority=1) //(priority = 1)
	public void RegisterNewUser() {
		driver.get("https://parabank.parasoft.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='register.htm']"))).click();

		System.out.println(driver.getTitle());

//		driver.findElement(By.id("FirstName")).sendKeys("Prabal");//approch no.1, it given instance result
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.firstName"))).sendKeys("sai");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.lastName"))).sendKeys("Mallavarapu");
		// approch no.2, and it is a better approch bcz it is giving stability
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.street"))).sendKeys("kandukur");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.city"))).sendKeys("Ongole");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.state"))).sendKeys("Andhra Pradesh");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.address.zipCode"))).sendKeys("12345");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.phoneNumber"))).sendKeys("9234567890");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.ssn"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.username"))).sendKeys("sai1234");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer.password"))).sendKeys("sai@123");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("repeatedPassword"))).sendKeys("sai@123");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Register']"))).click();

	}

	@Test(groups= {"regression"},priority=2) 
	public void Login() {

		driver.get("https://parabank.parasoft.com/");
		System.out.println("performing Login");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys("sai");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("sai123");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Log In']"))).click();
	}
	@Ignore
	@Test(groups= {"smoke"})
	public void CustomerCare() {
		driver.get("https://parabank.parasoft.com/");
		//System.out.println("Opening CustomerCare");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='contact.htm']"))).click();
		System.out.println("Opening CustomerCare");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys("sai");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("email"))).sendKeys("sai@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).sendKeys("1234567890");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("message"))).sendKeys("Hello");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Send to Customer Care']"))).click();
	}

	@AfterClass(groups= {"smoke"})
	// Reset to login page
	public void afterClass() throws InterruptedException {
		System.out.println("Closing the browser after done");
		if (driver != null) {
			Thread.sleep(5000);
			driver.quit();
		}
	}

}