package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SortLaptopPrice {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver (make sure the path to chromedriver is correct)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //maximise browser
        driver.manage().window().maximize();

        // Navigate to the search engine
        driver.get("https://www.amazon.in/"); // Replace with the URL of the e-commerce site

        // Find the search box, enter 'laptop', and submit
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("iphone 12");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> iphone_price = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        for (int i = 0; i < iphone_price.size(); i++) {
            String text = iphone_price.get(i).getText();
            System.out.println(text);
        }
        //string array
        String[] iphone = {"54,899","51,999","54,899","51,99951","999,51","999,60","900,61","999,34","999,69","999,61","900","1,299","1,399","2,299","1,999"};
        Arrays.sort(iphone);
        System.out.println(Arrays.toString(iphone));

        driver.quit();
    }
    }

