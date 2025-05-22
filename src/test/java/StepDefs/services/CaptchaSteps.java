package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import util.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CaptchaSteps {

    @And("I send request to validate captcha action key")
    public void Isendrequesttovalidatecaptchaactionkey(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CAPTCHA_VALIDATECAPTCHA);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        // dataMap.put("sweepstakeKey",dataLoad.getSweepstakeKey());
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization", "Bearer " + dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey", "MySandBoxKey");
        httpRequest.header("x-pl-actionToken", "action " + dataLoad.getActionToken());
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        RestResponse.initialize(httpRequest.post());
    }

    @Then("I should see captcha validated successfully")
    public void iShouldSeeCaptchaValidatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code see captcha validated successfully");
        Validations.assertEquals("true",RestResponse.getValue("success").toString(),"Validation of success");
    }
}
