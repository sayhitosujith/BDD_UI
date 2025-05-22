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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DispatchSteps {

    @And("I send request for create subscription with below details")
    public void iSendRequestForCreateSubscriptionWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_CREATESUBSCRIPTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        dataMap.put("wardKey", DataLoad.getInstance().getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        dataLoad.setSubscriptionName(dataMap.get("name"));
    }

    @Then("I should see subscription created successfully")
    public void iShouldSeeSubscriptionCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status code validation for subscription creation");
        DataLoad.getInstance().setSubscriptionKey(RestResponse.getAsString());
    }

    @And("I send request for get subscription")
    public void iSendRequestForGetSubscription() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        RestUtil.get(pathparams, true);
    }

    @Then("I should see subscription details in response with status code as {int}")
    public void iShouldSeeSubscriptionDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status code validation for get subscription");
        Validations.assertEquals(dataLoad.getSubscriptionKey(), RestResponse.getValue("subscriptionKey"), "Validation of subscriptionKey in get subscription response");
        Validations.assertEquals(dataLoad.getWardKey(), RestResponse.getValue("wardKey"), "Validation of wardKey in get subscription response");
        Validations.assertEquals(dataLoad.getEngagementKey(), RestResponse.getValue("engagementKey"), "Validation of engagementKey in get subscription response");
        Validations.assertEquals(dataLoad.getSubscriptionName(), RestResponse.getValue("name"), "Validation of name in get subscription response");
        Validations.assertEquals("true", RestResponse.getValue("isActive").toString(), "Validation of isActive in get subscription response");
    }

    @And("I send request for create opt-in")
    public void iSendRequestForCreateOptIn(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_CREATEOPTIN);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        dataLoad.setOptInDetails((HashMap<String, String>) dataMap);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see Opt-in created successfully with status code {int}")
    public void iShouldSeeOptInCreatedSuccessfully(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status code validation for create opt-in");
    }

    @And("I send request for get opt-in")
    public void iSendRequestForGetOptIn() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        RestUtil.get(pathparams, true);
    }

    @Then("I should see required opt-in details in response")
    public void iShouldSeeRequiredOptInDetailsInResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation for create opt-in");
        Validations.assertEquals(dataLoad.getSubscriptionKey(), RestResponse.getValue("subscriptionKey"), "Validation of subscriptionKey in get opt in response");
        Validations.assertEquals(dataLoad.getParticipantKey(), RestResponse.getValue("participantKey"), "Validation of participantKey in get opt in response");
        Validations.assertEquals(dataLoad.getOptInDetails().get("culture"), RestResponse.getValue("culture"), "Validation of culture in get opt in response");
        Validations.assertEquals(dataLoad.getOptInDetails().get("channel1"), RestResponse.getValue("channels[0].channel"), "Validation of channel 1 in get opt in response");
        Validations.assertEquals(dataLoad.getOptInDetails().get("channel1Pref"), RestResponse.getValue("channels[0].preference").toString(), "Validation of channel 1 preference in get opt in response");
        Validations.assertEquals(dataLoad.getOptInDetails().get("channel2"), RestResponse.getValue("channels[1].channel"), "Validation of channel 1 in get opt in response");
        Validations.assertEquals(dataLoad.getOptInDetails().get("channel2Pref"), RestResponse.getValue("channels[1].preference").toString(), "Validation of channel 1 preference in get opt in response");
    }

    @And("I send request for get subscription by filter")
    public void isendRequestForGetSubscriptionByFilter() {
        Map<String, String> queryparm = new HashMap<String, String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparm.put("wardKey", DataLoad.getInstance().getWardKey());
        queryparm.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        queryparm.put("name", DataLoad.getInstance().getSubscriptionName());
        RestUtil.get(queryparm, true);


    }

    @Then("I should see filtered subscription details with wardkey in response with status code as {int}")
    public void iShouldSeeFilteredSubscriptionDetailsWithWardkeyInResponseWithStatusCodeAs(int statusCode) {

        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Status Code validated for get subscriptions by ward");

        String subscriptionKey = DataLoad.getInstance().getSubscriptionKey();
        List<HashMap<Object, Object>> allSubscriptions = RestResponse.getResponseList("data");


        int numberOfSubscriptions = allSubscriptions.size();
        boolean isSubscriptionExistInResponse = false;

        for (int i = 0; i <= numberOfSubscriptions - 1; i++) {
            String subscriptionKeyInResponse = allSubscriptions.get(i).get("subscriptionKey").toString();
            if (subscriptionKeyInResponse.equals(subscriptionKey)) {
                Validations.assertEquals(subscriptionKey, subscriptionKeyInResponse, "Validated subscriptionKey associated to ward");
                Validations.assertEquals(DataLoad.getInstance().getSubscriptionName(), allSubscriptions.get(i).get("name").toString(), "Validated subscription name associated to ward");
                isSubscriptionExistInResponse = true;

            }

        }
        Validations.assertEquals(true, isSubscriptionExistInResponse, "ward list verified successfully");
    }

    @And("I send a put request with below details for updating subscription")
    public void iSendAPutRequestWithBelowDetailsForUpdatingSubscription(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_UPDATE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        dataMap.put("wardKey", DataLoad.getInstance().getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparam = new HashMap<>();
        pathparam.put("subscriptionKey", dataLoad.getSubscriptionKey());
        RestUtil.put(pathparam, jsonData, true);
        dataLoad.setSubscriptionName(dataMap.get("name"));

    }

    @And("I send a request to disable the subscription")
    public void iSendARequestToDisableTheSubscription() {
        HashMap<String, String> pathparam = new HashMap<>();
        DataLoad dataLoad = DataLoad.getInstance();
        pathparam.put("subscriptionKey", dataLoad.getSubscriptionKey());
        RestUtil.put(pathparam, "", true);

    }

    @Then("I should see subscription is disabled successfully")
    public void iShoulsSeeSubscriptionIsDisabledSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation for subscription disable");

    }

    @Then("I should see subscription is disabled in response")
    public void iShouldSeeSubscriptionIsDisabledInResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation for get subscription");
        Validations.assertEquals(dataLoad.getSubscriptionName(), RestResponse.getValue("name"), "Validation of name in get subscription response");
        Validations.assertEquals("false", RestResponse.getValue("isActive").toString(), "Validation of isActive is false in get subscription response");
    }

    @And("I send request for create opt-out with below details")
    public void iSendRequestForCreateOptOut(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_CREATEOPTOUT);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        dataMap.put("wardKey", DataLoad.getInstance().getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        RestUtil.put(pathparams, jsonData, true);
    }

    @Then("I should see Opt-Out created successfully with status code {int}")
    public void iShouldSeeOptOutCreatedSuccessfullyWithStausCode(int statuscode) {
        Validations.assertEquals(statuscode, RestResponse.getStatusCode(), "Status code validated for opt-out put request");
    }

    @And("I send request for create BulkOpt-in with below details")
    public void iSendRequestForCreateBulkOptInWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_CREATEBULKOPTIN);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        System.out.println(dataLoad.getParticipantKey());
        System.out.println(dataLoad.getParticipantKey1());
        dataMap.put("Participant1Key", dataLoad.getParticipantKey());
        dataMap.put("Participant2Key", dataLoad.getParticipantKey1());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        RestUtil.post(pathparams, jsonData, true);

    }

    @And("I send request for create BulkOpt-Out with below participants")
    public void iSendRequestForCreateBulkOptOutWithBelowParticipants(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SUBSCRIPTION_CREATEBULKOPTOUT);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        System.out.println(dataLoad.getParticipantKey());
        System.out.println(dataLoad.getParticipantKey1());
        dataMap.put("Participant2Key", dataLoad.getParticipantKey1());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("subscriptionKey", dataLoad.getSubscriptionKey());
        RestUtil.post(pathparams, jsonData, true);

    }

    @And("I send request for Send Email to Single User with below details")
    public void iSendRequestForSendEmailToSingleUserWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {

        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.SEND_EMAIL_TO_SINGLE_USER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("templateKey", dataLoad.getTemplateDetails("templateKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see Send Email to Single User successfully")
    public void iShouldSeeSendEmailToSingleUserSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status code validated for Send Email to Single User");
    }
}

