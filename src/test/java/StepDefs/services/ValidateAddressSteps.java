package StepDefs.services;

import StepDefs.Validations;
import configManager.Payloads;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

public class ValidateAddressSteps {

    @Given("I am able to get the validate address API")
    public void i_am_able_to_get_the_validate_address_API() {
        RestUtil.initialize("","");
    }

    @When("I send request with below address")
    public void i_send_request_with_below_address(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.VALIDATE_ADDRESS);
        String jsonData = genericUtil.jsonConstruct(StepUtil.toMap(dataTable),payload);
        RestUtil.post(jsonData,false);
    }

    @Then("I should see corrected address in response")
    public void i_should_see_corrected_address_in_response() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status Code");
        Validations.assertEquals(true,!RestResponse.getValue("correctedAddress.address1").equals(""),"Corrected Address");
        Validations.assertEquals(true,!RestResponse.getValue("submittedAddress.address1").equals(""),"Submitted Address");
    }

}
