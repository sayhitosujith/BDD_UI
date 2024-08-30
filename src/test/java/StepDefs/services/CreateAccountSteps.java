package StepDefs.services;

import StepDefs.Validations;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.StepUtil;

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
    public void shouldSeeAccountCreatedSuccessfully() {
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
        driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']")).sendKeys("SDET 2 Professional with Experience of 6 years. serving notice period of 1 month");
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        System.out.println("I Update Resume headline");
    }
}
