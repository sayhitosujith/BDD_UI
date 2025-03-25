package StepDefs.services.Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIGetTest {
    public static void main(String[] args) {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
