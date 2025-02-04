package org.foodics.tests.APITests;

import io.restassured.response.Response;
import org.foodics.api.apiRequests.APIConfigReader;
import org.foodics.api.apiRequests.GetUser;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class getUserTest extends APIConfigReader{

    @Test(dependsOnMethods = "org.foodics.tests.APITests.CreateUserTest.testCreateUser")
    public void testGetUser(ITestContext context){

        //GetUser getUser = new GetUser();
        String id = (String) context.getAttribute("id");
        Assert.assertNotNull(id, "User ID should be available from CreateUserTest");

        // Call GetUser API
        Response response = GetUser.getUser(id);

        // Validate Response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");

        String returnedId = response.jsonPath().getString("id");
        Assert.assertEquals(returnedId, id, "User ID should match the created user");

        System.out.println("User Retrieved: " + response.getBody().asString());
    }
}



