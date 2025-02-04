package org.foodics.api.apiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class CreateUser {
   private static   String baseURL;  // Global base URL

    // Method to send POST request
    public static Response createUser(String name, String job) {
        APIConfigReader apiConfigReader = new APIConfigReader();
        baseURL = apiConfigReader.getProperty("baseURL");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);

        return RestAssured.given()

                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post(baseURL+"/api/users")
                .then()
                .extract()
                .response();
    }
}
