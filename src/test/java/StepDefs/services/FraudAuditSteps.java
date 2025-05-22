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

public class FraudAuditSteps {
    @And("I send request for create engagement config with below details")
    public void ISendRequestForCreateEngagementConfigWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEENGAGEMENTCONFIG);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        dataMap.put("actionKey",dataLoad.getActionKey());
        dataLoad.setRuleKey(dataMap.get("ruleKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see engagement config is created successfully with status code as {int}")
    public void iShouldSeeEngagementConfigIsCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
//        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for Create Engagement config");
    }

    @And("I send request for update engagement config with below details")
    public void iSendRequestForUpdateEngagementConfigWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.UPDATEENGAGEMENTCONFIG);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("actionKey",dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
    }

    @Then("I should see engagement config is updated successfully with status code as {int}")
    public void iShouldSeeEngagementConfigIsUpdatedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for update engagement config");
    }

    @And("I send request for get engagement config")
    public void iSendRequestForGetEngagementConfig() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("engagementKey",DataLoad.getInstance().getEngagementKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see engagement config details with status code as {int}")
    public void iShouldSeeEngagementConfigDetailsWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for Get Engagement Config");
        Validations.assertEquals(dataLoad.getEngagementKey(),RestResponse.getValue("engagementKey").toString(),"Validation of engagement key in Get Engagement Config response");
        Validations.assertEquals(dataLoad.getActionKey(),RestResponse.getBody().jsonPath().getJsonObject("actionThresholds[0].actionKey").toString(),"Validation of action key in Get Engagement Config response");
        Validations.assertEquals(dataLoad.getRuleKey(),RestResponse.getBody().jsonPath().getJsonObject("suspiciousEventThresholds[0].ruleKey").toString(),"Validation of rule key in Get Engagement Config response");
    }

    @And("I send request for create violation")
    public void iSendRequestForCreateViolation(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEVIOLATION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        dataMap.put("wardKey",dataLoad.getWardKey());
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        dataMap.put("actionOccuranceKey",dataLoad.getActionKey());
        String [] violationDetails = {dataMap.get("actionOccuranceKey"),dataMap.get("category"),dataMap.get("fraudType"),dataLoad.getViolationKey()};
        dataLoad.setViolationDetails(violationDetails) ;
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see violation created successfully with status code as {int}")
    public void iShouldSeeViolationCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        //System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for Create Violation");
        DataLoad.getInstance().setViolationKey(RestResponse.getAsString());
    }

    @And("I send request for get violation by {string}")
    public void iSendRequestForGetViolationBy(String filter) {
        HashMap<String,String> pathparams = new HashMap<>();
        if(filter.equals("actionKey"))
            pathparams.put("actionKey",DataLoad.getInstance().getActionKey());
        if(filter.equals("participantKey"))
            pathparams.put("participantKey",DataLoad.getInstance().getParticipantKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see a list of violations filtered by {string} with status code as {int}")
    public void iShouldSeeAListOfViolationsByActionKeyWithStatusCodeAs(String filter, int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for Get Violations by actionKey");
        if(filter.equals("action key")){
            Validations.assertEquals(dataLoad.getViolationDetails()[0],RestResponse.getValue("actionOccurrenceKey").toString(),"Validation of actionOccuranceKey in Get Violations by "+filter+" response");
        }
        if(filter.equals("participant key")){
            Validations.assertEquals(dataLoad.getParticipantKey(),RestResponse.getValue("participantKey").toString(),"Validation of "+filter+" in Get Violations by "+filter+" response");
        }
        Validations.assertEquals(dataLoad.getViolationDetails()[1],RestResponse.getBody().jsonPath().getJsonObject("violations[0].fraudCategory.category").toString(),"Validation of fraud category in Get Violations by "+filter+" response");
        Validations.assertEquals(dataLoad.getViolationDetails()[2],RestResponse.getBody().jsonPath().getJsonObject("violations[0].fraudCategory.type").toString(),"Validation of fraud type in Get Violations by "+filter+" response");
        Validations.assertEquals(dataLoad.getViolationKey(),RestResponse.getBody().jsonPath().getJsonObject("violations[0].violationKey").toString(),"Validation of violation key in Get Violations by "+filter+" response");
    }

    @And("I send request for get manual fraud categories")
    public void iSendRequestForGetManualFraudCategories() {
        RestUtil.get(true);
    }

    @Then("I should see a list of manual fraud categories in response with status code as {int}")
    public void iShouldSeeAListOfManualFraudCategoriesInResponseWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for get manual categories response");
    }

    @Then("I should see blacklist address is disabled with status code as {int}")
    public void iShouldSeeBlacklistAddressIsDisabledWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for create blacklist IP address record");
    }

    @And("I send request for create blacklist {string}")
    public void iSendRequestForCreateBlacklist(String recordToBeBlacklisted,io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = "";
        if(recordToBeBlacklisted.equals("domain")) {
            payload = genericUtil.getFileData(Payloads.CREATEBLACKLISTDOMAINRECORD);
        }
        if(recordToBeBlacklisted.equals("address")) {
            payload = genericUtil.getFileData(Payloads.CREATEBLACKLISTADDRESSRECORD);
        }
        if(recordToBeBlacklisted.equals("ip")) {
            payload = genericUtil.getFileData(Payloads.CREATEBLACKLISTIPRECORD);
        }
        if(recordToBeBlacklisted.equals("phone")) {
            payload = genericUtil.getFileData(Payloads.CREATEBLACKLISTPHONERECORD);
        }
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        dataMap.put("wardKey",dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        if(recordToBeBlacklisted.equals("ip")) {
            dataLoad.setIpAddress(dataMap.get("IPaddress"));
        }
        if(recordToBeBlacklisted.equals("domain")) {
            dataLoad.setDomain(dataMap.get("domain"));
        }
        if(recordToBeBlacklisted.equals("phone")) {
            dataLoad.setPhone(dataMap.get("phone"));
        }
        RestUtil.post(jsonData,true);
    }

    @Then("I should see new blacklist {string} record is created successfully with status code as {int}")
    public void iShouldSeeNewBlacklistRecordIsCreatedSuccessfullyWithStatusCodeAs(String recordToBeBlacklist, int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for create blacklist "+recordToBeBlacklist+" record");
        Validations.assertEquals(true,!(RestResponse.getAsString().isEmpty()),"Response contains blacklist record key as \n"+RestResponse.getAsString());
    }

    @And("I send request for disable blacklist {string}")
    public void iSendRequestForDisableBlacklist(String recordToBeBlacklisted) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<>();
        Map<String,String> queryparams = new HashMap<>();
        queryparams.put("wardKey",dataLoad.getWardKey());
        queryparams.put("engagementKey",dataLoad.getEngagementKey());
        if(recordToBeBlacklisted.equals("ip")) {
            pathparams.put("ipAddress",dataLoad.getIpAddress());
            RestUtil.put(pathparams,queryparams,true);
        }
        if(recordToBeBlacklisted.equals("domain")) {
            pathparams.put("domain",dataLoad.getDomain());
            RestUtil.put(pathparams,queryparams,true);
        }
        if(recordToBeBlacklisted.equals("phone")) {
            pathparams.put("phone",dataLoad.getPhone());
            RestUtil.put(pathparams,queryparams,true);
        }
    }


    @Then("I should see blacklist {string} is disabled with status code as {int}")
    public void iShouldSeeBlacklistIsDisabledWithStatusCodeAs(String recordToBeBlacklist, int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for disable blacklist "+recordToBeBlacklist+" record");
    }

    @And("I send request for disable blacklist address")
    public void iSendRequestForDisableBlacklistAddress(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String, String> queryparams = new HashMap<>();
        queryparams.put("wardKey", dataLoad.getWardKey());
        queryparams.put("engagementKey", dataLoad.getEngagementKey());
        String payload = genericUtil.getFileData(Payloads.DISABLEBLACKLISTADDRESSRECORD);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.put(queryparams, jsonData, true);
    }

    @Then("I should see updated engagement config details with status code as {int}")
    public void iShouldSeeUpdatedEngagementConfigDetailsWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code Validation for Get Engagement Config");
        Validations.assertEquals(dataLoad.getActionKey(),RestResponse.getBody().jsonPath().getJsonObject("actionThresholds[0].actionKey").toString(),"Validation of updated action key in Get Engagement Config response");
    }
}
