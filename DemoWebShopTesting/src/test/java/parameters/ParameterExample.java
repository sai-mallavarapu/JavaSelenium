package parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParameterExample {

    @Test
    @Parameters({"username", "password"})
    public void loginTest(String user, String pass) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //  Explicit wait for elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".orangehrm-login-button"))).click();

        Thread.sleep(3000); 
        driver.quit();
    }
}
