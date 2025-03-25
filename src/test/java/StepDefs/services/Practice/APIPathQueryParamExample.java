package StepDefs.services.Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIPathQueryParamExample {
    public static void main(String[] args) {
        Response response = RestAssured.given()
                .pathParam("postId", 1)
                .queryParam("userId", 10)
                .get("https://jsonplaceholder.typicode.com/posts/{postId}");

        System.out.println(response.getBody().asString());
    }
}
