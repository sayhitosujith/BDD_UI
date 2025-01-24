package StepDefs.services.Practice;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaveCookieExample {
    public static void main(String[] args) {
        // Set up WebDriver (in this case, using ChromeDriver)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to a website
        driver.get("https://www.amazon.in/");

        // Retrieve a specific cookie by name
        Cookie myCookie = driver.manage().getCookieNamed("cookie_name");  // Replace 'cookie_name' with the actual name

        // Check if the cookie exists and print its value
        if (myCookie != null) {
            System.out.println("Cookie Name: " + myCookie.getName());
            System.out.println("Cookie Value: " + myCookie.getValue());
        } else {
            System.out.println("Cookie not found!");
        }

        // Save the cookie value in a variable (if needed for further processing)
        String cookieValue = myCookie.getValue();

        // Close the browser
        driver.quit();
    }
}
