package configManager;

public class Payloads {

    // ********* services.Privacy ************


    public static final String USER_LOGIN = "payloads/services/Login/UserLogin.json";
    // ********* services.address ***********
    public static final String VALIDATE_ADDRESS = "payloads/services/address/validateaddress.json";

    // ********* services.cognitive ***********
    public static final String SCREEN_PROFANITY = "payloads/services/cognitive/screenProfanity.json";

    // ********* services.engage ***********

    public static final String ENGAGE_ACCOUNTS = "payloads/services/engage/Accounts/engageAccounts.json";
    public static final String UPDATE_ENGAGE_ACCOUNTS = "payloads/services/engage/Accounts/updateEngageAccounts.json";
    public static final String CREATE_ENGAGE_ACCOUNT_AUTHENTICATION_PROVIDER = "payloads/services/engage/Accounts/createEngageAccountAuthenticationProvider.json";
    public static final String PARTICIPANT_CREATEPARTICIPANT = "payloads/services/Participant/CreateParticipant.json";
    public static final String PARTICIPANT_CREATEDATEDPARTICIPANTS = "payloads/services/Participant/CreateDatedParticipants.json";
    public static final String PARTICIPANT_REGISTERPARTICIPANT = "payloads/services/Participant/RegisterParticipant.json";
    public static final String PARTICIPANTISPHONEVALID = "payloads/services/Participant/VerifyPhoneIsValid.json";
    public static final String PARTICIPANT_UPDATEPASSWORD = "payloads/services/Participant/UpdatePassword.json";
    public static final String PARTICIPANT_BULKUPSERTPARTICIPANT = "payloads/services/Participant/BulkUpsertParticipants.json";
    public static final String PARTICIPANT_UPDATEPERSONALINFO = "payloads/services/Participant/VerifyUpdatePersonalInfo.json";
    public static final String PARTICIPANT_UPDATEPASSWORDWITHPIN = "payloads/services/Participant/UpdatePasswordwithPin.json";
    public static final String PARTICIPANT_ADDPARTICIPANTADDRESS = "payloads/services/Participant/VerifyaddParticipantAddress.json";
    public static final String PARTICIPANT_ADDPARTICIPANTEMAIL = "payloads/services/Participant/VerifyaddParticipantEmail.json";
    public static final String PARTICIPANT_ADDPARTICIPANTPHONE = "payloads/services/Participant/VerifyaddParticipantPhone.json";
    public static final String PARTICIPANT_ADDATTRIBUTE = "payloads/services/Participant/addattribute.json";
    public static final String PARTICIPANT_ADDENGAGEMENT = "payloads/services/Participant/VerifyaddEngagement.json";
    public static final String PARTICIPANT_HOUSEHOLDDEMOGRAPHICKEY = "payloads/services/Participant/VerifyHouseholdDemographicKey.json";
    public static final String PARTICIPANT_CREATEPARTICIPANTACTION = "payloads/services/Participant/CreateParticipantAction.json";
    public static final String PARTICIPANT_CREATEPARTICIPANT_ISREGESTERED_ORNOT = "payloads/services/Participant/VerifyParticipantisRegiesterorNot.json";
    public static final String PARTICIPANT_UPDATEPARTICIPANTUSERNAME = "payloads/services/Participant/VerifyParticipantsuserName.json";
    public static final String PARTICIPANT_ADDENGAGEMENTTOAPRTICIPANT = "payloads/services/Participant/Addanengagementtoaparticipantviainternalrequest.json";
    public static final String PARTICIPANT_MARKPARTICIPANT_UNDELIVERIBLE = "payloads/services/Participant/MarkParticipantUndeleiverible.json";


    public static final String CREATEACTION = "payloads/services/action/action.json";
    public static final String ENGAGE_CREATEWARD = "payloads/services/engage/Ward/createWard.json";
    public static final String ENGAGE_UPDATEWARD = "payloads/services/engage/Ward/updateWard.json";
    public static final String ENGAGE_CREATEWARDATTRIBUTE = "payloads/services/engage/Ward/createWardAttribute.json";
    public static final String ENGAGE_CREATEWARDATTRIBUTE_VALUE = "payloads/services/engage/Ward/createWardAttributeValue.json";
    public static final String ENGAGE_CREATEACCESSGROUP = "payloads/services/engage/AccessGroup/createAccessGroup.json";
    public static final String ENGAGE_ADDREMOVEENGAGEMENTS = "payloads/services/engage/AccessGroup/addRemoveEngagements.json";
    public static final String ENGAGE_ADDREMOVETEAM = "payloads/services/engage/AccessGroup/addRemoveTeam.json";
    public static final String ENGAGE_CREATEENGAGEMENT = "payloads/services/engage/Engagements/createEngagement.json";
    public static final String ENGAGE_CREATEENGAGEMENTWITHPHASE = "payloads/services/engage/Engagements/createEngagementWithPhase.json";
    public static final String ENGAGE_UPDATEENGAGEMENTWITHPHASE = "payloads/services/engage/Engagements/updateEngagementAddPhase.json";
    public static final String ENGAGE_UPDATEENGAGEMENT = "payloads/services/engage/Engagements/updateEngagement.json";
    public static final String ENGAGE_ADDENGAGEMENTATTRIBUTE = "payloads/services/engage/Engagements/addEngagementAttribute.json";
    public static final String ENGAGE_DELETEENGAGEMENTATTRIBUTE = "payloads/services/engage/Engagements/deleteEngagementAttribute.json";
    public static final String ENGAGE_CREATETEAM = "payloads/services/engage/Team/createTeam.json";
    public static final String ENGAGE_UPDATETEAM = "payloads/services/engage/Team/updateTeam.json";
    public static final String ENGAGE_ADDUSERTOTEAM = "payloads/services/engage/Team/AddUserToTeam.json";
    public static final String ENGAGE_REMOVEUSERTOTEAM = "payloads/services/engage/Team/RemoveUserToTeam.json";
    public static final String ENGAGE_CREATEUSER = "payloads/services/engage/EngageUser/createUser.json";
    public static final String ENGAGE_ADDREMOVEROLETOUSER = "payloads/services/engage/EngageUser/addRemoveRoleOfUser.json";
    public static final String ENGAGE_CHANGEUSEPASSWORD = "payloads/services/engage/EngageUser/changeEngageUserPassword.json";
    public static final String ENGAGE_CREATEINTEGRATION = "payloads/services/engage/Integrations/createIntegration.json";
    public static final String ENGAGE_UPDATEINTEGRATION = "payloads/services/engage/Integrations/updateIntegration.json";
    public static final String ENGAGE_CREATEINTEGRATIONFORENGAGEMENT = "payloads/services/engage/Integrations/createIntegrationForEngagement.json";
    public static final String ENGAGE_UPDATEINTEGRATIONFORENGAGEMENT = "payloads/services/engage/Integrations/updateIntegrationForEngagement.json";
    public static final String ENGAGE_CREATEROLE = "payloads/services/engage/Roles/createRole.json";
    public static final String ENGAGE_UPDATEROLE = "payloads/services/engage/Roles/updateRole.json";

    // ********* services.survey ************
    public static final String CREATE_AND_UPDATE_SURVEY = "payloads/services/survey/createAndUpdateSurvey.json";
    public static final String CREATESURVEYWITHTWOQUESTIONSTHREEANSWERS = "payloads/services/survey/createSurveyWith2Questions3Answers.json";
    public static final String CREATERESPONSE = "payloads/services/survey/response/createResponse.json";
    public static final String CREATERESPONSEWITH1QUESTIONS1ANSWER = "payloads/services/survey/response/createResponseWith1Question1Answer.json";
    public static final String CREATEENGAGEMENTRESPONSE = "payloads/services/survey/response/createEngagementResponse.json";
    public static final String CREATESURVEYWITHTWOQUESTIONSTHREEANSWERSWITHACTIONKEY = "payloads/services/survey/createSurveyWithActionKey.json";


    // ********* services.action ************
    public static final String ACTION_CREATEACTION = "payloads/services/action/createActionForToken.json";
    public static final String ACTION_ADDTIMEFRAME = "payloads/services/action/addTimeframe.json";
    public static final String ACTION_ADDFLOWTOACTION = "payloads/services/action/addFlow.json";
    public static final String ACTION_REQUESTTOKEN = "payloads/services/action/requestToken.json";
    public static final String ACTION_ADDLIMITTOACTION = "payloads/services/action/addLimitForActionToken.json";

    // ********** services.offering ************
    public static final String OFFERING_CREATEOFFERING = "payloads/services/offering/createOffering.json";
    public static final String OFFERING_CREATEOFFERINGSHIPPING = "payloads/services/offering/createOfferingsShipping.json";
    public static final String OFFERING_UPDATEOFFERINGSHIPPING = "payloads/services/offering/updateOfferingsShipping.json";
    public static final String OFFERING_GETOFFERINGCLAIMSTATUS = "payloads/services/offering/getOfferingClaimStatus.json";
    public static final String OFFERING_CREATESHIPPINGCARRER = "payloads/services/offering/createshippingcarrer.json";
    public static final String OFFERING_UPDATESHIPPINGCARRER = "payloads/services/offering/updateshippingcarrer.json";
    public static final String OFFERING_CREATESHIPPINGMETHOD = "payloads/services/offering/createshippingmethod.json";
    public static final String OFFERING_CREATEINVENTORYEDIT = "payloads/services/offering/createInventoryEditusingofferingKey.json";
    public static final String OFFERING_UPDATEOFFERINGUSINGOFFERINGKEY = "payloads/services/offering/updateOfferingusingofferingKey.json";


    // ********** services.sweepstakes ************
    public static final String SWEEPSTAKES_CREATESWEEPSTAKE = "payloads/services/sweepstakes/createSweepstake.json";
    public static final String SWEEPSTAKES_CREATESWEEPSTAKEENTRY = "payloads/services/sweepstakes/createSweepstakeEntry.json";
    public static final String SWEEPSTAKES_UPDATESWEEPSTAKE = "payloads/services/sweepstakes/updateSweepstake.json";

    // ********** services.dispatch **************
    public static final String SUBSCRIPTION_CREATESUBSCRIPTION = "payloads/services/dispatch/subscription/createSubscription.json";
    public static final String SUBSCRIPTION_CREATEOPTIN = "payloads/services/dispatch/subscription/createOptIn.json";
    public static final String SUBSCRIPTION_UPDATE = "payloads/services/dispatch/subscription/updateSubscription.json";
    public static final String SUBSCRIPTION_CREATEOPTOUT = "payloads/services/dispatch/subscription/optOut.json";
    public static final String SUBSCRIPTION_CREATEBULKOPTIN = "payloads/services/dispatch/subscription/createBulkOptin.json";
    public static final String SUBSCRIPTION_CREATEBULKOPTOUT = "payloads/services/dispatch/subscription/bulkOptOut.json";
    public static final String SEND_EMAIL_TO_SINGLE_USER = "payloads/services/dispatch/sendSingle/sendEmailToSingleUser.json";

    // ********* Services.action ******************
    public static final String CREATE_AND_UPDATE_ACTION = "payloads/services/action/action.json";
    public static final String ADD_REQUISITE_TO_ACTION = "payloads/services/action/actionPrerequisite.json";
    public static final String ADD_LIMIT_TO_ACTION = "payloads/services/action/limit.json";
    public static final String ADDFLOWTO_ACTION = "payloads/services/action/addflowtoaction.json";
    public static final String ADDTIMEFRAMETO_ACTION = "payloads/services/action/addtimeframetoaction.json";
    public static final String ADDCONSTRAINTTO_ACTION = "payloads/services/action/addconstraints.json";
    public static final String ADDLIMITTO_ACTION = "payloads/services/action/addlimit.json";
    public static final String ADDAVAILIBITYOFOCCURANCES = "payloads/services/action/AvailibilityofOccuranceStep.json";


    // ********* services.template ************
    public static final String CREATE_TEMPLATE_TYPE = "payloads/services/templates/templateType.json";
    public static final String CREATE_TEMPLATE = "payloads/services/templates/template.json";
    public static final String UPDATE_TEMPLATE = "payloads/services/templates/templateUpdate.json";
    public static final String UPDATE_TEMPLATE_STATUS_VERSION = "payloads/services/templates/updateTemplateVersionStatus.json";

    // ********* services.listCheck ************
    public static final String CREATE_LIST = "payloads/services/listCheck/createList.json";
    public static final String WARD_LIST = "payloads/services/listCheck/wardList.json";
    public static final String REMOVE_LIST_ITEMS = "payloads/services/listCheck/removeItemsFromList.json";
    public static final String UPDATE_LIST = "payloads/services/listCheck/updateList.json";
    public static final String ADD_ITEMS_TO_LIST = "payloads/services/listCheck/addItemsToList.json";


    // ************ services.fraudAudit ****************
    public static final String CREATEENGAGEMENTCONFIG = "payloads/services/fraudAudit/createEngagementConfig.json";
    public static final String UPDATEENGAGEMENTCONFIG = "payloads/services/fraudAudit/updateEngagementConfig.json";
    public static final String CREATEVIOLATION = "payloads/services/fraudAudit/createNewViolation.json";
    public static final String CREATEBLACKLISTIPRECORD = "payloads/services/fraudAudit/createBlacklistIPAddressRecord.json";
    public static final String CREATEBLACKLISTADDRESSRECORD = "payloads/services/fraudAudit/createBlacklistAddressRecord.json";
    public static final String DISABLEBLACKLISTADDRESSRECORD = "payloads/services/fraudAudit/disableBlacklistAddress.json";
    public static final String CREATEBLACKLISTDOMAINRECORD = "payloads/services/fraudAudit/createBlacklistDomainRecord.json";
    public static final String CREATEBLACKLISTPHONERECORD = "payloads/services/fraudAudit/createBlacklistPhoneRecord.json";


    // ********* services.purchaseIncentive *********
    public static final String CREATEPURCHASEINCENTIVE = "payloads/services/purchaseIncentive/purchaseIncentive/createPurchaseIncentive.json";
    public static final String CREATESKUENRICHMENT = "payloads/services/purchaseIncentive/purchaseIncentive/createSKUEnrichment.json";
    public static final String UPDATEPURCHASEINCENTIVE = "payloads/services/purchaseIncentive/purchaseIncentive/updatePurchaseIncentive.json";
    public static final String CREATEOFFER = "payloads/services/purchaseIncentive/offer/createOffer.json";
    public static final String UPDATEOFFER = "payloads/services/purchaseIncentive/offer/updateOffer.json";
    public static final String CREATEPRIZEALLOCATION = "payloads/services/purchaseIncentive/offer/createPrizeAllocation.json";
    public static final String CREATEINVENTORY = "payloads/services/purchaseIncentive/inventory/createInventory.json";
    public static final String CREATESUBMISSION = "payloads/services/purchaseIncentive/submission/createSubmission.json";
    public static final String CREATEHANDLEDSUBMISSIONANDCLAIM = "payloads/services/purchaseIncentive/submission/createHandledSubmissionAndClaim.json";
    public static final String CREATESUBMISSIONANDCLAIM = "payloads/services/purchaseIncentive/submission/createSubmissionAndClaim.json";
    public static final String CLAIMWITHQUALIFIEDSELECTION = "payloads/services/purchaseIncentive/submission/claimWithQualifiedSelection.json";
    public static final String CREATESUBMISSIONOVERRIDE = "payloads/services/purchaseIncentive/submission/createSubmissionOverride.json";
    public static final String UPDATEREVIEW = "payloads/services/purchaseIncentive/submission/updateSubmissionReview.json";
    public static final String UPDATESUBMISSIONCLAIM = "payloads/services/purchaseIncentive/submission/updateSubmissionClaim.json";
    public static final String GETSUBMISSIONSBYPARTICPANTKEYS = "payloads/services/purchaseIncentive/submission/getSubmissionsByParticipantKeys.json";
    public static final String CREATESUBMISSIONANDCLAIMWITHNOREVIEWREQUIRED = "payloads/services/purchaseIncentive/submission/createSubmissionAndClaimWithNoReviewReq.json";
    public static final String CREATESUBMISSIONCLAIM = "payloads/services/purchaseIncentive/submission/createSubmissionClaim.json";
    public static final String CREATERESUBMISSION = "payloads/services/purchaseIncentive/resubmission/createResubmission.json";
    public static final String CANUSERRESUBMIT = "payloads/services/purchaseIncentive/resubmission/canUserResubmit.json";
    public static final String CREATECLAIMOVERRIDE = "payloads/services/purchaseIncentive/submission/createClaimOverride.json";

    // *********** services.document *******************
    public static final String CREATE_FROM_TEMPLATE = "payloads/services/document/envelope/createFromTemplate.json";
    public static final String CREATE_EMBEDED_URL = "payloads/services/document/embededSigning/generateEmbededURL.json";

    // *********** services.rule *******************
    public static final String CREATEENGAGEMENTDATASET = "payloads/services/rule/engagementDataSet/createEngagementDataSet.json";
    public static final String UPDATEENGAGEMENTDATASET = "payloads/services/rule/engagementDataSet/updateEngagementDataSet.json";
    public static final String CREATERULE = "payloads/services/rule/rule/createRule.json";
    public static final String EVALUATEPURCHASEAGAINSTRULE = "payloads/services/rule/evaluation/evaluatePurchaseModelAgainstRuleSet.json";
    public static final String EVALUATESAMPLERULEAGAINSTJSON = "payloads/services/rule/evaluation/evaluateSampleRule.json";
    public static final String PAYLOADJSON = "payloads/services/rule/evaluation/payloadJson.json";
    public static final String EVALUATEARVCALCULATIONRULE = "payloads/services/rule/evaluation/evaluateArvCalculation.json";
    public static final String PAYLOADJSONFORARVCALCULATION = "payloads/services/rule/evaluation/payloadJsonForArvCalculation.json";
    public static final String EVALUATEOFFERSELECTIONRULE = "payloads/services/rule/evaluation/evaluateOfferSelectionRule.json";
    public static final String PAYLOADJSONFOROFFERSELECTION = "payloads/services/rule/evaluation/payloadJsonForOfferSelection.json";
    public static final String EVALUATEDEMOGRPHICSRULE = "payloads/services/rule/evaluation/evaluateDemographicsRule.json";
    public static final String UPDATERULE = "payloads/services/rule/rule/updateRule.json";

    // ********* services.email ************
    public static final String EMAIL_SENDEMAIL = "payloads/services/email/sendEmail.json";
    public static final String EMAIL_SENDEMAILS = "payloads/services/email/sendEmails.json";

    // ********* services.captcha ************
    public static final String CAPTCHA_VALIDATECAPTCHA = "payloads/services/captcha/validateCaptcha.json";

    // ********* services.Privacy ************
    public static final String PRIVACY_CREATEPRIVACY = "payloads/services/Privacy/CreatePrivacy.json";


    public static final String USERS_ADDUSER = "payloads/services/Users/AddUser.json";
}
