package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngageSteps{

    @And("I send request for create account with below details")
    public void iSendRequestForCreateAccountWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ENGAGE_ACCOUNTS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData,true);
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setEngageAccountKey(dataMap.get("accountKey"));
        dataLoad.setEngageAccountName(dataMap.get("name"));
    }

    @Then("I should see account created successfully with status code {int}")
    public void iShouldSeeAccountCreatedSuccessfully(int arg0) {
        if (!EngagementsSteps.EngagementExists) {
            System.out.println("Account Created Successfully:" + DataLoad.getInstance().getEngageAccountName());
            Validations.assertEquals(arg0, RestResponse.getStatusCode(), "Status Code Verification of Account Creation");
        }
    }
    @Then("I should see account created successfully")
    public void iShouldSeeAccountCreatedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code to see account created successfully");
        System.out.println("Account Key:"+DataLoad.getInstance().getEngageAccountKey());

    }

    @And("I send request for search account with below query parameters")
    public void iSendRequestForSearchAccountWithBelowQueryParameters(io.cucumber.datatable.DataTable dataTable) {
        RestUtil.get(StepUtil.toMap(dataTable),true);
    }

    @When("I send a request to get created account")
    public void iSendARequestToGetCreatedAccount() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("accountKey",dataLoad.getEngageAccountKey());
        RestUtil.get(pathParams,true);
    }

    @Then("I should see newly created account in the response")
    public void iShouldSeeNewlyCreatedAccountInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
    }

    @And("I send a request to update created account with following details")
    public void iSendARequestToUpdateCreatedAccountWithFollowingDetails(io.cucumber.datatable.DataTable dataTable) {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATE_ENGAGE_ACCOUNTS);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("accountKey",dataLoad.getEngageAccountKey());
        RestUtil.post(pathParams,jsonData,true);
    }

    @Then("I should see account should be updated")
    public void iShouldSeeAccountShouldBeUpdated() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code");
    }

    @And("I send request to get all accounts")
    public void iSendRequestToGetAllAccounts() {
        RestUtil.get(true);
    }

    @Then("I should see list of accounts in the response")
    public void iShouldSeeListOfAccountsInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        Validations.assertEquals(true, RestResponse.getList("").size()>=1,"Displaying List of Accounts");
        Validations.assertEquals(true, RestResponse.getList("").get(0).containsKey("accountKey"),"accountKey in List of Accounts");
    }

    @And("I send request for search account")
    public void iSendRequestForSearchAccount() {
        RestUtil.get(true);
    }

    @Then("I should see list of search accounts in the response")
    public void iShouldSeeListOfSearchAccountsInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        Validations.assertEquals(true, RestResponse.getList("data").size()>=1,"Displaying List of Accounts");
        Validations.assertEquals(true, RestResponse.getList("data").get(0).containsKey("accountKey"),"accountKey in List of Accounts");
    }

    @And("I get continuation token from search account")
    public void iGetContinuationTokenFromSearchAccount() {
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setContinuationToken(RestResponse.getValue("continuation"));
    }

    @And("I send request for search account with continuation token")
    public void iSendRequestForSearchAccountWithContinuationToken() {
        DataLoad dataLoad = DataLoad.getInstance();
        Map<String,String> map = new HashMap<String,String>();
        map.put("continuation",dataLoad.getContinuationToken());
        RestUtil.get(map,true);
    }

    @And("I send a request create new Authentication Provider for an Account with following details")
    public void iSendARequestCreateNewAuthenticationProviderForAnAccountWithFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_ENGAGE_ACCOUNT_AUTHENTICATION_PROVIDER);
        Map<String,String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap,payload);
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("accountKey",dataLoad.getEngageAccountKey());
        RestUtil.post(pathParams,jsonData,true);
        String accountKey = dataMap.get("authenticationScheme");
        dataLoad.setAuthenticationScheme(accountKey);
    }

    @Then("I should see Authentication Provider in the response")
    public void iShouldSeeAuthenticationProviderInTheResponse() {
        Validations.assertEquals(204, RestResponse.getStatusCode(),"Status Code");
    }


    @And("I send a request to get Account Authentication Providers")
    public void iSendARequestToGetAccountAuthenticationProviders() {
        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String,String> pathParams = new HashMap<String,String>();
        pathParams.put("accountKey",dataLoad.getEngageAccountKey());
        RestUtil.get(pathParams,true);
    }


    @Then("I should see Authentication Providers in the response")
    public void iShouldSeeAuthenticationProvidersInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(),"Status Code");
        DataLoad dataLoad = DataLoad.getInstance();
        List<HashMap<String,String>> accountsAuthProviders = RestResponse.getList("externalAuthenticationProviders");
        int index = 0;
        for(int i=0;i<accountsAuthProviders.size();i++){
            if(accountsAuthProviders.get(i).get("authenticationScheme").equals(dataLoad.getAuthenticationScheme())){
                index = i;
                break;
            }
        }
        Validations.assertEquals(dataLoad.getAuthenticationScheme(), accountsAuthProviders.get(index).get("authenticationScheme"),"authenticationScheme");
        String accountAuthenticationProviderKey = accountsAuthProviders.get(index).get("accountAuthenticationProviderKey");
        System.out.println("accountAuthenticationProviderKey = "+accountAuthenticationProviderKey);
    }



}
