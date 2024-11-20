package StepDefs.services.Practice;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class CheckCookiesExample {
    public static void main(String[] args) {
        // Set the path for the WebDriver executable (e.g., chromedriver)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // Navigate to a website
        driver.get("https://www.amazon.in/");

        // Retrieve all cookies
        Set<Cookie> cookies = driver.manage().getCookies();

        // Print all cookies
        for (Cookie cookie : cookies) {
            System.out.println("Name: " + cookie.getName() +
                    ", Value: " + cookie.getValue() +
                    ", Domain: " + cookie.getDomain() +
                    ", Path: " + cookie.getPath() +
                    ", Expiry: " + cookie.getExpiry());
        }

        // Close the browser
        driver.quit();
    }
}
