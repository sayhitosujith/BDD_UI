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

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

public class IntegrationSteps {
    @And("I send request for get all integrations")
    public void iSendRequestForGetAllIntegrations() {
        RestUtil.get(true);
    }

    @Then("I should see a list of integrations in response with status code as {int}")
    public void iShouldSeeAListOfIntegrationsInResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status code validation");
    }

    @And("I send request for creating an integration for newly created ward")
    public void ISendRequestForCreatingAnIntegrationForNewlyCreatedWard(io.cucumber.datatable.DataTable dataTable){
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEINTEGRATION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.post(pathparams,jsonData,true);
        dataLoad.setIntegrationName(dataMap.get("integrationName"));
    }

    @Then("I should see the integration created successfully with status code as {int}")
    public void iShouldSeeTheIntegrationCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation of create Integration");
        DataLoad.getInstance().setIntegrationKey(RestResponse.getAsString());
    }

    @And("I send request for get integration for ward")
    public void iSendRequestForGetIntegrationForWard(){
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the integration details in the response with status code as {int}")
    public void iShouldSeeTheIntegrationDetailsInTheResponseWithStatusCodeAs(int statusCode) throws InterruptedException {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation");
        //Validations.assertEquals(DataLoad.getInstance().getIntegrationName(),RestResponse.getValue("integrations[0].name").toString(),"Validation of Integration name is response");
    }

    @And("I send request for update integration for ward")
    public void iSendRequestForUpdateIntegrationForWard(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEINTEGRATION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());
        pathparams.put("integrationKey", dataLoad.getIntegrationKey());
        RestUtil.post(pathparams,jsonData,true);
        dataLoad.setIntegrationName(dataMap.get("integrationName"));
    }

    @Then("I should see the integration updated successfully response with status code as {int}")
    public void iShouldSeeTheIntegrationUpdatedSuccessfullyResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for update integration");
    }

    @And("I send request for delete integration for ward")
    public void iSendRequestForDeleteIntegrationForWard() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());
        pathparams.put("integrationKey", dataLoad.getIntegrationKey());
        RestUtil.delete(pathparams,true);
    }

    @Then("I should see the integration deleted successfully response with status code as {int}")
    public void iShouldSeeTheIntegrationDeletedSuccessfullyResponseWithStatusCodeAs(int statusCode) {
     Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code Validation");
    }

    @And("I send request for creating an integration for engagement")
    public void iSendRequestForCreatingAnIntegrationForEngagement(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEINTEGRATIONFORENGAGEMENT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("integrationKey",dataLoad.getIntegrationKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("engagementKey", dataLoad.getEngagementKey());
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request for get integration for engagement")
    public void iSendRequestForGetIntegrationForEngagement() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("engagementKey", dataLoad.getEngagementKey());
        RestUtil.get(pathparams,true);
    }

    @And("I send request for get engagement integration by type as {string}")
    public void iSendRequestForGetEngagementIntegrationByTypeAs(String integrationType) {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("engagementKey", dataLoad.getEngagementKey());
        pathparams.put("integrationType", integrationType);
        RestUtil.get(pathparams,true);
    }

    @And("I send request for update integration for engagement")
    public void iSendRequestForUpdateIntegrationForEngagement(io.cucumber.datatable.DataTable dataTable){
            GenericUtil genericUtil = new GenericUtil();
            DataLoad dataLoad = DataLoad.getInstance();
            String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEINTEGRATIONFORENGAGEMENT);
            Map<String,String> dataMap = StepUtil.toMap(dataTable);
            String jsonData = genericUtil.jsonConstruct(dataMap,payload);
            System.out.println(jsonData);
            HashMap<String, String> pathparams = new HashMap<String, String>();
            pathparams.put("engagementKey", dataLoad.getEngagementKey());
            pathparams.put("integrationKey", dataLoad.getIntegrationKey());
            RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request for delete integration for engagement")
    public void iSendRequestForDeleteIntegrationForEngagement() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("engagementKey", dataLoad.getEngagementKey());
        pathparams.put("integrationKey", dataLoad.getIntegrationKey());
        RestUtil.delete(pathparams,true);
    }

    @Then("I should see the integration updated successfully with status code as {int}")
    public void iShouldSeeTheIntegrationUpdatedSuccessfullyWithStatusCodeAs(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Status code validation for Updation");
    }

    @Then("I should see the integration deleted successfully with status code as {int}")
    public void iShouldSeeTheIntegrationDeletedSuccessfullyWithStatusCodeAs(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Status code validation for Updation");

    }
}
