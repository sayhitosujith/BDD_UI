package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.*;

public class ListCheck {
    DataLoad dataLoad = DataLoad.getInstance();

    @And("I send request for create list with below details")
    public void iSendRequestForCreateListWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_LIST);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.createList"));
        RestUtil.post(jsonData, true);
        RestResponse.getAsString();
        HashMap<String, String> listCheckElements = new HashMap<String, String>();
        listCheckElements.put("name", dataMap.get("name"));
        listCheckElements.put("description", dataMap.get("description"));
        listCheckElements.put("item1", dataMap.get("FirstName"));
        listCheckElements.put("item2", dataMap.get("Lastname"));
        listCheckElements.put("item3", dataMap.get("Address"));
        listCheckElements.put("listKey", RestResponse.getAsString());
        this.dataLoad.setListCheckDetails(listCheckElements);

    }

    @Then("I should see list created successfully")
    public void iShouldSeeListCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code validated for created list");

    }

    @And("I send a get request to fetch list items")
    public void iSendAGetRequestToFetchListItems() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.get(pathparams, true);
    }

    @Then("I should see items associated to list in response")
    public void iShouldSeeObjectsAssociatedToListInResponse() {


        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get list");
        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();
        ArrayList<String> requestItems = new ArrayList<>();
        requestItems.add(this.dataLoad.getListCheckDetails().get("item1"));
        requestItems.add(this.dataLoad.getListCheckDetails().get("item2"));
        requestItems.add(this.dataLoad.getListCheckDetails().get("item3"));
        int objCount = 0;

        while (objCount <= responseItems.size() - 1) {
            Validations.assertEquals(this.dataLoad.getListCheckDetails().get("listKey"), responseItems.get(objCount).get("listKey").toString(), "Validated list key existence in " + requestItems.get(objCount) + " item ");
            Validations.assertEquals(requestItems.get(objCount), responseItems.get(objCount).get("object").toString(), "Validated the item --->" + requestItems.get(objCount));
            Validations.assertTrue(!responseItems.get(objCount).get("itemKey").toString().isEmpty(), responseItems.get(objCount).get("itemKey").toString() + "---> Itemkey is exist ");
            objCount++;
        }


    }

    @And("I send request for create ward list")
    public void iSendRequestForCreateWardList(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.WARD_LIST);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);

        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        String wardK=hashMap.get("wardKey").toString();
        HashMap pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", wardK);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("ListCheck");
        StepUtil.generateEngageTrustedIdentifierToken("ListCheck");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.createWardList"));
        RestUtil.post(pathparams, jsonData, true);
        RestResponse.getAsString();
        HashMap<String, String> listCheckElements = new HashMap<String, String>();
        listCheckElements.put("name", dataMap.get("name"));
        listCheckElements.put("description", dataMap.get("description"));
        listCheckElements.put("listKey", RestResponse.getAsString());
        listCheckElements.put("wardKey", wardK);
        this.dataLoad.setListCheckDetails(listCheckElements);
    }

    @And("I send a get request to fetch ward list")
    public void iSendAGetRequestToFetchWardList() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("wardKey", this.dataLoad.getListCheckDetails().get("wardKey"));
        RestUtil.get(pathparams, true);
    }

    @Then("I should see lists associated to ward in response")
    public void iShouldSeeListsAssociatedToWardInResponse() {

        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for get lists by ward");
        String list_Key = this.dataLoad.getListCheckDetails().get("listKey");
        List<HashMap<Object, Object>> allWardLists = RestResponse.getArrayResponseList();
        int numberOFLists = allWardLists.size();
        boolean isListExist = false;


        for (int list = 0; list <= numberOFLists - 1; list++) {
            String listKeyInResponse = allWardLists.get(list).get("listKey").toString();
            if (listKeyInResponse.equals(list_Key)) {

                Validations.assertEquals(this.dataLoad.getListCheckDetails().get("listKey"),  allWardLists.get(list).get("listKey").toString(), "Validated listKey -->" +  allWardLists.get(list).get("listKey").toString() + " associated to " +  allWardLists.get(list).get("wardKey").toString() + " ward in response");
                Validations.assertEquals(this.dataLoad.getListCheckDetails().get("name"), allWardLists.get(list).get("name").toString(), "Validated name -->" +  allWardLists.get(list).get("name").toString() + " associated to " +  allWardLists.get(list).get("wardKey").toString() + " ward in response" );
                Validations.assertEquals(this.dataLoad.getListCheckDetails().get("description"), allWardLists.get(list).get("description").toString(), "Validated description --> " +  allWardLists.get(list).get("description").toString() + " associated to " +  allWardLists.get(list).get("wardKey").toString() + " ward in response");
                Validations.assertEquals(this.dataLoad.getListCheckDetails().get("wardKey"), allWardLists.get(list).get("wardKey").toString(), "Validated wardkey --> " +  allWardLists.get(list).get("wardKey").toString() + " associated to " +  allWardLists.get(list).get("wardKey").toString() + " ward in response");
                isListExist = true;

            }

        }
        Validations.assertTrue(isListExist, "ward list verified successfully");
    }

    @Then("I should see list details in response")
    public void iShouldSeeListDetailsInResponse() {
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get("listKey"), RestResponse.getValue("listKey"), "Validated created list key in response");
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get("name"), RestResponse.getValue("name"),"Validated created list name in response");
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get("description"), RestResponse.getValue("description"),"Validated created list description in response");
    }
    @And("I send a get request to fetch list items and collecting respective itemKeys")
    public void iSendAGetRequestToFetchListItemsAndRespectiveItemKeys() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.get(pathparams, true);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get list");
        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();
        HashMap<String, String> itemKeys = new HashMap<String, String>();
        itemKeys.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        itemKeys.put(responseItems.get(0).get("object").toString(), responseItems.get(0).get("itemKey").toString());
        itemKeys.put(responseItems.get(1).get("object").toString(), responseItems.get(1).get("itemKey").toString());
        itemKeys.put(responseItems.get(2).get("object").toString(), responseItems.get(2).get("itemKey").toString());
        this.dataLoad.setListCheckDetails(itemKeys);
    }

    @And("I send a request for removing {string} and {string} items from list")
    public void iSendARequestForRemovingItemsFromList(String item1, String item2) {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.REMOVE_LIST_ITEMS);
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("itemKey0", this.dataLoad.getListCheckDetails().get(item1));
        dataMap.put("itemKey2", this.dataLoad.getListCheckDetails().get(item2));
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("ListCheck");
        StepUtil.generateEngageTrustedIdentifierToken("ListCheck");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.removeItemsFromList"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.delete(pathparams, jsonData, true);
        RestResponse.getStatusCode();
    }

    @Then("I should see items deleted successfully")
    public void iShouldSeeItemsRemovedFromList() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status code validated for get list");
    }


    @Then("I should see {string} and {string} items are removed from list")
    public void iShouldSeeAndItemsAreRemovedFromList(String item1, String item2) {

        int count = 0;
        boolean isItem1Exeist = true;
        boolean isItem2Exeist = true;

        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();
        while (count <= responseItems.size() - 1) {
            isItem1Exeist = responseItems.get(count).containsValue(this.dataLoad.getListCheckDetails().get(item1));
            Validations.assertEquals(isItem1Exeist, false, this.dataLoad.getListCheckDetails().get(item1) + " itemkey deleted successfully ");
            isItem2Exeist = responseItems.get(count).containsValue(this.dataLoad.getListCheckDetails().get(item2));
            Validations.assertEquals(isItem2Exeist, false, this.dataLoad.getListCheckDetails().get(item2) + " itemkey deleted successfully");
            count++;
        }

    }

    @And("I send request for disabling the list")
    public void iSendRequestForDisablingTheList() {
        HashMap<String, String> pathparams = new HashMap<>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.delete(pathparams, true);

    }

    @Then("I should see list deleted successfully")
    public void iShouldSeeListDeletedSuccessfully() {

        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status code validated for disable list");

    }

    @Then("I should see list is disabled")
    public void iShouldSeeListIsDisabled() {

        Validations.assertEquals(this.dataLoad.getListCheckDetails().get("listKey"), RestResponse.getValue("listKey"), "list key validated in response");
        Validations.assertEquals(false, RestResponse.getValue("isActive"), "list is disabled in response i.e isActive = 'false'");

    }

    @And("I send request to update the list with following items")
    public void iSendRequestToUpdateTheListWithFollowingItems(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATE_LIST);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.updateList"));
        HashMap<String, String> pathparams = new HashMap<>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.put(pathparams, jsonData, true);
        RestResponse.getAsString();

    }

    @Then("I should see list updated successfully")
    public void iShouldSeeListUpdatedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status code validated for update list");

    }

    @Then("I should see {string} ,{string} and {string} items are updated in list")
    public void iShouldSeeAndItemsAreUpdatedInList(String item1, String item2, String item3) {
        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();
        System.out.println(responseItems.get(0).get("object").toString());
        System.out.println(responseItems.get(1).get("object").toString());
        System.out.println(responseItems.get(2).get("object").toString());
        Validations.assertEquals(item1, responseItems.get(0).get("object").toString(), item1 + " item is updated successfully ");
        Validations.assertEquals(item2, responseItems.get(1).get("object").toString(), item2 + " item is updated successfully ");
        Validations.assertEquals(item3, responseItems.get(2).get("object").toString(), item3 + " item is updated successfully ");

    }

    @And("I send a get request to fetch page {string} list items")
    public void iSendAGetRequestToFetchPageListItemsForItemKey(String pageCount) {
        Map<String, String> queryparameters = new HashMap<String, String>();
        queryparameters.put("limit", pageCount);
        HashMap<String, String> pathparameter = new HashMap<String, String>();
        pathparameter.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        RestUtil.get(pathparameter, queryparameters, true);
    }

    @Then("I should see {int} items {string} and {string} retrieved in list")
    public void iShouldSeeItemsAndRetrievedInList(int numberOfItems, String item1, String item2) {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get page list");
        List<HashMap<String, String>> responseItems = RestResponse.getList("data");
        Validations.assertEquals(numberOfItems, responseItems.size(), numberOfItems + " items retrieved in list");
        Validations.assertEquals(item1, responseItems.get(0).get("object").toString(), item1 + " item exists");
        Validations.assertEquals(item2, responseItems.get(1).get("object").toString(), item2 + " item exists");
    }

    @Then("I should see {int} items {string}, {string} and {string} retrieved in list")
    public void iShouldSeeItemsAndRetrievedInList(int numberOfItems, String item1, String item2, String item3) {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get page list");
        List<HashMap<String, String>> responseItems = RestResponse.getList("data");
        Validations.assertEquals(numberOfItems, responseItems.size(), numberOfItems + " items retrieved in list");
        Validations.assertEquals(item1, responseItems.get(0).get("object").toString(), item1 + " 1st item exists in response for get by page 3");
        Validations.assertEquals(item2, responseItems.get(1).get("object").toString(), item2 + " 2nd item exists in response for get by page 3");
        Validations.assertEquals(item3, responseItems.get(2).get("object").toString(), item3 + " 3rd item exists in response for get by page 3");

    }


    @And("I check {string} item is in list")
    public void iCheckItemIsInList(String item) {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        pathparams.put("item", item);
        RestUtil.get(pathparams, true);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get " + item + " item is in list");
        Validations.assertEquals(true, RestResponse.getValue("isInList"), item + " is in list");
    }

    @And("I check {string} value is in list")
    public void iCheckValueIsInList(String value) {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        Map<String, String> queryparameters = new HashMap<String, String>();
        queryparameters.put("values", value);
        RestUtil.get(pathparams, queryparameters, true);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get values item is in list");
        String values = RestResponse.getAsString().toString();
        Validations.assertTrue(values.contains(value), "Value matched");
    }


    @And("I check {string} value is in lists")
    public void iCheckValueIsInLists(String value) {
        Map<String, String> queryparameters = new HashMap<String, String>();
        queryparameters.put("listKeys", this.dataLoad.getListCheckDetails().get("listKey"));
        queryparameters.put("values", value);
        RestUtil.get(queryparameters, true);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for get values item is in lists");
        List<String> list = RestResponse.getResponseList(value);
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get("listKey"), list.get(0), " Validated " + list.get(0) + "in" + value);


    }

    @And("I send a request for create list with file {string} uploaded")
    public void iSendARequestForCreateListWithFileUploaded(String filename, io.cucumber.datatable.DataTable dataTable) throws Exception {

        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String filePath = "resources/files/" + filename;
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.createListFileUpload"));
        String URI = this.dataLoad.getRequestURL();
        Response response = RestUtil.postFormParamsWithFileUpload(dataMap, filePath, URI);
        HashMap<String, String> listCheckElements = new HashMap<String, String>();
        listCheckElements.put("name", dataMap.get("name"));
        listCheckElements.put("description", dataMap.get("description"));
        listCheckElements.put("listKey", response.body().asString());
        listCheckElements.put("statusCode", Integer.toString(response.getStatusCode()));
        System.out.println("status code " + response.getStatusCode());
        System.out.println("listKey " + response.body().asString());
        this.dataLoad.setListCheckDetails(listCheckElements);

    }

    @Then("I should see list with file uploaded created successfully")
    public void iShouldSeeListWithFileUploadedCreatedSuccessfully() {
        String statusCode = this.dataLoad.getListCheckDetails().get("statusCode");
        Validations.assertEquals(201, Integer.parseInt(statusCode), "Status Code validated for created list with file attached");
    }

    @Then("I should see items uploaded to list in response")
    public void iShouldSeeItemsUploadedToListInResponse() {
        String filePath = "resources/files/listItmes.csv";
        GenericUtil genericUtil = new GenericUtil();
        String itemsFromFile = genericUtil.getFileData(filePath);
        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();

        Validations.assertEquals(true, itemsFromFile.contains(responseItems.get(0).get("object").toString()), responseItems.get(0).get("object") + " Validated");
        Validations.assertEquals(true, itemsFromFile.contains(responseItems.get(0).get("value").toString()), responseItems.get(0).get("value") + " Validated");
        Validations.assertEquals(true, itemsFromFile.contains(responseItems.get(1).get("object").toString()), responseItems.get(1).get("object") + " Validated");
        Validations.assertEquals(true, itemsFromFile.contains(responseItems.get(1).get("value").toString()), responseItems.get(1).get("value") + " Validated");

    }


    @And("I send a request for create list by ward file {string} uploaded")
    public void iSendARequestForCreateListByWardFileUploaded(String filename, io.cucumber.datatable.DataTable dataTable) throws Exception {

        Map<String, String> dataMap = StepUtil.toMap(dataTable);

        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        String wardKey = hashMap.get("wardKey").toString();
        String filePath = "resources/files/" + filename;
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.createWardListFileUpload"));
        String URI = this.dataLoad.getRequestURL();
        String URIwithWardKey = URI.replace("{wardKey}", wardKey);
        Response response = RestUtil.postFormParamsWithFileUpload(dataMap, filePath, URIwithWardKey);
        HashMap<String, String> listCheckElements = new HashMap<String, String>();
        listCheckElements.put("name", dataMap.get("name"));
        listCheckElements.put("description", dataMap.get("description"));
        listCheckElements.put("wardKey", wardKey);
        listCheckElements.put("listKey", response.body().asString());
        listCheckElements.put("statusCode", Integer.toString(response.getStatusCode()));
        System.out.println("status code " + response.getStatusCode());
        System.out.println("listKey " + response.body().asString());
        this.dataLoad.setListCheckDetails(listCheckElements);

    }

    @And("I send a put request to add below list items")
    public void iSendAPutRequestToAddBelowListItems(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ADD_ITEMS_TO_LIST);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesListCheckApi"), ResourceData.getEndPoint("services.listCheck.addItemsToList"));
        RestUtil.put(pathparams, jsonData, true);
        RestResponse.getAsString();
        HashMap<String, String> listCheckElements = new HashMap<String, String>();
        listCheckElements.putAll(dataMap);
        listCheckElements.put("listKey", this.dataLoad.getListCheckDetails().get("listKey"));
        this.dataLoad.setListCheckDetails(listCheckElements);


    }

    @Then("I should see items added successfully to list")
    public void iShouldSeeItemsAddedSuccessfullyToList() {

        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code validated for Items added to list");
    }

    @Then("I should see {string} {string} {string} and {string} items are added to list")
    public void iShouldSeeAndItemsAreAddedToList(String item1, String item2, String item3, String item4) {

        List<HashMap<Object, Object>> responseItems = RestResponse.getArrayResponseList();
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get(item1), responseItems.get(0).get("object").toString(), this.dataLoad.getListCheckDetails().get(item1) + " item added successfully to list");
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get(item2), responseItems.get(1).get("object").toString(), this.dataLoad.getListCheckDetails().get(item2) + " item added successfully to list");
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get(item3), responseItems.get(2).get("object").toString(), this.dataLoad.getListCheckDetails().get(item3) + " item added successfully to list");
        Validations.assertEquals(this.dataLoad.getListCheckDetails().get(item4), responseItems.get(3).get("object").toString(), this.dataLoad.getListCheckDetails().get(item4) + " item added successfully to list");

    }
}
