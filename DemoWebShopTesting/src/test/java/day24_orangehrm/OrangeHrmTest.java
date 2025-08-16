package day24_orangehrm;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrangeHrmTest {
	WebDriver driver;

	@Test(dataProvider = "logInTestData")
	public void login(String userName, String password) throws InterruptedException {
		System.out.println("loading driver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		System.out.println("logging in");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']"))).sendKeys(userName);

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
//	Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='client brand banner']")).isDisplayed());
		Thread.sleep(5000);
//	driver.quit();
	}

	@DataProvider(name = "logInTestData")
	public Object[][] loginData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "Admin";
		data[0][1] = "admin123";
		data[1][0] = "Admin";
		data[1][1] = "admin123";
		return data;
	}
}
