package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TeamSteps {
    @Then("I should see the response with a list of all Teams")
    public void iShouldSeeTheResponseWithAListOfAllTeams() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for Get all Teams");
    }

    @And("I send request for create Team with below details ward")
    public void iSendRequestForCreateTeamWithBelowDetailsWard(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATETEAM);
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("accountKey",dataLoad.getEngageAccountKey());
        dataMap.put("userIds",dataLoad.getEngageUserKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        dataLoad.setTeamname(dataMap.get("teamName"));
        RestUtil.post(jsonData,true);
    }

    @Then("I should see the team created with status code as {int}")
    public void iShouldSeeTheTeamCreatedwithStatusCodeAs(int arg0) {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for team creation");
        DataLoad dataLoad=DataLoad.getInstance();
        dataLoad.setTeamKey(RestResponse.getAsString());
        System.out.println("Team Key :"+RestResponse.getAsString());
    }

    @And("I send request for get all teams")
    public void iSendRequestForGetAllTeams() {
        RestUtil.get(true);
    }

    @And("I send request for get team with accountkey")
    public void iSendRequestForGetTeamWithAccountkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        System.out.println("Account Key" +dataLoad.getEngageAccountKey());
        queryparams.put("accountKey",dataLoad.getEngageAccountKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see team details added to the given account")
    public void Ishouldseeteamdetailsaddedtothegivenaccount() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for team list of accounts associated by that account key");
        Validations.assertEquals(dataLoad.getEngageAccountKey(),RestResponse.getArrayResponseList().get(0).get("accountKey").toString(),"Validation on Account Key in the repsonse");
        Validations.assertEquals(dataLoad.getTeamKey(),RestResponse.getArrayResponseList().get(0).get("teamKey").toString(),"Validation on team Key in the repsonse");

    }

    @And("I send request for get team with teamkey")
    public void iSendRequestForGetTeamWithTeamkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> queryparams = new HashMap<String,String>();
        System.out.println("TeamKey" +dataLoad.getTeamKey());
        queryparams.put("teamKey",dataLoad.getTeamKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see team details added to the given account with TeamKey")
    public void iShouldSeeTeamDetailsAddedToTheGivenAccountWithTeamKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification to see team details added to the given account with TeamKey");

    }

    @Then("I should see teamkey in response")
    public void iShouldSeeTeamKeyInResponseWithTeamKey() {
        System.out.println(RestResponse.getStatusCode());
        boolean containsTeamKey = false;
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Validation of status code");
        ArrayList<HashMap<String,String>> responseList = RestResponse.getBody().jsonPath().get();
        for(int i = 0;i< responseList.size();i++){
            if(responseList.get(i).containsValue(DataLoad.getInstance().getTeamKey())){
                containsTeamKey = true;
            }
        }
        Validations.assertEquals(true,containsTeamKey,"Validation of teamKey in response");
    }

    @And("I send request for get all teams with teamkey")
    public void iSendRequestForGetAllTeamsWithTeamkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        System.out.println("TeamKey" +dataLoad.getTeamKey());
        queryparams.put("teamKey",dataLoad.getTeamKey());
        RestUtil.get(queryparams,true);
        System.out.println(RestResponse.getStatusCode());
    }


    @And("I send request for Update team with teamkey")
    public void iSendRequestForUpdateTeamWithTeamkey(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATETEAM);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("teamKey", dataLoad.getTeamKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @And("I send request for Delete team")
    public void iSendRequestForDeleteTeam() {
        System.out.println(RestResponse.getStatusCode());
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("teamKey", dataLoad.getTeamKey());
        RestUtil.delete(pathparams,true);
    }

    @Then("I should see team Deleted Successfully")
    public void iShouldSeeTeamDeletedSuccessfully() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Validation of Delete Team");
    }

    @And("I send request Add Users to team")
    public void iSendRequestAddUsersToTeam(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDUSERTOTEAM);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("EngageUserKeys", dataLoad.getEngageUserKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
    }

    @Then("I should see Users added to team Successfully")
    public void iShouldSeeUsersAddedToTeamSuccessfully() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200, RestResponse.getStatusCode(),"User added successfully");
    }

    @And("I send request Remove Users from team")
    public void iSendRequestRemoveUsersToTeam(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_REMOVEUSERTOTEAM);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("userKey",dataLoad.getEngageUserKey());
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("teamKey", dataLoad.getTeamKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.delete(pathparams, jsonData,true);
    }

    @Then("I should see Users Removed from team Successfully")
    public void iShouldSeeUsersRemovedFromTeamSuccessfully() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Validation to Remove Users from Team");
    }

    @Then("I should see teamkey in team details response")
    public void iShouldSeeTeamkeyInTeamDetailsResponse() {
        System.out.println(RestResponse.getStatusCode());
        boolean containsTeamKey = false;
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Validation of status code");
        Validations.assertEquals(DataLoad.getInstance().getTeamKey(),RestResponse.getValue("teamKey"),"Validation of teamKey in response");

    }

    @Then("I should see newly added user in the team details response with status code as {int}")
    public void iShouldSeeNewlyAddedUserInTheTeamDetailsResponse(int statuscode) {
        Validations.assertEquals(statuscode,RestResponse.getStatusCode(),"Status Code validation for response");
    }
}
