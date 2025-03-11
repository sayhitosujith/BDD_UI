package configManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
    public static WebDriver getDriver() {
        // Set the path to the GeckoDriver executable
        System.setProperty("webdriver.gecko.driver", "C:/Users/Sadashivareddys/IdeaProjects/BDD_UI/drivers/geckodriver.exe");

        // Set Firefox options
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);

        // Initialize the WebDriver
        WebDriver driver = null;
        try {
            driver = new FirefoxDriver(options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }

        return driver;
    }
}