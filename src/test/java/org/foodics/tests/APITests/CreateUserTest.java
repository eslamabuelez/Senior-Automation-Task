package org.foodics.tests.APITests;

import io.restassured.response.Response;
import org.foodics.api.apiRequests.APIConfigReader;
import org.foodics.api.apiRequests.CreateUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.ITestContext;
public class CreateUserTest extends APIConfigReader {

    @Test
    public void testCreateUser(ITestContext context) {
        CreateUser createUser = new CreateUser();
        Response response = createUser.createUser("RestAssured", "QA Automation Engineer");

        // Validate HTTP Status Code
        Assert.assertEquals(response.getStatusCode(), 201, "Expected HTTP 201 Created");

        // Validate JSON Response
        String id = response.jsonPath().getString("id");
        Assert.assertNotNull(id, "User ID should not be null");

        Assert.assertEquals(response.jsonPath().getString("name"), "RestAssured");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Automation Engineer");

        System.out.println("User Created: " + response.getBody().asString());
        context.setAttribute("id", id);
    }
}
