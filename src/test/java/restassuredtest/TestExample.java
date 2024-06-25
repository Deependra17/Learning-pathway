package restassuredtest;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExample {

    @Test
    public void test_1() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.asString());
        System.out.println(response.getContentType());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusLine());
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200, "Status code does not match");
    }

    @Test
    public void test_2() {
        baseURI = "https://reqres.in/";
        given().
                get("api/users?page=2").
                then().
                statusCode(200).
                body("data[1].id", equalTo(8)).
                log().all();

    }
}
