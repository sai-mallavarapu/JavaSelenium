package parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersExample2 {
	WebDriver driver;
	@Test
    @Parameters({"browserName","url","username", "password"})
	public void loginTest(String browserName,String url,String user, String pass) throws InterruptedException {
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

        //  Explicit wait for elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".orangehrm-login-button"))).click();

        Thread.sleep(3000); 
        driver.quit();
	}
}
