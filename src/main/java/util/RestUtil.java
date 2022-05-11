package util;

import configManager.DataLoad;
import configManager.ResourceData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestUtil {

    public static void initialize(String baseURI,String path){
        RestAssured.baseURI= baseURI;
        RestAssured.basePath = path;
        DataLoad dataLoad = DataLoad.getInstance();
        dataLoad.setRequestURL(baseURI+path);
        System.out.println("URL = "+dataLoad.getRequestURL());
    }

    public static String getRequestURL(String environmentURL, String endpoint){
        String envURL = ResourceData.getEnvironmentURL(ResourceData.getEnvironment()+"."+environmentURL);
        String endpoints = ResourceData.getEndPoint(endpoint);
        return envURL+endpoints;
    }

    public static void get(boolean authorization){
        get(null,null,authorization);
    }

    public static void get(Map<String, String> queryParams, boolean authorization){
        get(null,queryParams,authorization);
    }

    public static void get(HashMap<String, String> pathParams, boolean authorization){
        get(pathParams,null,authorization);
    }

    public static void post(String payload,boolean authorization){
        post(null,null,payload,authorization);
    }

    public static void post(HashMap<String, String> pathParams, boolean authorization){
        post(pathParams,null,null,authorization);
    }

    public static void post(Map<String, String> queryParams, String payload, boolean authorization){
        post(null,queryParams,payload,authorization);
    }

    public static void post(HashMap<String, String> pathParams, String payload, boolean authorization){

        post(pathParams,null,payload,authorization);
    }

    public static void put(HashMap<String, String> pathParams,boolean authorization){
        put(pathParams,null,null,authorization);
    }
    public static void put(String payload,boolean authorization){
        put(null,null,payload,authorization);
    }

    public static void put(Map<String, String> queryParams, String payload, boolean authorization){
        put(null,queryParams,payload,authorization);
    }

    public static void put(HashMap<String, String> pathParams, String payload, boolean authorization){
        put(pathParams,null,payload,authorization);
    }

    public static void put(HashMap<String, String> pathParams,Map<String, String> queryParams, boolean authorization){
        put(pathParams,queryParams,null,authorization);
    }

    public static void delete(boolean authorization){
        delete(null,null,authorization);
    }

    public static void delete(Map<String, String> queryParams, boolean authorization){
        delete(null,queryParams,null,authorization);
    }
    public static void delete(HashMap<String, String> pathParams, String payload, boolean authorization){
        delete(pathParams,null,payload,authorization);
    }

    public static void delete(HashMap<String, String> pathParams, boolean authorization){
        delete(pathParams,null,null,authorization);
    }

    public static void get(HashMap<String, String> pathParams,Map<String, String> queryParams, boolean authorization){
        RequestSpecification httpRequest = RestAssured.given();
        DataLoad dataLoad = DataLoad.getInstance();
        if(authorization){
            httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        }
        if(pathParams!=null) {
            httpRequest.pathParams(pathParams);
        }
        if(queryParams!=null) {
            httpRequest.queryParams(queryParams);
        }
        dataLoad.setRequest("");
        RestResponse.initialize(httpRequest.get());
    }


    public static void post(HashMap<String, String> pathParams,Map<String, String> queryParams, String payload, boolean authorization){
        RequestSpecification httpRequest = RestAssured.given();
        if(authorization){
            DataLoad dataLoad = DataLoad.getInstance();
            httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        }
        httpRequest.headers(APIHeaders.defaultHeaders());
        if(pathParams!=null) {
            httpRequest.pathParams(pathParams);
        }
        if(queryParams!=null) {
            httpRequest.queryParams(queryParams);
        }
        if(payload!=null) {
            httpRequest.body(payload);
        }
        RestResponse.initialize(httpRequest.post());
    }


    public static void postFormContent(boolean authorization){
        RequestSpecification httpRequest = RestAssured.given();
        if(authorization){
            DataLoad dataLoad = DataLoad.getInstance();
            httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        }
        httpRequest.headers(APIHeaders.defaultHeaders());
        httpRequest.contentType(ContentType.URLENC.withCharset("UTF-8"));
        httpRequest.formParams(APIFormParams.defaultFormParams());
        RestResponse.initialize(httpRequest.post());
    }

    public static void put(HashMap<String, String> pathParams,Map<String, String> queryParams, String payload, boolean authorization){
        RequestSpecification httpRequest = RestAssured.given();
        if(authorization){
            DataLoad dataLoad = DataLoad.getInstance();
            httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        }
        if(pathParams!=null) {
            httpRequest.pathParams(pathParams);
        }
        if(queryParams!=null) {
            httpRequest.queryParams(queryParams);
        }
        if(payload!=null){
            httpRequest.body(payload);
        }
        httpRequest.headers(APIHeaders.defaultHeaders());
        RestResponse.initialize(httpRequest.put());
    }

    public static void delete(HashMap<String, String> pathParams,Map<String, String> queryParams,String payload ,boolean authorization){
        RequestSpecification httpRequest = RestAssured.given();
        if(authorization){
            DataLoad dataLoad = DataLoad.getInstance();
            httpRequest.header("Authorization","Bearer "+dataLoad.getParticipantToken());
        }
        if(pathParams!=null) {
            httpRequest.pathParams(pathParams);
        }
        if(queryParams!=null) {
            httpRequest.queryParams(queryParams);
        }
        httpRequest.headers(APIHeaders.defaultHeaders());
        if(payload!=null){
            httpRequest.body(payload);
        }
        RestResponse.initialize(httpRequest.delete());
    }

    public static Response postFormParamsWithFileUpload(Map<String, String> formdata, String filepath, String URI) throws IOException {
        StepUtil.generateParticipantToken("ListCheck");
        StepUtil.generateEngageTrustedIdentifierToken("ListCheck" );
        RequestSpecification httpRequest = RestAssured.given();
        DataLoad dataLoad = DataLoad.getInstance();
        httpRequest.header("Authorization", "Bearer " + dataLoad.getParticipantToken());
        httpRequest.header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
        httpRequest.formParams(formdata);
        return httpRequest.multiPart("file", new File(filepath)).when().post(URI);
    }
    public static Response iSendRequestForCreateBulkUpsertAParticipantWithCSVFileWithBelowDetails(Map<String, String> formdata, String filepath, String URI) {
        StepUtil.generateParticipantToken("Participant");
        StepUtil.generateEngageTrustedIdentifierToken("Participant" );
        RequestSpecification httpRequest = RestAssured.given();
        DataLoad dataLoad = DataLoad.getInstance();
        httpRequest.header("Authorization", "Bearer " + dataLoad.getParticipantToken());
        httpRequest.header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
        httpRequest.formParams(formdata);
        return httpRequest.multiPart("UploadFile", new File(filepath)).when().post(URI);

    }


    public static void initialize(String url) {

            RestAssured.baseURI= url;

            DataLoad dataLoad = DataLoad.getInstance();
            dataLoad.setRequestURL(url);
            System.out.println("URL = "+dataLoad.getRequestURL());
        }
    }

