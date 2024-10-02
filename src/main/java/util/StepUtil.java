package util;

import configManager.DataLoad;
import configManager.Payloads;
import configManager.ResourceData;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StepUtil {


    /**
     * Return Map data from cucumber DataTable
     *
     * @param dataTable
     * @return
     */
    public static Map<String, String> toMap(DataTable dataTable) {
        List<Map<String, String>> mapList = dataTable.asMaps(String.class, String.class);
        return dataReplace(mapList.get(0));
    }

    /**
     * Return Map data from cucumber DataTable
     *
     * @param dataTable
     * @return
     */
    public static HashMap<String, String> toHashMap(DataTable dataTable) {
        List<Map<String, String>> mapList = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = dataReplace(mapList.get(0));
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        return hashMap;
    }

    /**
     * To replace test data with generated data based on matching patterns like., <datetime>, <rstr5>
     *
     * @param unmap
     * @return
     */
    public static Map<String, String> dataReplace(Map<String, String> unmap) {

        final Map<String, String> map = new HashMap<>(unmap);
        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (entry.getValue().contains("<datetime>")) {
                String value = entry.getValue();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmm");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String ts = sdf1.format(timestamp);
                value = value.replace("<datetime>", ts);
                map.replace(entry.getKey(), entry.getValue(), value);
            }

            if (entry.getValue().contains("<rstr")) {
                String value = entry.getValue();
                int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                String numberOnly = value.replaceAll("[^0-9]", "");
                int targetStringLength = Integer.parseInt(numberOnly);
                Random random = new Random();
                String generatedString = random.ints(leftLimit, rightLimit + 1)
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                value = value.replaceAll("<rstr(.*)>", generatedString.toUpperCase());
                map.replace(entry.getKey(), entry.getValue(), value);
            }
            if (entry.getValue().contains("<rnum4>")) {
                String value = entry.getValue();
                Random random = new Random();
                int randomGeneratedString = random.nextInt(9999);
                if (randomGeneratedString < 1000) {
                    randomGeneratedString = randomGeneratedString + 1000;
                }
                //String randomGeneratedString = random.ints(6,100001,999999).toString();
                value = value.replaceAll("<rnum4>", randomGeneratedString + "");
                //value = value.replaceAll("<rnum6>", (random.nextInt(999999)) + "");
                //value = value.replaceAll("<rnum6>",random.ints(100001, 999999).toString());

                map.replace(entry.getKey(), entry.getValue(), value);
            }
        }
        return map;
    }

    public static void generateParticipantToken(String scope) {
        System.out.println("Generating access token for scope as " + scope + "....");
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setParticipantTokenScope(scope);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesParticipantTokenApi"), ResourceData.getEndPoint("services.participant.tokenApi.createToken"));
        RestUtil.postFormContent(true);
        dataLoad.setParticipantToken(RestResponse.getValue("access_token").toString());
        //System.out.println(dataLoad.getParticipantToken());
        //System.out.println(dataLoad.getRequestURL());
    }

    public static HashMap getEngagementIDWardAccount() throws Exception {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        generateParticipantToken("Engage");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "ServicesEngageEngageApi"), ResourceData.getEndPoint("services.engage.engagements.getAllEngagements"));
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization", "Bearer " + DataLoad.getInstance().getParticipantToken());
        httpRequest.headers(APIHeaders.defaultHeaders());
        RestResponse.initializeWithoutPrintingResponse(httpRequest.get());
        Random random = new Random();
        int randomIndex = 0;
        int numberOfTotalEngagementRecords = RestResponse.getArrayResponseList().size();
        if (numberOfTotalEngagementRecords != 0) {
            randomIndex = random.nextInt(numberOfTotalEngagementRecords);
            hashMap.put("accountKey", RestResponse.getArrayResponseList().get(randomIndex).get("accountKey").toString());
            hashMap.put("engagementKey", RestResponse.getArrayResponseList().get(randomIndex).get("engagementKey").toString());
            hashMap.put("wardKey", RestResponse.getArrayResponseList().get(randomIndex).get("wardKey").toString());
            System.out.println(hashMap);
            return hashMap;
        } else {
            throw new Exception("No existing records available for Ward and Engagement. Please create new");
        }
    }

    public static void generateEngageTrustedIdentifierToken(String scope) {
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setParticipantTokenScope(scope);
        dataLoad.setSubScopeTrustedIdentifier("5d7fb8621dc0df0ac48db264");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesEngageTokenApi"), ResourceData.getEndPoint("services.engage.tokenAPI.trustedIdentifier"));
        RestUtil.postFormContent(true);
        dataLoad.setParticipantToken(RestResponse.getValue("access_token").toString());
        System.out.println(dataLoad.getParticipantToken());
        System.out.println(dataLoad.getRequestURL());
        dataLoad.setSubScopeTrustedIdentifier("");
    }

    public static String generateActionToken(String engagementKey, String wardKey, io.cucumber.datatable.DataTable dataTable) {
        generateParticipantToken("Action");
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), ResourceData.getEndPoint("services.action.createAction"));
        GenericUtil genericUtil = new GenericUtil();
        String payload = genericUtil.getFileData(Payloads.ACTION_CREATEACTION);
        Map<String, String> dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", engagementKey);
        dataMap.put("wardKey", wardKey);
        String jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);
        System.out.println(jsonData);
        String actionKey = RestResponse.getAsString();
        DataLoad.getInstance().setActionKey(actionKey);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), "/action/" + actionKey + "/timeframe");
        payload = genericUtil.getFileData(Payloads.ACTION_ADDTIMEFRAME);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RestUtil.post(jsonData, true);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), "/action/" + actionKey + "/flow");
        payload = genericUtil.getFileData(Payloads.ACTION_ADDFLOWTOACTION);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), "/action/" + actionKey + "/limit");
        payload = genericUtil.getFileData(Payloads.ACTION_ADDLIMITTOACTION);
        dataMap = StepUtil.toMap(dataTable);
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        System.out.println(jsonData);
        RestUtil.post(jsonData, true);
        RestUtil.initialize(ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesActionActionApi"), "/token/request");
        payload = genericUtil.getFileData(Payloads.ACTION_REQUESTTOKEN);
        System.out.println("Generating action token....");
        dataMap = StepUtil.toMap(dataTable);
        dataMap.put("engagementKey", engagementKey);
        dataMap.put("wardKey", wardKey);
        dataMap.put("participantKey", DataLoad.getInstance().getParticipantKey());
        dataMap.put("actionKey", actionKey);
        jsonData = genericUtil.jsonConstruct(dataMap, payload);
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization", "Bearer " + DataLoad.getInstance().getParticipantToken());
        httpRequest.header("x-pl-sandboxkey", "MySandBoxKey");
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.body(jsonData);
        System.out.println(jsonData);
        RestResponse.initialize(httpRequest.post());
        String actionToken = RestResponse.getValue("token").toString();
        return actionToken;
    }
}
