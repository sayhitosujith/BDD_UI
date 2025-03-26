package StepDefs.services.Practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import java.io.File;

public class NaukriResumeUpdateTest {

    public static void main(String[] args) {
        // Set Base URI for the Naukri profile update endpoint
        RestAssured.baseURI = "https://www.naukri.com/mnjuser/profile";

        // Authenticate and get session token (Assumed API, replace with actual if available)
        Response authResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"sayhitosujith@gmail.com\", \"password\":\"Qw@12345678\"}")
                .post("/login");

        // Check if authentication was successful
        if (authResponse.statusCode() != 200) {
            System.out.println("Authentication failed: " + authResponse.getBody().asString());
            return;
        }

        // Extract the token from the response
        String token = authResponse.jsonPath().getString("token");
        if (token == null || token.isEmpty()) {
            System.out.println("Failed to retrieve authentication token.");
            return;
        }

        // Define the path to the resume file you want to upload
        File resumeFile = new File(System.getProperty("user.dir") + "/resources/files/your_resume.pdf");

        // Check if the resume file exists
        if (!resumeFile.exists()) {
            System.out.println("Resume file not found!");
            return;
        }

        // Make the request to upload the resume
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .multiPart("resume", resumeFile)
                .post("/uploadResume");

        // Handle the response
        if (response.statusCode() == 200) {
            System.out.println("Resume updated successfully!");
        } else {
            System.out.println("Failed to update resume: " + response.getBody().asString());
        }
    }
}
