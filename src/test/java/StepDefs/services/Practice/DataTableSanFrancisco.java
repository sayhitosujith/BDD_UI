package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

//how can i find employees who work for San Francisco
public class DataTableSanFrancisco {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Maximise the window
        driver.manage().window().maximize();

        // Navigate to the DataTable URL
        driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");

        // Find all rows where the Office is "San Francisco"
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr[td[text()='San Francisco']]"));

        // Loop through and print the employee details
        for (WebElement row : rows) {
            // Get all the columns (td) of the row
            List<WebElement> columns = row.findElements(By.tagName("td"));

            // Print the employee's name (first column)
            String employeeName = columns.get(0).getText();
            System.out.println("Employee Name : " + employeeName);

            // Optionally print other details like position, salary, etc.
            String position = columns.get(1).getText();
            System.out.println("Employee Position: " + position);
        }

        // Close the browser
        driver.quit();
    }
}
