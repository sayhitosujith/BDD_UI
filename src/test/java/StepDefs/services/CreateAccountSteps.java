package StepDefs.services;

import StepDefs.Validations;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.StepUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CreateAccountSteps<IJavaScriptExecutor>

{
    WebDriver driver;

    @Given("I enter the Valid URL of Application by Launching Chrome Browser")
    public void IentertheValidURLofApplicationbyLaunchingChromeBrowser(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        driver.get(ResourceData.getEnvironmentURL(ResourceData.getEnvironment()+"."+dataMap.get("url")));
        System.out.println("This Step open the Chrome and launch the application.");
        Thread.sleep(10000);
    }
    @When("I enter Valid details and Update account")
    public void iEnterValidDetailsAndGenerateAccount()throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys("sayhitosujith@gmail.com");
        System.out.println("Enter the User Name");

        driver.findElement(By.xpath("//input[@id='passwordField']")).sendKeys("Qw@12345678");
        System.out.println("Enter the Password");

        driver.findElement(By.xpath("//button[@class='waves-effect waves-light btn-large btn-block btn-bold blue-btn textTransform']")).click();
        System.out.println("Click on login button");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[normalize-space()='View profile']")).click();
        Thread.sleep(2000);
        System.out.println("Click on view profile");
    }

    @Then("should Logout Profile successfully")
    public void shouldSeeAccountCreatedSuccessfully() throws InterruptedException {
        driver.findElement(By.xpath("//img[@alt='naukri user profile img']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
        Thread.sleep(3000);
        System.out.println("Logged Successfully..!!");
        driver.quit();
    }

    @And("I should Add User")
    public <select> void iShouldAddUser() throws InterruptedException {
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
       driver.getCurrentUrl();
        //Click on Add user
        driver.findElement(By.xpath("//button[normalize-space()='Add user']")).click();
        Thread.sleep(5000);
        //qty
        driver.findElement(By.xpath("//input[@id='textInput']")).sendKeys("1");
        Thread.sleep(5000);

        //select country
        driver.findElement(By.xpath("//div[contains(text(),'County')]")).click();
        driver.findElement(By.xpath("//input[@id='filterInput2']")).sendKeys("Avon");
        Thread.sleep(1000);
        //select Area code
        driver.findElement(By.xpath("//div[contains(text(),'Area code')]")).click();
        driver.findElement(By.xpath("//input[@id='filterInput3']")).sendKeys("117 - Bristol");
        Thread.sleep(1000);
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
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.getCurrentUrl();
        //Click on Add user
        driver.findElement(By.xpath("//button[normalize-space()='Add number']")).click();
        Thread.sleep(10000);

        //Click on Add Number
        driver.findElement(By.xpath("//button[@title='County']")).click();
        driver.findElement(By.xpath("//*[@id=\"filterInput22\"]")).sendKeys("Avon");
        Thread.sleep(10000);
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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).sendKeys("SDET 2 Professional with Experience of 6 years. serving notice period of 1 month , can join immediately");
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        System.out.println("I Update Resume headline");
        //get updated date
        WebElement updateddate = driver.findElement(By.xpath("//div[@class='updateOn typ-14Regular']"));
        System.out.println(updateddate.getText());
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
        driver.findElement(By.xpath("//em[@class='icon edit']")).click();
        Thread.sleep(2000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Wait for a while to observe the scrolling
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@id='exp-years-droopeFor']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='exp-years-droopeFor']")).sendKeys("6 Years");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='exp-months-droopeFor']")).sendKeys("1 Month");
        Thread.sleep(2000);
        driver.findElement((By.xpath("//span[normalize-space()='Total experience']"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='saveBasicDetailsBtn']")).click();
        Thread.sleep(2000);
        String exp = driver.findElement(By.xpath("//span[@name='Experience']")).getText();
        System.out.println(exp);
    }

    @And("I want to Find the Number of Rows and Columns")
    public void iWantToFindTheNumberOfRowsAndColumns() {

                try {
                    Thread.sleep(5000);
                    // Scroll down the page
                    // Locate the element you want to scroll to
                    WebElement elementToScrollTo = driver.findElement(By.xpath("//ul[@class='mb0']"));  // Replace with the appropriate locator

                    // Use JavascriptExecutor to scroll to the element
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);

                    // Locate the web table using a suitable locator
                    WebElement table = driver.findElement(By.xpath("//ul[@class='mb0']"));  // Replace "tableId" with the actual ID of the table

                    // Find all rows in the table
                    java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));
                    int rowCount = rows.size();
                    System.out.println("Number of rows in the table: " + rowCount);

                    // Find all columns in the first row (header or a data row)
                    List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));  // For header row
                    if (columns.isEmpty()) {
                        columns = rows.get(0).findElements(By.tagName("td"));  // For data row if no header row is present
                    }
                    int columnCount = columns.size();
                    System.out.println("Number of columns in the table: " + columnCount);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
}
