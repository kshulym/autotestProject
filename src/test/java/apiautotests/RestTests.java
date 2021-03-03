package apiautotests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RestTests {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void userCreationTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Jira");
        requestBody.put("job", "board");

        Response response = RestAssured.given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody.toString())
                .when().post("/api/users")
                .then().log().all().extract().response();

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("Jira", response.jsonPath().getString("name"));
        Assert.assertTrue(response.asString().contains("createdAt"));
    }

    @Test
    public void getUserById() {
        Response response = RestAssured.when()
                .get("/api/users/12")
                .then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void updateUserTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Idea");
        requestBody.put("job", "ide");

        Response response = RestAssured.given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody.toString())
                .when().put("/api/users/11")
                .then().log().all().extract().response();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Idea", response.jsonPath().getString("name"));
        Assert.assertTrue(response.asString().contains("updatedAt"));
    }

    @Test
    public void deleteUserById() {
        Response response = RestAssured.when()
                .delete("/api/users/12")
                .then().log().all().extract().response();
        Assert.assertEquals(204, response.getStatusCode());
    }
}
