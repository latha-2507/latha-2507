package com.salesforce.login.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.salesforce.login.pages.LoginPage;

public class ValidLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
            loginPage = new LoginPage(driver);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            loginPage.open(System.getProperty("salesforce.url", "https://login.salesforce.com/?locale=in"));
        } catch (Exception e) {
            throw new RuntimeException("Valid login setup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void validLoginShouldNavigateToHomePage() {
        try {
            String username = System.getProperty("salesforce.username");
            String password = System.getProperty("salesforce.password");

            if (username == null || password == null || username.isBlank() || password.isBlank()) {
                throw new SkipException("Set -Dsalesforce.username and -Dsalesforce.password to run the valid login test.");
            }

            loginPage.login(username, password);
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("lightning"),
                    ExpectedConditions.urlContains("visualforce"),
                    ExpectedConditions.titleContains("Salesforce")));

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("lightning")
                            || driver.getCurrentUrl().contains("visualforce")
                            || driver.getTitle().contains("Salesforce"),
                    "Expected successful login to navigate to Salesforce home page.");
        } catch (SkipException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Valid login verification failed: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
