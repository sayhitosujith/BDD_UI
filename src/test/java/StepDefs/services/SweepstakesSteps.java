package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SweepstakesSteps {
            @And("I send request for create sweepstake entry with below details")
            public void iSendRequestForCreateSweepstakeEntryWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
                GenericUtil genericUtil = new GenericUtil();
                DataLoad dataLoad = DataLoad.getInstance();
                String payload = genericUtil.getFileData(Payloads.SWEEPSTAKES_CREATESWEEPSTAKEENTRY);
                Map<String,String> dataMap = StepUtil.toMap(dataTable);
                dataMap.put("participantKey",dataLoad.getParticipantKey());
                dataMap.put("sweepstakeKey",dataLoad.getSweepstakeKey());
                String jsonData = genericUtil.jsonConstruct(dataMap,payload);
                RequestSpecification httpRequest = RestAssured.given();
                httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
                httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
                httpRequest.header("x-pl-actionToken","action "+dataLoad.getActionToken());
                httpRequest.headers(APIHeaders.defaultHeaders());
                httpRequest.body(jsonData);
                RestResponse.initialize(httpRequest.post());
            }

    @And("I send request for create sweepstake with below details")
    public void iSendRequestForCreateSweepstakeWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SWEEPSTAKES_CREATESWEEPSTAKE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        dataMap.put("OfferingKey",dataLoad.getOfferingKey());
        dataMap.put("actionKey",dataLoad.getActionKey());
        dataLoad.setSweepstakeName(dataMap.get("name"));
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see the sweepstake created successfully")
    public void iShouldSeeTheSweepstakeCreatedSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for sweepstake creation");
        DataLoad.getInstance().setSweepstakeKey(RestResponse.getAsString());
      }

    @Then("I should see sweepstake entry created successfully")
    public void iShouldSeeSweepstakeEntryCreatedSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for sweepstake entry creation");
        DataLoad.getInstance().setSweepstakeEntrySetKey(RestResponse.getAsString());
     }

    @And("I send request for get sweepstake entry")
    public void iSendRequestForGetSweepstakeEntry() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("sweepsConfirmKey", DataLoad.getInstance().getSweepstakeConfirmKey());
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+DataLoad.getInstance().getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.pathParams(pathparams);
        RestResponse.initialize(httpRequest.get());
       }

    @And("I send request for get sweepstake")
    public void iSendRequestForGetSweepstake() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("sweepsKey", DataLoad.getInstance().getSweepstakeKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the sweepstake details in response")
    public void iShouldSeeTheSweepstakeDetailsInResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation of get sweepstake");
        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("engagementKey").toString(), "Validation of engagement key get sweepstake response");
        Validations.assertEquals(DataLoad.getInstance().getOfferingKey(), RestResponse.getValue("offeringKeys[0]").toString(), "Validation of offering key in get sweepstake response");
        Validations.assertEquals(DataLoad.getInstance().getActionKey(), RestResponse.getValue("allowedActionKeys[0]").toString(), "Validation of action key in get sweepstake response");
    }

    @Then("I should see the sweepstake details in response of get sweepstakes by participant key")
    public void iShouldSeeTheSweepstakeDetailsInResponseByParticiantKey() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation of get sweepstake");
        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("engagementKey.get(0)").toString(), "Validation of engagement key in get sweepstake response");
        Validations.assertEquals(DataLoad.getInstance().getOfferingKey(), RestResponse.getValue("offeringKeys[0].get(0)").toString(), "Validation of offering key in get sweepstake response");
        Validations.assertEquals(DataLoad.getInstance().getActionKey(), RestResponse.getValue("allowedActionKeys[0].get(0)").toString(), "Validation of action key in get sweepstake response");
    }

    @Then("I should see the sweepstake entry details in the response")
    public void iShouldSeeTheSweepstakeEntryDetailsInTheResponse(){
        DataLoad dataLoad=DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get Entry");
        Validations.assertEquals(dataLoad.getParticipantKey(), RestResponse.getValue("participantKey"),"Validation of participant key in get Entry response");
        Validations.assertEquals(dataLoad.getSweepstakeConfirmKey(), RestResponse.getValue("confirmationKey"),"Validation of confirmation key in get Entry response");
        Validations.assertEquals(dataLoad.getSweepstakeKey(), RestResponse.getValue("sweepstakeKey"),"Validation of sweepstake key in get Entry response");
    }

    @And("I send request for create Participant with additional header with below details")
    public void iSendRequestForCreateParticipantWithAdditionalHeaderWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_CREATEPARTICIPANT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        RestResponse.initialize(httpRequest.post());
    }

    @And("I send request for get sweepstake by participant key")
    public void iSendRequestForGetSweepstakeByParticipantKey() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("participantKey", DataLoad.getInstance().getParticipantKey());
        RestUtil.get(pathparams,true);
    }

    @And("I send request for get sweepstake entry by participant key and sweepstake key")
    public void iSendRequestForGetSweepstakeEntryByParticipantKeyAndSweepstakeKey() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        pathparams.put("participantKey", DataLoad.getInstance().getParticipantKey());
        pathparams.put("sweepstakeKey", DataLoad.getInstance().getSweepstakeKey());
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.pathParams(pathparams);
        RestResponse.initialize(httpRequest.get());
    }

    @And("I send request for get sweepstake by filter")
    public void iSendRequestForGetSweepstakeByFilter() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("offeringKey",dataLoad.getOfferingKey());
        RestUtil.get(queryparams,true);
    }

    @And("I send request for get sweepstake entry by filter")
    public void iSendRequestForGetSweepstakeEntryByFilter() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("entrySetKey",dataLoad.getSetSweepstakeEntrySetKey());
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.queryParams(queryparams);
        RestResponse.initialize(httpRequest.get());
    }

    @And("I send request for update sweepstake by updating name")
    public void iSendRequestForUpdateSweepstakeByUpdatingName(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SWEEPSTAKES_UPDATESWEEPSTAKE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",DataLoad.getInstance().getEngagementKey());
        dataMap.put("actionKey",DataLoad.getInstance().getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("sweepsKey", dataLoad.getSweepstakeKey());
        RestUtil.post(pathparams,jsonData,true);
        dataLoad.setSweepstakeName(dataMap.get("name"));
        System.out.println(dataMap);
        String [] offeringKeys = {dataLoad.getOfferingKey(),dataMap.get("offeringKeyOne").toString()};
        dataLoad.setOfferingKeys(offeringKeys);
    }

    @Then("I should see the updated sweepstake details in the response")
    public void iShouldSeeTheUpdatedSweepstakeDetailsInTheResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation of update sweepstake response");
        Validations.assertEquals(dataLoad.getOfferingKeys()[1], RestResponse.getValue("offeringKeys[0]").toString(), "Validation of newly added offering key in get sweepstake response");
        Validations.assertEquals(dataLoad.getSweepstakeName(),RestResponse.getValue("name"),"Validation of updated sweepstake name in response");
    }

    @And("I send request for delete sweepstake")
    public void iSendRequestForDeleteSweepstake() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("sweepsKey", DataLoad.getInstance().getSweepstakeKey());
        RestUtil.delete(pathparams,true);
    }

    @Then("I should see the sweepstake details in response with filter")
    public void iShouldSeeTheSweepstakeDetailsInResponseWithFilter() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation of get sweepstake");
        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("data[0].engagementKey").toString(), "Validation of get sweepstake in response");
        Validations.assertEquals(DataLoad.getInstance().getOfferingKey(), RestResponse.getValue("data[0].offeringKeys[0]").toString(), "Validation of offering key in get sweepstake response");
        Validations.assertEquals(DataLoad.getInstance().getActionKey(), RestResponse.getValue("data[0].allowedActionKeys[0]").toString(), "Validation of action key in get sweepstake response");

    }


    @Then("I should not see any response as the record has been deleted")
    public void iShouldNotSeeAnyResponseAsTheRecordHasBeenDeleted() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation of get sweepstake");
        Validations.assertEquals("null",RestResponse.getAsString(),"Validation of null response");
            }

    @Then("I should see the sweepstake entry details in the get entry by filter response")
    public void iShouldSeeTheSweepstakeEntryDetailsInTheGetEntryByFilterResponse() {
        DataLoad dataLoad=DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get Entry by filter");
        Validations.assertEquals(dataLoad.getParticipantKey(), RestResponse.getValue("data[0].participantKey"),"Validation of participant key in get Entry by filter response");
        Validations.assertEquals(dataLoad.getSweepstakeKey(), RestResponse.getValue("data[0].sweepstakeKey"),"Validation of sweepstake key in get Entry by filter response");
        Validations.assertEquals(dataLoad.getSweepstakeName(), RestResponse.getValue("data[0].sweepstakeName"),"Validation of sweepstake name in get Entry by filter response");
        dataLoad.setSweepstakeConfirmKey(RestResponse.getValue("data[0].confirmationKey").toString());
    }

    @Then("I should see the sweepstake entry details in the get entry response by participant key and sweepstake key")
    public void iShouldSeeTheSweepstakeEntryDetailsInTheGetEntryResponseByParticipantKeyAndSweepstakeKey() {
        DataLoad dataLoad=DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get Entry by filter");
        Validations.assertEquals(dataLoad.getParticipantKey(), RestResponse.getArrayResponseList().get(0).get("participantKey").toString(),"Validation of participant key in get Entry by participant key and sweepstake key response response");
        Validations.assertEquals(dataLoad.getSweepstakeKey(), RestResponse.getArrayResponseList().get(0).get("sweepstakeKey").toString(),"Validation of sweepstake key in get Entry by participant key and sweepstake key response response");
        Validations.assertEquals(dataLoad.getSetSweepstakeEntrySetKey(), RestResponse.getArrayResponseList().get(0).get("entrySetKey").toString(),"Validation of sweepstake name in get Entry by participant key and sweepstake key response response");
    }
}
