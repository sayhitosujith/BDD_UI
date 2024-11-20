package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.Map;

public class EmailSteps {
    @When("I am able to get the following endpoint and generate token for {string}")
    public void iAmAbleToGetTheFollowingEndpointAndGenerateTokenFor(String arg0, io.cucumber.datatable.DataTable dataTable) {

        StepUtil.generateParticipantToken(arg0);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + dataMap.get("url")), ResourceData.getEndPoint(dataMap.get("endpoint")));
        System.out.println(ResourceData.getEnvironment() + "." + dataMap.get("url"));
    }

    @And("I send request to send email with below details")
    public void iAmGettingTheParticipantTokenForTheScope(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.EMAIL_SENDEMAIL);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataLoad.setUsername(dataMap.get("userName"));
        dataLoad.setCity(dataMap.get("city"));
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);
    }


    @Then("I should send email successfully")
    public void iShouldSendEmailSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code to verify email sent successfully");
    }

    @And("I send request to send emails with below details")
    public void iSendRequestToSendEmailsWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.EMAIL_SENDEMAILS);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataLoad.setUsername(dataMap.get("userName"));
        dataLoad.setCity(dataMap.get("city"));
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);

    }


}
