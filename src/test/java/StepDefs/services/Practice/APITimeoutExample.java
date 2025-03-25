package StepDefs.services.Practice;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APITimeoutExample {
    public static void main(String[] args) {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .config(RestAssured.config().httpClient(
                        RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", 5000)));

        request.get("/posts/1")
                .then()
                .statusCode(200);
    }
}
