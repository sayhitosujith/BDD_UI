package StepDefs.services.Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClearCookiesExample {
    public static void main(String[] args) {
        // Set the path for the WebDriver executable (e.g., chromedriver)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // Navigate to a website
        driver.get("https://www.amazon.in/");

        // Clear all cookies
        driver.manage().deleteAllCookies();
        System.out.println("Clearing the cookies");
        // Close the browser
        driver.quit();
    }
}
