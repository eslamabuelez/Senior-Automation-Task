package org.foodics.api.apiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUser {
    private static String baseURL;

    public static Response getUser(String id) {
        APIConfigReader apiConfigReader = new APIConfigReader();
        baseURL = apiConfigReader.getProperty("baseURL");
        System.out.println("URL is : "+baseURL+"/api/users/"+id);
        return RestAssured.given()
                .pathParam("id", id)  // Path parameter for ID
                .when()
                .get(baseURL + "/api/users/{id}")  // Corrected URL syntax
                .then()

                .extract()
                .response();

    }

}
