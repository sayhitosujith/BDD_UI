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



public class WardSteps {
    @Then("I send request for create ward with below details ward")
    public void iSendRequestForCreateWardWithBelowDetailsWard(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEWARD);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        String wardKey = dataMap.get("wardKey");
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setWardKey(wardKey);
    }

    @Then("I should see the ward created successfully status code as {int}")
    public void iShouldSeeTheResponseWithStatusCodeAs(int arg0) {
        if(!EngagementsSteps.EngagementExists) {
            System.out.println("Ward created successfully with Wardkey:"+DataLoad.getInstance().getWardKey());
            Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code Verification of Account Creation");
        }
    }

    @And("I send a request to get ward by search with newly created ward")
    public void iSendARequestToGetWardBySearchWithNewlyCreatedWard(){
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        System.out.println("Wardkey" +dataLoad.getWardKey());
        queryparams.put("search",dataLoad.getWardKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see newly created ward details in the response")
    public void iShouldSeeNewlyCreatedWardDetailsInTheResponse() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get ward by search");
        Validations.assertEquals(dataLoad.getWardKey(),RestResponse.getValue("data[0].wardKey"),"Wardkey validation in the response");
    }

    @And("I send request for get all wards")
    public void iSendRequestForGetAllWards() {
        RestUtil.get(true);
    }

    @Then("I should see the response with a list of all wards")
    public void iShouldSeeTheResponseWithAListOfAllWards() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get");
    }

    @And("I send a request to update ward")
    public void iSendARequestToUpdateWard(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEWARD);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("wardKey", dataLoad.getWardKey());
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("wardKey",dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        dataLoad.setWardDescription(dataMap.get("description"));
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send a request to Create Ward Attribute")
    public void i_send_a_request_to_Create_Ward_Attribute(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEWARDATTRIBUTE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardkey", dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }
    @Then("I should see the response with updated ward details")
    public void iShouldSeeTheResponseWithUpdatedWardDetails() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code verification for Update Ward");

    }
    @Then("I should see the response to Create Attribute to ward details")
    public void i_should_see_the_response_to_Create_Attribute_to_ward_details() {
        String attributeKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println("Attribute Key :"+attributeKey);
        dataLoad.setAttributeKey(attributeKey);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for Create Ward Attribute");
    }





    @And("I send a request to Create Ward Attribute Value")
    public void iSendARequestToCreateWardAttributeValue(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEWARDATTRIBUTE_VALUE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributeKey", dataLoad.getAttributeKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);

    }

   @Then("I should see the attribute value key is created with status code as {int}")
    public void iShouldSeeTheAttributeValueKeyIsCreated(int arg0) {
        String attributeValueKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setAttributeValueKey(attributeValueKey);
        Validations.assertEquals(arg0, RestResponse.getStatusCode(),"Status Code verification for Create ward attribute value");
    }


    @Then("I should see updated details in response with status code as {int}")
    public void iShouldSeeUpdatedDetailsInResponseWithStatusCodeAs(int arg0) {
        Validations.assertEquals(arg0,RestResponse.getStatusCode(),"Status code validation");
        Validations.assertEquals(DataLoad.getInstance().getWardDescription(),RestResponse.getValue("data[0].description"),"Validation of Updated description");

    }

    @And("I send a request for get ward Attribute")
    public void iSendARequestForGetWardAttribute() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributekey", dataLoad.getAttributeKey());
        RestUtil.get(pathparams,true);
    }

    @And("I send a request for delete ward Attribute")
    public void iSendARequestForDeleteWardAttribute() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributekey", dataLoad.getAttributeKey());
        RestUtil.get(pathparams,true);

    }

    @Then("I should see the ward attribute value deleted successfully status code as {int}")
    public void iShouldSeeTheWardattributeValueDeletedSuccessfullyStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status Code verification for delete ward attribute");
    }

    @And("I send a request for delete ward Attribute Value")
    public void iSendARequestForDeleteWardAttributeValue() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributekey", dataLoad.getAttributeKey());
        pathparams.put("attributeValueKey", dataLoad.getAttributeValueKey());
        RestUtil.delete(pathparams,true);
      }

    @Then("I should see the ward attribute deleted successfully status code as {int}")
    public void iShouldSeeTheWardAttributeDeletedSuccessfullyStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status Code verification for delete ward attribute");
    }

    @And("I send a request to Update Ward Attribute")
    public void iSendARequestToUpdateWardAttribute(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEWARDATTRIBUTE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributekey", dataLoad.getAttributeKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.put(pathparams, jsonData, true);
    }

    @Then("I should see the response to Update Attribute to ward details")
    public void iShouldSeeTheResponseToUpdateAttributeToWardDetails() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code verification for Updateward");
    }

    @And("I send a request for get ward Attributes")
    public void iSendARequestForGetWardAttributes() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.get(pathparams,true);
    }

    @And("I send a request to Update Ward Attribute Value")
    public void iSendARequestToUpdateWardAttributeValue(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEWARDATTRIBUTE_VALUE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("attributekey", dataLoad.getAttributeKey());
        pathparams.put("attributeValueKey", dataLoad.getAttributeValueKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.put(pathparams, jsonData, true);
    }
}
