package StepDefs.services.Practice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExperienceCalculator {

    public static void main(String[] args) {
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
        String experienceText = years + " Years, " + months + " Months, " + days + " Days";

        // Assume you have a method insertExperienceToLocator() to insert the experience into the locator
        insertExperienceToLocator(experienceText);
    }

    // Method to simulate inserting experience into a locator (like a web element)
    public static void insertExperienceToLocator(String experienceText) {
        // Simulating insertion into a locator (in real case, you might use Selenium WebDriver or a database)
        System.out.println("Inserting experience into locator: " + experienceText);
        // Example: WebElement locator = driver.findElement(By.id("experienceLocator"));
        // locator.sendKeys(experienceText);
    }
}
