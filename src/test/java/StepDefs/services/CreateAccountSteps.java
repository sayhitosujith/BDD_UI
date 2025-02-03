package StepDefs.services;

import Pages.LoginPage;
import Pages.LogoutPage;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import util.StepUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class CreateAccountSteps {
    WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;

    @Given("I enter the Valid URL of Application by Launching Chrome Browser")
    public void IentertheValidURLofApplicationbyLaunchingChromeBrowser(io.cucumber.datatable.DataTable dataTable) throws InterruptedException, MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run Chrome in headless mode
        options.addArguments("--no-sandbox"); // Ensure Chrome runs in a secure environment
        options.addArguments("--disable-dev-shm-usage"); // Avoid resource limits
        WebDriver driver = new RemoteWebDriver(new URL("http://10.1.0.18:4444/wd/hub"), options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        driver.get(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + dataMap.get("url")));
        System.out.println("This Step open the Chrome and launch the application.");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @When("I enter Valid details and Update account")
    public void iEnterValidDetailsAndGenerateAccount() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("sayhitosujith@gmail.com");
        System.out.println("Enter the User Name");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.enterPassword("Qw@12345678");
        System.out.println("Enter the Password");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage.clickLogin();
        System.out.println("Click on login button");
        driver.findElement(By.xpath("//a[normalize-space()='View profile']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Click on view profile");
    }

    @Then("should Logout Profile successfully")
    public void shouldSeeAccountCreatedSuccessfully() throws InterruptedException
    {
        logoutPage = new LogoutPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logoutPage.userprofile();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logoutPage.logoutButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        System.out.println("Logged Successfully..!!");
        driver.quit();
    }

    @And("I should Add User")
    public <select> void iShouldAddUser() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.getCurrentUrl();
        //Click on Add user
        driver.findElement(By.xpath("//button[normalize-space()='Add user']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //qty
        driver.findElement(By.xpath("//input[@id='textInput']")).sendKeys("1");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //select country
        driver.findElement(By.xpath("//div[contains(text(),'County')]")).click();
        driver.findElement(By.xpath("//input[@id='filterInput2']")).sendKeys("Avon");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //select Area code
        driver.findElement(By.xpath("//div[contains(text(),'Area code')]")).click();
        driver.findElement(By.xpath("//input[@id='filterInput3']")).sendKeys("117 - Bristol");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @And("I should see user added successfully")
    public void iShouldSeeUserAddedSuccessfully() {
    }


    @Then("should Logout successfully")
    public void shouldLogoutSuccessfully() {
        driver.quit();
        System.out.println("Logout Successfully and close the Browser");
    }

    @When("I should Add Number")
    public void iShouldAddNumber() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.getCurrentUrl();
        //Click on Add user
        driver.findElement(By.xpath("//button[normalize-space()='Add number']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Click on Add Number
        driver.findElement(By.xpath("//button[@title='County']")).click();
        driver.findElement(By.xpath("//*[@id=\"filterInput22\"]")).sendKeys("Avon");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @And("I Update my Resume")
    public void iUpdateMyResume() throws InterruptedException {
        WebElement upload_file = driver.findElement(By.xpath("//input[@value='Update resume']"));
        upload_file.click();
        upload_file.sendKeys("F://BDD_UI//BDD_UI//resources//files//Profile.pdf");
        System.out.println("upload resume");

        //get updated date
        WebElement updateddate = driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']"));
        System.out.println(updateddate.getText());
    }

    @And("I Update Resume headline")
    public void iUpdateResumeHeadline() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt15']//div//span[@class='edit icon'][normalize-space()='editOneTheme']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).sendKeys("SDET-Professional with Experience of 6.5 years.");
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        System.out.println("I Update Resume headline");
        //get updated date
        WebElement updateddate = driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']"));
        System.out.println(updateddate.getText());
        Assert.assertEquals(updateddate,updateddate);
    }

    @And("I take screenshot")
    public void AndiTakeScreenshot() {
        try {
            // Create a Robot instance to capture the screen
            Robot robot = new Robot();
            // Get the screen size
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Capture the screen
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Save the image to a file
            File file = new File("Screenshots//screenshot.png");
            ImageIO.write(screenFullImage, "png", file);
            System.out.println("Screenshot saved as screenshot.png");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    @And("I Scroll Page Down and Update Total experience")
    public void iScrollPageDownAndUpdateTotalExperience() throws InterruptedException {
        driver.findElement(By.xpath("//em[contains(@class,'icon edit')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//input[@id='exp-years-droopeFor']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//input[@id='exp-years-droopeFor']")).sendKeys("6 Years");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//input[@id='exp-months-droopeFor']")).sendKeys("5 Months");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//span[normalize-space()='Total experience']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//button[@id='saveBasicDetailsBtn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println("User experience updated successfully..!!");
        String totalExperience = driver.findElement(By.xpath("//span[@name='Experience']")).getText();
        Assert.assertEquals(totalExperience,totalExperience);
        System.out.println(totalExperience);
    }

    @And("I want to Update Profile Summary")
    public void IwanttoUpdateProfileSummary() {
        driver.findElement(By.xpath("//span[normalize-space()='Profile summary']")).click();
        System.out.println("Clicked on Update profile successfully..!!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("//div[@class='card']//div//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']")).click();
        System.out.println("Clicked on Edit Icon");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //click and clear Text
        driver.findElement(By.xpath("//textarea[@id='profileSummaryTxt']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Enter text
       driver.findElement(By.xpath("//textarea[@id='profileSummaryTxt']")).sendKeys("SDET Engineer at Exostar with 6.5 years of experience\n" +
                "\n" +
                "Having hands on experience in manual testing, C# ,Java ,Java script selenium with BDD Cucumber Framework ,REST API Automation with BDD, Jenkins for CI/CD.\n" +
                "working on Selenium BDD framework also Functionize AI Automation tool.\n" +
                "Using Git lab for source code control");
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println("Profile summary updated successfully");
    }

    @When("I enter Valid details and search Iphone{int}")
    public void iEnterValidDetailsandSearch(int arg0) throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("iphone 12");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

    }

    @And("I sort the price in ascending order")
    public void ISortThePriceInAscendingOrder() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> iphone_price = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        for (int i = 0; i < iphone_price.size(); i++) {
            String text = iphone_price.get(i).getText();
            System.out.println(text);

            //string array
            String[] iphone = {"54,899", "51,999", "54,899", "51,99951", "999,51", "999,60", "900,61", "999,34", "999,69", "999,61", "900", "1,299", "1,399", "2,299", "1,999"};
            Arrays.sort(iphone);
            System.out.println(Arrays.toString(iphone));
        }
    }

    @Then("should Logout from Amazon successfully")
    public void shouldLogoutFromAmazonSuccessfully() throws InterruptedException {
//        WebElement elementToHover = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(elementToHover).perform();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        driver.findElement(By.xpath("//span[normalize-space()='Sign Out']")).click();
        driver.quit();
    }
}
