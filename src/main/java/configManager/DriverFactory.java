package configManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.NoSuchDriverException;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                // Update the path to geckodriver if necessary
                System.setProperty("webdriver.gecko.driver", "C://Users//Sadashivareddys//IdeaProjects//BDD_UI//drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "chrome":
                // Specify the path to chromedriver if using Chrome
                System.setProperty("webdriver.chrome.driver","C://Users//Sadashivareddys//IdeaProjects//BDD_UI//drivers//chromedriver.exe");
                driver = new ChromeDriver();
                break;

            default:
                throw new NoSuchDriverException("No valid driver specified for browser: " + browser);
        }

        return driver;
    }
}