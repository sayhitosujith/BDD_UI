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

import java.util.HashMap;
import java.util.Map;

public class OfferingSteps {


    @And("I send request for create offering with below details")
    public void iSendRequestForCreateOfferingWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATEOFFERING);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("wardKey", dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);

    }

    @Then("I should see the offering created successfully")
    public void iShouldSeeTheOfferingCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status code validation for offering creation");
        DataLoad dataLoad = DataLoad.getInstance();
        String offeringKey =RestResponse.getAsString();
        dataLoad.setOfferingKey(offeringKey);
        System.out.println("OfferingKey: "+dataLoad.getOfferingKey());
    }

    @And("I am getting the trusted Identifiers token for the {string} sub scope")
    public void iAmGettingTheTrustedIdentifiersTokenForTheSubScope(String scope) {
        StepUtil.generateEngageTrustedIdentifierToken(scope);
    }


    @And("I send request for get Offerings")
    public void iSendRequestForGetOfferings() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("OfferingKey", dataLoad.getOfferingKey());
        //pathParams.put("wardKey",dataLoad.getWardKey());
        RestUtil.get(pathParams, true);

    }

    @Then("I should see the response with a list of Offerings")
    public void iShouldSeeTheResponseWithAListOfOfferings() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for Get response with a list of all Slim Participant ");
        Validations.assertEquals("62.5",RestResponse.getValue("maxArv").toString(),"Validation of maxArv");
        //Validations.assertEquals("true",RestResponse.getValue("isArvCalculated").toString(),"Validation to see the response of offering is ArvCalculated");
        Validations.assertEquals("false",RestResponse.getValue("isCombinable").toString(),"Validation to see the response of offering is isCombinable");
        Validations.assertEquals("135.0",RestResponse.getValue("splitArv").toString(),"Validation of splitArv");
        Validations.assertEquals("true",RestResponse.getValue("isActive").toString(),"Validation to see the response of offering is ArvCalculated");
        Validations.assertEquals("Digital",RestResponse.getValue("deliveryType").toString(),"Validation of deliveryType");
        Validations.assertEquals("true",RestResponse.getValue("isOnDemand").toString(),"Validation to see the response of offering is OnDemand");
        Validations.assertEquals("false",RestResponse.getValue("isCustomerCare").toString(),"Validation to see the response of offering is isCustomerCare");
    }


    @And("I send request for get Offerings using ShippingKey")
    public void iSendRequestForGetOfferingsUsingShippingKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("OfferingShippingKey", dataLoad.getOfferingShippingKey());
        //pathParams.put("wardKey",dataLoad.getWardKey());
        RestUtil.get(pathParams, true);
    }

    @And("I send request for delete Offerings using ShippingKey")
    public void iSendRequestForDeleteOfferingsUsingShippingKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("OfferingShippingKey", dataLoad.getOfferingShippingKey());
        RestUtil.get(pathparams, true);


    }

    @Then("I should see the offering deleted Successfully")
    public void iShouldSeeTheOfferingDeletedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for see Attribute Deleted successfully");
    }

    @And("I send request for Get Offering Shipping by offering")
    public void iSendRequestForGetOfferingShippingByOffering() {


        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("OfferingKey", dataLoad.getOfferingKey());
        RestUtil.get(pathparams, true);
    }

    @Then("I should see the response with a list of Offerings Shipping by offering")
    public void iShouldSeeTheResponseWithAListOfOfferingsShippingByOffering() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for see the response with a list of Offerings Shipping by offering");
        //Validations.assertEquals(true,RestResponse.getAsString().contains(DataLoad.getInstance().getOfferingKey()[0]),"Validation of OfferingKey in offering key by ward as filter");
    }

    @And("I send request for Create Offerings Shipping")
    public void iSendRequestForCreateOfferingsShipping(io.cucumber.datatable.DataTable dataTable) {

        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATEOFFERINGSHIPPING);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("OfferingKey",dataLoad.getOfferingKey());
        dataMap.put("shippingMethodKey",dataLoad.getShippingMethodKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.print(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see the Offerings Shipping created successfully")
    public void iShouldSeeTheOfferingsShippingCreatedSuccessfully() {
        System.out.println("Offering Shipping key:"+RestResponse.getAsString());
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status code for Offerings Shipping created successfully");
    }

    @And("I send request for Update Offerings Shipping")
    public void iSendRequestForUpdateOfferingsShipping(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATEOFFERINGSHIPPING);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        Map<String, String> queryparams = new HashMap<String, String>();
        queryparams.put("OfferingKey", dataLoad.getOfferingKey());
        queryparams.put("shippingMethodKey", dataLoad.getShippingMethodKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("OfferingKey", dataLoad.getOfferingKey());
        pathParams.put("shippingMethodKey", dataLoad.getShippingMethodKey());
        System.out.println("OfferingKey -" + dataLoad.getOfferingKey());
        System.out.println("shippingMethodKey -" + dataLoad.getShippingMethodKey());
        System.out.print(jsonData);
    }

    @Then("I should see the Offerings Shipping Updated successfully")
    public void iShouldSeeTheOfferingsShippingUpdatedSuccessfully() {
        {
            System.out.println(RestResponse.getAsString());
            System.out.println(RestResponse.getStatusCode());
            Validations.assertEquals(201, RestResponse.getStatusCode(), "Status code the Offerings Shipping Updated successfully");
        }
    }

    @And("I send request for get Offering Claim Status")
    public void iSendRequestForGetOfferingClaimStatus(io.cucumber.datatable.DataTable dataTable) {
            GenericUtil genericUtil = new GenericUtil();
            DataLoad dataLoad = DataLoad.getInstance();
            String payload = genericUtil.getFileData(Payloads.OFFERING_GETOFFERINGCLAIMSTATUS);
            Map<String, String> dataMap = StepUtil.toMap(dataTable);
            String jsonData = genericUtil.jsonConstruct(dataMap, payload);
            System.out.println(jsonData);
            RestUtil.post(jsonData, true);
        }


    @Then("I should get Offering Claim Status")
    public void iShouldGetOfferingClaimStatus() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for Get Offering Claim Status");
        System.out.println(RestResponse.getAsString());
    }

    @And("I send request for delete Offerings using offeringkey")
    public void iSendRequestForDeleteOfferingsUsingOfferingkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
//      pathparams.put("attributekey", dataLoad.getAttributeKey());
      pathparams.put("OfferingKey", dataLoad.getOfferingKey());
        RestUtil.get(pathparams, true);

    }

    @Then("I should see the offering deleted with offeringkey Successfully")
    public void iShouldSeeTheOfferingDeletedWithOfferingkeySuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for see Attribute Deleted successfully");
    }

    @And("I send request for get All Shipping Carriers")
    public void iSendRequestForGetAllShippingCarriers() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        RestUtil.get(pathParams, true);

    }

    @Then("I should see the response with a list of all Shippinig Carriers")
    public void iShouldSeeTheResponseWithAListOfAllShippingCarriers() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for Get response with a list of all Slim Participant ");

    }


    @And("I send request for Create Shipping carrer with below details")
    public void IsendrequestforCreateShippingcarrerwithbelowdetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATESHIPPINGCARRER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see Shipping carrier created successfully")
    public void iShouldSeeShippingCarrerCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code Validation for Participant Creation");
        String shippingCarrierKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setshippingCarrierKey(shippingCarrierKey);

    }

    @And("I send request for updateShippingCarrier with below details")
    public void iSendRequestForUpdateShippingCarrierWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_UPDATESHIPPINGCARRER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("shippingCarrierKey" + dataLoad.getshippingCarrierKey());
        pathparams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.put(pathparams, jsonData, true);

    }

    @Then("I should see ShippingCarrier is Updated Successfully")
    public void iShouldSeeShippingCarrierIsUpdatedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code verification for see ShippingCarrier is Updated Successfully");
    }

    @And("I send request for get Shipping Carrier using shippingCarrierKey")
    public void iSendRequestForGetShippingCarrierUsingShippingCarrierKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        RestUtil.get(pathParams, true);


    }

    @Then("I should see the response with a list of all Shippinig Carriers using shippingCarrierKey")
    public void iShouldSeeTheResponseWithAListOfAllShippinigCarriersUsingShippingCarrierKey() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code see the response with a list of all Shippinig Carriers using shippingCarrierKey");

    }

    @And("I send request to deleteShippingCarrier")
    public void iSendRequestToDeleteShippingCarrier() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        RestUtil.delete(pathparams, true);

    }


    @Then("I should see ShippingCarrier Deleted successfully")
    public void iShouldSeeShippingCarrierDeletedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code verification for see Attribute Deleted successfully");
    }

    @And("I send request for Create Shipping Methods with below details")
    public void iSendRequestForCreateShippingMethosWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATESHIPPINGMETHOD);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("shippingCarrierKey",dataLoad.getshippingCarrierKey());
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData, true);
    }

    @Then("I should see Shipping Method created successfully")
    public void iShouldSeeShippingMethodCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code see Shipping Method created successfully");
        String shippingMethodKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setShippingMethodKey(shippingMethodKey);
        System.out.println("Shipping method key:"+dataLoad.getShippingMethodKey());

    }

    @And("I send request to get Shipping Methods with below details")
    public void iSendRequestToGetShippingMethodsWithBelowDetails() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("shippingMethodKey", dataLoad.getShippingMethodKey());
        RestUtil.get(pathParams, true);

    }

    @Then("I should get Shipping Method successfully")
    public void iShouldGetShippingMethodSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code get Shipping Method successfully");
        Validations.assertEquals("ShippingCarrierdescription", RestResponse.getValue("description").toString(), "Validation of isPreferred");
    }

    @And("I send request to update Shipping Method with below details")
    public void iSendRequestToUpdateShippingMethodWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_UPDATESHIPPINGCARRER);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("shippingMethodKey", dataLoad.getShippingMethodKey());
        RestUtil.get(pathparams, true);
           }

    @Then("I should see Shipping Method Updated successfully")
    public void iShouldSeeShippingMethodUpdatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code get Shipping Method successfully");
        Validations.assertEquals("ShippingCarrierdescription", RestResponse.getValue("description").toString(), "Validation of description");
    }

    @And("I send request to deleteShippingMethod")
    public void iSendRequestToDeleteShippingMethod() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
      pathparams.put("shippingMethodKey", dataLoad.getShippingMethodKey());
   //   pathparams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        RestUtil.delete(pathparams, true);

    }

    @Then("I should see deleteShippingMethod Deleted successfully")
    public void iShouldSeeDeleteShippingMethodDeletedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code verification for see deleteShippingMethod");
    }

    @And("I send request to get Shipping Carrier by Name")
    public void iSendRequestToGetShippingCarrierByName() {
        Map<String, String> queryparams = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("OfferingShippingKey", dataLoad.getOfferingShippingKey());
        RestUtil.get(true);
    }

    @Then("I should get Shipping Carrier by Name successfully")
    public void iShouldGetShippingCarrierByNameSuccessfully() {
       Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code get Shipping Method successfully");
       Validations.assertEquals("UPS", RestResponse.getValue("name").toString(), "Validation of name");
       Validations.assertEquals("UPS", RestResponse.getValue("normalizedName").toString(), "Validation of normalizedName");
       Validations.assertEquals("United Parcel Service", RestResponse.getValue("description").toString(), "Validation of description");

    }

    @And("I send request to get All Shipping Methods by Carrier")
    public void iSendRequestToGetAllShippingMethodsByCarrier() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("shippingCarrierKey", dataLoad.getshippingCarrierKey());
        RestUtil.get(pathParams, true);


    }

    @Then("I should get get All Shipping Methods by Carrier successfully")
    public void iShouldGetGetAllShippingMethodsByCarrierSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code get Shipping Method successfully");
        //Validations.assertEquals("ShippingCarrierdescription", RestResponse.getValue("description").toString(), "Validation of isPreferred");
    }

    @And("I send request for create Inventory Edit using offering Key with below details")
    public void iSendRequestForCreateInventoryEditUsingOfferingKeyWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_CREATEINVENTORYEDIT);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("OfferingKey", dataLoad.getOfferingKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData, true);


    }

    @Then("I should see the Inventory Edit created successfully")
    public void iShouldSeeTheInventoryEditCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code should see the Inventory Edit created successfully");

    }

    @And("I send request to get InventoryEdits")
    public void iSendRequestToGetInventoryEdits() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("OfferingKey",dataLoad.getOfferingKey());
        System.out.println("OfferingKey -" +dataLoad.getOfferingKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the InventoryEdits  successfully")
    public void iShouldSeeTheInventoryEditsSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for see the InventoryEdits  successfully");
    }

    @And("I send request for get Offerings by shippingkey")
    public void iSendRequestForGetOfferingsByShippingkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("OfferingShippingKey", dataLoad.getOfferingShippingKey());
        //pathParams.put("wardKey",dataLoad.getWardKey());
        RestUtil.get(pathParams, true);

    }

    @Then("I should see the response with a list of Offerings by shippingkey")
    public void iShouldSeeTheResponseWithAListOfOfferingsByShippingkey() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for Get response with a list of all Slim Participant ");
    }

    @And("I send request to Get Offering Shipping by offering")
    public void iSendRequestToGetOfferingShippingByOffering() {
        HashMap<String, String> pathParams = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        pathParams.put("OfferingShippingKey", dataLoad.getOfferingShippingKey());
        RestUtil.get(pathParams, true);

    }

    @Then("I should get Offering Shipping by OfferingKey")
    public void iShouldGetOfferingShippingByOfferingKey() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code to get Offering Shipping by OfferingKey");
    }

    @And("I send request for update Offering using offeringKey with below details")
    public void iSendRequestForUpdateOfferingUsingOfferingKeyWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.OFFERING_UPDATEOFFERINGUSINGOFFERINGKEY);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("OfferingKey", dataLoad.getOfferingKey());
        RestUtil.get(pathParams,true);


    }

    @Then("I should see Offering is Updated Successfully")
    public void iShouldSeeOfferingIsUpdatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for see ShippingCarrier is Updated Successfully");
        Validations.assertEquals("true",RestResponse.getValue("isActive").toString(),"Validation verification for see the response of offering is Active");
        //Validations.assertEquals("true",RestResponse.getValue("isArvCalculated").toString(),"Validation verification for see the response of offering isArvCalculated");
    }

    @And("I send request for Get Offering Shipping by wardkey")
    public void iSendRequestForGetOfferingShippingByWardkey() {

        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", dataLoad.getWardKey());

        RestUtil.get(pathparams, true);
    }
}

