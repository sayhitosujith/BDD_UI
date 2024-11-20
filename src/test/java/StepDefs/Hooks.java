package StepDefs;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import configManager.DataLoad;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.GenericUtil;
import util.ScreenshotUtil;

import java.util.List;
import java.util.Set;

public class Hooks extends ScreenshotUtil {
    private WebDriver driver;

    @Before
    public void beforeScenario() throws Exception {
        driver = new WebDriver() {
            @Override
            public void get(String url) {

            }

            @Override
            public String getCurrentUrl() {
                return null;
            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public List<WebElement> findElements(By by) {
                return null;
            }

            @Override
            public WebElement findElement(By by) {
                return null;
            }

            @Override
            public String getPageSource() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public void quit() {

            }

            @Override
            public Set<String> getWindowHandles() {
                return null;
            }

            @Override
            public String getWindowHandle() {
                return null;
            }

            @Override
            public TargetLocator switchTo() {
                return null;
            }

            @Override
            public Navigation navigate() {
                return null;
            }

            @Override
            public Options manage() {
                return null;
            }
        };
    }

    @After
    public void writeExtentReport(Scenario scenario) {

        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String[][] validations = genericUtil.getReportData(dataLoad.getDataList());

        ExtentCucumberAdapter.getCurrentStep().getModel().setStatus(Status.INFO);
        ExtentCucumberAdapter.getCurrentStep().getModel().setName("<br/> <br/> <strong>API DATA :</strong> <br/>");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "<br/> <strong>Request URL: </strong> <br/> " + dataLoad.getRequestURL());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MarkupHelper.createCodeBlock("Request: \n" + dataLoad.getRequest(), "Response: \n" + dataLoad.getResponse()));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "<br/> <br/> <strong>VALIDATIONS :</strong> <br/>");
        ExtentCucumberAdapter.getCurrentStep().info(MarkupHelper.createTable(validations));
        ExtentCucumberAdapter.getCurrentStep().getModel().updateResult();
        dataLoad.resetDataList();
        if (scenario.isFailed()) {
            // Take screenshot if scenario fails
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        }
    }
}
