package com.salesforce.login.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[self::input and (@id='username' or @name='username' or @name='un' or @autocomplete='username' or @placeholder='Username')][1]")
    private WebElement usernameInput;

    @FindBy(xpath = "//*[self::input and (@id='password' or @name='pw' or @name='password' or @type='password' or @autocomplete='current-password' or @placeholder='Password')][1]")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[self::input and (@id='Login' or @name='login' or @value='Log In')][1]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[self::input and (@id='rememberUn' or @name='rememberUn')][1]")
    private WebElement rememberMeCheckbox;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        try {
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
        } catch (Exception e) {
            throw new RuntimeException("Unable to open Salesforce login page: " + e.getMessage(), e);
        }
    }

    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
            usernameInput.clear();
            usernameInput.sendKeys(username);
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter username: " + e.getMessage(), e);
        }
    }

    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            passwordInput.clear();
            passwordInput.sendKeys(password);
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter password: " + e.getMessage(), e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Unable to click Login button: " + e.getMessage(), e);
        }
    }

    public void login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLogin();
        } catch (Exception e) {
            throw new RuntimeException("Login attempt failed: " + e.getMessage(), e);
        }
    }

    public boolean isRememberMeChecked() {
        try {
            return rememberMeCheckbox.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInvalidCredentialsErrorDisplayed() {
        try {
            List<WebElement> errors = driver.findElements(By.xpath(
                    "//div[contains(@class,'error') and (contains(.,'Please check your username and password.') or contains(.,'username and password') or contains(.,'Invalid username') or contains(.,'invalid username'))]"));
            return !errors.isEmpty() && errors.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(usernameInput)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
