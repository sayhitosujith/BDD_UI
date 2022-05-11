package configManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLoad {

    private String requestURL;
    private String request;
    private String response;
    private String statusCode;
    private String participantTokenScope;
    private String participantToken;
    private String offeringToken;
    private int executionCount;
    private String authenticationScheme;
    private static List<HashMap<String, String>> dataList;

    // Services data
    private String engageAccountKey;
    private String engageAccountName;
    private String wardKey;
    private String continuationToken;
    private  String engagementKey;
     private String PhoneNumber;
    private String Password;
    private String Passwordwithpin;
    private String participantKey;
    private String PersonalInfo;
    private String pin;
    private String participantAddress;
    private String participantEmail;
    private String participantphone;
    private String ParticipantHouseholdDemographicKey;
    private String attribute;
    private String EngagementIDWardAccount;
    private String ActionElements;
    private String username;
    private String name;
    private String normalizedName;
    private String description;
    private String userName;
    private String city;
    private String accessgroupname;
    private String accessgroupkey;
    private String teamName;
    private String teamKey;
    private String EngageUserKey;
    private String engagementName;
    private String attributeKey;
    private String attributeValueKey;
    private String engagementAttributeValueKey;
    private String subScopeTrustedIdentifier;
    private String roleKeys;
    private String wardDescription;
    private String integrationName;
    private String integrationKey;
    private String roleKey;
    private String surveyKey;
    private String responseKey;
    private String surveyName;
    private String surveyUpdatedElements[];
    private String surveyQuestionKeys[];
    private List<HashMap<String,String>> surveyAnswerKeys;
    private String surveyKeys[];
    private String surveyNames[];
    private  String actionKey;
    private String actionToken;
    private String [] offeringKeys;
    private String offeringKey;
    private String sweepstakeKey;
    private String sweepstakeName;
    private String sweepstakeConfirmKey;
    private String sweepstakeEntrySetKey;
    private String subscriptionKey;
    private String subscriptionName;
    private String shippingMethodKey;
    private HashMap<String,String> optInDetails;
    private  String phaseKey;
    private  String phaseName;
    private  String participantKey1;
    private  String participantActionKey;
    private HashMap<String,String> documentDetails;
    private String wardAttributeKey;


    private String ipAddress;
    private String domain;
    private String phone;
    private String invoiceKey;
    private  String ruleKey;
    private String [] violationDetails;
    private String violationKey;
    private  String purchaseIncentiveKey;
    private String [] purchaseIncentiveDetails;
    private String offerKey;
    private  String [] offerDetails;
    private String submissionKey;
    private String submissionClaimKey;
    private String submissionOverrideKey;
    private String [] engagementDataSetDetails;
    private String engagementDataSetKey;
    private  String [] evaluationData;
    private String offerTag;
    private String ruleModelType;
    private String dataType;



    private HashMap<String, String> actionDetails;
    private String flowKey;
    private String timeframeKey;
    private String ConstraintKey;
    private String occurrenceKey;
    private String OfferingShippingKey;
    private String claimSourceKeys;
    private String shippingCarrierKey;





    private static DataLoad singleInstance = null;
    private String prerequisiteKey;
    private String limitKey;
    private String templateTypeKey;
    private HashMap<String,String> templateDetails;
    private HashMap<String,String> template2Details;
    private HashMap<String,String> listCheckDetails;
    private String  privacyRequestKey;

    public DataLoad() {
    }

    public static DataLoad getInstance() {
        if (singleInstance == null) {
            singleInstance = new DataLoad();
            dataList = new ArrayList<>();
        }
        return singleInstance;
    }


    public List<HashMap<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(HashMap<String, String> dataValidator) {
        dataList.add(dataValidator);
    }

    public String getParticipantToken() {
        if (participantToken == null || participantToken.isEmpty() || participantToken.trim().isEmpty()) {
            participantToken = ResourceData.getConfigProperty("bearerToken");
        }
        return participantToken;
    }

    public void resetDataList() {
        dataList.clear();
    }

    public void setParticipantToken(String participantToken) {
        this.participantToken = participantToken;
    }

    public String getParticipantTokenScope() {
        return participantTokenScope;
    }

    public void setParticipantTokenScope(String participantTokenScope) {
        this.participantTokenScope = participantTokenScope;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public String getEngageAccountKey() {
        return engageAccountKey;
    }

    public void setEngageAccountKey(String engageAccountKey) {
        this.engageAccountKey = engageAccountKey;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public String getAuthenticationScheme() {
        return authenticationScheme;
    }

    public void setAuthenticationScheme(String authenticationScheme) {
        this.authenticationScheme = authenticationScheme;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getParticipantKey() {
        return participantKey;
    }

    public void setParticipantKey(String participantKey) {
        this.participantKey = participantKey;
    }

    public String getPersonalInfo() {
        return PersonalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        PersonalInfo = personalInfo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(String participantAddress) {
        this.participantAddress = participantAddress;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getParticipantphone() {
        return participantphone;
    }

    public void setParticipantphone(String participantphone) {
        this.participantphone = participantphone;
    }

    public String getParticipantHouseholdDemographicKey() {
        return ParticipantHouseholdDemographicKey;
    }

    public void setParticipantHouseholdDemographicKey(String participantHouseholdDemographicKey) {
        ParticipantHouseholdDemographicKey = participantHouseholdDemographicKey;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getEngagementIDWardAccount() {
        return EngagementIDWardAccount;
    }

    public void setEngagementIDWardAccount(String engagementIDWardAccount) {
        EngagementIDWardAccount = engagementIDWardAccount;
    }

    public String getPasswordwithpin() {
        return Passwordwithpin;
    }

    public void setPasswordwithpin(String passwordwithpin) {
        Passwordwithpin = passwordwithpin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getRoleKeys() {
        return roleKeys;
    }

    public void setRoleKeys(String roleKeys) {
        this.roleKeys = roleKeys;
    }

    public String getTeamname() {
        return teamName;
    }

    public void setTeamname(String teamName) {
        this.teamName = teamName;
    }

    public String getAccessgroupname() {
        return accessgroupname;
    }

    public void setAccessgroupname(String accessgroupname) {
        this.accessgroupname = accessgroupname;
    }

    public String getAccessgroupkey() {return accessgroupkey; }

    public void setAccessgroupkey(String accessgroupkey) {
        this.accessgroupkey = accessgroupkey;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getEngageUserKey() {
        return EngageUserKey;
    }

    public void setEngageUserKey(String engageUserKey) {
        EngageUserKey = engageUserKey;
    }

    public String getEngageAccountName() {
        return engageAccountName;
    }

    public void setEngageAccountName(String engageAccountName) {
        this.engageAccountName = engageAccountName;
    }

    public String getEngagementName() {
        return engagementName;
    }

    public void setEngagementName(String engagementName) {
        this.engagementName = engagementName;
    }

    public String getAttributeValueKey() {
        return attributeValueKey;
    }

    public void setAttributeValueKey(String attributeValueKey) {
        this.attributeValueKey = attributeValueKey;
    }

    public String getWardDescription() {
        return wardDescription;
    }

    public void setWardDescription(String wardDescription) {
        this.wardDescription = wardDescription;
    }

    public String getIntegrationName() {
        return integrationName;
    }

    public void setIntegrationName(String integrationName) {
        this.integrationName = integrationName;
    }

    public String getIntegrationKey() {
        return integrationKey;
    }

    public void setIntegrationKey(String integrationKey) {
        this.integrationKey = integrationKey;
    }

    public String getEngagementAttributeValueKey() {
        return engagementAttributeValueKey;
    }

    public void setEngagementAttributeValueKey(String engagementAttributeValueKey) {
        this.engagementAttributeValueKey = engagementAttributeValueKey;
    }

    public String getSubScopeTrustedIdentifier() {
        return subScopeTrustedIdentifier;
    }

    public void setSubScopeTrustedIdentifier(String subScopeTrustedIdentifier) {
        this.subScopeTrustedIdentifier = subScopeTrustedIdentifier;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String[] getSurveyUpdatedElements() {
        return surveyUpdatedElements;
    }

    public void setSurveyUpdatedElements(String[] surveyUpdatedElements) {
        this.surveyUpdatedElements = surveyUpdatedElements;
    }

    public String[] getSurveyQuestionKeys() {
        return surveyQuestionKeys;
    }

    public void setSurveyQuestionKeys(String[] surveyQuestionKeys) {
        this.surveyQuestionKeys = surveyQuestionKeys;
    }

    public String[] getSurveyKeys() {
        return surveyKeys;
    }

    public void setSurveyKeys(String[] surveyKeys) {
        this.surveyKeys = surveyKeys;
    }

    public String[] getSurveyNames() {
        return surveyNames;
    }

    public void setSurveyNames(String[] surveyNames) {
        this.surveyNames = surveyNames;
    }

    public String getSurveyKey() {
        return surveyKey;
    }

    public void setSurveyKey(String surveyKey) {
        this.surveyKey = surveyKey;
    }

    public String getResponseKey() {
        return responseKey;
    }

    public void setResponseKey(String responseKey) {
        this.responseKey = responseKey;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public List<HashMap<String, String>> getSurveyAnswerKeys() {
        return surveyAnswerKeys;
    }

    public void setSurveyAnswerKeys(List<HashMap<String, String>> surveyAnswerKeys) {
        this.surveyAnswerKeys = surveyAnswerKeys;
    }

    public String getActionToken() {
        return actionToken;
    }

    public void setActionToken(String actionToken) {
        this.actionToken = actionToken;
    }



    public String getSweepstakeKey() {
        return sweepstakeKey;
    }

    public void setSweepstakeKey(String sweepstakeKey) {
        this.sweepstakeKey = sweepstakeKey;
    }

    public String getSweepstakeConfirmKey() {
        return sweepstakeConfirmKey;
    }

    public void setSweepstakeConfirmKey(String sweepstakeConfirmKey) {
        this.sweepstakeConfirmKey = sweepstakeConfirmKey;
    }



    public String getSweepstakeName() {
        return sweepstakeName;
    }

    public void setSweepstakeName(String sweepstakeName) {
        this.sweepstakeName = sweepstakeName;
    }

    public String getSetSweepstakeEntrySetKey() {
        return sweepstakeEntrySetKey;
    }

    public void setSweepstakeEntrySetKey(String sweepstakeEntrySetKey) {
        this.sweepstakeEntrySetKey = sweepstakeEntrySetKey;
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(String subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }


    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public HashMap<String, String> getOptInDetails() {
        return optInDetails;
    }

    public void setOptInDetails(HashMap<String, String> optInDetails) {
        this.optInDetails = optInDetails;
    }

    public void setActionElements(HashMap store) {
        actionDetails = new HashMap<String,String>();
        actionDetails.putAll(store);
    }

    public String getActionElements(String element) {
        return actionDetails.get(element);
    }

    public String getPrerequisiteKey() {
        return prerequisiteKey;
    }

    public void setPrerequisiteKey(String prerequisiteKey) {
        this.prerequisiteKey = prerequisiteKey;
    }

    public String getLimitKey() {
        return limitKey;
    }

    public void setLimitKey(String limitKey) {
        this.limitKey = limitKey;
    }

    public String getTemplateTypeKey() {
        return templateTypeKey;
    }

    public void setTemplateTypeKey(String templateTypeKey) {
        this.templateTypeKey = templateTypeKey;
    }

    public String getTemplateDetails(String element) {
        return templateDetails.get(element);
    }

    public void setTemplateDetails(HashMap templateElements) {

        templateDetails = new HashMap<String,String>();
        templateDetails.putAll(templateElements);

    }
    public String getTemplate2Details(String element) {
        return template2Details.get(element);
    }

    public void setTemplate2Details(HashMap templateElements) {

        template2Details = new HashMap<String,String>();
        template2Details.putAll(templateElements);

    }



    public String getWardKey() {
        return wardKey;
    }

    public void setWardKey(String wardKey) {
        this.wardKey = wardKey;
    }

    public String getEngagementKey() {
        return engagementKey;
    }

    public void setEngagementKey(String engagementKey) {
        this.engagementKey = engagementKey;
    }

    public String getFlowKey() {
        return flowKey;
    }


    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getTimeframeKey() {
        return timeframeKey;
    }

    public void setTimeframeKey(String timeframeKey) {
        this.timeframeKey = timeframeKey;
    }

    public String getConstraintKey() {
        return ConstraintKey;
    }

    public void setConstraintKey(String constraintKey) {
        ConstraintKey = constraintKey;
    }

    public String getOccurrenceKey() {
        return occurrenceKey;
    }

    public void setOccurrenceKey(String occurrenceKey) {
        this.occurrenceKey = occurrenceKey;
    }

    public HashMap<String, String> getListCheckDetails() {
        return listCheckDetails;
    }

    public void setListCheckDetails(HashMap<String, String> listCheckelements) {
        listCheckDetails = new HashMap<String,String>();
        listCheckDetails.putAll(listCheckelements);
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getPhaseKey() {
        return phaseKey;
    }

    public void setPhaseKey(String phaseKey) {
        this.phaseKey = phaseKey;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String[] getViolationDetails() {
        return violationDetails;
    }

    public void setViolationDetails(String[] violationDetails) {
        this.violationDetails = violationDetails;
    }

    public String getViolationKey() {
        return violationKey;
    }

    public void setViolationKey(String violationKey) {
        this.violationKey = violationKey;
    }

    public String getOfferingShippingKey() {
        return OfferingShippingKey;
    }

    public void setOfferingShippingKey(String offeringShippingKey) {
        OfferingShippingKey = offeringShippingKey;
    }

    public String getOfferingToken() {
        return offeringToken;
    }

    public void setOfferingToken(String offeringToken) {
        this.offeringToken = offeringToken;
    }

    public String getShippingMethodKey() {
        return shippingMethodKey;
    }

    public void setShippingMethodKey(String shippingMethodKey) {
        this.shippingMethodKey = shippingMethodKey;
    }

    public String getClaimSourceKeys() {
        return claimSourceKeys;
    }

    public void setClaimSourceKeys(String claimSourceKeys) {
        this.claimSourceKeys = claimSourceKeys;
    }

    public String getshippingCarrierKey() {
        return shippingCarrierKey;
    }

    public void setshippingCarrierKey(String shippingCarrierKey) {
        this.shippingCarrierKey = shippingCarrierKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getOfferingKeys() {
        return offeringKeys;
    }

    public void setOfferingKeys(String[] offeringKeys) {
        this.offeringKeys = offeringKeys;
    }

    public String getOfferingKey() {
        return offeringKey;
    }

    public void setOfferingKey(String offeringKey) {
        this.offeringKey = offeringKey;
    }

    public String getPurchaseIncentiveKey() {
        return purchaseIncentiveKey;
    }

    public void setPurchaseIncentiveKey(String purchaseIncentiveKey) {
        this.purchaseIncentiveKey = purchaseIncentiveKey;
    }

    public String[] getPurchaseIncentiveDetails() {
        return purchaseIncentiveDetails;
    }

    public void setPurchaseIncentiveDetails(String[] purchaseIncentiveDetails) {
        this.purchaseIncentiveDetails = purchaseIncentiveDetails;
    }

    public String getOfferKey() {
        return offerKey;
    }

    public void setOfferKey(String offerKey) {
        this.offerKey = offerKey;
    }

    public String[] getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String[] offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getSubmissionKey() {
        return submissionKey;
    }

    public void setSubmissionKey(String submissionKey) {
        this.submissionKey = submissionKey;
    }

    public String getSubmissionClaimKey() {
        return submissionClaimKey;
    }

    public void setSubmissionClaimKey(String submissionClaimKey) {
        this.submissionClaimKey = submissionClaimKey;
    }

    public String getSubmissionOverrideKey() {
        return submissionOverrideKey;
    }

    public void setSubmissionOverrideKey(String submissionOverrideKey) {
        this.submissionOverrideKey = submissionOverrideKey;
    }

    public String getParticipantKey1() {
        return participantKey1;
    }

    public void setParticipantKey1(String participantKey) {
        this.participantKey1 = participantKey;
    }

    public HashMap<String, String> getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(HashMap<String, String> documentElements) {
        documentDetails = new HashMap<String,String>();
        documentDetails.putAll(documentElements);
    }

    public String[] getEngagementDataSetDetails() {
        return engagementDataSetDetails;
    }

    public void setEngagementDataSetDetails(String[] engagementDataSetDetails) {
        this.engagementDataSetDetails = engagementDataSetDetails;
    }

    public String getEngagementDataSetKey() {
        return engagementDataSetKey;
    }

    public void setEngagementDataSetKey(String engagementDataSetKey) {
        this.engagementDataSetKey = engagementDataSetKey;
    }

    public String[] getEvaluationData() {
        return evaluationData;
    }

    public void setEvaluationData(String[] evaluationData) {
        this.evaluationData = evaluationData;
    }

    public String getOfferTag() {
        return offerTag;
    }

    public void setOfferTag(String offerTag) {
        this.offerTag = offerTag;
    }

    public String getRuleModelType() {
        return ruleModelType;
    }

    public void setRuleModelType(String ruleModelType) {
        this.ruleModelType = ruleModelType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPrivacyRequestKey() {
        return privacyRequestKey;
    }

    public void setPrivacyRequestKey(String privacyRequestKey) {
        this.privacyRequestKey = privacyRequestKey;
    }

    public String getParticipantActionKey() {
        return participantActionKey;
    }

    public void setParticipantActionKey(String participantActionKey) {
        this.participantActionKey = participantActionKey;
    }


    public String getWardAttributeKey() {
        return wardAttributeKey;
    }

    public void setWardAttributeKey(String wardAttributeKey) {
        this.wardAttributeKey = wardAttributeKey;
    }
}



