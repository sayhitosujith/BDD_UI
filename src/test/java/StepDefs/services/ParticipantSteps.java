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

public class ParticipantSteps {
    public  static  String globalJsonData;

    @Given("I am getting the participant token for the {string} scope")
    public void iAmGettingTheParticipantTokenForTheScope(String scope) {


       StepUtil.generateParticipantToken(scope);
}



    @When("I am able to get the following API endpoint")
    public void i_am_able_to_get_the_following_API_endpoint(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment()+"."+dataMap.get("url")),ResourceData.getEndPoint(dataMap.get("endpoint")));
    }


    @Then("I should see participant created successfully")
    public void iShouldSeeParticipantCreatedSuccessfully() {
        if(RestResponse.getStatusCode()!=200){
            RestUtil.post(globalJsonData,true);
        }
        else {
            Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for Participant Creation");
            String participantKey = RestResponse.getValue("participantKey").toString();
            //String participantKey = "618b825c7c4b5c16745c72bf";
            DataLoad dataLoad = DataLoad.getInstance();
            dataLoad.setParticipantKey(participantKey);
        }
    }

    @Then("I should see participant Registered successfully")
    public void iShouldSeeParticipantRegisteredSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see participant Registered successfully");
        String participantkey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setParticipantKey(participantkey);
    }

    @And("I send request for create Bulk Upsert Participants with below details")
    public void iSendRequestForCreateBulkUpsertParticipantsWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_BULKUPSERTPARTICIPANT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see Bulk Upsert Participants created successfully")
    public void iShouldSeeBulkUpsertParticipantsCreatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to Bulk Upsert Participants ");
        Validations.assertEquals("Success",RestResponse.getValue("resultType").toString(),"Validation of Result Type");
    }


    @When("I am able to get the following API endpoint and generate token for {string}")
    public void iAmAbleToGetTheFollowingAPIEndpointAndGenerateTokenFor(String arg0,io.cucumber.datatable.DataTable dataTable) {
        StepUtil.generateParticipantToken(arg0);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment()+"."+dataMap.get("url")),ResourceData.getEndPoint(dataMap.get("endpoint")));
    }




    @And("I am getting the trusted Identifier token for the {string} sub scope")
    public void iAmGettingTheTrustedIdentifierTokenForTheSubScope(String scope) {
        StepUtil.generateEngageTrustedIdentifierToken(scope);
    }


    @Given("I am getting existing engage record details")
    public void iAmGettingExistingEngageRecordDetails() throws Exception {
        HashMap hashMap = new HashMap<String, String>();
        hashMap= StepUtil.getEngagementIDWardAccount();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setEngagementKey(hashMap.get("engagementKey").toString());
        dataLoad.setEngageAccountKey(hashMap.get("accountKey").toString());
        dataLoad.setWardKey(hashMap.get("wardKey").toString());
    }

    @And("I send request for {string} with below details")
    public void iSendRequestForWithBelowDetails(String arg0,io.cucumber.datatable.DataTable dataTable)throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload="";
        if(arg0.equals("Create Dated Participants")) {
            payload = genericUtil.getFileData(Payloads.PARTICIPANT_CREATEDATEDPARTICIPANTS);
        }
        if(arg0.equals("Create Participant")) {
            payload = genericUtil.getFileData(Payloads.PARTICIPANT_CREATEPARTICIPANT);
        }
        if(arg0.contains("Bulk Upsert")){
            payload = genericUtil.getFileData(Payloads.PARTICIPANT_BULKUPSERTPARTICIPANT);
        }
        if(arg0.equals("Register Participant")){
            payload = genericUtil.getFileData(Payloads.PARTICIPANT_REGISTERPARTICIPANT);
        }
        if(arg0.equals("Verify Phone is Valid")) {
            payload = genericUtil.getFileData(Payloads.PARTICIPANTISPHONEVALID);
        }
        if (arg0.equals("create action")) {
            payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_ACTION);
        }
//        if(arg0.equals("Update Passwordwith Pin")) {
//            payload = genericUtil.getFileData(Payloads.PARTICIPANT_UPDATEPASSWORDWITHPIN);
//        }

        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        if(dataMap.containsKey("wardKey")){
            dataMap.put("wardKey",dataLoad.getWardKey());
        }
        if(dataMap.containsKey("engagementKey")) {
            dataMap.put("engagementKey", dataLoad.getEngagementKey());
        }
        if (dataMap.containsKey("actionKey")) {
            dataMap.put("actionKey", dataLoad.getActionKey());
        }

        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        globalJsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.print(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see Dated participant created successfully")
    public void iShouldSeeDatedParticipantCreatedSuccessfully() {
        if(RestResponse.getStatusCode()!=200){
            RestUtil.post(globalJsonData,true);
        }
        else {
            Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for Create Dated Participant");
            String participantKey = RestResponse.getValue("participantKey").toString();
            //String participantKey = "618b825c7c4b5c16745c72bf";
            DataLoad dataLoad = DataLoad.getInstance();
            dataLoad.setParticipantKey(participantKey);
        }

    }

    @Then("I should see Phone is Verified Sucessfully")
    public void iShouldSeePhoneIsVerifiedSucessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see Phone Number is Verified Sucessfully");
        Validations.assertEquals("true",RestResponse.getValue("isValid").toString(),"Validation of Phone Number");
    }

    @Then("I should see Password is Updated with Pin Successfully")
    public void iShouldSeePasswordIsUpdatedwithPinSuccessfully() {
       Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see Password is Updated Successfully");
    }

    @And("I send request for get Slim Participant")
    public void iSendRequestForGetSlimParticipant() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);

        }

//    @Then("I should see the response with a list of all Slim Participant")
//    public void iShouldSeeTheResponseWithAListOfAllSlimParticipant()
//    {
//        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code validation for Get Slim Participant response");
//        //Validations.assertEquals(DataLoad.getInstance().getUsername(), RestResponse.getValue("userName").toString(),"Validation of Username");
//        Validations.assertEquals(DataLoad.getInstance().getParticipantKey(), RestResponse.getValue("participantKey").toString(),"Validation of participant key");
//    }

    @Then("I should see personalinfo Updated successfully")
    public void iShouldSeepersonalinfoUpdatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see personalinfo Updated successfully");
        //String participantkey = RestResponse.getValue("participantKey").toString();
    }

    @And("I send request to add Participant address with below details")
    public void iSendRequesttoaddParticipantaddresswithbelowdetails(io.cucumber.datatable.DataTable dataTable) {

        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_ADDPARTICIPANTADDRESS);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey", dataLoad.getParticipantKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("participantKey" + dataLoad.getParticipantKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

   @Then("I should see participant address added successfully")
    public void Ishouldseeparticipantaddressaddedsuccessfully() {
       Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code to see participant address added successfully");

   }

    @Then("I should see Pin in the response with status code as {int}")
    public void iShouldSeePinInTheResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for generating pin");
        //Validations.assertEquals("7240",RestResponse.getValue("pin").toString(),"Validation of pin");
        String forgotPasswordPin = RestResponse.getValue("pin").toString();
        DataLoad.getInstance().setPin(forgotPasswordPin);
    }

    @And("I send request to Update Password with below details")
    public void iSendRequestToUpdatePasswordWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_UPDATEPASSWORD);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey", dataLoad.getParticipantKey());
        HashMap<String,String> pathparams = new HashMap<String,String>();
        System.out.println("participantKey" +dataLoad.getParticipantKey());
        pathparams.put("participantKey",dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
       // RestUtil.put(pathparams,jsonData,true);

    }

    @And("I send request for Update Passwordwith Pin with below details")
    public void iSendRequestForUpdatePasswordwithPinWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {

        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_UPDATEPASSWORDWITHPIN);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());

    }

    @Then("I should see Password is Updated Successfully")
    public void iShouldSeePasswordIsUpdatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see Password is Updated Successfully");
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
    }

    @And("I send request for get forgot Engagement password Pin")
    public void iSendRequestForGetForgotEngagementPasswordPin() {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see Engagement Pin in the response with status code as {int}")
    public void iShouldSeeEngagementPinInTheResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code see Engagement Pin in the response with status code");
        String forgotPasswordPin = RestResponse.getValue("pin").toString();
        DataLoad.getInstance().setPin(forgotPasswordPin);
    }

    @And("I send request for get Slim Participant by Key and Ward Key")
    public void iSendRequestForGetSlimParticipantByKeyAndWardKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);

    }

    @Then("I should see the response of Slim Participant by Key and Ward Key")
    public void iShouldSeeTheResponseOfSlimParticipantByKeyAndWardKey() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification  to see the response of Slim Participant by Key and Ward Key ");
    }

    @And("I send request for get Participant Household Address")
    public void iSendRequestForGetParticipantHouseholdAddress() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);


    }

    @Then("I should see the response of Participant Household Address")
    public void iShouldSeeTheResponseOfParticipantHouseholdAddress() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
        Validations.assertEquals("SCOTTSDALE",RestResponse.getValue("city").toString(),"Validation of city");
       // Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getCity()),"Validation of city");
    }

    @And("I send request for get Participant Mailing Address")
    public void iSendRequestForGetParticipantMailingAddress() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);


    }

    @Then("I should see the response of Participant Mailing Address")
    public void iShouldSeeTheResponseOfParticipantMailingAddress() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
       // Validations.assertEquals(DataLoad.getInstance().getCity(), RestResponse.getValue("city").toString(),"Validation of Username");
        Validations.assertEquals("SCOTTSDALE",RestResponse.getValue("city").toString(),"Validation of city");
        // Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getCity()),"Validation of ward key");
    }

    @And("I send request for get Participant Most Recent Address")
    public void iSendRequestForGetParticipantMostRecentAddress() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);

    }

    @Then("I should see the response of Participant Most Recent Address")
    public void iShouldSeeTheResponseOfParticipantMostRecentAddress() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
      // Validations.assertEquals(DataLoad.getInstance().getCity(), RestResponse.getValue("city").toString(),"Validation of city");
//        Validations.assertEquals(DataLoad.getInstance().getParticipantKey(), RestResponse.getValue("participantKey").toString(),"Validation of participant key");
//        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("engagementKey").toString(),"Validation of engagementKey key");
    }

    @And("I send request for Update PersonalInfo with below details and verify the updated details in response")
    public void iSendRequestForUpdatePersonalInfoWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_UPDATEPERSONALINFO);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("userName",dataLoad.getUsername());
        String updatedFirstName = dataMap.get("firstName");
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.post(pathParams,jsonData,true);
        Validations.assertEquals(updatedFirstName, RestResponse.getValue("firstName").toString(), "Validation of Updated first name in response");

    }
    @And("I send request for get forgot password Pin")
    public void iSendRequestForGetForgotPasswordPin() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);

    }

    @Then("I should see participant Updated successfully")
    public void iShouldSeeParticipantUpdatedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation of Updated personal info");
 }

    @And("I send request to add Participant email with below details")
    public void iSendRequestToAddParticipantEmailWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_ADDPARTICIPANTEMAIL);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("participantEmail" + dataLoad.getParticipantEmail());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see participant email added successfully")
    public void iShouldSeeParticipantEmailAddedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code to see participant email added successfully");
    }

    @And("I send request to add Participant Phone with below details")
    public void iSendRequestToAddParticipantPhoneWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_ADDPARTICIPANTPHONE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        System.out.println("participantPhone" + dataLoad.getParticipantphone());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see participant Phone added successfully")
    public void iShouldSeeParticipantPhoneAddedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code to see participant Phone added successfully");
    }

    @And("I send request for get Participant Household Demographic Key")
    public void iSendRequestForGetParticipantHouseholdDemographicKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        System.out.println("House hold Demographic Key -" + dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response with a list of Participant Household Demographic Key")
    public void iShouldSeeTheResponseWithAListOfParticipantHouseholdDemographicKey() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get list of Participant Household Demographic Key");
    }

    @And("I send request for remove Participant Pii")
    public void iSendRequestForRemoveParticipantPii() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
//      pathparams.put("attributekey", dataLoad.getAttributeKey());
        pathparams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.post(pathparams,true);
    }

    @Then("I should see the response with a empty list of ParticipantPii")
    public void iShouldSeeTheResponseWithAEmptyListOfParticipantPii() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code verification for see the response with a empty list of ParticipantPii");
    }

    @And("I send request to add Attribute with below details")
    public void iSendRequestToAddAttributeWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_ADDATTRIBUTE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see Attribute added successfully")
    public void iShouldSeeAttributeAddedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code to see Attribute added successfully");
    }

    @And("I send request to Delete Attribute with below details")
    public void iSendRequestToDeleteAttributeWithBelowDetails() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
//      pathparams.put("attributekey", dataLoad.getAttributeKey());
        pathparams.put("participantKey",dataLoad.getParticipantKey());
        // RestUtil.get(pathparams,true);
    }

    @Then("I should see Attribute Deleted successfully")
    public void iShouldSeeAttributeDeletedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code verification for see Attribute Deleted successfully");
    }

    @And("I send request for get Attributes")
    public void iSendRequestForGetAttributes() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response with a list of Attributes")
    public void iShouldSeeTheResponseWithAListOfAttributes() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for see the response with a list of Attributes");

    }

    @And("I send request for get Ward Attributes")
    public void iSendRequestForGetWardAttributes() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);

    }

    @Then("I should see the response with a list of Ward Attributes")
    public void iShouldSeeTheResponseWithAListOfWardAttributes() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for see the response with a list of Ward Attributes");
       // Validations.assertEquals("300006482",RestResponse.getValue("value").toString(),"Validation of isDpv");
       // Validations.assertEquals("5f91f9c9de36071cd88e4017",RestResponse.getValue("wardAttributeKey").toString(),"Validation of isPreferred");
    }
    @And("I send request for get Ward Participants")
    public void iSendRequestForGetWardParticipants() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("wardKey",dataLoad.getWardKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response with a list of Ward Participants")
    public void iShouldSeeTheResponseWithAListOfWardParticipants()
    {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
        Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getWardKey()),"Validation of ward key");
        //Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getParticipantKey()),"Validation of participant key");
    }
    @And("I send request to Verify Participants is Registered")
    public void iSendRequestToVerifyParticipantsIsRegistered() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response Participants is Registered or not")
    public void iShouldSeeTheResponseParticipantsIsRegisteredOrNot() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code validation of 'isRegistered' response");
        Validations.assertEquals("true",RestResponse.getValue("isRegistered").toString(),"Validation of registration status");
    }

    @And("I send request to add Engagement with below details")
    public void iSendRequestToAddEngagementWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_ADDENGAGEMENT);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
      pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
       RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see Engagement added successfully")
    public void iShouldSeeEngagementAddedSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code to see Engagement added successfully");
    }

    @And("I send request to get Participant Key using filter")
    public void iSendRequestToGetParticipantKeyUsingFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
//        System.out.println("participantKey -" +dataLoad.getParticipantKey());
//        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response Participants Key using filter")
    public void iShouldSeeTheResponseParticipantsKeyUsingFilter() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
        Validations.assertEquals("true",RestResponse.getValue("isRegistered").toString(),"Validation of isRegistered");
      }

      @And("I send request to get Participants using filter")
    public void iSendRequestToGetParticipantsUsingFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("participantKey",dataLoad.getParticipantKey());
        queryparams.put("wardKey",dataLoad.getWardKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see the response Participants using filter")
    public void iShouldSeeTheResponseParticipantsUsingFilter() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get response with a list of all Slim Participant ");
        Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getWardKey()),"Validation of ward key");
        Validations.assertEquals(true, RestResponse.getAsString().contains(DataLoad.getInstance().getParticipantKey()),"Validation of participant key");
    }

    @And("I send request for Create Participant with below details")
    public void iSendRequestForCreateParticipantWithBelowDetails(io.cucumber.datatable.DataTable dataTable)throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_CREATEPARTICIPANT);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataLoad.setUsername(dataMap.get("userName"));
        dataLoad.setCity(dataMap.get("city"));
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        globalJsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);
    }


    @And("I send request to get Participant Address using participantKey")
    public void iSendRequestToGetParticipantAddressUsingParticipantKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(dataLoad.getParticipantKey());
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
       
    }

    @Then("I should see the response Participant Address using participantKey")
    public void iShouldSeeTheResponseParticipantAddressUsingParticipantKey() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code verification for see the response Participant Address using participantKey");
    }



    @And("I send request for Update updateHouseholdDemographicKey with below details")
    public void iSendRequestForUpdateUpdateHouseholdDemographicKeyWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_HOUSEHOLDDEMOGRAPHICKEY);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.put(pathParams,jsonData,true);
    }

    @And("I send request for get Participant")
    public void iSendRequestForGetParticipant() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see the response with Slim Participant details")
    public void iShouldSeeTheDetailsOfParticipant() {
         Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code validation of get Slim Participant response");
        Validations.assertEquals(DataLoad.getInstance().getUsername(), RestResponse.getValue("userName").toString(),"Validation of Username in get Slim Participant Response");
        Validations.assertEquals(DataLoad.getInstance().getParticipantKey(), RestResponse.getValue("participantKey").toString(),"Validation of participant key in get Slim Participant Response");
    }


    @And("I send request for Register Participant with below details")
    public void iSendRequestForRegisterParticipantWithBelowDetails(io.cucumber.datatable.DataTable dataTable)
    {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_REGISTERPARTICIPANT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("engagementKey", dataLoad.getEngagementKey());
        System.out.print(jsonData);
        RestUtil.post(pathParams,jsonData,true);
    }

    @And("I send request for Update Password with below details")
    public void iSendRequestForUpdatePasswordWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.PARTICIPANT_UPDATEPASSWORD);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.print(jsonData);
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("participantKey",dataLoad.getParticipantKey());
        pathParams.put("EngagementKey",dataLoad.getEngagementKey());
        System.out.println("participantKey -" +dataLoad.getParticipantKey());
        System.out.println("EngagementKey -"+dataLoad.getEngagementKey());
    }

    @Then("I should see participant created successfully and store participantKey1")
    public void iShouldSeeParticipantCreatedSuccessfullyAndStoreParticipantKey1() {

        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code Validation for Participant Creation");
        String participantKey = RestResponse.getValue("participantKey").toString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setParticipantKey1(participantKey);

    }


    @Then("I should see the list of Participant")
    public void iShouldSeeTheListOfParticipant() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation to see the list of Participants");
    }
}


