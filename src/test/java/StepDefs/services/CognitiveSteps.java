package StepDefs.services;

import StepDefs.Validations;
import configManager.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

public class CognitiveSteps {


    @And("I send request for profanity check with below text and culture")
    public void iSendRequestForProfanityCheckWithBelowTextAndCulture(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.SCREEN_PROFANITY);
        String jsonData = genericUtil.jsonConstruct(StepUtil.toMap(dataTable),payload);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see the response with no profanity")
    public void iShouldSeeTheResponseWithNoProfanity() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        Validations.assertEquals(false,RestResponse.getValue("isProfanityFound"),"isProfanityFound");
    }

    @Then("I should see the response with profanity")
    public void iShouldSeeTheResponseWithProfanity() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        Validations.assertEquals(true,RestResponse.getValue("isProfanityFound"),"isProfanityFound");
    }

    @And("I should see error message {string} for empty screen text")
    public void iShouldSeeErrorMessageForEmptyScreenText(String errorMessage) {
        Validations.assertEquals(errorMessage,RestResponse.getValue("errors.text[0]"), "Error Message");
    }

}
