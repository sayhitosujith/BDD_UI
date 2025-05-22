package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import groovyjarjarantlr4.v4.parse.ResyncToEndOfRuleBlock;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

public class RuleSteps {

    @And("I send request for create engagement dataSet with below details")
    public void ISendRequestForCreateEngagementDataSetWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATEENGAGEMENTDATASET);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        String[] engagementDataSetDetails = {dataMap.get("evaluationDataKey"), dataMap.get("evaluationDataValue"), dataMap.get("dataType")};
        dataLoad.setEngagementDataSetDetails(engagementDataSetDetails);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see engagement dataSet is created successfully with status code as {int}")
    public void iShouldSeeEngagementDataSetIsCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for Create EngagementDataSet");
        DataLoad.getInstance().setEngagementDataSetKey(RestResponse.getAsString());
    }

    @And("I send request for get engagement dataSet")
    public void iSendRequestForGetEngagementDataSet() {
        HashMap<String, String> pathparams = new HashMap<>();
        pathparams.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        RestUtil.get(pathparams, true);
    }

    @Then("I should see engagement dataset details in response with status code as {int}")
    public void iShouldEngagementDatasetDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for Get Engagement DataSet");
        Validations.assertEquals(dataLoad.getEngagementDataSetKey(), RestResponse.getValue("engagementDatasetKey").toString(), "Validation of engagementDataSetKey in Get Engagement DataSet response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEngagementDataSetDetails()[0]), "Validation of typedEvaluationData key in Get Engagement DataSet response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEngagementDataSetDetails()[1]), "Validation of typedEvaluationData value in Get Engagement DataSet response");
        Validations.assertEquals(dataLoad.getEngagementDataSetDetails()[2], RestResponse.getBody().jsonPath().getJsonObject("typedEvaluationData." + dataLoad.getEngagementDataSetDetails()[0] + ".dataType").toString(), "Validation of data type in Get Engagement DataSet response");

    }

    @And("I send request to update engagement dataset with below details")

    public void iSendRequestToUpdateEngagementDatasetWithBelowDetails(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATEENGAGEMENTDATASET);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        HashMap<String, String> pathparams = new HashMap<>();
        pathparams.put("engagementKey", dataLoad.getEngagementKey());
        String[] engagementDataSetDetails = {dataMap.get("evaluationDataKey"), dataMap.get("evaluationDataValue"), dataMap.get("dataType")};
        dataLoad.setEngagementDataSetDetails(engagementDataSetDetails);
        RestUtil.post(pathparams, jsonData, true);
    }

    @Then("I should see engagement dataset is updated successfully with status code as {int}")
    public void iShouldSeeEngagementDatasetIsUpdatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for Update Engagement DataSet details");

    }

    @Then("I should engagement dataset updated details in response with status code as {int}")
    public void iShouldEngagementDatasetUpdatedDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for Get Update Engagement DataSet details");
        Validations.assertEquals(dataLoad.getEngagementDataSetKey(), RestResponse.getValue("engagementDatasetKey").toString(), "Validation of engagementDataSetKey in Get Engagement DataSet response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEngagementDataSetDetails()[0]), "Validation of typedEvaluationData key in Get Engagement DataSet response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEngagementDataSetDetails()[1]), "Validation of typedEvaluationData value in Get Engagement DataSet response");
        Validations.assertEquals(dataLoad.getEngagementDataSetDetails()[2], RestResponse.getBody().jsonPath().getJsonObject("typedEvaluationData." + dataLoad.getEngagementDataSetDetails()[0] + ".dataType").toString(), "Validation of data type in Get Engagement DataSet response");
    }

    @And("I send request for delete engagement dataSet")
    public void iSendRequestForDeleteEngagementDataSet() {
        HashMap<String, String> pathparams = new HashMap<>();
        pathparams.put("engagementKey", DataLoad.getInstance().getEngagementKey());
        RestUtil.delete(pathparams, true);
    }



    @And("I send request for create a {string} rule")
    public void iSendRequestForCreateARule(String RuleModelType, io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATERULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        dataMap.put("wardKey", dataLoad.getWardKey());
        dataMap.put("ruleModelType", RuleModelType);
        if (RuleModelType.equals("Purchase")) {
            dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation13\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferPurchaseEvaluation14\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"CreatedOn\\\",\\\"IsRule\\\":false,\\\"Format\\\":\\\"MMM dd, yyyy\\\"},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferPurchaseEvaluation15\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"lessOrEqual\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferPurchaseEvaluation16\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Value\\\":\\\"1/13/2022 0:0:0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferPurchaseEvaluation17\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation18\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation19\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation20\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"else\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation21\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation22\\\",\\\"Oper\\\":0,\\\"FuncType\\\":1,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Max\\\":256,\\\"Value\\\":\\\"Error&nbsp;in&nbsp;rule\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation23\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"}],\\\"Id\\\":\\\"c534f042-8808-4730-a6cb-3e879c97e1e6\\\",\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"TEST API Purchase Incentive\\\",\\\"Desc\\\":\\\"Rule1\\\",\\\"SkipNameValidation\\\":false}");
        }
        if (RuleModelType.equals("OfferSelection")) {
            dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferSelection6\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferSelection58\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"Submission.Status\\\",\\\"IsRule\\\":false,\\\"En\\\":\\\"Prizelogic.Services.Rule.Domain.EvaluationModels.SubmissionStatus\\\"},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferSelection59\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"equal\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferSelection60\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferSelection61\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection62\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6C0896EFE91FC93686BC7684719EF012\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection65\\\",\\\"Oper\\\":1,\\\"FuncType\\\":1,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Dec\\\":false,\\\"Min\\\":-2147483648,\\\"Max\\\":2147483647,\\\"Value\\\":\\\"1\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection63\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6C0896EFE91FC93686BC7684719EF012\\\"}],\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"Test\\\",\\\"Desc\\\":\\\"Rule\\\",\\\"SkipNameValidation\\\":false}");
        }
        if (RuleModelType.equals("ArvCalculation")) {
            dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferArvCalculation2\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferArvCalculation5\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"TotalArv\\\",\\\"IsRule\\\":false,\\\"Dec\\\":true,\\\"Cal\\\":true,\\\"Min\\\":-9007199254740992,\\\"Max\\\":9007199254740992},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferArvCalculation6\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"equal\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferArvCalculation8\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Value\\\":\\\"0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferArvCalculation9\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":17,\\\"Name\\\":\\\"_OfferArvCalculation10\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"set\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferArvCalculation11\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"TotalArv\\\",\\\"IsRule\\\":false,\\\"Dec\\\":true,\\\"Cal\\\":true,\\\"Min\\\":-9007199254740992,\\\"Max\\\":9007199254740992},{\\\"Type\\\":17,\\\"Name\\\":\\\"_OfferArvCalculation12\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"to\\\"},{\\\"Type\\\":2,\\\"Name\\\":\\\"_OfferArvCalculation13\\\",\\\"Oper\\\":1,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6D9A89A1CC30CF54D659045C3D2BF84B\\\",\\\"IsFuncValue\\\":true},{\\\"Type\\\":2,\\\"Name\\\":\\\"_OfferArvCalculation14\\\",\\\"Oper\\\":1,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6D9A89A1CC30CF54D659045C3D2BF84B\\\",\\\"IsFuncValue\\\":true}],\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"ARV calculation\\\",\\\"Desc\\\":\\\"rule\\\",\\\"SkipNameValidation\\\":false}");
        }
        if (RuleModelType.equals("Demographics")) {
            dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_Demographics0\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_Demographics1\\\",\\\"Oper\\\":0,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"ParticipantAddress.UpdatedOn\\\",\\\"IsRule\\\":false,\\\"Max\\\":256},{\\\"Type\\\":3,\\\"Name\\\":\\\"_Demographics2\\\",\\\"Oper\\\":0,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"isNotNull\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_Demographics3\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics4\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"C18A09DF208451C0721F440BE4285FD4\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics6\\\",\\\"Oper\\\":0,\\\"FuncType\\\":1,\\\"InpType\\\":0,\\\"CalType\\\":9,\\\"Value\\\":\\\"ParticipantAddress.Address1\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics5\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"C18A09DF208451C0721F440BE4285FD4\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_Demographics7\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"and\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics8\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"C18A09DF208451C0721F440BE4285FD4\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics10\\\",\\\"Oper\\\":0,\\\"FuncType\\\":1,\\\"InpType\\\":0,\\\"CalType\\\":9,\\\"Value\\\":\\\"ParticipantAddress.Address2\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_Demographics9\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"C18A09DF208451C0721F440BE4285FD4\\\"}],\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"Demographic Rule\\\",\\\"Desc\\\":\\\"Rule\\\",\\\"SkipNameValidation\\\":false}");
        }
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        String[] evaluationData = {dataMap.get("typedEvaluationDataKey").toString(), dataMap.get("typedEvaluationDataValue").toString()};
        dataLoad.setEvaluationData(evaluationData);
        dataLoad.setRuleModelType(RuleModelType);
        dataLoad.setRuleKey(RestResponse.getAsString());
    }

    @Then("I should see a rule created successfully with status code as {int}")
    public void iShouldSeeARuleCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for create rule");
        System.out.println(RestResponse.getAsString());
    }

    @And("I send request for evaluate purchase model against rule set")
    public void iSendRequestForEvaluatePurchaseModelAgainstRuleSet(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.EVALUATEPURCHASEAGAINSTRULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("ruleKey", dataLoad.getRuleKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see evaluate purchase model against rule is created successfully with status code as {int}")
    public void iShouldSeeEvaluatePurchaseModelAgainstRuleIsCreatedSuccessfullyWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate purchase rule");
        Validations.assertEquals(true, RestResponse.getValue("evaluationResults").toString().contains(dataLoad.getRuleKey()), "Validation of rule key presence in evaluate purchase rule response");
        Validations.assertEquals("true", RestResponse.getBody().jsonPath().getJsonObject("evaluationResults."+dataLoad.getRuleKey()+".passed").toString(),"Validation passed status in evaluate purchase rule response");
        Validations.assertEquals("TEST API Purchase Incentive", RestResponse.getBody().jsonPath().getJsonObject("evaluationResults."+dataLoad.getRuleKey()+".ruleName").toString(),"Validation ruleName in evaluate purchase rule response");
        Validations.assertEquals("Rule1", RestResponse.getBody().jsonPath().getJsonObject("evaluationResults."+dataLoad.getRuleKey()+".ruleDescription").toString(),"Validation ruleDescription in evaluate purchase rule response");

    }

    @And("I send request for evaluate purchase model against rule json")
    public void iSendRequestForEvaluatePurchaseModelAgainstRuleJson(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.PAYLOADJSON);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        payload = genericUtil.getFileData(Payloads.EVALUATESAMPLERULEAGAINSTJSON);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = jsonData.replace("\"", "\\\"");
        jsonData = jsonData.replace("\n", "\r\n");
        System.out.println(jsonData);
        dataMap.put("payloadJson", jsonData);
        dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation13\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferPurchaseEvaluation14\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"CreatedOn\\\",\\\"IsRule\\\":false,\\\"Format\\\":\\\"MMM dd, yyyy\\\"},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferPurchaseEvaluation15\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"lessOrEqual\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferPurchaseEvaluation16\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Value\\\":\\\"1/13/2022 0:0:0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferPurchaseEvaluation17\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation18\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation19\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation20\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"else\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation21\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation22\\\",\\\"Oper\\\":0,\\\"FuncType\\\":1,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Max\\\":256,\\\"Value\\\":\\\"Error&nbsp;in&nbsp;rule\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation23\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"}],\\\"Id\\\":\\\"c534f042-8808-4730-a6cb-3e879c97e1e6\\\",\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"TEST API Purchase Incentive\\\",\\\"Desc\\\":\\\"Rule1\\\",\\\"SkipNameValidation\\\":false}");
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @And("I send request for evaluate arv calculation rule")
    public void iSendRequestForEvaluateArvCalculationRule(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.EVALUATEARVCALCULATIONRULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("ruleKey", dataLoad.getRuleKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see evaluate arv calculation rule is completed successfully with status code as {int}")
    public void iShouldSeeEvaluateArvCalculationRuleIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate arv calculation rule");
        Validations.assertEquals("7.0", RestResponse.getValue("calculatedArv").toString(), "Validation of CalculatedArv value in response evaluate arv calculation rule response");
    }

    @And("I send request for evaluate arv calculation rule against rule json")
    public void iSendRequestForEvaluateArvCalculationRuleAgainstRuleJson(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.PAYLOADJSONFORARVCALCULATION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        payload = genericUtil.getFileData(Payloads.EVALUATESAMPLERULEAGAINSTJSON);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = jsonData.replace("\"", "\\\"");
        jsonData = jsonData.replace("\n", "\r\n");
        System.out.println(jsonData);
        dataMap.put("payloadJson", jsonData);
        dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferArvCalculation2\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferArvCalculation5\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"TotalArv\\\",\\\"IsRule\\\":false,\\\"Dec\\\":true,\\\"Cal\\\":true,\\\"Min\\\":-9007199254740992,\\\"Max\\\":9007199254740992},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferArvCalculation6\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"equal\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferArvCalculation8\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Value\\\":\\\"0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferArvCalculation9\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":17,\\\"Name\\\":\\\"_OfferArvCalculation10\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"set\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferArvCalculation11\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"TotalArv\\\",\\\"IsRule\\\":false,\\\"Dec\\\":true,\\\"Cal\\\":true,\\\"Min\\\":-9007199254740992,\\\"Max\\\":9007199254740992},{\\\"Type\\\":17,\\\"Name\\\":\\\"_OfferArvCalculation12\\\",\\\"Oper\\\":1,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"to\\\"},{\\\"Type\\\":2,\\\"Name\\\":\\\"_OfferArvCalculation13\\\",\\\"Oper\\\":1,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6D9A89A1CC30CF54D659045C3D2BF84B\\\",\\\"IsFuncValue\\\":true},{\\\"Type\\\":2,\\\"Name\\\":\\\"_OfferArvCalculation14\\\",\\\"Oper\\\":1,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6D9A89A1CC30CF54D659045C3D2BF84B\\\",\\\"IsFuncValue\\\":true}],\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"ARV calculation\\\",\\\"Desc\\\":\\\"rule\\\",\\\"SkipNameValidation\\\":false}");
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see evaluation of arv calculation rule against json is completed successfully with status code as {int}")
    public void iShouldSeeEvaluationOfArvCalculationRuleAgainstJsonIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate arv calculation rule against ruleJson");
        Validations.assertEquals("7.0", RestResponse.getValue("calculatedArv").toString(), "Validation of CalculatedArv value in response evaluate arv calculation rule response");
    }

    @And("I send request for evaluate offer selection rule")
    public void iSendRequestForEvaluateOfferSelectionRule(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.EVALUATEOFFERSELECTIONRULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("ruleKey", dataLoad.getRuleKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        dataLoad.setOfferKey(dataMap.get("offerKey"));
        dataLoad.setOfferingKey(dataMap.get("offeringKey"));
        dataLoad.setOfferTag(dataMap.get("offerTag"));
    }

    @Then("I should see evaluate offer selection rule is completed successfully with status code as {int}")
    public void iShouldSeeEvaluateOfferSelectionRuleIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate offer selection rule");
        Validations.assertEquals(dataLoad.getOfferKey(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offerKey").toString(), "Validation of qualified offer key in evaluate offer selection rule response");
        Validations.assertEquals(dataLoad.getOfferingKey(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offeringKey").toString(), "Validation of qualified offering key in evaluate offer selection rule response");
        Validations.assertEquals(dataLoad.getOfferTag(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offerTags[0]").toString(), "Validation of qualified offer tag in evaluate offer selection rule response");
    }

    @And("I send request for evaluate sample offer selection rule")
    public void iSendRequestForEvaluateSampleOfferSelectionRule(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.PAYLOADJSONFOROFFERSELECTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        dataLoad.setOfferKey(dataMap.get("offerKey"));
        dataLoad.setOfferingKey(dataMap.get("offeringKey"));
        dataLoad.setOfferTag(dataMap.get("offerTag"));
        System.out.println(jsonData);
        payload = genericUtil.getFileData(Payloads.EVALUATESAMPLERULEAGAINSTJSON);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = jsonData.replace("\"", "\\\"");
        jsonData = jsonData.replace("\n", "\r\n");
        System.out.println(jsonData);
        dataMap.put("payloadJson", jsonData);
        dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferSelection6\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferSelection58\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"Submission.Status\\\",\\\"IsRule\\\":false,\\\"En\\\":\\\"Prizelogic.Services.Rule.Domain.EvaluationModels.SubmissionStatus\\\"},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferSelection59\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"equal\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferSelection60\\\",\\\"Oper\\\":6,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferSelection61\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection62\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6C0896EFE91FC93686BC7684719EF012\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection65\\\",\\\"Oper\\\":1,\\\"FuncType\\\":1,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Dec\\\":false,\\\"Min\\\":-2147483648,\\\"Max\\\":2147483647,\\\"Value\\\":\\\"1\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferSelection63\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"6C0896EFE91FC93686BC7684719EF012\\\"}],\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"Test\\\",\\\"Desc\\\":\\\"Rule\\\",\\\"SkipNameValidation\\\":false}");
        dataMap.put("engagementKey", dataLoad.getEngagementKey());
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see evaluate sample offer selection rule is completed successfully with status code as {int}")
    public void iShouldSeeEvaluateSampleOfferSelectionRuleIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate sample offer selection rule");
        Validations.assertEquals(dataLoad.getOfferKey(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offerKey").toString(), "Validation of qualified offer key in evaluate offer selection rule response");
        Validations.assertEquals(dataLoad.getOfferingKey(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offeringKey").toString(), "Validation of qualified offering key in evaluate offer selection rule response");
        Validations.assertEquals(dataLoad.getOfferTag(), RestResponse.getBody().jsonPath().getJsonObject("selectedOffers[0].offerTags[0]").toString(), "Validation of qualified offer tag in evaluate offer selection rule response");

    }

    @And("I send request for evaluate demographics rule")
    public void iSendRequestForEvaluateDemographicsRule(io.cucumber.datatable.DataTable dataTable) {
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.EVALUATEDEMOGRPHICSRULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("ruleKey", dataLoad.getRuleKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
    }

    @Then("I should see evaluate demographics rule is completed successfully with status code as {int}")
    public void iShouldSeeEvaluateDemographicsRuleIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        System.out.println(RestResponse.getAsString());
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate demographics rule");
    }

    @Then("I should see evaluate sample purchase rule is completed successfully with status code as {int}")
    public void iShouldSeeEvaluateSamplePurchaseRuleIsCompletedSuccessfullyWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance();
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for evaluate purchase rule");
        Validations.assertEquals("true", RestResponse.getValue("passed").toString(),"Validation passed status in evaluate purchase rule response");
        Validations.assertEquals("TEST API Purchase Incentive", RestResponse.getValue("ruleName").toString(),"Validation ruleName in evaluate purchase rule response");
        Validations.assertEquals("Rule1", RestResponse.getValue("ruleDescription").toString(),"Validation ruleDescription in evaluate purchase rule response");
    }

    @Then("I send request for get rule by ruleKey")
    public void iSendRequestForGetRuleByRuleKey() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("ruleKey",DataLoad.getInstance().getRuleKey());
        RestUtil.get(pathparams,true);
    }

    @Then("I should see the rule details in response with status code as {int}")
    public void iShouldSeeTheRuleDetailsInResponseWithStatusCodeAs(int statusCode) {
        DataLoad dataLoad = DataLoad.getInstance() ;
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for get rule");
        Validations.assertEquals(dataLoad.getRuleKey(), RestResponse.getValue("ruleKey"), "Validation of ruleKey in get rule");
        Validations.assertEquals(dataLoad.getEngagementKey(), RestResponse.getValue("engagementKey"), "Validation of engagement key in get rule response");
        Validations.assertEquals(dataLoad.getRuleModelType(), RestResponse.getValue("ruleModelType"), "Validation of ruleModelType in get rule response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEvaluationData()[0]), "Validation of typedEvaluationData key in Get Engagement DataSet response");
        Validations.assertEquals(true, RestResponse.getValue("typedEvaluationData").toString().contains(dataLoad.getEvaluationData()[1]), "Validation of typedEvaluationData value in Get Engagement DataSet response");
    }

    @Then("I send request to update the rule")
    public void iSendRequestToUpdateTheRule(io.cucumber.datatable.DataTable dataTable) {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("ruleKey",DataLoad.getInstance().getRuleKey());
        DataLoad dataLoad = DataLoad.getInstance();
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATERULE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("ruleJson", "{\\\"Elements\\\":[{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation13\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"if\\\"},{\\\"Type\\\":1,\\\"Name\\\":\\\"_OfferPurchaseEvaluation14\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"CreatedOn\\\",\\\"IsRule\\\":false,\\\"Format\\\":\\\"MMM dd, yyyy\\\"},{\\\"Type\\\":3,\\\"Name\\\":\\\"_OfferPurchaseEvaluation15\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"lessOrEqual\\\"},{\\\"Type\\\":4,\\\"Name\\\":\\\"_OfferPurchaseEvaluation16\\\",\\\"Oper\\\":2,\\\"FuncType\\\":4,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Value\\\":\\\"1/13/2022 0:0:0\\\"},{\\\"Type\\\":6,\\\"Name\\\":\\\"_OfferPurchaseEvaluation17\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"then\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation18\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation19\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"F0B3674108218ACA0164D51167626008\\\"},{\\\"Type\\\":0,\\\"Name\\\":\\\"_OfferPurchaseEvaluation20\\\",\\\"Oper\\\":16,\\\"FuncType\\\":4,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"else\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation21\\\",\\\"Oper\\\":16,\\\"FuncType\\\":0,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation22\\\",\\\"Oper\\\":0,\\\"FuncType\\\":1,\\\"InpType\\\":1,\\\"CalType\\\":9,\\\"Max\\\":256,\\\"Value\\\":\\\"Error&nbsp;in&nbsp;rule\\\"},{\\\"Type\\\":7,\\\"Name\\\":\\\"_OfferPurchaseEvaluation23\\\",\\\"Oper\\\":16,\\\"FuncType\\\":3,\\\"InpType\\\":2,\\\"CalType\\\":9,\\\"Value\\\":\\\"1AC315D6F239CB5A7C3D5488E051A12C\\\"}],\\\"Id\\\":\\\"c534f042-8808-4730-a6cb-3e879c97e1e6\\\",\\\"IsLoadedRuleOfEvalType\\\":false,\\\"Command\\\":\\\"ceExtract\\\",\\\"Mode\\\":0,\\\"Name\\\":\\\"TEST API Purchase Incentive\\\",\\\"Desc\\\":\\\"Rule1\\\",\\\"SkipNameValidation\\\":false}");
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(pathparams,jsonData, true);
        String[] evaluationData = {dataMap.get("typedEvaluationDataKey").toString(), dataMap.get("typedEvaluationDataValue").toString()};
        dataLoad.setEvaluationData(evaluationData);
        dataLoad.setDataType(dataMap.get("dataType"));
    }

    @Then("I should see the rule is updated successfully with status code as {int}")
    public void iShouldSeeTheRuleIsUpdatedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for update rule");
    }

    @Then("I send request to delete the rule")
    public void iSendRequestToDeleteTheRule() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("ruleKey",DataLoad.getInstance().getRuleKey());
        RestUtil.delete(pathparams,true);
    }

    @Then("I should see the rule is deleted successfully with status code as {int}")
    public void iShouldSeeTheRuleIsDeletedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for delete rule");
    }


    @And("I send request for get list of evaluation SKUs")
    public void iSendRequestForGetListOfEvaluationSKUs() {
        HashMap<String,String> pathparams = new HashMap<>();
        pathparams.put("ruleKey",DataLoad.getInstance().getRuleKey());
        pathparams.put("listKey",DataLoad.getInstance().getEvaluationData()[0]);
        RestUtil.get(pathparams,true);
    }

    @Then("I should see list in response with status code as {int}")
    public void iShouldSeeListInResponseWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for get list of evaluation SKUs in response");
    }

    @Then("I should see dataset deleted successfully with status code as {int}")
    public void iShouldSeeDatasetDeletedSuccessfullyWithStatusCodeAs(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for delete dataset engagement dataset");
    }

    @Then("I should see response as null response with status code as {int}")
    public void iShouldSeeResponseAsNull(int statusCode) {
        Validations.assertEquals(statusCode, RestResponse.getStatusCode(), "Validation of Status code for get dataset engagement dataset");
        Validations.assertEquals("null", RestResponse.getAsString(), "Validation of null response for deleted engagement dataSet");
    }
}
