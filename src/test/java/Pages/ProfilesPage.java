package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilesPage {

    WebDriver driver;

    // Locators
    By UploadResume = By.xpath("//input[@value='Update resume']");


    // Constructor
    public ProfilesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void userprofile() {
        driver.findElement(UploadResume).sendKeys("F://BDD_UI//BDD_UI//resources//files//Profile.pdf");
    }
}
