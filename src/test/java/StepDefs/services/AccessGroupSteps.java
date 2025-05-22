package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccessGroupSteps {

    @And("I send request for get all access groups")
    public void iSendRequestForGetAllAccessGroups() {
        RestUtil.get(true);
    }


    @Then("I should see a list containing all access groups")
    public void iShouldSeeAListContainingAllAccessGroups() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get all access group");
    }

    @And("I send a request to get all access group by filter with limit as {string} skip as {string}")
    public void iSendARequestToGetAllAccessGroupByFilterWithLimitAsSkipAs(String arg0, String arg1) {
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("limit",arg0);
        queryparams.put("skip",arg1);
        RestUtil.get(queryparams,true);
    }

    @Then("I should see a list of {int} access groups")
    public void iShouldSeeAListOfAccessGroups(int arg0) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get access group by filter");
        Validations.assertEquals(arg0,RestResponse.getList("data").size(),"Verification of number of access groups in response");
    }


    @And("I send a request to get all access group by filter with limit as {string} , skip as {string} and  wardKey")
    public void iSendARequestToGetAllAccessGroupByFilterWithLimitAsSkipAsAndWardKey(String arg0, String arg1) {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("limit",arg0);
        queryparams.put("skip",arg1);
        queryparams.put("wardKey",dataLoad.getWardKey());
        queryparams.put("engagementKey",dataLoad.getEngagementKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see a list of {int} access groups for the given wardKey")
    public void iShouldSeeAListOfAccessGroupsForTheGivenWardKey(int arg0) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get access group by filter");
        Validations.assertEquals(arg0,RestResponse.getList("data").size(),"Verification of number of access groups in response");
        Validations.assertEquals(dataLoad.getWardKey(), RestResponse.getValue("data[0].wardKey"), "Verification of wardkey for accessgroup");
        dataLoad.setContinuationToken(RestResponse.getValue("continuation"));
    }



    @Then("I should see a list of access groups that are added to the given engagements")
    public void iShouldSeeAListOfAccessGroupsThatAreAddedToTheEngagements() {
        System.out.println(RestResponse.getAsString());
        String responseString = RestResponse.getAsString();
        System.out.println(RestResponse.getStatusCode());
        DataLoad dataLoad = DataLoad.getInstance();
        boolean containsAccessGroupKey = false;
        if(responseString.contains(dataLoad.getAccessgroupkey())){
            containsAccessGroupKey = true;
        }
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get access group by filter");
        EngagementsSteps engagementsSteps = new EngagementsSteps();
        if(!engagementsSteps.EngagementExists)
        Validations.assertEquals(true,containsAccessGroupKey,"Verification of Access Group Key in the response");
    }


    @And("I send a request to get all access group by filter with teamkey")
    public void iSendARequestToGetAllAccessGroupsByFilterWithTeamkey() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("name",dataLoad.getTeamname());
        queryparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see a list of access groups that are added to the team with given teamkey")
    public void iShouldSeeAListOfAccessGroupsThatAreAddedToTheTeamWithGivenTeamKey() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get access group by filter");
    }

    @And("I send a request to get all access group by filter with continuation token")
    public void iSendARequestToGetAllAccessGroupByFilterWithContinuationToken() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("continuationToken",dataLoad.getContinuationToken());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see a list of access groups")
    public void iShouldSeeAListOfAccessGroups() {
        System.out.println(RestResponse.getAsString());
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for get access group by filter");
    }

    @And("I send request for create access group with below details")
    public void iSendRequestForCreateAccessGroupWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEACCESSGROUP);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("wardKey", dataLoad.getWardKey());
        dataMap.put("engagementKeys",dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        String accessgroup = dataMap.get("name");
        dataLoad.setAccessgroupname(accessgroup);
    }

    @Then("I should see the access group created successfully status code as {int}")
    public void iShouldSeeTheAccessGroupCreatedSuccessfullyStatusCodeAs(int arg0) {
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setAccessgroupkey(RestResponse.getAsString());
        System.out.println("Access Group Created Successfully:"+DataLoad.getInstance().getAccessgroupname());
        System.out.println("Access Group Key:"+DataLoad.getInstance().getAccessgroupkey());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for access group creation");
    }

    @Then("I should see the newly created access group in the response with continuation token")
    public void iShouldSeeTheNewlyCreatedAccessGroupInTheResponseWithContinuationToken() {
        System.out.println(RestResponse.getStatusCode());
        Boolean containsAccessGroupKey = false;
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for valid response");
        DataLoad dataLoad = DataLoad.getInstance();
        List<HashMap<String,String>> responseList = RestResponse.getBody().jsonPath().getList("data");
        for(int i=0;i<responseList.size();i++){
           if(responseList.get(i).containsValue(dataLoad.getAccessgroupkey())){
               containsAccessGroupKey = true;
           }
        }
        Validations.assertEquals(true,containsAccessGroupKey,"Validation of access group key in response");
    }

    @Then("I should see the newly created access group in the response")
    public void iShouldSeeTheNewlyCreatedAccessGroupInTheResponse() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code verification for valid response");
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(dataLoad.getAccessgroupkey(),RestResponse.getValue("accessGroupKey"),"Access Group Key validation");
    }


    @And("I send request for add engagements to the access group")
    public void iSendRequestForAddEngagementsToTheAccessGroup(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVEENGAGEMENTS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
    }

    @Then("I should see the response with status code as {int}")
    public void iShouldSeeTheResponseStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(),"Status Code verification valid response");
    }

    @And("I send request to remove engagements from the access group")
    public void iSendRequestToRemoveEngagementsFromTheAccessGroup(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVEENGAGEMENTS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey",dataLoad.getEngagementKey());
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.delete(pathparams,jsonData,true);
    }

    @And("I send request for get access group details")
    public void iSendRequestForGetAccessGroupDetails() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        RestUtil.get(pathparams,true);
    }

    @And("I send request for add team to the access group")
    public void iSendRequestForAddTeamToTheAccessGroup(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVETEAM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        if(dataMap.get("teamKey").equals("teamKey")) {
            dataMap.put("teamKey", dataLoad.getTeamKey());
        }
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.put(pathparams,jsonData,true);
    }

    @And("I send request for remove team to the access group")
    public void iSendRequestForRemoveTeamToTheAccessGroup(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVETEAM);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        System.out.println("Team :"+dataLoad.getTeamname());
        dataMap.put("teamname",dataLoad.getTeamname());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        RestUtil.delete(pathparams, jsonData, true);
    }

    @And("I send a request to get details of access group with given key")
    public void iSendARequestToGetDetailsOfAccessGroupWithGivenKey() {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("accessGroupKey",dataLoad.getAccessgroupkey());
        RestUtil.get(pathparams,true);
    }


    @Then("I should see the required access group details with status code as {int}")
    public void iShouldSeeTheRequiredAccessGroupDetailsWithStatusCodeAs(int arg0) {
        Validations.assertEquals(arg0, RestResponse.getStatusCode(),"Status Code verification valid response");
        Validations.assertEquals(DataLoad.getInstance().getWardKey(),RestResponse.getValue("wardKey"),"Verification of Wardkey in the response");
        Validations.assertEquals(DataLoad.getInstance().getAccessgroupname(),RestResponse.getValue("name"),"Verification of Access Group Name in the response");
    }

    @Then("I should see the required access group details with status code as {int} and newly added engagements")
    public void iShouldSeeTheRequiredAccessGroupDetailsWithStatusCodeAsAndNewlyAddedEngagements(int arg0) {
        Boolean containsProperty = false;
        int i;
        ArrayList<HashMap<String,String>> responselist= RestResponse.getBody().jsonPath().get("engagements");
        for(i=0; i<responselist.size();i++){
            if(responselist.get(i).containsValue(DataLoad.getInstance().getEngagementKey())){
                containsProperty = true;
            }
        }
        Validations.assertEquals(true,containsProperty,"Validation of Engagement key in response");
        Validations.assertEquals(arg0, RestResponse.getStatusCode(),"Status Code verification valid response");
        Validations.assertEquals(DataLoad.getInstance().getWardKey(),RestResponse.getValue("wardKey"),"Verification of Wardkey in the response");
        Validations.assertEquals(DataLoad.getInstance().getAccessgroupname(),RestResponse.getValue("name"),"Verification of Access Group Name in the response");
    }

    @And("I send a request to get all access group by filter with engagementkeys")
    public void iSendARequestToGetAllAccessGroupByFilterWithLimitAsSkipAsAndEngagementkeys() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("wardKey",dataLoad.getWardKey());
        queryparams.put("engagements", dataLoad.getEngagementKey());
        RestUtil.get(queryparams,true);
    }


}
