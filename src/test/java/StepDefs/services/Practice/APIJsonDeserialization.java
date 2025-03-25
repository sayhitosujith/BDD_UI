package StepDefs.services.Practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

class Post {
    public int userId;
    public int id;
    public String title;
    public String body;
}

public class APIJsonDeserialization {
    public static void main(String[] args) throws IOException {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        ObjectMapper objectMapper = new ObjectMapper();
        Post post = objectMapper.readValue(response.getBody().asString(), Post.class);

        System.out.println("Title: " + post.title);
    }
}
