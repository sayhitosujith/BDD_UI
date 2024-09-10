package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

class GetEmployeeNames {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://selectorshub.com/xpath-practice-page/");

        //maximise browser
        driver.manage().window().maximize();

        // Locate the employee name elements using XPath
        List<WebElement> employeeNames = driver.findElements(By.xpath("//table[@id='resultTable']//td[4]"));

        // Print each employee name
        for (WebElement name : employeeNames) {
            System.out.println(name.getText());
        }

        // Close the browser
        driver.quit();
    }
}
