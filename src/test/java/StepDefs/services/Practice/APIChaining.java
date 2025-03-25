package StepDefs.services.Practice;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class APIChaining {
    public static void main(String[] args) {
        Response postResponse = given()
                .contentType("application/json")
                .body("{ \"title\": \"API Test\", \"body\": \"Automation\", \"userId\": 1 }")
                .post("https://jsonplaceholder.typicode.com/posts");

        int newPostId = postResponse.jsonPath().getInt("id");

        Response getResponse = given()
                .pathParam("postId", newPostId)
                .get("https://jsonplaceholder.typicode.com/posts/{postId}");

        System.out.println("Fetched Post: " + getResponse.getBody().asString());
    }
}
