package StepDefs.services.Practice;

import StepDefs.services.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExperienceCalculator extends BaseTest {
    static WebDriver driver;
    private String experienceTextmonths;  // Instance variable instead of static

    public static void main(String[] args) {
        // Starting date of experience
        LocalDate startDate = LocalDate.of(2017, 8, 1);

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the experience in years, months, and days
        long years = ChronoUnit.YEARS.between(startDate, currentDate);
        long months = ChronoUnit.MONTHS.between(startDate, currentDate) % 12;
        long days = ChronoUnit.DAYS.between(startDate.plusYears(years).plusMonths(months), currentDate);

        // Print experience duration
        System.out.println("Experience Duration: " + years + " Years, " + months + " Months, " + days + " Days");

        // Create an instance of ExperienceCalculator to call non-static methods
        ExperienceCalculator calculator = new ExperienceCalculator();

        // Call the methods to insert experience
        String experienceTextyears = years + " Years, ";
        calculator.insertexperienceTextyears(experienceTextyears);

        String experienceTextmonths = months + " Months, ";
        calculator.insertexperienceTextmonths(experienceTextmonths);
    }

    // Method to insert years into the input field
    public static void insertexperienceTextyears(String experienceTextyears) {

        if (driver != null) {
            try {
                // Use explicit wait to ensure the element is visible and clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement Years = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='exp-years-droopeFor']']")));

                // Clear the existing value and send new experience
                Years.clear();
                Years.sendKeys(experienceTextyears);
                System.out.println("Inserting experience into locator: " + experienceTextyears);
            } catch (Exception e) {
                System.out.println("Error while interacting with the element: " + e.getMessage());
            }
        } else {
            System.out.println("WebDriver is not initialized.");
        }
    }


    // Method to insert months into the input field
    public static void insertexperienceTextmonths(String experienceTextmonths) {
        // Ensure WebDriver is initialized correctly
        if (driver != null) {
            try {
                // Use explicit wait to ensure the element is visible and clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement Years = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='exp-months-droopeFor']']")));

                // Clear the existing value and send new experience
                Years.clear();
                Years.sendKeys(experienceTextmonths);
                System.out.println("Inserting experience into locator: " + experienceTextmonths);
            } catch (Exception e) {
                System.out.println("Error while interacting with the element: " + e.getMessage());
            }
        } else {
            System.out.println("WebDriver is not initialized.");
        }
    }
}
