package com.restful.booker.crudtest;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthCreateTest extends TestBase {

    String token;

//Token creation

    @Test
    public void createToken()
    {

//        AuthPojo authPojo = new AuthPojo();
//        authPojo.setUserName("admin");
//        authPojo.setPassword( "password123");

        Response response = given()
                .auth()
                .basic("admin" , "password123")
                .header("Content-Type", "application/json")
               // .body(authPojo)
                .when()
                .post("/auth");
        response.then().statusCode(200);
      //  token = (response.path("token"));
        response.prettyPrint();




    }

    public String getToken()
    {
        AuthCreateTest auth = new AuthCreateTest();
        return auth.token;
    }

}
