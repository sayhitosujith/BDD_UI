package StepDefs.services;

import StepDefs.Validations;
import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.ResponseBody;
import util.GenericUtil;
import util.RestResponse;
import util.RestUtil;
import util.StepUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateSteps {
    DataLoad dataLoad = DataLoad.getInstance();

    @And("i send request for create templateType with below details")
    public void iSendRequestForCreateTemplateTypeWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_TEMPLATE_TYPE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesTemplateApi"), ResourceData.getEndPoint("services.template.createTemplateType"));
        RestUtil.post(jsonData, true);
    }

    @Then("I should see templateTypeCreated successfully")
    public void iShouldSeeTemplateTypeCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code validated for created templateType");
        this.dataLoad.setTemplateTypeKey(RestResponse.getAsString());

    }

    @And("I send request for create template with below details")
    public void iSendRequestForCreateTemplateWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_TEMPLATE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        if(hashMap.get("wardKey").toString().equals("MILLERCOORS"))
        {
            dataMap.put("wardKey","FORESEE");
        }
        else{
            dataMap.put("wardKey", hashMap.get("wardKey").toString());
        }

        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        dataMap.put("templateTypeKey", DataLoad.getInstance().getTemplateTypeKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("jason data1 :" + jsonData);
        StepUtil.generateParticipantToken("Template");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesTemplateApi"), ResourceData.getEndPoint("services.template.createTemplate"));
        RestUtil.post(jsonData, true);
        HashMap templateElements = new HashMap<String, String>();
        templateElements.put("name", dataMap.get("name"));
        templateElements.put("description", dataMap.get("description"));
        templateElements.put("wardKey", dataMap.get("wardKey"));
        templateElements.put("engagementKey", dataMap.get("engagementKey"));
        templateElements.put("templateTypeKey", dataMap.get("templateTypeKey"));
        templateElements.put("templateKey", RestResponse.getAsString());
        this.dataLoad.setTemplateDetails(templateElements);

    }

    @And("I send request for create another template with below details")
    public void iSendRequestForCreateAnotherTemplateWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_TEMPLATE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        String wardKey = this.dataLoad.getTemplateDetails("wardKey");
        dataMap.put("wardKey", wardKey);
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        dataMap.put("templateTypeKey", DataLoad.getInstance().getTemplateTypeKey());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("jason data2 :" + jsonData);
        StepUtil.generateParticipantToken("Template");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesTemplateApi"), ResourceData.getEndPoint("services.template.createTemplate"));
        RestUtil.post(jsonData, true);
        HashMap template2Elements = new HashMap<String, String>();
        template2Elements.put("name", dataMap.get("name"));
        template2Elements.put("description", dataMap.get("description"));
        template2Elements.put("wardKey", wardKey);
        template2Elements.put("engagementKey", dataMap.get("engagementKey"));
        template2Elements.put("templateTypeKey", dataMap.get("templateTypeKey"));
        template2Elements.put("templateKey", RestResponse.getAsString());
        this.dataLoad.setTemplate2Details(template2Elements);

    }


    @Then("I should see templateCreated successfully")
    public void iShouldSeeTemplateCreatedSuccessfully() {
        Validations.assertEquals(201, RestResponse.getStatusCode(), "Status Code validated for created template");

    }

    @And("I send get request for get template")
    public void iSendGetRequestForGetTemplate() {
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("templateKey", this.dataLoad.getTemplateDetails("templateKey"));
        RestUtil.get(pathparams, true);

    }

    @Then("I should see newly created template details in response")
    public void iShouldSeeNewlyCreatedTemplateDetailsInResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for get template");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("name"), RestResponse.getValue("name"), "Validated template name in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("description"), RestResponse.getValue("description"), "Validated template description in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("wardKey"), RestResponse.getValue("wardKey"), "Validated template wardkey in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("engagementKey"), RestResponse.getValue("engagementKey"), "Validated template engagementKey in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("templateTypeKey"), RestResponse.getValue("templateTypeKey"), "Validated templateTypeKey associated  to template in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("templateKey"), RestResponse.getValue("templateKey"), "Validated templateKey in get response");

    }


    @And("I send a put request to update template")
    public void isendputrequestforupdatingtemplate(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATE_TEMPLATE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Template");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesTemplateApi"), ResourceData.getEndPoint("services.template.updateTemplate"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("templateKey", this.dataLoad.getTemplateDetails("templateKey"));
        RestUtil.put(pathparams, jsonData, true);
        System.out.println("Status code ; " + RestResponse.getStatusCode());
        HashMap updatedTemplateElements = new HashMap<String, String>();
        updatedTemplateElements.put("UpdatedengagementKey", dataMap.get("engagementKey"));
        updatedTemplateElements.put("Updatedname", dataMap.get("name"));
        updatedTemplateElements.put("Updateddescription", dataMap.get("description"));
        updatedTemplateElements.put("templateKey", this.dataLoad.getTemplateDetails("templateKey"));
        this.dataLoad.setTemplateDetails(updatedTemplateElements);
    }


    @Then("I should see updated template details in response")
    public void iShouldSeeUpdatedTemplateDetailsInResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for get template");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("UpdatedengagementKey"), RestResponse.getValue("engagementKey"), "Validated updated engagementKey description in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("Updatedname"), RestResponse.getValue("name"), "Validated updated template name in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("Updateddescription"), RestResponse.getValue("description"), "Validated updated template description in get response");
    }

    @Then("I should see template updated successfully")
    public void iShouldSeeTemplateUpdatedSuccessfully() {
        Validations.assertEquals(204, RestResponse.getStatusCode(), "Status Code validated for updated template");
    }

    @And("I send get request to filter out templates using wardkey")
    public void iSendGetRequestToFilterOutTemplatesUsingWardKey() {
        Map<String, String> queryparm = new HashMap<String, String>();
        queryparm.put("wardKey", this.dataLoad.getTemplateDetails("wardKey"));
        RestUtil.get(queryparm, true);

    }

    @Then("I should see associated ward templates details in response")
    public void iShouldSeeAssociatedWardTemplatesDetailsInResponse() {

        String wardkey = this.dataLoad.getTemplateDetails("wardKey");
        String template1_Key = this.dataLoad.getTemplateDetails("templateKey");
        String template2_Key = this.dataLoad.getTemplate2Details("templateKey");


        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for get templates by ward");
        List<HashMap<Object, Object>> allTemplates = RestResponse.getResponseList("data");

        int numberOFtemplates = allTemplates.size();
        boolean isTemp1Exist = false;
        boolean isTemp2Exist = false;

        for (int templates = 0; templates <= numberOFtemplates - 1; templates++) {
            String templateKeyInReponse = allTemplates.get(templates).get("templateKey").toString();

            if (templateKeyInReponse.equals(template1_Key)) {
                Validations.assertEquals(this.dataLoad.getTemplateDetails("name"), RestResponse.getValue("data[" + templates + "].name"), "Validated template1 name associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplateDetails("description"), RestResponse.getValue("data[" + templates + "].description"), "Validated description associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplateDetails("wardKey"), RestResponse.getValue("data[" + templates + "].wardKey"), "Validated wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplateDetails("engagementKey"), RestResponse.getValue("data[" + templates + "].engagementKey"), "Validated engagementKey associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplateDetails("templateTypeKey"), RestResponse.getValue("data[" + templates + "].templateTypeKey"), "Validated templateTypeKey associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplateDetails("templateKey"), RestResponse.getValue("data[" + templates + "].templateKey"), "Validated templateKey associated to wardkey " + wardkey + " in response");
                isTemp1Exist = true;

            } else if (templateKeyInReponse.equals(template2_Key)) {
                Validations.assertEquals(this.dataLoad.getTemplate2Details("name"), RestResponse.getValue("data[" + templates + "].name"), "Validated template2 name associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplate2Details("description"), RestResponse.getValue("data[" + templates + "].description"), "Validated template2 description associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplate2Details("wardKey"), RestResponse.getValue("data[" + templates + "].wardKey"), "Validated template2 wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplate2Details("engagementKey"), RestResponse.getValue("data[" + templates + "].engagementKey"), "Validated template2 engagementKey associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplate2Details("templateTypeKey"), RestResponse.getValue("data[" + templates + "].templateTypeKey"), "Validated template2 templateTypeKey associated to wardkey " + wardkey + " in response");
                Validations.assertEquals(this.dataLoad.getTemplate2Details("templateKey"), RestResponse.getValue("data[" + templates + "].templateKey"), "Validated template2 templateKey associated to wardkey " + wardkey + " in response");
                isTemp2Exist = true;
            }
        }
        Validations.assertEquals(true,isTemp1Exist,"Template1 details verified successfully");
        Validations.assertEquals(true,isTemp2Exist,"Template2 details verified successfully");
    }

    @And("I send a put request to update template version status")
    public void iSendAPutRequestToUpdateTemplateVersionStatus(io.cucumber.datatable.DataTable dataTable) throws Exception {

        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.UPDATE_TEMPLATE_STATUS_VERSION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        StepUtil.generateParticipantToken("Template");
        StepUtil.generateEngageTrustedIdentifierToken("Template");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesTemplateApi"), ResourceData.getEndPoint("services.template.updateTemplateStstusVersion"));
        HashMap<String, String> pathparams = new HashMap<String, String>();
        pathparams.put("templateKey", this.dataLoad.getTemplateDetails("templateKey"));
        RestUtil.put(pathparams, jsonData, true);
        System.out.println("Status code ; " + RestResponse.getStatusCode());
        HashMap updatedTemplateElements = new HashMap<String, String>();
        updatedTemplateElements.put("status", dataMap.get("status"));
        updatedTemplateElements.put("isActive", dataMap.get("isActive"));
        updatedTemplateElements.put("name", this.dataLoad.getTemplateDetails("name"));
        updatedTemplateElements.put("templateKey", this.dataLoad.getTemplateDetails("templateKey"));
        this.dataLoad.setTemplateDetails(updatedTemplateElements);

    }

    @Then("I should see updated template version status in response")
    public void iShouldSeeUpdatedTemplateVersionStatusInResponse() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status Code validated for updated template");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("templateKey"), RestResponse.getValue("templateKey"), "Validated updated engagementKey description in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("name"), RestResponse.getValue("name"), "Validated updated template name in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("status"), RestResponse.getValue("versions[0].status"), "Validated updated template description in get response");
        Validations.assertEquals(this.dataLoad.getTemplateDetails("isActive"), RestResponse.getValue("versions[0].isActive").toString(), "Validated updated template description in get response");
    }

    }

