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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSteps
{
    @And("I send request for create user with below details")
    public void iSendRequestForCreateUserWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEUSER);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("accountKey",dataLoad.getEngageAccountKey());
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }
    @And("I should see the team created successfully status code as 201")
    public void iSendRequestForCreateUserWithBelowDetailsWard() {
        Validations.assertEquals(201, RestResponse.getStatusCode(),"Status Code");
        String userKey = RestResponse.getAsString();
        System.out.println("User created successfully with Userkey:"+userKey);
        DataLoad.getInstance().setEngageUserKey(userKey);
    }

    @Then("I should see newly created User key in the response")
    public void iShouldSeeNewlyCreatedUserKeyInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        String userKey = RestResponse.getAsString();
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setEngageUserKey(userKey);
    }

    @And("I send request for Change user password with below details")
    public void iSendRequestForChangeUserPasswordWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CHANGEUSEPASSWORD);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("id",dataLoad.getEngageUserKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
    }

    @Then("I should see newly Updated Password in the response")
    public void iShouldSeeNewlyUpdatedPasswordInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code for Password change");
    }

    @And("I send request for adding a role to the user")
    public void iSendRequestForAddingRoleToTheUser(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVEROLETOUSER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("userkeys", dataLoad.getEngageUserKey());
        RestUtil.post(pathparams,jsonData,true);
        System.out.println(jsonData);
        dataLoad.setRoleKeys(dataMap.get("RoleKeys"));
    }



    @And("I send request for remove roles with below details")
    public void iSendRequestForRemoveRolesWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ADDREMOVEROLETOUSER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("userkeys", dataLoad.getEngageUserKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData,true);
    }

    @And("I send request for Is user ward member with wardKey")
    public void iSendRequestForIsUserWardMemberWithWardKey() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("userkeys", dataLoad.getEngageUserKey());
        pathparams.put("wardKey", dataLoad.getWardKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see {string} as the user is not added to the Ward with status code as {int}")
    public void iShouldSeeAsTheUserIsNotAddedToTheWard(String isUserWardMember, int statuscode) {
        Validations.assertEquals(statuscode,RestResponse.getStatusCode(),"Status code validation");
        Validations.assertEquals("false",RestResponse.getAsString(),"Validation of is user Ward member");
    }

    @And("I send request for search user by accountKey filter")
    public void iSendRequestForSearchUserByAccountKeyFilter() {
        Map<String,String> queryparams = new HashMap<String,String>();
        queryparams.put("account_key",DataLoad.getInstance().getEngageAccountKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see user details in the response")
    public void iShouldSeeUserDetailsInTheResponse() {
      HashMap<String,String> hashMap = RestResponse.getBody().jsonPath().get("data[0]");
      Validations.assertEquals(true,hashMap.containsValue(DataLoad.getInstance().getEngageUserKey()),"Validation of Engage User Key in response");
    }

    @And("I send request for search user with teamName, roleKey and accountkey")
    public void iSendRequestForSearchUserWithTeamNameRoleKeyAndAccountkey() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("roleKey",dataLoad.getRoleKeys());
        queryparams.put("teamName", dataLoad.getTeamname());
        queryparams.put("account_key", dataLoad.getEngageAccountKey());
        RestUtil.get(queryparams,true);
    }

    @Then("I should see the newly created user in the response with status code as {int}")
    public void iShouldSeeTheNewlyCreatedUserInTheResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode,RestResponse.getStatusCode(),"Status code validation for Search User");
        Validations.assertEquals(DataLoad.getInstance().getEngageUserKey(),RestResponse.getValue("data[0].engageUserKey"),"Validation of User Key presence in Response");
    }

    @And("I send request for delete user")
    public void iSendRequestForDeleteUser() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("userkeys", dataLoad.getEngageUserKey());
        RestUtil.delete(pathparams,true);
    }
}
