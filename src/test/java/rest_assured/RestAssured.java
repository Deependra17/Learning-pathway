package rest_assured;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class RestAssured {

    @Test
    public static void getResponseBody() {
        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
                .body();

        given().when().get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1").then().log()
                .all();
    }

}
