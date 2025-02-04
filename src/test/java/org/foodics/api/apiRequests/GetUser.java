package org.foodics.api.apiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUser {
    private static String baseURL;

    public static Response getUser(String id) {
        APIConfigReader apiConfigReader = new APIConfigReader();
        baseURL = apiConfigReader.getProperty("baseURL");

        return RestAssured.given()
                .pathParam("id", id)  // Path parameter for ID
                .when()
                .get(baseURL + "/{id}")  // Corrected URL syntax
                .then()
                .extract()
                .response();
    }
}
