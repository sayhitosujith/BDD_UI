package StepDefs.services;

import configManager.DataLoad;
import configManager.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.HashMap;
import java.util.Map;

public class RolesSteps {

    @And("I send request for create a role with below details")
    public void iSendRequestForCreateARoleWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_CREATEROLE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        System.out.println(dataMap);
        dataMap.put("accountKey",dataLoad.getEngageAccountKey());
        System.out.println(dataMap);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        dataLoad.setRoleKey(RestResponse.getAsString());
    }

    @And("I send a request to get created role")
    public void iSendARequestToGetCreatedRole() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("rolekey",dataLoad.getRoleKey());
        RestUtil.get(pathParams,true);
    }

    @And("I send a request to get created role with filter")
    public void iSendARequestToGetCreatedRoleWithFilter() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> queryParams = new HashMap<String,String>();
        queryParams.put("account_key",dataLoad.getEngageAccountKey());
        RestUtil.get(queryParams,true);
    }


    @When("I send request to update created role with following data")
    public void iSendRequestToUpdateCreatedRoleWithFollowingData(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_UPDATEROLE);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        HashMap<String,String> pathparams = new HashMap<String,String>();
        pathparams.put("rolekey",dataLoad.getRoleKey());
        RestUtil.post(pathparams,jsonData,true);
    }
}
