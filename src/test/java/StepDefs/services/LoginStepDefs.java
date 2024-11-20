package StepDefs.services;

import StepDefs.Validations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;


public class LoginStepDefs<IJavaScriptExecutor> {
    WebDriver driver;


    @When("I enter Valid Credentials and Login Successfully")
    public void IenterValidCredentialsandLoginSuccessfully() throws Throwable {
        //  driver.findElement(By.xpath("//a[@href='/login']")).click();
//        GenericUtil genericUtil = new GenericUtil();
//        DataLoad dataLoad = DataLoad.getInstance();
//        String payload = genericUtil.getFileData(Payloads.USER_LOGIN);
//        Map<String,String> dataMap = StepUtil.toMap(dataTable);

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("sayhitosujith@gmail.com");
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Qw@12345678");
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        System.out.println("Enter Valid Credentials and verify the details");
        System.out.println("Login is Successful");

    }

    @Then("i should Login Successfully")
    public void iShouldLoginSuccessfully() {


        System.out.println("This Step should Login Successfully");
    }


    @And("i should Verify page title")
    public void iShouldVerifyPageTitle() {

        Validations.assertEquals("Demo Web Shop", driver.getTitle(), "Status code validation for Verify page title");
        System.out.println("Verification of Page Title");
    }

    @And("I Add smart phone product to cart")
    public void iAddSmartPhoneProductToCart() throws InterruptedException {

        driver.findElement(By.xpath("//div[@class='listbox']/ul/li[3]/a[@href='/electronics']")).click();
        System.out.println("Click on Electronics Section");


        driver.findElement(By.xpath("//div[@class='item-box'][2]")).click();
        System.out.println("Click on Cell Phones Module");

        driver.findElement(By.xpath("//div[@class='product-item']//img[@title='Show details for Smartphone']")).click();
        System.out.println("Click on smart phone");

        driver.findElement(By.xpath("//input[@id='addtocart_43_EnteredQuantity']")).clear();
        System.out.println("Clear the Quantity");
        Thread.sleep(5000);

        Random randomGenerator = new Random();
        driver.findElement(By.xpath("//input[@id='addtocart_43_EnteredQuantity']")).sendKeys("2");
        System.out.println("Enter the Quantity - 2 ");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@class='button-1 add-to-cart-button']")).click();
        System.out.println("Add to cart");
        Thread.sleep(5000);

        String heading = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
        System.out.println("The expected heading is same as actual heading --- " + heading);

        Validations.assertEquals("Demo Web Shop. Smartphone", driver.getTitle(), "Status code validation for Verify page title");

    }

    @And("I verify cart details")
    public void iVerifyCartDetails() {

        //....Click on shopping cart....
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
        System.out.println("Click on shopping cart");
    }

    @And("I Delete the cart details")
    public void iDeleteTheCartDetails() {
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
        System.out.println("Click on shopping cart");

        // Remove product from cart
        driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
        System.out.println("Remove product from shopping cart");

        //Update Shoopping Cart
        driver.findElement(By.xpath("//input[@name='updatecart']")).click();
        System.out.println("Update Shoopping Cart - Your Shopping Cart is empty!");

        String heading = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
        System.out.println("The expected heading is same as actual heading --- " + heading);

    }
// IJavaScriptExecutor js = (IJavaScriptExecutor)SeleniumActions.driver;
//WebDriverWait wait = new WebDriverWait(SeleniumActions.driver, new TimeSp(0, 0, 40));
//wait.Until(wd => js.ExecuteScript("return document.readyState").ToString() == "complete");

}
