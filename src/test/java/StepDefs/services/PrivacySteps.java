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
import io.restassured.parsing.Parser;

import io.restassured.specification.RequestSpecification;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;


import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;


import java.util.Map;

public class PrivacySteps {


    @And("I send request for Create Privacy with below details")
    public void iSendRequestForCreatePrivacyWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PRIVACY_CREATEPRIVACY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("wardKey", dataLoad.getWardKey());
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see Privacy  Details created successfully")
    public void iShouldSeePrivacyDetailsCreatedSuccessfully() {
        //  Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code Validation for Participant Creation");
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setPrivacyRequestKey(RestResponse.getAsString());
    }

    @And("I send request for get Privacy with below details")
    public void iSendRequestForGetPrivacyWithBelowDetails() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String, String> queryparams = new HashMap<String, String>();
        queryparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.get(queryparams, true);

    }

    @Then("i should get privacy request using filter")
    public void iShouldGetPrivacyRequestUsingFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation of get privacy request using filter");
        String responseString = RestResponse.getAsString();
       // Validations.assertEquals(true, responseString.contains(dataLoad.getParticipantKey()), "Validation of participant key in get privacy by filter response");
       // Validations.assertEquals(true, responseString.contains(dataLoad.getWardKey()), "Validation of wardKey in get privacy by filter response");
        //Validations.assertEquals("sujith.s@winwire.com",responseString.contains(dataLoad.getEmail()),"Validation of emailAddress");

    }


    @And("I send request for get privacy request using continuation Token filter")
    public void iSendRequestForGetPrivacyRequestUsingContinuationTokenFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String, String> queryparams = new HashMap<String, String>();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("wardKey", dataLoad.getWardKey());
        queryparams.put("limit", "3");
        RestUtil.get(pathParams, queryparams, true);
    }

    @Then("i should get privacy request using using continuation Token filter")
    public void iShouldGetPrivacyRequestUsingUsingContinuationTokenFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation of get privacy request using filter");
        String responseString = RestResponse.getAsString();
        Validations.assertEquals(true, responseString.contains(dataLoad.getParticipantKey()), "Validation of participant key in get privacy by filter response");
        Validations.assertEquals(true, responseString.contains(dataLoad.getWardKey()), "Validation of wardKey in get privacy by filter response");
        //Validations.assertEquals("sujith.s@winwire.com",responseString.contains(dataLoad.getEmail()),"Validation of emailAddress");
    }

















    @And("I send request for get Privacy using continuation Token with below details")
    public void iSendRequestForGetPrivacyUsingContinuationTokenWithBelowDetails() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String, String> queryparams = new HashMap<String, String>();
        queryparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.get(queryparams, true);
    }

    @Then("i should get privacy using continuation using filter")
    public void iShouldGetPrivacyUsingContinuationUsingFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation of get privacy request using filter");
        String responseString = RestResponse.getAsString();
        // Validations.assertEquals(true, responseString.contains(dataLoad.getParticipantKey()), "Validation of participant key in get privacy by filter response");
        // Validations.assertEquals(true, responseString.contains(dataLoad.getWardKey()), "Validation of wardKey in get privacy by filter response");
        //Validations.assertEquals("sujith.s@winwire.com",responseString.contains(dataLoad.getEmail()),"Validation of emailAddress");
    }

    @And("I send request for get privacy request for a specific Ward using filter")
    public void iSendRequestForGetPrivacyRequestForASpecificWardUsingFilter() {


        DataLoad dataLoad = DataLoad.getInstance();
        //Map<String, String> queryparams = new HashMap<String, String>();
       // queryparams.put("wardKey", dataLoad.getWardKey());
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("wardKey", dataLoad.getWardKey());
      //  RestUtil.get(queryparams, true);
      // RestUtil.get(pathParams, queryparams, true);
        RestUtil.get(pathParams, true);
      //  RestUtil.get(pathParams, queryparams, true);
        
    }

    @Then("i should get specific Ward using filter")
    public void iShouldGetSpecificWardUsingFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation of get privacy request using filter");
        String responseString = RestResponse.getAsString();
        // Validations.assertEquals(true, responseString.contains(dataLoad.getParticipantKey()), "Validation of participant key in get privacy by filter response");
        // Validations.assertEquals(true, responseString.contains(dataLoad.getWardKey()), "Validation of wardKey in get privacy by filter response");
        //Validations.assertEquals("sujith.s@winwire.com",responseString.contains(dataLoad.getEmail()),"Validation of emailAddress");

    }
}
