package StepDefs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.service.ExtentService;
import configManager.DataLoad;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import util.GenericUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hooks {


    @Before
    public void beforeScenario() throws Exception{

    }

    @After
    public static void writeExtentReport(Scenario scenario) {

        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String[][] validations = genericUtil.getReportData(dataLoad.getDataList());

        ExtentCucumberAdapter.getCurrentStep().getModel().setStatus(Status.INFO);
        ExtentCucumberAdapter.getCurrentStep().getModel().setName("<br/> <br/> <strong>API DATA :</strong> <br/>");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"<br/> <strong>Request URL: </strong> <br/> "+dataLoad.getRequestURL());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,MarkupHelper.createCodeBlock("Request: \n"+dataLoad.getRequest(), "Response: \n"+dataLoad.getResponse()));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"<br/> <br/> <strong>VALIDATIONS :</strong> <br/>");
        ExtentCucumberAdapter.getCurrentStep().info(MarkupHelper.createTable(validations));
        ExtentCucumberAdapter.getCurrentStep().getModel().updateResult();
        dataLoad.resetDataList();
    }

}
