package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    @And("I send get request for search ward participants")
    public void iSendGetRequestForSearchWardParticipants() throws Exception {

        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount() ;
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("wardKey", hashMap.get("wardKey").toString());
        queryparams.put("lastName","smith");
        queryparams.put("phone","DO_NOT_SAVE_PII");
        RestUtil.get(queryparams,true);

    }

    @Then("I should see get search ward participants successful")
    public void iShouldSeeGetSearchWardParticipantsSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validated for search ward participants");
    }

    @And("I send get request for search participants claims")
    public void iSendGetRequestForSearchParticipantsClaims() throws Exception {

        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount() ;
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("wardKey", hashMap.get("wardKey").toString());
        queryparams.put("engagementKey", hashMap.get("engagementKey").toString());
        queryparams.put("eventkey","8686de08-a182-4d67-b014-a32b69b084a3");
        RestUtil.get(queryparams,true);
    }

    @Then("I should see get search participants claims successful")
    public void iShouldSeeGetSearchParticipantsClaimsSuccessful() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validated for search participants claims");

    }
}
