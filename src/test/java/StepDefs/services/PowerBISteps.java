package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.HashMap;
import java.util.Map;

public class PowerBISteps {

    @And("i send request for get report panel rendering model")
    public void iSendRequestForGetReportPanelRenderingModel() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        RestUtil.get(true);
    }

    @Then("I should see embed report panel rendering model")
    public void iShouldSeeEmbedReportPanelRenderingModel() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), " Validated status code for embed report panel rendering model");
        Validations.assertEquals(true, !RestResponse.getValue("accessToken").toString().isEmpty(), "accessToken is displayed--->" + RestResponse.getValue("accessToken").toString());
        Validations.assertEquals(true, !RestResponse.getValue("embedUrl").toString().isEmpty(), "embedUrl is displayed--->" + RestResponse.getValue("embedUrl").toString());
        Validations.assertEquals(true, !RestResponse.getValue("id").toString().isEmpty(), "ID is displayed--->" + RestResponse.getValue("id").toString());
    }
}
