package StepDefs.services;

import StepDefs.services.Practice.ExperienceCalculator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class BaseTest {

    public static WebDriver driver;

    // Launches the browser
    public void setUp() {
        WebDriverManager.chromedriver().setup();  // Automatically download the right version of the driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");  // Maximize the window at launch
        driver = new ChromeDriver(options);
    }

    // Wait for an element to be visible
    public void waitForElementToBeVisible(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Sleep
    public void waitForElement(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout));
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

    //  calculate experience range
    public void  calculate_experience_range(WebElement element, String text) {
            // Starting date of experience
            LocalDate startDate = LocalDate.of(2018, 8, 1);

            // Get current date
            LocalDate currentDate = LocalDate.now();

            // Calculate the experience in years, months, and days
            long years = ChronoUnit.YEARS.between(startDate, currentDate);
            long months = ChronoUnit.MONTHS.between(startDate, currentDate) % 12;
            long days = ChronoUnit.DAYS.between(startDate.plusYears(years).plusMonths(months), currentDate);

            // Print experience duration
            System.out.println("Experience Duration: " + years + " Years, " + months + " Months, " + days + " Days");

            // Insert the experience into a specific locator (for example, updating a web element)
            String experienceText_years = years + "Years";
            String experienceText_months = months + "Months";

            // Assume you have a method insertExperienceToLocator() to insert the experience into the locator
        ExperienceCalculator.insertexperienceTextyears(experienceText_years);
        waitForElementToBeVisible(element, 10);  // Wait until the element is visible
        element.sendKeys(text);
        System.out.println("Inserting experience into locator: " + experienceText_years);

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
