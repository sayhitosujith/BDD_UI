package StepDefs.services;

import Pages.LoginPage;
import Pages.LogoutPage;
import configManager.ResourceData;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.StepUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class CreateAccountSteps extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;


    @Given("I enter the Valid URL of Application by Launching Chrome Browser")
    public void IentertheValidURLofApplicationbyLaunchingChromeBrowser(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
//        DriverFactory factory = new DriverFactory();
//        driver = factory.getDriver("chrome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    public void shouldSeeAccountCreatedSuccessfully()
    {
//        logoutPage = new LogoutPage(driver);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        logoutPage.userprofile();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        logoutPage.logoutButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
//        System.out.println("Logged Successfully..!!");
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
        // Locate the hidden file input - update XPath if needed
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

        // Make it visible if it's hidden using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);

        // Upload file (make sure path uses \\ in Java)
        String filePath = new File("resources/files/Sujith_Profile.pdf").getAbsolutePath();
        fileInput.sendKeys(filePath);
        System.out.println("Upload resume");

        // Wait for resume upload and updated date to reflect
        Thread.sleep(3000); // Better to use WebDriverWait if possible

        // Get updated date
        WebElement updateddate = driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']"));
        System.out.println("Updated on: " + updateddate.getText());
    }


    @And("I Update Resume headline")
    public void iUpdateResumeHeadline() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt15']//div//span[@class='edit icon'][normalize-space()='editOneTheme']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).sendKeys("SDET-Professional with Experience of 6 years and 11 months");
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
        // Scroll down if needed
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        // Wait until the edit icon is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//em[contains(@class,'icon edit')]")));
        editIcon.click();

        // Wait for and update years of experience
        WebElement yearsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exp-years-droopeFor")));
        yearsInput.clear();
        yearsInput.sendKeys("6 Years");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Update months of experience
        WebElement monthsInput = driver.findElement(By.id("exp-months-droopeFor"));
        monthsInput.clear();
        monthsInput.sendKeys("10 Months");

        // Click save
        WebElement saveButton = driver.findElement(By.id("saveBasicDetailsBtn"));
        saveButton.click();

        // Wait for save to reflect (e.g. button disappears or loading stops)
        Thread.sleep(3000); // Better: wait for some indicator to confirm update

        // Optional: scroll again if needed
      //  js.executeScript("window.scrollBy(0,300)");

        // Click or hover to expand Total Experience info if necessary
        WebElement totalExpLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Total experience']")));
        totalExpLabel.click();

        // Wait for experience text to be visible
        WebElement experienceText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@name='Experience']")));
        String totalExperience = experienceText.getText();

        System.out.println("✅ Updated experience: " + totalExperience);

        // Assertion
        Assert.assertEquals(totalExperience, "6 Years 10 Months");
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
    @After
    public void tearDown() throws IOException {
        // Check if the driver is an instance of TakesScreenshot
//        if (driver instanceof TakesScreenshot) {
//            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
//            File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
//            FileHandler.copy(screenshot, new File("screenshot.png"));  // Save the screenshot to a file
//        }
        driver.quit();
    }

    @And("I Update Profile summary")
    public void iUpdateProfileSummary() {
        driver.findElement(By.xpath("//span[@class='text'][normalize-space()='Profile summary']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[@class='card']//div//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='profileSummaryTxt']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//textarea[@id='profileSummaryTxt']")).sendKeys("SDET professional . Having hands on experience in manual testing, C# ,Java ,Java script selenium with BDD Cucumber Framework ,REST API Automation with BDD, Jenkins for CI/CD.\n" +
                "working on Selenium BDD framework also Functionize AI Automation tool.\n" +
                "Using Git lab for source code control.");
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        System.out.println("I Updated Profle summary");
        //get updated date
        WebElement updateddate = driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']"));
        System.out.println(updateddate.getText());
        Assert.assertEquals(updateddate,updateddate);
    }
}
