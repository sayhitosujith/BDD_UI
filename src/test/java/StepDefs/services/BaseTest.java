package StepDefs.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    // Launches the browser
    public void setUp() {
//        WebDriverManager.chromedriver().setup();  // Automatically download the right version of the driver
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");  // Maximize the window at launch
//        driver = new ChromeDriver(options);
    }

    // Wait for an element to be visible
    public void waitForElementToBeVisible(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Click an element
    public void clickElement(WebElement element) {
        waitForElementToBeVisible(element, 10);  // Wait until the element is visible
        element.click();
    }

    // Type text into an input field
    public void typeText(WebElement element, String text) {
        waitForElementToBeVisible(element, 10);  // Wait until the element is visible
        element.sendKeys(text);
    }

    // Navigate to a URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Take a screenshot
    public void takeScreenshot(String fileName) {
        // You can use Selenium's TakesScreenshot interface to capture a screenshot.
        // Example for implementation of screenshot capture can go here
    }

    // Tear down the WebDriver after tests
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
