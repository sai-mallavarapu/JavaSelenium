package com;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BuggyclassRatingTesting {
	WebDriver driver;

	@BeforeClass
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void Register() throws InterruptedException {
	    driver.get("https://buggy.justtestit.org/");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Go to register page
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/register']"))).click();

	    // Short pause to let Angular finish rendering form
	    Thread.sleep(1000);

	    // Fill registration form
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login"))).clear();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login"))).sendKeys("Sai123");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).clear();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys("Sai");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).clear();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName"))).sendKeys("Mallavarapu");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).clear();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("Sai@12345");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword"))).clear();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword"))).sendKeys("Sai@12345");

	    // Wait for register button to become enabled
	    wait.until(ExpectedConditions.elementToBeClickable(
	    	    By.cssSelector("button[type='submit']")
	    	)).click();

	}


//	@AfterClass
//	public void afterClass() throws InterruptedException {
//		System.out.println("Closing the browser if we want");
//		if (driver != null) {
//			driver.wait();
//		}
//	}
}
