package StepDefs.services.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

// print Second-Largest Price of_Iphone12
class SecondLargestPriceof_Iphone12 {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver (make sure the path to chromedriver is correct)
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver");
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
        int[] arr = {54899, 51999, 54899, 5199951, 99951, 99960, 90061, 99934, 99969, 99961, 900, 1299, 1399, 2299, 1999};
        int firstLargest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = num;
            } else if (num > secondLargest && num != firstLargest) {
                secondLargest = num;
            }
        }

        System.out.println("Second Largest Number: " + secondLargest);
        driver.quit();
    }
}

