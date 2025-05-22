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

import java.util.HashMap;
import java.util.Map;

public class DocumentSteps {
    DataLoad dataload = DataLoad.getInstance();

    @And("I send request for create envelope from template with below details")
    public void iSendRequestForCreateEnvelopeFromTemplateWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_FROM_TEMPLATE);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        HashMap hashMap = new HashMap<String, String>();
        hashMap = StepUtil.getEngagementIDWardAccount();
        dataMap.put("templateId", ".AQC-Q2LzY8uXzr0rx2pvJioW0");
        dataMap.put("participantKey", this.dataload.getParticipantKey().toString());
        dataMap.put("engagementKey", hashMap.get("engagementKey").toString());
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println("jason data :" + jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesDocumentApi"), ResourceData.getEndPoint("services.document.createFromTemplate"));
        RestUtil.post(jsonData, true);
        HashMap documentDetails = new HashMap<String, String>();
        documentDetails.put("envelopeId", RestResponse.getValue("envelopeId").toString());
        documentDetails.put("name", dataMap.get("name"));
        documentDetails.put("emailAddress", dataMap.get("emailAddress"));
        this.dataload.setDocumentDetails(documentDetails);

    }

    @Then("I should see envelope created successfully with name {string}")
    public void iShouldSeeEnvelopeCreatedSuccessfullyWith(String name) {
        String test = this.dataload.getDocumentDetails().get(name);
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validation for envelope creation");
        Validations.assertEquals(this.dataload.getDocumentDetails().get("envelopeId"), RestResponse.getValue("envelopeId").toString(), "Validated envelopeId: " + RestResponse.getValue("envelopeId").toString());
        Validations.assertEquals(this.dataload.getDocumentDetails().get(name), RestResponse.getValue("signer.name").toString(), "Validated envelope name");
    }

    @And("I send request for generate embeded URL with below details")
    public void iSendRequestForGenerateEmbededURLWithBelowDetails(io.cucumber.datatable.DataTable dataTable) throws Exception {
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.CREATE_EMBEDED_URL);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("name", this.dataload.getDocumentDetails().get("name"));
        dataMap.put("emailAddress", this.dataload.getDocumentDetails().get("emailAddress"));
        dataMap.put("recipientId", "");
        dataMap.put("signerClientId", "");
        String returnUrl = this.dataload.getRequestURL().toString();
        System.out.println("returnURL :" + returnUrl);
        dataMap.put("returnUrl", returnUrl.replace("{envelopeId}", this.dataload.getDocumentDetails().get("envelopeId")));
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        HashMap pathparam = new HashMap<String, String>();
        pathparam.put("envelopeId", this.dataload.getDocumentDetails().get("envelopeId"));
        System.out.println("jason data :" + jsonData);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesDocumentApi"), ResourceData.getEndPoint("services.document.generateEmbededURL"));
        RestUtil.post(pathparam, jsonData, true);

    }

    @Then("I should see generate embeded URL successfully with URL link")
    public void iShouldSeeGenerateEmbededURLSuccessfullyWithURLLink() {

        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for generate embeded URL");
        Validations.assertEquals(true, !RestResponse.getValue("url").toString().isEmpty(), "Validated Embeded URL :" + RestResponse.getValue("url").toString());
    }

    @And("I send a signing return get request")
    public void iSendASigningReturnGetRequest() {
        HashMap pathparam = new HashMap<String, String>();
        pathparam.put("envelopeId", this.dataload.getDocumentDetails().get("envelopeId"));
        pathparam.put("SignerRecipientId", null);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesDocumentApi"), ResourceData.getEndPoint("services.document.signingReturn"));
        RestUtil.get(pathparam, true);
    }


    @Then("I should see signing return successfully")
    public void iShouldSeeSigningReturnSuccessfully() {
        Validations.assertEquals(200, RestResponse.getStatusCode(), "Status code validated for signing return get request");
    }

    @And("I send a final signing return get request")
    public void iSendAFinalSigningReturnGetRequest() {
        HashMap pathparam = new HashMap<String, String>();
        pathparam.put("envelopeId", this.dataload.getDocumentDetails().get("envelopeId"));
        pathparam.put("SignerRecipientId", null);
        Map queryparam = new HashMap<String, String>();
        queryparam.put("event", "signing_complete");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesDocumentApi"), ResourceData.getEndPoint("services.document.signingReturn"));
        RestUtil.get(pathparam, queryparam, true);
    }
}
