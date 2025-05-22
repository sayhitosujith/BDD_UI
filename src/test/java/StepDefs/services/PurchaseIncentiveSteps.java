package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import util.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PurchaseIncentiveSteps
{
    @And("I send request for create purchase incentive with below details")
    public void iSendRequestForCreatePurchaseIncentiveWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEPURCHASEINCENTIVE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        dataLoad.setPurchaseIncentiveDetails(new String[]{dataMap.get("name"), dataMap.get("description"), dataMap.get("details"),dataMap.get("instructions")});
    }

    @Then("I should see purchase incentive created successfully with status code {int}")
    public void iShouldSeePurchaseIncentiveCreatedSuccessfullyWithStatusCode(int statusCode) {
        DataLoad.getInstance().setPurchaseIncentiveKey(RestResponse.getAsString());
        System.out.println("Purchase incentive key:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status code validation for Create Purchase Incentive");

    }

    @And("I send a request for get purchase incentive")
    public void iSendARequestForGetPurchaseIncentive() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",DataLoad.getInstance().getPurchaseIncentiveKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the purchase incentive details in the response with status code as {int}")
    public void iShouldSeeThePurchaseIncentiveDetailsInTheResponseWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Validation of status code for get purchase incentive response");
        Validations.assertEquals (DataLoad.getInstance().getPurchaseIncentiveKey(),RestResponse.getValue("purchaseIncentiveKey"),"Validation of purchase incentive key in get purchase incentives response");
        Validations.assertEquals(DataLoad.getInstance().getEngagementKey(), RestResponse.getValue("engagementKey"),"Validation of engagement key in get purchase incentive response");
        Validations.assertEquals(DataLoad.getInstance().getPurchaseIncentiveDetails()[0], RestResponse.getValue("name"),"Validation of purchase incentive name in get purchase incentive response");
        Validations.assertEquals(DataLoad.getInstance().getPurchaseIncentiveDetails()[1], RestResponse.getValue("description"),"Validation of purchase incentive description in get purchase incentive response");
        Validations.assertEquals(DataLoad.getInstance().getPurchaseIncentiveDetails()[2], RestResponse.getValue("details"),"Validation of purchase incentive details in get purchase incentive response");
        Validations.assertEquals(DataLoad.getInstance().getPurchaseIncentiveDetails()[3], RestResponse.getValue("instructions"),"Validation of purchase incentive instructions in get purchase incentive response");
    }

    @And("I send a request to create SKU enrichment")
    public void iSendARequestToCreateSKUEnrichment(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESKUENRICHMENT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send a request to update purchase incentive")
    public void iSendARequestToUpdatePurchaseIncentive(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.UPDATEPURCHASEINCENTIVE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
        dataLoad.setPurchaseIncentiveDetails(new String[]{dataMap.get("name"), dataMap.get("description"), dataMap.get("details"),dataMap.get("instructions")});
    }

    @And("I send a request to set purchase incentive as disabled {string}")
    public void iSendARequestToSetPurchaseIncentiveAsDisabled(String disabledStatus) {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",DataLoad.getInstance().getPurchaseIncentiveKey());
        pathparams.put("disabledStatus",disabledStatus);
        RestUtil.put(pathparams,true);
    }


    @Then("I should see the disabled status as {string} in response")
    public void iShouldSeeTheDisabledStatusAsInResponse(String disabledStatus) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(disabledStatus,RestResponse.getValue("isDisabled").toString(),"Validation of disabled status in get purchase incentuve response");
    }

    @And("I send a request for get purchase incentives with engagement key")
    public void iSendARequestForGetPurchaseIncentivesWithEngagementKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("engagementKey",DataLoad.getInstance().getEngagementKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the purchase incentives in the response with status code as {int}")
    public void iShouldSeeThePurchaseIncentivesInTheResponseWithStatusCodeAs(int statusCode) {
        String responseString = RestResponse.getAsString();
        System.out.println(responseString);
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation of get purchase incentives by engagement key");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveKey()),"Validation of purchase incentive key in get purchase incentives response by engagement key");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[0]),"Validation of purchase incentive name in get purchase incentives response by engagement key");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[1]),"Validation of purchase incentive description in get purchase incentives response by engagement key");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[2]),"Validation of purchase incentive details in get purchase incentives response by engagement key");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[3]),"Validation of purchase incentive instructions in get purchase incentives response by engagement key");
    }

    @And("I send a request to get purchase incentives by filter")
    public void iSendARequestToGetPurchaseIncentivesByFilter() {
        Map<String,String> queryparams = new HashMap<>();
        queryparams.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        queryparams.put("search", DataLoad.getInstance().getPurchaseIncentiveDetails()[0]);
        System.out.println(DataLoad.getInstance().getPurchaseIncentiveDetails()[0]);
        RestUtil.get(queryparams,true);
    }


    @Then("I should see the response with status code as {int} for {string}")
    public void iShouldSeeTheResponseWithStatusCodeAsFor(int statusCode, String serviceName) {
            System.out.println(RestResponse.getAsString());
            Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status Code verification for '"+serviceName+"' response");
        }

    @Then("I should see the updated purchase incentive details in the response with status code as {int}")
    public void iShouldSeeTheUpdatedPurchaseIncentiveDetailsInTheResponseWithStatusCodeAs(int statusCode) {
        String responseString = RestResponse.getAsString();
        System.out.println(responseString);
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation of get purchase incentives");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveKey()),"Validation of purchase incentive key in get purchase incentives response");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[0]),"Validation of updated purchase incentive name in get purchase incentives response");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[1]),"Validation of updated purchase incentive description in get purchase incentives response");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[2]),"Validation of updated purchase incentive details in get purchase incentives response");
        Validations.assertEquals(true,responseString.contains(DataLoad.getInstance().getPurchaseIncentiveDetails()[3]),"Validation of updated purchase incentive instructions in get purchase incentives response");
    }


    /********** Offer Steps *************/

    @And("I send a request to create offer")
    public void iSendARequestToCreateOffer(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEOFFER);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        if(dataMap.get("offeringKey").equals("offeringKey")){
            dataMap.put("offeringKey",dataLoad.getOfferingKey());
        }
        dataMap.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(dataLoad.getPurchaseIncentiveKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        String [] offerDetails = {dataMap.get("ruleKey"),dataMap.get("offeringKey"),dataMap.get("name"),dataMap.get("description"),dataMap.get("termsUrl"),dataMap.get("arvCalculationRuleKey")};
        dataLoad.setRuleKey(dataMap.get("ruleKey"));
        dataLoad.setOfferDetails(offerDetails);
    }


    @Then("I should see the offer created successfully with response status code as {int}")
    public void iShouldSeeTheOfferCreatedSuccessfullyWithResponseStatusCodeAs(int statusCode) {
        System.out.println("Offer Key:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create offer");
        DataLoad.getInstance().setOfferKey(RestResponse.getAsString());
    }

    @And("I send a request to get offer")
    public void iSendARequestToGetOffer() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("offerKey",DataLoad.getInstance().getOfferKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see offer details in response with status code as {int}")
    public void iShouldSeeOfferDetailsInResponseWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get offer");
        Validations.assertEquals (dataLoad.getPurchaseIncentiveKey(),RestResponse.getValue("purchaseIncentiveKey"),"Validation of purchase incentive key in get offer response");
        Validations.assertEquals(dataLoad.getOfferKey(), RestResponse.getValue("offerKey"),"Validation of offer key in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[0],RestResponse.getValue("ruleKey"),"Validation of rule key in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[1],RestResponse.getValue("offeringKey"),"Validation of offering key in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[2],RestResponse.getValue("name"),"Validation of name in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[3],RestResponse.getValue("description"),"Validation of description in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[4],RestResponse.getValue("termsUrl"),"Validation of terms url in get offer response");
        //Validations.assertEquals(dataLoad.getOfferDetails()[5],RestResponse.getValue("arvCalculationRuleKey"),"Validation of arv calculation rule key in get offer response");
        Validations.assertEquals(false,RestResponse.getValue("isDisabled"),"Validation of offer disabled status in get offer response");

    }

    @And("I send a request to get offers by purchase incentive key")
    public void iSendARequestToGetOffersByPurchaseIncentiveKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",DataLoad.getInstance().getPurchaseIncentiveKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see list of offers in get offers response with status code as {int}")
    public void iShouldSeeListOfOffersInGetOffersResponseWithStatusCodeAs(int statusCode) {
        String responseString = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(responseString);
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferKey()),"Validation of Offer key in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getPurchaseIncentiveKey()),"Validation of purchase incentive key in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[0]),"Validation of ruleKey in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[1]),"Validation of offering key in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[2]),"Validation of name in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[3]),"Validation of description in get offers by purchase incentive key response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[4]),"Validation of terms url in get offers by purchase incentive key response");
        //Validations.assertEquals(true,responseString.contains(dataLoad.getOfferDetails()[5]),"Validation of arvCalculationRuleKey in get offers by purchase incentive key response");
    }

    @And("I send a request to update offer")
    public void iSendARequestToUpdateOffer(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.UPDATEOFFER);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("offerKey",DataLoad.getInstance().getOfferKey());
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
        String [] offerDetails = {dataMap.get("offeringKey"),dataMap.get("name"),dataMap.get("description"),dataMap.get("termsUrl"),dataMap.get("arvCalculationRuleKey")};
        dataLoad.setOfferDetails(offerDetails);
    }

    @Then("I should see updated offer details in response with status code as {int}")
    public void iShouldSeeUpdatedOfferDetailsInResponseWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get offer");
        Validations.assertEquals (dataLoad.getPurchaseIncentiveKey(),RestResponse.getValue("purchaseIncentiveKey"),"Validation of purchase incentive key in get offer response");
        Validations.assertEquals(dataLoad.getOfferKey(), RestResponse.getValue("offerKey"),"Validation of offer key in get offer response");
        //Validations.assertEquals(dataLoad.getOfferDetails()[0],RestResponse.getValue("ruleKey"),"Validation of rule key in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[0],RestResponse.getValue("offeringKey"),"Validation of offering key in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[1],RestResponse.getValue("name"),"Validation of updated name in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[2],RestResponse.getValue("description"),"Validation of updated description in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[3],RestResponse.getValue("termsUrl"),"Validation of terms url in get offer response");
        Validations.assertEquals(dataLoad.getOfferDetails()[4],RestResponse.getValue("arvCalculationRuleKey"),"Validation of arv calculation rule key in get offer response");

    }

    @Then("I should see offer disabled status as {string}")
    public void iShouldSeeOfferDisabledStatusAs(String disabledStatus) {
        Validations.assertEquals(disabledStatus,RestResponse.getValue("isDisabled").toString(),"Validation of offer disabled status in get offer response");
    }

    @And("I send a request to set offer disabled status as {string}")
    public void iSendARequestToSetOfferDisabledStatusAs(String disabledStatus) {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("disabledStatus",disabledStatus);
        pathparams.put("offerKey",DataLoad.getInstance().getOfferKey());
        RestUtil.put(pathparams,true);
    }

    @And("I send a request to create prize allocation")
    public void iSendARequestToCreatePrizeAllocation(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEPRIZEALLOCATION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("offeringKey",dataLoad.getOfferingKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("offerKey",dataLoad.getOfferKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see prize allocation is created successfully with status code as {int}")
    public void iShouldSeePrizeAllocationIsCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create prize allocation for an offer");
    }

    @And("I send a request to create offering inventory")
    public void iSendARequestToCreateOfferingInventory() {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATEINVENTORY);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("offeringKey",DataLoad.getInstance().getOfferingKey());
        System.out.println(payload);
        RestUtil.post(pathparams,payload,true);
    }

    @Then("I should see the inventory created successfully with response status code as {int}")
    public void iShouldSeeTheInventoryCreatedSuccessfullyWithResponseStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create inventory");
    }


    /********** Submission Steps ***********/

    @And("I send a request to create submission for purchase incentive")
    public void iSendARequestToCreateSubmissionForPurchaseIncentive(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESUBMISSION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        dataLoad.setInvoiceKey(dataMap.get("invoiceKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see the submission created successfully with response status code as {int}")
    public void iShouldSeeTheSubmissionCreatedSuccessfullyWithResponseStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create submission");
        Validations.assertEquals(true,!(RestResponse.getAsString().isEmpty()),"Validation for non-empty response\n"+RestResponse.getAsString());
        DataLoad.getInstance().setSubmissionKey(RestResponse.getAsString());
        System.out.println("submissionKey:"+RestResponse.getAsString());
    }

    @And("I send request for create handled submission and claim")
    public void iSendRequestForCreateHandledSubmissionAndClaim(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATEHANDLEDSUBMISSIONANDCLAIM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        dataMap.put("desiredOfferKey",dataLoad.getOfferKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see the handled submission created successfully with status code as {int}")
    public void iShouldSeeTheHandledSubmissionCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create handled submission");
        System.out.println("Response:\n"+RestResponse.getAsString());
    }

    @Then("I should see submission and claim created successfully with status code as {int}")
    public void iShouldSeeSubmissionAndClaimCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println("Response:\n"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create submission and claim");
        Validations.assertEquals(true,!(RestResponse.getAsString().isEmpty()),"Validation of submissionClaimKey presence in response\n"+RestResponse.getValue("submissionClaimKey"));
        Validations.assertEquals(true,!(RestResponse.getAsString().isEmpty()),"Validation of submissionKey presence in response\n"+RestResponse.getValue("submissionKey"));
        DataLoad.getInstance().setSubmissionClaimKey(RestResponse.getValue("submissionClaimKey"));
        DataLoad.getInstance().setSubmissionKey(RestResponse.getValue("submissionKey"));
    }

    @And("I send request for create submission and claim")
    public void iSendRequestForCreateSubmissionAndClaim(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESUBMISSIONANDCLAIM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        dataMap.put("desiredOfferKey",dataLoad.getOfferKey());
        dataLoad.setInvoiceKey(dataMap.get("invoiceKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request qualified selection using submissionClaimKey")
    public void iSendRequestQualifiedSelectionUsingSubmissionClaimKey(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CLAIMWITHQUALIFIEDSELECTION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("offerKey",dataLoad.getOfferKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionClaimKey",dataLoad.getSubmissionClaimKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see the claim process with qualified selection is successful with status code as {int}")
    public void iShouldSeeTheClaimProcessWithQualifiedSelectionIsSuccessfulWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for claim with qualified selection");
    }

    @And("I send request for get offers by submissionClaimKey")
    public void iSendRequestForGetOffersBySubmissionClaimKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionClaimKey",DataLoad.getInstance().getSubmissionClaimKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see a list of offers in response with status code as {int}")
    public void iShouldSeeAListOfOffersInResponseWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get offers by submissionClaimKey");
        Validations.assertEquals(dataLoad.getOfferKey(),RestResponse.getBody().jsonPath().getJsonObject("offer[0].offerKey").toString(),"Validation for offer key in get Offers by submissionClaimKey response");
        Validations.assertEquals(dataLoad.getPurchaseIncentiveKey(),RestResponse.getBody().jsonPath().getJsonObject("offer[0].purchaseIncentiveKey").toString(),"Validation for purchaseIncentive key in get Offers by submissionClaimKey response");
        Validations.assertEquals(dataLoad.getRuleKey(),RestResponse.getBody().jsonPath().getJsonObject("offer[0].ruleKey").toString(),"Validation for rule key in get Offers by submissionClaimKey response");
        Validations.assertEquals(dataLoad.getOfferingKey(),RestResponse.getBody().jsonPath().getJsonObject("offer[0].offeringKey").toString(),"Validation for offering key in get Offers by submissionClaimKey response");

    }

    @And("I send request for get pending reviews")
    public void iSendRequestForGetPendingReviews() {
        RestUtil.get(true);
    }

    @Then("I should see a list of reviews in response with status code as {int}")
    public void iShouldSeeAListOfReviewsInResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get pending reviews");
        System.out.println("Response:"+RestResponse.getAsString());
    }

    @And("I send request to override submission with offerKey")
    public void iSendRequestToOverrideSubmissionWithOfferKey(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESUBMISSIONOVERRIDE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("originalSubmissionKey",dataLoad.getSubmissionKey());
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("offerKey",dataLoad.getOfferKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request for get related submissions")
    public void iSendRequestForGetRelatedSubmissions() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey", DataLoad.getInstance().getSubmissionKey());
        RestUtil.get(pathparams,true);

    }

    @Then("I should see the response with related submissions and status code as {int}")
    public void iShouldSeeTheResponseWithRelatedSubmissionsAndStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
       Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get related submissions");
       Validations.assertEquals(dataLoad.getSubmissionKey(),RestResponse.getArrayResponseList().get(0).get("submissionKey").toString(),"Validation of submission key in get related submissions response");
        Validations.assertEquals(dataLoad.getParticipantKey(),RestResponse.getArrayResponseList().get(0).get("participantKey").toString(),"Validation of participant key in get related submissions response");
       Validations.assertEquals(dataLoad.getPurchaseIncentiveKey(),RestResponse.getArrayResponseList().get(0).get("purchaseIncentiveKey").toString(),"Validation of purchase incentive key in get related submissions response");
       //Validations.assertEquals(dataLoad.getOfferKey(),RestResponse.getArrayResponseList().get(0).get("offerKey").toString(),"Validation of offer key in get related submissions response");
        Validations.assertEquals(true,RestResponse.getBody().jsonPath().getJsonObject("invoices.invoiceKey").toString().contains(dataLoad.getInvoiceKey()),"Validation of invoice key("+dataLoad.getInvoiceKey()+") present get related submissions response");
        System.out.println(RestResponse.getAsString());
    }

    @And("I send a request to update a  submission review")
    public void iSendARequestToUpdateASubmissionReview(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.UPDATEREVIEW);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey",dataLoad.getSubmissionKey());
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
    }

    @Then("I should see the review updated successfully with status code as {int}")
    public void iShouldSeeTheReviewUpdatedSuccessfullyWithStattusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for Update review");
    }

    @And("I send request to update submission claim")
    public void iSendRequestToUpdateSubmissionClaim(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.UPDATESUBMISSIONCLAIM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("offerKey",dataLoad.getOfferKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionClaimKey",dataLoad.getSubmissionClaimKey());
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
    }

    @Then("I should see submission claim update successfully with status code as {int}")
    public void iShouldSeeSubmissionClaimUpdateSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for Update submission claim");
    }

    @And("I send request for get submissions by participant key")
    public void iSendRequestForGetSubmissionsByParticipantKey(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.GETSUBMISSIONSBYPARTICPANTKEYS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see a list of submissions done by the given participant key with status code as {int}")
    public void iShouldSeeAListOfSubmissionsDoneByTheGivenParticipantKeyWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get submissions by participant key");
        Validations.assertEquals(dataLoad.getSubmissionKey(),RestResponse.getArrayResponseList().get(0).get("submissionKey").toString(),"Validation of submission key in get submissions  by participant key response");
        Validations.assertEquals(dataLoad.getParticipantKey(),RestResponse.getArrayResponseList().get(0).get("participantKey").toString(),"Validation of participant key in get submissions by participant key response");
        Validations.assertEquals(dataLoad.getPurchaseIncentiveKey(),RestResponse.getArrayResponseList().get(0).get("purchaseIncentiveKey").toString(),"Validation of purchase incentive key in get submissions by participant key response");
        Validations.assertEquals(true,RestResponse.getBody().jsonPath().getJsonObject("invoices.invoiceKey").toString().contains(dataLoad.getInvoiceKey()),"Validation of invoice key("+dataLoad.getInvoiceKey()+") present get submissions by participant key response");
        System.out.println("Response:"+RestResponse.getAsString());
    }

    @And("I send request to get can user create submission request")
    public void iSendRequestToGetCanUserCreateSubmissionRequest() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",DataLoad.getInstance().getPurchaseIncentiveKey());
        pathparams.put("participantKey",DataLoad.getInstance().getParticipantKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the response as {string} with status code as {int} for {string}")
    public void iShouldSeeTheResponseAs(String canUserCreateSubmission, int statusCode,String serviceName) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for "+serviceName);
        Validations.assertEquals(canUserCreateSubmission, RestResponse.getAsString(),"Validation for '"+serviceName+"' status");
        System.out.println("Response:"+RestResponse.getAsString());
    }

    @And("I send request to get submission claim by participant key")
    public void iSendRequestToGetSubmissionClaimByParticipantKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("participantKey",DataLoad.getInstance().getParticipantKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see a list of submission claim for the given participant key with status code as {int}")
    public void iShouldSeeAListOfSubmissionClaimForTheGivenParticipantKeyWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get submission claim by participant key");
        Validations.assertEquals(dataLoad.getSubmissionKey(),RestResponse.getArrayResponseList().get(0).get("submissionKey").toString(),"Validation of submission key in get related submissions response");
        Validations.assertEquals(dataLoad.getSubmissionClaimKey(),RestResponse.getArrayResponseList().get(0).get("submissionClaimKey").toString(),"Validation of submission claim key in get related submissions response");
        Validations.assertEquals(true,RestResponse.getArrayResponseList().get(0).get("submission").toString().contains(dataLoad.getParticipantKey()),"Validation of participant key in get related submissions response");
        Validations.assertEquals(true,RestResponse.getArrayResponseList().get(0).get("submission").toString().contains(dataLoad.getPurchaseIncentiveKey()),"Validation of purchase incentive key in get related submissions response");
        System.out.println("Response:"+RestResponse.getAsString());
    }

    @And("I send request to get customer service offerings")
    public void iSendRequestToGetCustomerServiceOfferings() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey",DataLoad.getInstance().getSubmissionKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the offering details in response with status code as {int}")
    public void iShouldSeeTheOfferingDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get customer offering response");
        Validations.assertEquals(true,RestResponse.getArrayResponseList().toString().contains(dataLoad.getOfferingKey()),"Validation of offering key in get customer offerings response");
        System.out.println("Response:"+RestResponse.getAsString());
    }


    @And("I send request for create submission and claim with no review required")
    public void iSendRequestForCreateSubmissionAndClaimWithNoReviewRequired(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESUBMISSIONANDCLAIMWITHNOREVIEWREQUIRED);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        dataMap.put("desiredOfferKey",dataLoad.getOfferKey());
        dataLoad.setInvoiceKey(dataMap.get("invoiceKey"));
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("purchaseIncentiveKey",dataLoad.getPurchaseIncentiveKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request to create submission claim")
    public void iSendRequestToCreateSubmissionClaim(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESUBMISSIONCLAIM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("invoiceKey",dataLoad.getInvoiceKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey",dataLoad.getSubmissionKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see claim created successfully with status code as {int}")
    public void iShouldSeeClaimCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println("Response:"+RestResponse.getAsString());
        DataLoad.getInstance().setSubmissionClaimKey(RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create submission claim");
    }

    @And("I send request for resubmission")
    public void iSendRequestForResubmission(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATERESUBMISSION);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("invoiceKey",dataLoad.getInvoiceKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey",dataLoad.getSubmissionKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see the resubmission is completed successfully with status code as {int}")
    public void iShouldSeeTheResubmissionIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for resubmission");
        System.out.println("Response:"+RestResponse.getAsString());
    }

    @And("I send request for verify can user resubmit")
    public void iSendRequestForVerifyCanUserResubmit(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CANUSERRESUBMIT);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionKey",dataLoad.getSubmissionKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }


    @And("I send request for get related submission overrides")
    public void iSendRequestForGetRelatedSubmissionOverrides() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionOverrideKey",DataLoad.getInstance().getSubmissionOverrideKey());
        System.out.println(DataLoad.getInstance().getSubmissionOverrideKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the response with related submission overrides with status code as {int}")
    public void iShouldSeeTheResponseWithRelatedSubmissionOverridesWithStatusCodeAs(int statusCode) {
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get related submission overrides");
    }

    @Then("I should see the response with status code as {int} with submission override key")
    public void iShouldSeeTheResponseWithStatusCodeAsWithSubmissionOverrideKey(int statusCode) {
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create submission override");
        DataLoad.getInstance().setSubmissionOverrideKey(RestResponse.getAsString());

    }

    @And("I send request to create submission claim override")
    public void iSendRequestToCreateSubmissionClaimOverride(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATECLAIMOVERRIDE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("offerKey",dataLoad.getOfferKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionOverrideKey",dataLoad.getSubmissionOverrideKey());
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @Then("I should see claim override created successfully with status code as {int}")
    public void iShouldSeeClaimOverrideCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for create submission claim override");
        //DataLoad.getInstance().setSubmissionClaimOverrideKey(RestResponse.getAsString());
    }

    @And("I send request to get claims by filter")
    public void iSendRequestToGetClaimsByFilter() {
        Map<String,String> queryparams = new HashMap<>();
        queryparams.put("submissionKey",DataLoad.getInstance().getSubmissionKey());
        queryparams.put("participantKey",DataLoad.getInstance().getParticipantKey());
        queryparams.put("purchaseIncentiveKey",DataLoad.getInstance().getPurchaseIncentiveKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see a list of claims in response with status code as {int}")
    public void iShouldSeeAListIfClaimsInResponseWithStatusCodeAs(int statusCode) {
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get claim by filter");
    }

    @And("I send request to get claims by claim key")
    public void iSendRequestToGetClaimsByByClaimKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("submissionClaimKey",DataLoad.getInstance().getSubmissionClaimKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see claim details in response with status code as {int}")
    public void iShouldSeeClaimDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get claim");
        Validations.assertEquals(dataLoad.getSubmissionClaimKey(), RestResponse.getArrayResponseList().get(0).get("submissionClaimKey").toString(),"Validation of submission claim key in response");
        Validations.assertEquals(dataLoad.getSubmissionKey(), RestResponse.getArrayResponseList().get(0).get("submissionKey").toString(),"Validation of submission key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("claimedOffers.offerKey").toString().contains(dataLoad.getOfferKey()),"Validation of offer key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("claimedOfferDetails.purchaseIncentiveKey").toString().contains(dataLoad.getPurchaseIncentiveKey()),"Validation of purchase incentive key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("invoices.invoiceKey").toString().contains(dataLoad.getInvoiceKey()),"Validation of invoice key in response");
    }

    @And("I send request to get claims by participant keys")
    public void iSendRequestToGetClaimsByParticipantKeys(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.GETSUBMISSIONSBYPARTICPANTKEYS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("participantKey",dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see submission claim details in response with status code as {int}")
    public void iShouldSeeSubmissionClaimDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get claim");
        Validations.assertEquals(dataLoad.getSubmissionClaimKey(), RestResponse.getBody().jsonPath().getJsonObject("submissionClaimKey").toString(),"Validation of submission claim key in response");
        Validations.assertEquals(dataLoad.getSubmissionKey(), RestResponse.getBody().jsonPath().getJsonObject("submissionKey").toString(),"Validation of submission key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("claimedOffers.offerKey").toString().contains(dataLoad.getOfferKey()),"Validation of offer key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("claimedOfferDetails.purchaseIncentiveKey").toString().contains(dataLoad.getPurchaseIncentiveKey()),"Validation of purchase incentive key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("invoices.invoiceKey").toString().contains(dataLoad.getInvoiceKey()),"Validation of invoice key in response");
    }

    @And("I send request to get claims by submission keys")
    public void iSendRequestToGetClaimsBySubmissionKeys() {
        Map<String,String> queryparams = new HashMap<>();
        queryparams.put("submissionKeys",DataLoad.getInstance().getSubmissionKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see claims in response with status code as {int}")
    public void iShouldSeeClaimsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println("Response:"+RestResponse.getAsString());
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for get claim");
        Validations.assertEquals(dataLoad.getSubmissionClaimKey(), RestResponse.getArrayResponseList().get(0).get("submissionClaimKey").toString(),"Validation of submission claim key in response");
        Validations.assertEquals(dataLoad.getSubmissionKey(), RestResponse.getArrayResponseList().get(0).get("submissionKey").toString(),"Validation of submission key in response");
        Validations.assertEquals(true, RestResponse.getBody().jsonPath().getJsonObject("claimedOffers.offerKey").toString().contains(dataLoad.getOfferKey()),"Validation of offer key in response");

    }

}
