package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators
    By usernameField = By.xpath("//input[@id='usernameField']");
    By passwordField = By.xpath("//input[@id='passwordField']");
    By loginButton = By.xpath("//button[@class='waves-effect waves-light btn-large btn-block btn-bold blue-btn textTransform']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isUserLoggedIn() {
        // Logic to verify if the user is logged in
        return driver.findElement(By.id("logoutButton")).isDisplayed();
    }
}
