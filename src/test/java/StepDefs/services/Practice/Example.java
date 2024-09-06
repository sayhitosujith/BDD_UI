package StepDefs.services.Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //maximise browser
        driver.manage().window().maximize();

        // Navigate to the search engine
        driver.get("https://www.naukri.com/"); // Replace with the URL of the e-commerce site
        System.out.println("page opened");


        driver.quit();
    }
}

