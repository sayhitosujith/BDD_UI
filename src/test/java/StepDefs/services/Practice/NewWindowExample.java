package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class NewWindowExample {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver");

        WebDriver driver = new ChromeDriver();

        // Navigate to a URL
        driver.get("https://example.com");

        // Get the current window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Click on a link or button that opens a new window
        WebElement link = driver.findElement(By.id("newWindowLink"));
        link.click();

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Switch to the new window (other than the main one)
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Perform actions on the new window
        System.out.println("Title of the new window: " + driver.getTitle());

        // Switch back to the original window
        driver.switchTo().window(mainWindowHandle);

        // Perform actions on the original window
        System.out.println("Title of the main window: " + driver.getTitle());

        // Close the driver
        driver.quit();
    }
}
