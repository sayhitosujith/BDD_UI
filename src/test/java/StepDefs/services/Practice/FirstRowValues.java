package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

// print First Row Values of the table in selectorshub
public class FirstRowValues {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://selectorshub.com/xpath-practice-page/");

        //maximise browser
        driver.manage().window().maximize();

        // Locate the first row of the table
        List<WebElement> firstRow = driver.findElements(By.xpath("//table[@id='resultTable']//tr[1]//td"));

        // Print each cell value from the first row
        for (WebElement cell : firstRow) {
            System.out.println(cell.getText());
        }

        // Close the browser
        driver.quit();
    }
}
