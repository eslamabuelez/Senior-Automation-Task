package org.foodics.tests.APITests;

import io.restassured.response.Response;
import org.foodics.api.apiRequests.APIConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends APIConfigReader {

    @Test
    public void testCreateUser() {
        api.CreateUser createUser = new api.CreateUser();
        Response response = api.CreateUser.createUser("RestAssured", "QA Automation Engineer");

        // ✅ Validate HTTP Status Code
        Assert.assertEquals(response.getStatusCode(), 201, "Expected HTTP 201 Created");

        // ✅ Validate JSON Response
        Assert.assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("name"), "RestAssured");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Automation Engineer");

        System.out.println("User Created: " + response.getBody().asString());
    }
}