package configManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver getDriver(String browser) {
        WebDriver driver;  // Initialize driver to null

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();  // Automatically manage ChromeDriver version
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();  // Automatically manage GeckoDriver version
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();  // Automatically manage EdgeDriver version
                driver = new EdgeDriver();
                break;

            default:
                // Log message and throw an exception for unsupported browsers
                System.out.println("Browser " + browser + " is not supported.");
                throw new IllegalArgumentException("Browser " + browser + " is not supported.");
        }

        return driver;
    }
}
