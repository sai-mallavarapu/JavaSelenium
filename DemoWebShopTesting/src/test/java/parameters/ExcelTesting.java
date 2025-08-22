package parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelTesting {
 String filepath="src/test/resources/RegistrationParabank.xlsx";
 WebDriver driver;
	public void RegisterNewUser(String firstname, String lastname, String streetname, String city, String state,
			String zipcode, String phonenumber, String ssn, String username, String password, String repeatedpassword)
			throws InterruptedException {

		driver.get("https://parabank.parasoft.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='register.htm']"))).click();
      //  String Firstname=ExcelUtils.getSheetData(filepath,"Sheet1",2,1);
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
}
