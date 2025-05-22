package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import util.*;

import javax.sound.sampled.DataLine;
import javax.xml.crypto.Data;
import java.util.*;

public class SurveySteps {

    @And("I send request for create survey with below details")
    public void iSendRequestForCreateSurveyWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_SURVEY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        dataMap.put("wardkey", hashMap.get("wardKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Survey");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesSurveyApi"), ResourceData.getEndPoint("services.survey.createSurvey"));
        RestUtil.post(jsonData, true);
        String surveyName = dataMap.get("name");
        String engagementKey = dataMap.get("engagementKey");
        DataLoad.getInstance().setSurveyName(surveyName);
        DataLoad.getInstance().setEngagementKey(engagementKey);
    }

    @Then("I should see Survey created successfully")
    public void iShouldSeeNewlyCreatedSurveyInTheResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for Create Survey");
        DataLoad.getInstance().setSurveyKey(RestResponse.getAsString());
 }

    @And("I send get request for get survey")
    public void isendgetrequestforgetsurvey() throws Exception {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("surveyKey", DataLoad.getInstance().getSurveyKey());
        RestUtil.get(pathparams, true);

    }

    @Then("I should see newly created survey details in response")
    public void iShouldSeeNewlyCreatedSurveyDetailsInResponse() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation for Get Survey");
        Validations.assertEquals(DataLoad.getInstance().getSurveyName(), RestResponse.getValue("name"), "Verification of Survey name in get response");
    }

    @And("I send request for create survey without wardkey details")
    public void isendrequestforcreatesurveywithoutwardkeydetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_SURVEY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Survey");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesSurveyApi"), ResourceData.getEndPoint("services.survey.createSurvey"));
        RestUtil.post(jsonData, true);
    }

    @Then("I should see error message for missing wardkey")
    public void ishouldseeerrormessageformissingwardkey() {
        System.out.println(RestResponse.getStatusCode());
        Validations.assertEquals(400, RestResponse.getStatusCode(), "Status Code validation for Ward Key Error Response");
        List<String> wardKeyError = RestResponse.getResponseList("errors.wardKey");
        Validations.assertEquals("'Ward Key' must not be empty.", wardKeyError.get(0), "Verification of error:'Ward Key' must not be empty' in response");
        Validations.assertEquals("'Ward Key' is not in the correct format.", wardKeyError.get(1), "Verification of error:'Ward Key' is not in the correct format. in response");
    }

    @And("I send a put request to update survey")
    public void isendputrequestforupdatingsurvey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_SURVEY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        dataMap.put("wardkey", hashMap.get("wardKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Survey");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesSurveyApi"), ResourceData.getEndPoint("services.survey.updateSurvey"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("surveyKey", DataLoad.getInstance().getSurveyKey());
        String surveyUpdatedElements[] = {dataMap.get("name"),
                dataMap.get("description"),
                dataMap.get("isRepeating"),
                dataMap.get("questions_supportsMultipleAnswers"),
                dataMap.get("questions_text"),
                dataMap.get("answers_text")};
        DataLoad.getInstance().setSurveyUpdatedElements(surveyUpdatedElements);
        RestUtil.put(pathparams, jsonData, true);

    }

    @Then("I should see updated survey details in response")

    public void ishouldseeupdatedsurveydetailsinresponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validation for Update Survey");
        String fetchUpdatedelementsFromResponse[] = {"name",
                "description", "isRepeating",
                "questions[0].supportsMultipleAnswers",
                "questions[0].prompts[0].text",
                "questions[0].answers[0].prompts[0].text"};
        String text = "";
        for (int i = 0; i < fetchUpdatedelementsFromResponse.length; i++) {
            if (i < 3)
                Validations.assertEquals(DataLoad.getInstance().getSurveyUpdatedElements()[i].toString(), RestResponse.getValue(fetchUpdatedelementsFromResponse[i]).toString(), "Validation of updated value of " + fetchUpdatedelementsFromResponse[i] + " in response");
            else {
                text = fetchUpdatedelementsFromResponse[i].substring(fetchUpdatedelementsFromResponse[i].lastIndexOf(".")+1);
                if (i == 5)
                    Validations.assertEquals(DataLoad.getInstance().getSurveyUpdatedElements()[i].toString(), RestResponse.getValue(fetchUpdatedelementsFromResponse[i]).toString(), "Validation of updated value of answer in response");
                else
                    Validations.assertEquals(DataLoad.getInstance().getSurveyUpdatedElements()[i].toString(), RestResponse.getValue(fetchUpdatedelementsFromResponse[i]).toString(), "Validation of updated value of question " + text + " in response");

            }
        }
    }

    @And("I send request for create two surveys")
    public void iSendRequestForCreateTwoSurvey(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_AND_UPDATE_SURVEY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        dataMap.put("wardkey", hashMap.get("wardKey").toString());
        String jsonData1 = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("First Survey Payload: " +jsonData1);
        StepUtil.generateParticipantToken("Survey");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesSurveyApi"), ResourceData.getEndPoint("services.survey.createSurvey"));
        RestUtil.post(jsonData1, true);
        String[] surveyNames = new String[2];
        String[] surveyKeys = new String[2];
        surveyNames[0] = dataMap.get("name");
        String engagementKey = dataMap.get("engagementKey");
        DataLoad.getInstance().setEngagementKey(engagementKey);
        surveyKeys[0] = RestResponse.getAsString();
        System.out.println("First Survey created with survey Key :"+surveyKeys[0]);
        String surveyName2 = dataMap.get("name");
        surveyName2= surveyName2.replaceFirst("Survey", "Survey02");
        dataMap.put("name", surveyName2);
        String jsonData2 = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("Second Survey Payload: " +jsonData2);
        RestUtil.post(jsonData2, true);
        surveyNames[1] = surveyName2;
        surveyKeys[1] = RestResponse.getAsString();
        System.out.println("Second Survey created with survey Key :"+surveyKeys[1]);
        DataLoad.getInstance().setSurveyNames(surveyNames);
        DataLoad.getInstance().setSurveyKeys(surveyKeys);
    }

    @And("I send a get survey by filter request with engagement key")
    public void isendagetsurveybyfiltergetrequest()  {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println("Engagement Key:"+dataLoad.getEngagementKey());
        queryparams.put("engagementKey", dataLoad.getEngagementKey());
        RestUtil.get(queryparams,true);
    }


    boolean verifySurveyDetailsinResponse(List<HashMap<String,String>> responseList, String surveyKey, String surveyName){
        Boolean containsSurveyKey = false;
        DataLoad dataLoad = DataLoad.getInstance();
        for (int i = 0; i < responseList.size(); i++) {
            if (responseList.get(i).containsValue(surveyKey)) {
                Validations.assertEquals(dataLoad.getEngagementKey(), responseList.get(i).get("engagementKey"), "Validation of engagementKey in response");
                Validations.assertEquals(surveyName, responseList.get(i).get("name"), "Validation of survey name  in response");
                containsSurveyKey = true;
                break;
            }
        }
        return containsSurveyKey;
    }
    @Then("I should see created survey's under engagement key in response")
    public void ishouldseecreatedsurveysunderengagementkeyinresponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code Validation for get Survey by engagement Key filter");
        DataLoad dataLoad = DataLoad.getInstance();
        Boolean containsSurveyKey = false;
        List<HashMap<String,String>> responseList = RestResponse.getBody().jsonPath().getList("data");
        //System.out.println("First Survey Key :"+dataLoad.getSurveyKeys()[0]);
        containsSurveyKey = verifySurveyDetailsinResponse(responseList,dataLoad.getSurveyKeys()[0], dataLoad.getSurveyNames()[0]);
        Validations.assertEquals(true,containsSurveyKey,"Validation of first Survey Key in response");
        //System.out.println("Second Survey Key :"+dataLoad.getSurveyKeys()[1]);
        containsSurveyKey = verifySurveyDetailsinResponse(responseList,dataLoad.getSurveyKeys()[1], dataLoad.getSurveyNames()[1]);
        Validations.assertEquals(true,containsSurveyKey,"Validation of Second Survey Key in response");
 }



    @Then("I fetch the questionKey and answerKeys from get Survey response")
    public void iFetchTheQuestionKeyAndAnswerKeysFromGetSurveyResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get survey");
        List<HashMap<String,String>> responseList = RestResponse.getBody().jsonPath().getList("questions");
        String [] questionKeys = { responseList.get(0).get("questionKey").toString(),responseList.get(1).get("questionKey").toString()};
        dataLoad.setSurveyQuestionKeys(questionKeys);
        HashMap<String,String> answerKeysForQuestion1Map = new HashMap<String, String>();
        answerKeysForQuestion1Map.put(RestResponse.getValue("questions[0].answers[0].prompts[0].text").toString(),RestResponse.getValue("questions[0].answers[0].answerKey").toString());
        answerKeysForQuestion1Map.put(RestResponse.getValue("questions[0].answers[1].prompts[0].text").toString(),RestResponse.getValue("questions[0].answers[1].answerKey").toString());
        answerKeysForQuestion1Map.put(RestResponse.getValue("questions[0].answers[2].prompts[0].text").toString(),RestResponse.getValue("questions[0].answers[2].answerKey").toString());
        HashMap<String,String> answerKeysForQuestion2Map = new HashMap<String, String>();
        answerKeysForQuestion2Map.put(RestResponse.getValue("questions[1].answers[0].prompts[0].text").toString(),RestResponse.getValue("questions[1].answers[0].answerKey").toString());
        answerKeysForQuestion2Map.put(RestResponse.getValue("questions[1].answers[1].prompts[0].text").toString(),RestResponse.getValue("questions[1].answers[1].answerKey").toString());
        answerKeysForQuestion2Map.put(RestResponse.getValue("questions[1].answers[2].prompts[0].text").toString(),RestResponse.getValue("questions[1].answers[2].answerKey").toString());
        List<HashMap<String,String>> answers = new ArrayList<HashMap<String, String>>();
        answers.add(answerKeysForQuestion1Map);
        answers.add(answerKeysForQuestion2Map);
        dataLoad.setSurveyAnswerKeys(answers);
    }

    @And("I send a request to create response with below details")
    public void iSendARequestToCreateResponseWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATERESPONSE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("questionKey_01", dataLoad.getSurveyQuestionKeys()[0]);
        dataMap.put("answerKey_01", dataLoad.getSurveyAnswerKeys().get(0).get(dataMap.get("answerText_01")));
        dataMap.put("questionKey_02", dataLoad.getSurveyQuestionKeys()[1]);
        dataMap.put("answerKey_02", dataLoad.getSurveyAnswerKeys().get(1).get(dataMap.get("answerText_02")));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("surveyKey", dataLoad.getSurveyKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @And("I send a request to create response with only one answer with below details")
    public void iSendARequestToCreateResponseWithOnlyOneAnswerWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATERESPONSEWITH1QUESTIONS1ANSWER);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("questionKey_01", dataLoad.getSurveyQuestionKeys()[0]);
        dataMap.put("answerKey_01", dataLoad.getSurveyAnswerKeys().get(0).get(dataMap.get("answerText_01")));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("surveyKey", dataLoad.getSurveyKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see response created successfully")
    public void iShouldSeeResponseCreatedSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code Validation for Survey Response Creation");
        DataLoad.getInstance().setResponseKey(RestResponse.getAsString());
    }

    @And("I send a request to create survey with two questions and three answers")
    public void iSendARequestToCreateSurveyWithTwoQuestionsAndThreeAnswers(io.cucumber.datatable.DataTable dataTable){
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESURVEYWITHTWOQUESTIONSTHREEANSWERS);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("wardkey", dataLoad.getWardKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        String surveyName = dataMap.get("name");
        dataLoad.setSurveyName(surveyName);
    }

    @And("I send a request to get unanswered questions")
    public void iSendARequestToGetUnansweredQuestions() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        DataLoad dataLoad= DataLoad.getInstance();
        pathparams.put("surveyKey", dataLoad.getSurveyKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        RestUtil.get(pathparams, true);
    }

    @Then("I should see only unanswered question details in response")
    public void iShouldSeeOnlyUnansweredQuestionDetailsInResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation");
        String responseString = RestResponse.getBody().jsonPath().get().toString();
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyQuestionKeys()[1]),"Unanswered question present in the Response");
        Validations.assertEquals(false,responseString.contains(dataLoad.getSurveyQuestionKeys()[0]),"Answered question not present in the Response");
    }

    @And("I send a request for get response")
    public void iSendARequestForGetResponse() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        DataLoad dataLoad= DataLoad.getInstance();
        pathparams.put("responseKey", dataLoad.getResponseKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        System.out.println("Pathparams:"+pathparams);
        RestUtil.get(pathparams, true);
    }

    @Then("I should see the response details in the response of {string}")
    public void iShouldSeeTheResponseDetailsInTheResponse(String arg0) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for "+arg0);
        String responseString = RestResponse.getBody().jsonPath().get().toString();
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyQuestionKeys()[0]),"First question present in the "+arg0+" Response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyQuestionKeys()[1]),"Second question present in the "+arg0+" Response");
    }

    @And("I send a request for get response by filter")
    public void iSendARequestForGetResponseByFilter() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("surveyKey", dataLoad.getSurveyKey());
        RestUtil.get(queryparams,true);
    }

    @And("I send a request to create engagement response with below details")
    public void iSendARequestToCreateEngagementResponseWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
            GenericUtil genericUtil = new GenericUtil();
            DataLoad dataLoad = DataLoad.getInstance();
            String payload = genericUtil.getFileData(Payloads.CREATEENGAGEMENTRESPONSE);
            Map<String, String> dataMap = StepUtil.toMap(dataTable);
            dataMap.put("questionKey_01", dataLoad.getSurveyQuestionKeys()[0]);
            dataMap.put("answerKey_01", dataLoad.getSurveyAnswerKeys().get(0).get(dataMap.get("answerText_01")));
            dataMap.put("questionKey_02", dataLoad.getSurveyQuestionKeys()[1]);
            dataMap.put("answerKey_02", dataLoad.getSurveyAnswerKeys().get(1).get(dataMap.get("answerText_02")));
            dataMap.put("participantKey",dataLoad.getParticipantKey());
            HashMap<String, String> pathparams = new HashMap<String, String>();
            pathparams.put("surveyKey", dataLoad.getSurveyKey());
            pathparams.put("engagementKey", dataLoad.getEngagementKey());
            String jsonData = genericUtil.jsonConstruct(dataMap, payload);
            System.out.println(jsonData);
            RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.header("x-pl-actionToken","action "+dataLoad.getActionToken());
        //System.out.println(dataLoad.getActionToken());
       // httpRequest.header("x-pl-sandboxnow","2050-9-28T13:14:53.999Z");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        httpRequest.pathParams(pathparams);
        RestResponse.initialize(httpRequest.post());
        //RestUtil.post(pathparams, jsonData, true);
    }

    @And("I send a put request to update response with below details")
    public void iSendAPutRequestToUpdateResponseWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATERESPONSE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("questionKey_01", dataLoad.getSurveyQuestionKeys()[0]);
        dataMap.put("answerKey_01", dataLoad.getSurveyAnswerKeys().get(0).get(dataMap.get("answerText_01")));
        dataMap.put("questionKey_02", dataLoad.getSurveyQuestionKeys()[1]);
        dataMap.put("answerKey_02", dataLoad.getSurveyAnswerKeys().get(1).get(dataMap.get("answerText_02")));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("responseKey", dataLoad.getResponseKey());
        pathparams.put("participantKey", dataLoad.getParticipantKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        String [] updatedAnswers = {dataMap.get("answerText_01"),dataMap.get("answerText_02")};
        dataLoad.setSurveyUpdatedElements(updatedAnswers);
        RestUtil.put(pathparams, jsonData, true);
    }

    @Then("I should see the updated response details in the response")
    public void iShouldSeeTheUpdatedResponseDetailsInTheResponse() {
        DataLoad dataLoad = DataLoad.getInstance();
        String responseString = RestResponse.getBody().jsonPath().get().toString();
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyQuestionKeys()[0]),"First question present in the Update Response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyQuestionKeys()[1]),"Second question present in the Update Response");
        //Validations.assertEquals(dataLoad.getSurveyUpdatedElements()[0].toString(),RestResponse.getValue("responseAnswers[0].answerText[0]"),"Updated first answer validation in the Response");
        //Validations.assertEquals(dataLoad.getSurveyUpdatedElements()[1].toString(),RestResponse.getValue("responseAnswers[1].answerText[0]"),"Updated second answer validation in the Response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyUpdatedElements()[0].toString()),"Updated first answer validation in Update Response");
        Validations.assertEquals(true,responseString.contains(dataLoad.getSurveyUpdatedElements()[1].toString()),"Updated second answer validation in Update Response");
    }

    @Then("I should see the response updated successfully")
    public void iShouldSeeTheResponseUpdatedSuccessfully() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for successful Update");
    }

    @Then("I should see the response details in the response with engagement Key")
    public void iShouldSeeTheResponseDetailsInTheResponseWithEngagementKey() {
        Validations.assertEquals(200,RestResponse.getStatusCode(),"Status code validation for get response");
        Validations.assertEquals(DataLoad.getInstance().getResponseKey(), RestResponse.getList("data").get(0).get("responseKey").toString(),"Validation of response key in get response by filter");
    }

    @And("I send a request for get response by filter with survey key and participant key")
    public void iSendARequestForGetResponseByFilterWithSurveyKeyAndParticipantKey() {
        Map<String,String> queryparams = new HashMap<String,String>();
        DataLoad dataLoad = DataLoad.getInstance();
        queryparams.put("surveyKey", dataLoad.getSurveyKey());
        queryparams.put("participantKey", dataLoad.getParticipantKey());
        RestUtil.get(queryparams,true);
    }

    @And("I send a request to create survey with two questions and three answers with actionKey")
    public void iSendARequestToCreateSurveyWithTwoQuestionsAndThreeAnswersWithActionKey(io.cucumber.datatable.DataTable dataTable){
        GenericUtil genericUtil = new GenericUtil();
        DataLoad dataLoad = DataLoad.getInstance();
        String payload = genericUtil.getFileData(Payloads.CREATESURVEYWITHTWOQUESTIONSTHREEANSWERSWITHACTIONKEY);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("wardkey", dataLoad.getWardKey());
        dataMap.put("actionKey", dataLoad.getActionKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        httpRequest.header("x-pl-sandboxkey","MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        RestResponse.initialize(httpRequest.post());
        //RestUtil.post(jsonData, true);
        String surveyName = dataMap.get("name");
        dataLoad.setSurveyName(surveyName);
    }
}