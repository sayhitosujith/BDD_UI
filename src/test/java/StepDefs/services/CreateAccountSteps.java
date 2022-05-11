package StepDefs.services;

import StepDefs.Validations;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.StepUtil;

import java.util.Map;


public class CreateAccountSteps<IJavaScriptExecutor>

{
    WebDriver driver;

    @Given("I enter the Valid URL of Application by Launching Chrome Browser")
    public void IentertheValidURLofApplicationbyLaunchingChromeBrowser(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        driver.get(ResourceData.getEnvironmentURL(ResourceData.getEnvironment()+"."+dataMap.get("url")));
        System.out.println("This Step open the Chrome and launch the application.");
        Thread.sleep(10000);
    }
    @When("I enter Valid details and generate account")
    public void iEnterValidDetailsAndGenerateAccount()throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("aqa.api.signup(brand=7710,tierid=7751)");
        System.out.println("Enter the Text to Generate account");

        //Click on Start
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        System.out.println("Click on Start");
        //Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to verify email sent successfully");
        Thread.sleep(20000);

        //click on Results
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/p[3]/a[1]")).click();
        System.out.println("click on Results");
        System.out.println("Title - "+ driver.getTitle());
        Thread.sleep(1000);

        //Click on the main Number
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/table/tbody/tr/td[3]/a/img")).click();
        System.out.println("Click on the main Number");
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
        Thread.sleep(30000);
    }




    @And("should see account created successfully")
    public void shouldSeeAccountCreatedSuccessfully() {
        Validations.assertEquals("AGS2 devf13ams0101/dft11-t13-ags01.lab.nordigy.ru", driver.getTitle(),"Status code validation for Verify page title");
        //Validations.assertEquals("http://dft11-t13-ags01.lab.nordigy.ru:8081/ag/results?jobId=16077747", driver.getCurrentUrl(),"Status code validation for Verify page URL");



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
}
