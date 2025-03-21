import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty", "json:out/cucumber.json", "junit:out/cucumber.xml", "rerun:target/failed_scenarios.txt"},
        features = "src/test/java/features",
        glue = "StepDefs",
        dryRun = false,
        //tags = "@Naukri",
        tags = "@VerifyEmploymentExperienceAndUpdateAccount",
        monochrome = true)

public class RunCukesTest extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void preset() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("Report/report.html");
        extentSparkReporter.config().setCss(".info-bg {\n" +
                " background-color: #e5e5e5; \n" +
                " }");
        ExtentService.getInstance().attachReporter(extentSparkReporter);
        ExtentService.getInstance().setSystemInfo("OS : ", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("Java Version : ", System.getProperty("java.version"));
        ExtentService.getInstance().setSystemInfo("User Name : ", System.getProperty("user.name"));
    }
}

