package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Open the website
        driver.get("https://www.naukri.com/nlogin/login?utm_source=google&utm_medium=cpc&utm_campaign=Brand&gad_source=1&gclid=CjwKCAjwo6GyBhBwEiwAzQTmc34DfBd9dNPPn_R_W3UozmHxoGFxQRepNJgOcFPHLMUoYhEwNErtOxoC6a0QAvD_BwE&gclsrc=aw.ds");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    // DataProvider method supplying test data
    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][] {
                {"sayhitosujith@gmail.com", "Qw@12345678"},
        };
    }

    // Test method that uses the DataProvider
    @Test(dataProvider = "userData")
    public void fillForm(String firstName, String lastName) {
        // Locate the first name field and enter the data
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='usernameField']"));
        firstNameField.sendKeys(firstName);

        // Locate the last name field and enter the data
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='passwordField']"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Submit the form (example: clicking a submit button)
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // You could add verification steps here to check if the form was submitted successfully
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}