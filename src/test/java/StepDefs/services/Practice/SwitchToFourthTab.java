package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwitchToFourthTab {
    public static void main(String[] args) {
        // Set up WebDriver (Make sure to set the correct path to your chromedriver)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set a timeout for waiting
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the page where the button is located
        driver.get("https://example.com");  // Replace with the URL where the button is

        // Click the button to open new tabs
        driver.findElement(By.id("buttonId")).click(); // Replace with the actual ID of the button

        // Wait for new tabs to open
        try {
            Thread.sleep(3000);  // Wait for 3 seconds to ensure tabs have opened
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get all open window handles (tabs)
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the 4th tab (index 3 as the index starts from 0)
        if (tabs.size() >= 4) {
            driver.switchTo().window(tabs.get(3));  // Switch to the 4th tab
        } else {
            System.out.println("Less than 4 tabs are open!");
        }

        // Perform any actions on the 4th tab (e.g., printing the title of the tab)
        System.out.println("Title of 4th tab: " + driver.getTitle());

        // Close the WebDriver (optional)
        driver.quit();
    }
}
