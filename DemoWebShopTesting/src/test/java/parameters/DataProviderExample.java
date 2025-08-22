package parameters;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderExample {
    WebDriver driver;
    WebDriverWait wait;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(url);   
    }

    @Test(dataProvider = "registerData")
    public void RegisterNewUser(String firstname, String lastname, String streetname, String city, String state,
            String zipcode, String phonenumber, String ssn, String username, String password, String repeatedpassword) {

        // Click Register link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customer.firstName"))).sendKeys(firstname);
        driver.findElement(By.name("customer.lastName")).sendKeys(lastname);
        driver.findElement(By.name("customer.address.street")).sendKeys(streetname);
        driver.findElement(By.name("customer.address.city")).sendKeys(city);
        driver.findElement(By.name("customer.address.state")).sendKeys(state);
        driver.findElement(By.name("customer.address.zipCode")).sendKeys(zipcode);
        driver.findElement(By.name("customer.phoneNumber")).sendKeys(phonenumber);
        driver.findElement(By.name("customer.ssn")).sendKeys(ssn);
        driver.findElement(By.name("customer.username")).sendKeys(username);
        driver.findElement(By.name("customer.password")).sendKeys(password);
        driver.findElement(By.name("repeatedPassword")).sendKeys(repeatedpassword);

        // Submit
        driver.findElement(By.xpath("//input[@value='Register']")).click();

        // Validate successful registration
        Assert.assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Welcome")),
                "Registration failed!");

        // Logout
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))).click();
    }


    @AfterClass
    public void afterClass() throws InterruptedException {
        System.out.println("Closing the browser after done");
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }


    @DataProvider(name = "registerData")
    public Object[][] registerdata() {
        return new Object[][] {
                { "Sai", "Mallavarapu", "Kandukur Street", "Kandukur", "AP", "523105", "9876543210", "123456789",
                        "saiuser16620620", "sai@123", "sai@123" },
                { "Prabal", "Kumar", "MG Road", "Bangalore", "KA", "560001", "9123456780", "987654321",
                        "prabaluser12839202", "prabal@123", "prabal@123" }
        };
    }
}
