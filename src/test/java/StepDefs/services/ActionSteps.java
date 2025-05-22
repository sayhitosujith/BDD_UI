package StepDefs.services;

import configManager.DataLoad;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import util.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ActionSteps {

    @And("I am creating action token with below details")
    public void iAmCreatingActionTokenWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception{
        DataLoad dataLoad = DataLoad.getInstance();
        String actionToken =  StepUtil.generateActionToken(dataLoad.getEngagementKey(),dataLoad.getWardKey(),dataTable);
        dataLoad.setActionToken(actionToken);
        System.out.println(dataLoad.getActionKey());
    }

    @And("I send request for create action with below details")
    public void iSendRequestForCreateActionWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        String wardK = hashMap.get("wardKey").toString();
        dataMap.put("wardkey", hashMap.get("wardKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Action");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), ResourceData.getEndPoint("services.action.createAction"));
        RestUtil.post(jsonData, true);
        HashMap actionElements = new HashMap<String, String>();
        actionElements.put("name", dataMap.get("name"));
        actionElements.put("description", dataMap.get("description"));
        actionElements.put("wardkey", wardK);
        actionElements.put("engagementKey", dataMap.get("engagementKey"));
        actionElements.put("tag1", dataMap.get("1tag"));
        actionElements.put("tag2", dataMap.get("2tag"));
        actionElements.put("actionKey", RestResponse.getAsString());
        //Pre-storing action elements
        DataLoad.getInstance().setActionElements(actionElements);

    }

    @And("I send get request for get action")
    public void isendgetrequestforgetaction() throws Exception {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        //  pathparams.put("flowKey", DataLoad.getInstance().getActionElements("flowKey"));
        RestUtil.get(pathparams, true);
    }

    @Then("I should see action created successfully")
    public void iShouldSeeNewlyCreatedActionInTheResponse() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code Validation for Action key is Successful");
        String ActionKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setActionKey(ActionKey);
    }

    @Then("I should see newly created action details in response")
    public void iShouldSeeNewlyCreatedActionDetailsInResponse() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation for Get Survey");
        DataLoad data = DataLoad.getInstance();
        Validations.assertEquals(data.getActionElements("name"), RestResponse.getValue("name"), "Validated action name in response");
        Validations.assertEquals(data.getActionElements("actionKey"), RestResponse.getValue("actionKey"), "Validated action key in response");
        Validations.assertEquals(data.getActionElements("description"), RestResponse.getValue("description"), "Validated action description in response");
    }

    @And("I send request add flow to action using action key")
    public void iSendRequestAddFlowToActionUsingActionKey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ADDFLOWTO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey", dataLoad.getActionKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("actionKey" + dataLoad.getActionKey());
        pathparams.put("actionKey", dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);

    }

    @Then("I should see flow to action created successfully")
    public void iShouldSeeFlowToActionCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code see flow to action created successfully");

    }

    @And("I send request add timeframe to action using action key")
    public void iSendRequestAddTimeframeToActionUsingActionKey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ADDTIMEFRAMETO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey", dataLoad.getActionKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("actionKey" + dataLoad.getActionKey());
        pathparams.put("actionKey", dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see timeframe to timeframe created successfully")
    public void iShouldSeeTimeframeToActionCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code see timeframe to action created successfully");
    }

    @And("I send request add constraints to action using action key")
    public void iSendRequestAddConstraintsToActionUsingActionKey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ADDCONSTRAINTTO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey", dataLoad.getActionKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("actionKey" + dataLoad.getActionKey());
        pathparams.put("actionKey", dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see constraints to action created successfully")
    public void iShouldSeeConstraintsToActionCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code see constraints to action created successfully");
    }

    @And("I send request add limit to action using action key")
    public void iSendRequestAddLimitToActionUsingActionKey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ADDLIMITTO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey", dataLoad.getActionKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("actionKey" + dataLoad.getActionKey());
        pathparams.put("actionKey", dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see limit adjustment added  successfully")
    public void iShouldSeeLimitAdjustmentAddedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code see limit adjustment added  successfully");
    }

    @And("I send request remove flow to action using action key")
    public void iSendRequestRemoveFlowToActionUsingActionKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        pathparams.put("flowKey", dataLoad.getFlowKey());
        RestUtil.delete(pathparams, true);
    }

    @And("I send a get request for get action to capture flowkey")
    public void iSendAGetRequestForGetActionToCaptureFlowkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        RestUtil.get(pathparams, true);
        List<HashMap<String, String>> flowDetailsInActionResponse = RestResponse.getList("flows");
        dataLoad.setFlowKey(flowDetailsInActionResponse.get(0).get("flowKey"));

    }

    @Then("I should see flow to action removed successfully")
    public void iShouldSeeFlowToActionRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification to see flow to action removed successfully");

    }

    @Then("is should see flowkey removed successfully")
    public void isShouldSeeFlowkeyRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification see flowkey removed successfully");
    }

    @And("I send a get request for get response to capture flowkey")
    public void iSendAGetRequestForGetResponseToCaptureFlowkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        RestUtil.get(pathparams, true);

    }

    @And("I send request remove timeframe to action using action key")
    public void iSendRequestRemoveTimeframeToActionUsingActionKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        pathparams.put("timeframeKey", dataLoad.getTimeframeKey());
        RestUtil.delete(pathparams, true);

    }

    @Then("I should see timeframe removed successfully")
    public void iShouldSeeTimeframeRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification to see timeframe removed successfully");
    }

    @Then("I should see timeframe to action removed successfully")
    public void iShouldSeeTimeframeToActionRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification to see timeframe to action removed successfully");

    }

    @And("I send a get request for get timeframe to capture flowkey")
    public void iSendAGetRequestForGetTimeframeToCaptureFlowkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        RestUtil.get(pathparams, true);
//        List<HashMap<String,String>> flowDetailsInActionResponse= RestResponse.getList("flows");
//        dataLoad.setFlowKey(flowDetailsInActionResponse.get(0).get("flowKey"));

    }

    @And("I send a get request for get response to capture Timeframe")
    public void iSendAGetRequestForGetResponseToCaptureTimeframe() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        // pathparams.put("timeframeKey", dataLoad.getTimeframeKey());
        RestUtil.get(pathparams, true);

    }

    @And("I send request remove constraints to action using action key")
    public void iSendRequestRemoveConstraintsToActionUsingActionKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        pathparams.put("ConstraintKey", dataLoad.getConstraintKey());
        RestUtil.delete(pathparams, true);
    }

    @Then("I should see constraints to action removed successfully")
    public void iShouldSeeConstraintsToActionRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification see constraints to action removed successfully");
    }

    @And("I send a get request for get response to capture constraints")
    public void iSendAGetRequestForGetResponseToCaptureConstraints() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", dataLoad.getActionKey());
        // pathparams.put("timeframeKey", dataLoad.getTimeframeKey());
        RestUtil.get(pathparams, true);
    }

    @Then("is should see constraints removed successfully")
    public void isShouldSeeConstraintsRemovedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification see flowkey removed successfully");
    }

    @And("I send request add OccuranceStep to action")
    public void iSendRequestAddOccuranceStepToAction(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ADDAVAILIBITYOFOCCURANCES);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("occurrenceKey", dataLoad.getOccurrenceKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("occurrenceKey", dataLoad.getOccurrenceKey());
        System.out.println("occurrenceKey" + dataLoad.getOccurrenceKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see OccuranceStep created successfully")
    public void iShouldSeeOccuranceStepCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code to see OccuranceStep created successfully");
    }

    @And("I send a put request to update created action with below update details")
    public void isendputrequestforupdatingaction(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        DataLoad data = DataLoad.getInstance();
        dataMap.put("engagementKey", data.getActionElements("engagementKey"));
        dataMap.put("wardkey", data.getActionElements("wardkey"));
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("Update action payload " + jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), ResourceData.getEndPoint("services.action.updateAction"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", data.getActionElements("actionKey"));
        RestUtil.put(pathparams, jsonData, true);

        //Pre-storing update details
        HashMap updatedElements = new HashMap<String, String>();
        updatedElements.put("name", dataMap.get("name"));
        updatedElements.put("description", dataMap.get("description"));
        updatedElements.put("tag1", dataMap.get("1tag"));
        updatedElements.put("tag2", dataMap.get("2tag"));
        updatedElements.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        DataLoad.getInstance().setActionElements(updatedElements);
    }

    @Then("I should see updated action details in response")
    public void iShouldSeeUpdatedActionDetailsInResponse() {

        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for get action");
        DataLoad data = DataLoad.getInstance();
        Validations.assertEquals(data.getActionElements("name"), RestResponse.getValue("name"), "Validated updated action name in response");
        Validations.assertEquals(data.getActionElements("description"), RestResponse.getValue("description"), "Validated updated action description in response");
        List<String> Updatedtags = RestResponse.getResponseList("tags");
        Validations.assertEquals(data.getActionElements("tag1"), Updatedtags.get(0), "Validated updated action tag1 in response");
        Validations.assertEquals(data.getActionElements("tag2"), Updatedtags.get(1), "Validated updated action tag2 in response");
        Validations.assertEquals(data.getActionElements("actionKey"), RestResponse.getValue("actionKey"), "Validated actionKey in response");

    }


    @Then("I should see action updated successfully")
    public void iShouldSeeActionUpdatedAndStatusCodeInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for Update Action is Successful");
    }

    @And("I send a request to add prerequisite to action with below details")
    public void iSendARequestToAddPrerequisiteToActionWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ADD_REQUISITE_TO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), ResourceData.getEndPoint("services.action.addPrerequisite"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        RestUtil.post(pathparams, jsonData, true);
        DataLoad Prerequisite = DataLoad.getInstance();
        HashMap<String, String> prerequisiteDetails = new HashMap<String, String>();
        prerequisiteDetails.put("actionKey", dataMap.get("actionKey"));
        prerequisiteDetails.put("quantity", dataMap.get("quantity"));
        prerequisiteDetails.put("period", dataMap.get("period"));
        prerequisiteDetails.put("mustBeSatisfied ", dataMap.get("mustBeSatisfied"));
        Prerequisite.setActionElements(prerequisiteDetails);

    }

    @Then("I should see successfully prerequisite is added to the action")
    public void iShouldSeeSuccessfullyPrerequisiteIsAddedToTheAction() {

        Validations.assertEquals(201, RestResponse.getStatusCode(), "Prerequisite added to the action and Status Code Validated for same");
    }

    @Then("I should see prerequisite details under prerequisites section in action response")
    public void iShouldSeePrerequisiteDetailsUnderPrerequisitesSectionInActionResponse() {

        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for get Action is Successful");
        DataLoad data = DataLoad.getInstance();
        List<HashMap<String, String>> prerequisitesDetailsInActionResponse = RestResponse.getList("prerequisites");
        Validations.assertTrue(!(prerequisitesDetailsInActionResponse.get(0).get("prerequisiteKey") == null), "Validated prerequisite key under prerequisite section in action response");
        Validations.assertEquals(data.getActionElements("actionKey"), prerequisitesDetailsInActionResponse.get(0).get("actionKey"), "Validated actionKey in prerequisite section in action response");
        Validations.assertEquals(data.getActionElements("quantity"), String.valueOf(prerequisitesDetailsInActionResponse.get(0).get("quantity")), "Validated quantity in prerequisite section in action response");
        Validations.assertEquals(data.getActionElements("period"), prerequisitesDetailsInActionResponse.get(0).get("period"), "Validated period in prerequisite section in action response");
        data.setPrerequisiteKey(prerequisitesDetailsInActionResponse.get(0).get("prerequisiteKey"));
    }

    @And("I send a request to remove added prerequisite from action")
    public void iSendARequestToRemoveAddedPrerequisiteFromAction() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        pathparams.put("prerequisiteKey", DataLoad.getInstance().getPrerequisiteKey());
        RestUtil.delete(pathparams, true);
    }

    @Then("I should see prerequisite is removed successfully from action")
    public void iShouldSeePrerequisiteIsRemovedSuccessfullyFromAction() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Prerequisite is removed successfully and Status Code is Validated for the same");
    }

    @Then("I should see prerequisite details removed under prerequisites section in action response")
    public void iShouldSeePrerequisiteDetailsRemovedUnderPrerequisitesSectionInActionResponse() {
        boolean emptyPrerequisites = RestResponse.getResponseList("prerequisites").isEmpty();
        Validations.assertTrue(emptyPrerequisites, "successfully prerequisite details removed under prerequisites section in action response");
    }

    @And("I send request to add limit to the action")
    public void iSendRequestToAddLimitToTheAction(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ADD_LIMIT_TO_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), ResourceData.getEndPoint("services.action.addLimit"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        RestUtil.post(pathparams, jsonData, true);
        DataLoad data = DataLoad.getInstance();
        HashMap<String, String> limit = new HashMap<String, String>();
        limit.put("appliesTo", dataMap.get("appliesTo"));
        limit.put("demographicKey", dataMap.get("demographicKey"));
        limit.put("demographicKey", dataMap.get("demographicKey"));
        limit.put("actionKey",DataLoad.getInstance().getActionElements("actionKey"));
        data.setActionElements(limit);

    }
    @Then("I should see limits details added to the action")
    public void iShouldSeeLimitsDetailsAddedToTheAction() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for get Action is Successful");
        DataLoad data = DataLoad.getInstance();
        List<HashMap<String, String>> limits = RestResponse.getList("limits");
        Validations.assertTrue(!(limits.get(0).get("limitKey") == null), "Validated limitKey under limits section in action response");
        Validations.assertEquals(data.getActionElements("appliesTo"), limits.get(0).get("appliesTo"), "Validated appliesTo under limits section in action response");
        Validations.assertEquals(data.getActionElements("demographicKey"), limits.get(0).get("demographicKey"), "Validated demographicKey under limit section in action response");
        data.setLimitKey(limits.get(0).get("limitKey"));
    }


    @Then("I should see successfully limit is added to the action")
    public void iShouldSeeSuccessfullyLimitIsAddedToTheAction() {

        Validations.assertEquals(201, RestResponse.getStatusCode(), "limit added successfully and Status Code is Validated for the same");
    }


    @And("I send a request to remove added limits from action")
    public void iSendARequestToRemoveAddedLimitsFromAction() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionElements("actionKey"));
        pathparams.put("limitKey", DataLoad.getInstance().getLimitKey());
        RestUtil.delete(pathparams, true);

    }

    @Then("I should see limit is removed successfully from action")
    public void iShouldSeeLimitIsRemovedSuccessfullyFromAction() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "limit is removed successfully and Status Code is Validated for the same");
    }

    @Then("I should see limit details removed under limits section in action response")
    public void iShouldSeeLimitDetailsRemovedUnderLimitsSectionInActionResponse() {
        boolean emptylimits = RestResponse.getResponseList("limits").isEmpty();
        Validations.assertTrue(emptylimits, "successfully limit is removed under limits section in action response");
    }



    @And("I send request for get available limit of occurances")
    public void iSendRequestForGetAvailableLimitOfOccurances() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("actionKey", DataLoad.getInstance().getActionKey());
        pathparams.put("participantKey", DataLoad.getInstance().getParticipantKey());
        Map<String, String> queryparams = new HashMap<String, String>();
        queryparams.put("period", "AllTime");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        //httpRequest.header("x-pl-actionToken","action "+dataLoad.getActionToken());
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.pathParams(pathparams);
        httpRequest.queryParams(queryparams);
        RestResponse.initialize(httpRequest.get());

        //RestUtil.get(pathparams,queryparams, true);
    }

    @Then("I should see action token created successfully")
    public void iShouldSeeActionTokenCreatedSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for action token creation");
    }

    @And("I send request for create action with existing engage details")
    public void iSendRequestForCreateActionWithWithExistingEngageDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_ACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("wardkey", dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        RestResponse.initialize(httpRequest.post());
        //RestUtil.post(jsonData,true);
    }
}
