package restassuredtest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class GetAndPostExample {

    @Test
    public void testGetRequest() {
        baseURI = "https://reqres.in/";
        System.out.println("Sending GET request to: " + baseURI + "api/users?page=2");
        given().
                when().
                get("api/users?page=2").
                then().
                log().all().
                statusCode(200).
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItems("George", "Rachel")).
                extract().body().asString();
    }
    @Test
    public void testPostRequest() {
        Map<String, Object> map= new HashMap<>();
        map.put("name","Deepen");
        map.put("job", "Qa");
        System.out.println(map);
        JSONObject request = new JSONObject(map);
        System.out.println(request);
        Response response = RestAssured.given().get();
        System.out.println(response.getStatusCode());
    }
}
