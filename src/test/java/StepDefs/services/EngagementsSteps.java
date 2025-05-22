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

import javax.xml.crypto.Data;
import java.awt.dnd.DropTargetAdapter;
import java.util.*;


public class EngagementsSteps {

    public static boolean EngagementExists = false;

    @And("I send request for get all engagements")
    public void iSendRequestForGetAllEngagements() {
        RestUtil.get(true);
    }

    @Then("I should see a list of all engagements")
    public void iShouldSeeAListOfAllEngagements() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Validation of Status code for Get All Engagements");
        System.out.println("Total number of engagements displayed:"+RestResponse.getArrayResponseList().size());
    }

    @And("I send request for creating an engagement")
    public void iSendRequestForCreatingAnEngagement(io.cucumber.datatable.DataTable dataTable){
        DataLoad dataLoad = DataLoad.getInstance();
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.engagements.createEngagement"));
        if(!EngagementExists) {
            GenericUtil genericUtil = new GenericUtil();
            String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEENGAGEMENT);
            Map<String,String> dataMap = StepUtil.toMap(dataTable);
            dataMap.put("wardKey",dataLoad.getWardKey());
            dataMap.put("accountKey",dataLoad.getEngageAccountKey());
            dataMap.put("engagementKey",dataLoad.getEngagementKey());
            String jsonData = genericUtil.jsonConstruct(dataMap,payload);
            System.out.println(jsonData);
            RestUtil.post(jsonData, true);
            dataLoad.setEngagementKey(dataMap.get("engagementKey"));
            dataLoad.setEngagementName(dataMap.get("name"));
        }
        else{
            System.out.println("Engagement already found as:"+dataLoad.getEngagementKey());
        }
    }

    @Then("I should see the new engagement must be created successfully with status code as {int}")
    public void iShouldSeeTheNewEngagementMustBeCreatedSuccessfullyWithStatusCodeAs(int arg0) throws Exception {
        DataLoad dataLoad = DataLoad.getInstance();
        if(!EngagementExists) {
            Validations.assertEquals(204, RestResponse.getStatusCode(), "Validation of Status code for Create Engagement");
            System.out.println("Engagement created successfully with engagementKey as:" + DataLoad.getInstance().getEngagementKey());
        }
    }

    @And("I send request for get engagement with engagement key")
    public void iSendRequestForGetEngagementWithEngagementKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the engagement details in response with code {int}")
    public void iShouldSeeTheEngagementDetailsInResponseWithCode(int arg0) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of Get Engagement");
        Validations.assertEquals(dataLoad.getEngageAccountKey(),RestResponse.getValue("accountKey").toString(),"Validation of Account Key in the response");
        Validations.assertEquals(dataLoad.getWardKey(),RestResponse.getValue("wardKey").toString(),"Validation of Ward Key in the response");
    }

    @And("I send request for updating the engagement")
    public void iSendRequestForUpdatingTheEngagement(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEENGAGEMENT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.post(pathparams,jsonData,true);
        dataLoad.setEngagementName(dataMap.get("name"));
    }

    @And("I send a request to add ward attribute value to the engagement")
    public void iSendARequestToAddWardAttributeValueToTheEngagement(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDENGAGEMENTATTRIBUTE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("wardAttributeKeyValue", dataLoad.getAttributeValueKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        pathparams.put("attributeKey",dataLoad.getAttributeKey());
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see the enriched engagement details in response with code {int}")
    public void iShouldSeeTheEnrichedEngagementDetailsInResponseWithCode(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of status code for get  enriched engagement response");
        Map<String,String> map =    RestResponse.getBody().jsonPath().get();
        Validations.assertEquals(true,map.containsKey("enrichedProperties"),"Validation of enriched properties in response");
        System.out.println(RestResponse.getAsString());
    }

    @And("I send request for get salesforce metadata for engagementKey as {string}")
    public void iSendRequestForGetSalesforceMetadataForEngagementKeyAs(String arg0) {
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",arg0);
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the response for salesforce metadata with code {int}")
    public void iShouldSeeTheResponseForSalesforceMetadataWithCode(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of status code for get salesforce metadata");
    }

    @And("I send request for get engagement summaries with partial engagement key")
    public void iSendRequestForGetEngagementSummariesWithPartialEngagementKey() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getEngagementKey().substring(4));
        queryparams.put("partialEngagementKey",dataLoad.getEngagementKey().substring(5));
        RestUtil.get(queryparams,true);
    }

    @Then("I should see the engagement details containing partial engagement key in response with code {int}")
    public void iShouldSeeTheEngagementDetailsContainingPartialEngagementKeyInResponseWithCode(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of Status code");
//        Boolean containsProperty = false;
//        int i;
//        ArrayList<HashMap<String,String>> responselist= RestResponse.getBody().jsonPath().get();
//        for(i=0; i<responselist.size();i++){
//            if(responselist.get(i).containsValue(DataLoad.getInstance().getEngagementKey())){
//                containsProperty = true;
//            }
//        }
//        Validations.assertEquals(true,containsProperty,"Validation of engagement key in engagement summary response");
    }

    @And("I send request for get engagement with {string} filter")
    public void iSendRequestForGetEngagementWithFilter(String arg0) {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        if(arg0.equals("wardKey")){
            queryparams.put("wardKey",dataLoad.getWardKey());
        }
        if(arg0.equals("continuationToken")){
            queryparams.put("continuationToken",dataLoad.getContinuationToken());
        }
        else{
            queryparams.put("name",arg0);
        }
        RestUtil.get(queryparams,true);
    }

    @Then("I should see the engagement key in response with code {int}")
    public void iShouldSeeTheEngagementsWithGivenInResponseWithCode(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of Status code");
        Boolean containsProperty = false;
        int i;
        ArrayList<HashMap<String,String>> responselist= RestResponse.getBody().jsonPath().get("data");
        for(i=0; i<responselist.size();i++){
            if(responselist.get(i).containsValue(DataLoad.getInstance().getEngagementKey())){
                containsProperty = true;
            }
        }
        if(!EngagementExists)
        Validations.assertEquals(true,containsProperty,"Validation of engagementKey in response");

    }

    public boolean verifyEngagementExists(String engagementKey){
        DataLoad dataLoad = DataLoad.getInstance();
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.engagements.getEngagement"));
        HashMap<String,String> pathparams = new HashMap<String,String>();
        dataLoad.setEngagementKey(engagementKey);
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.get(pathparams, true);
        if(RestResponse.getAsString().equals("null")){
            EngagementExists = false;
        }
        else{
            EngagementExists = true;
        }
        return EngagementExists;
    }

    @And("I send request for create account in order to create engagement Key {string}")
    public void iSendRequestForCreateAccountInOrderToCreateEngagementKey(String engagementKey,io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        Random random = new Random();
        int randomGeneratedString = random.nextInt(9999);
        if(randomGeneratedString<1000){
            randomGeneratedString = randomGeneratedString+1000;
        }
        engagementKey = engagementKey.replaceAll("<rnum4>",randomGeneratedString+"");
        //engagementKey = "PL012408";
        //dataLoad.setEngagementKey(engagementKey);
        System.out.println("EngagementKey:"+engagementKey);
        boolean engagementKeyExists = verifyEngagementExists(engagementKey);
        if(!engagementKeyExists){
            EngageSteps eS = new EngageSteps();
            EngagementExists = false;
            RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.account.createAccount"));
            eS.iSendRequestForCreateAccountWithBelowDetails(dataTable);
        }
        else{
            dataLoad.setEngageAccountKey(RestResponse.getValue("accountKey").toString());
            dataLoad.setWardKey(RestResponse.getValue("wardKey").toString());
            System.out.println("EngagementKey already found as:" + DataLoad.getInstance().getEngagementKey());
            EngagementExists = true;
        }
       }


    @And("I send request for create ward with below details ward in order to create engagement")
    public void iSendRequestForCreateWardWithBelowDetailsWardInOrderToCreateEngagement(io.cucumber.datatable.DataTable dataTable) {
        WardSteps wS = new WardSteps();
        if(!EngagementExists){
            RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.ward.createWard"));
            wS.iSendRequestForCreateWardWithBelowDetailsWard(dataTable);
        }
        else{
            System.out.println("No new Ward created as EngagementKey already found as:" + DataLoad.getInstance().getEngagementKey());
        }
    }

    @And("I send request for creating an another engagement as {string}")
    public void iSendRequestForCreatingAnAnotherEngagement(String engagementKey,io.cucumber.datatable.DataTable dataTable) {
        Random random = new Random();
        int randomGeneratedString = random.nextInt(9999);
        if(randomGeneratedString<1000){
            randomGeneratedString = randomGeneratedString+1000;
        }
        engagementKey = engagementKey.replaceAll("<rnum4>",randomGeneratedString+"");
        System.out.println("EngagementKey:"+engagementKey);
        boolean engagementKeyExists = verifyEngagementExists(engagementKey);
        if(!engagementKeyExists){
            iSendRequestForCreatingAnEngagement(dataTable);
        }


    }

    @And("I send request for get engagement with {string} filter with value as {string}")
    public void iSendRequestForGetEngagementWithFilterWithValueAs(String arg0, String arg1) {
         Map<String,String> queryparams = new HashMap<String,String>();
         DataLoad dataLoad = DataLoad.getInstance();
         queryparams.put(arg0,arg1);
         RestUtil.get(queryparams,true);
    }

    @Then("I should see the continuation token in response with code {int}")
    public void iShouldSeeTheContinuationTokenInResponseWithCode(int arg0) {
        Map<String,String> responseMap = RestResponse.getBody().jsonPath().get();
        DataLoad.getInstance().setContinuationToken(responseMap.get("continuation"));
        //Validations.assertEquals(true,RestResponse.getArrayResponseList().get(0).containsKey("continuation"),"Validation of continuation token presence in response");
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of status code");
    }

    @And("I send request for get all timezones")
    public void iSendRequestForGetAllTimezones() {
        RestUtil.get(true);
    }


    @Then("I should see a list of all timezones in the response with status code as {int}")
    public void iShouldSeeAListOfAllTimezonesInTheResponseWithStatusCodeAs(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Validation of Status code in response");
        Map<String,String> responseMap = RestResponse.getBody().jsonPath().get();
        Validations.assertEquals(true,responseMap.containsKey("timeZones"),"Validation of timeZones key presence in response");
    }


    @And("I send a request to get engagement attribute and value")
    public void iSendARequestToGetEngagementAttributeAndValue() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.get(pathparams, true);
        dataLoad.setEngagementAttributeValueKey(RestResponse.getValue("attributesAndValues[0].engagementAttributeValueKey"));
    }

    @And("I send a request to delete engagement attribute")
    public void iSendARequestToDeleteEngagementAttribute(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_DELETEENGAGEMENTATTRIBUTE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementAttributeValueKey", dataLoad.getEngagementAttributeValueKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.delete(pathparams,jsonData, true);
    }

    @Then("I should see the engagement details in response")
    public void iShouldSeeTheEngagementDetailsInResponse() {
       Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get engagement response");
       Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("engagementKey").toString(),"engagementKey present in get engagement response");
       Validations.assertEquals(true,RestResponse.getAsString().contains("wardKey"),"wardKey present in get engagement response");
       Validations.assertEquals(true,RestResponse.getAsString().contains("name"),"name property present in get engagement response");
       Validations.assertEquals(true,RestResponse.getAsString().contains("accountKey"),"accountKey property present in get engagement response");
       Validations.assertEquals(true,RestResponse.getAsString().contains("features"),"features list present in get engagement response");
    }

    @And("I send request for get engagement with engagement key as {string}")
    public void iSendRequestForGetEngagementWithEngagementKeyAs(String engagementKey) {
        DataLoad.getInstance().setEngagementKey(engagementKey);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",engagementKey);
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the engagement phases details in response")
    public void iShouldSeeTheEngagementPhasesDetailsInResponse() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get engagement response");
        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getArrayResponseList().get(0).get("engagementKey").toString(),"engagementKey present in get engagement phases response");
        Validations.assertEquals(true,RestResponse.getAsString().contains("phaseKey"),"phaseKey present in get engagement phases response");
        DataLoad.getInstance().setPhaseKey(RestResponse.getArrayResponseList().get(0).get("phaseKey").toString());
        Validations.assertEquals(true,RestResponse.getAsString().contains("name"),"name property present in get engagement phases response");
        Validations.assertEquals("true",RestResponse.getArrayResponseList().get(0).get("isActive").toString(),"isActive present in get engagement phases response");
    }

    @And("I send request for get engagement phase by phase key")
    public void iSendRequestForGetEngagementPhaseByPhaseKey() {
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("phaseKey",DataLoad.getInstance().getPhaseKey());
        RestUtil.get(pathparams,true);
    }

    @And("I send request for creating an engagement with phase")
    public void iSendRequestForCreatingAnEngagementWithPhase(io.cucumber.datatable.DataTable dataTable){
        DataLoad dataLoad = DataLoad.getInstance();
        if(!EngagementExists) {
            RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.engagements.createEngagement"));
            GenericUtil genericUtil = new GenericUtil();
            String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEENGAGEMENTWITHPHASE);
            Map<String,String> dataMap = StepUtil.toMap(dataTable);
            dataMap.put("wardKey",dataLoad.getWardKey());
            dataMap.put("accountKey",dataLoad.getEngageAccountKey());
            dataMap.put("engagementKey",dataLoad.getEngagementKey());
            String jsonData = genericUtil.jsonConstruct(dataMap,payload);
            System.out.println(jsonData);
            RestUtil.post(jsonData, true);
            dataLoad.setEngagementKey(dataMap.get("engagementKey"));
            dataLoad.setEngagementName(dataMap.get("name"));
            dataLoad.setPhaseName(dataMap.get("phaseName"));
        }
        else{
            System.out.println("Engagement("+dataLoad.getEngagementKey()+") already found so only updating it by adding a phase");
            GenericUtil genericUtil = new GenericUtil();
            String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEENGAGEMENTWITHPHASE);
            Map<String,String> dataMap = StepUtil.toMap(dataTable);
            dataLoad.setPhaseName(dataMap.get("phaseName"));
            String jsonData = genericUtil.jsonConstruct(dataMap,payload);
            System.out.println(jsonData);
            RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.engagements.updateEngagement"));
            HashMap<String,String> pathparams = new HashMap<String,String>();
            pathparams.put("engagementKey",dataLoad.getEngagementKey());
            RestUtil.post(pathparams,jsonData, true);
        }
    }

    @Then("I should see the engagement phases details in response for the given phase Key")
    public void iShouldSeeTheEngagementPhasesDetailsInResponseForTheGivenPhaseKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get engagement phase by key");
        Validations.assertEquals(dataLoad.getEngagementKey(), RestResponse.getValue("engagementKey").toString(),"engagementKey present in get engagement phase by key response");
        Validations.assertEquals(dataLoad.getPhaseKey(), RestResponse.getValue("phaseKey").toString(),"phaseKey present in get engagement phase by key response");
        Validations.assertEquals("true",RestResponse.getValue("isActive").toString(),"isActive present in get engagement phase by key response");
    }

    @And("I send request for get engagement phases with engagement key")
    public void iSendRequestForGetEngagementPhasesWithEngagementKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.get(pathparams,true);
    }
}

