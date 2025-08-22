package day24_orangehrm;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryAnnotation {
    WebDriver driver;
    String browserName;
    String url;
    String user;
    String pass;

    // Constructor for Factory
    public FactoryAnnotation(String browserName, String url, String user, String pass) {
        this.browserName = browserName;
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    @Test
    public void loginTest() throws InterruptedException {
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
                return;
        }

        driver.manage().window().maximize();
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".orangehrm-login-button"))).click();

        Thread.sleep(3000); 
        driver.quit();
    }

    @Factory
    public static Object[] createInstances() {
        return new Object[] {
            new FactoryAnnotation("chrome", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", "Admin", "admin123"),
           
            new FactoryAnnotation("chrome", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", "WrongUser", "wrongPass")
        };
    }
}
