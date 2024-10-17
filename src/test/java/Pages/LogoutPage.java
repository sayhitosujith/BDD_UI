package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    // Locators
    By userprofile = By.xpath("//img[@alt='naukri user profile img']");
    By logoutButton = By.xpath("//a[@title='Logout']");

    // Constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void userprofile() {
        driver.findElement(userprofile).click();
    }

    public void logoutButton() {
        driver.findElement(logoutButton).click();
    }

}
