package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class BookingAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIT()
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //RestAssured.basePath="/booking";
        response = given()
                .when()
                .get("/booking")
                .then().statusCode(200);
    }
    //1.Verify the firstname of id=1130 is 'Josh'
    @Test
    public void test001()
    {
        response.body("booking.findAll{it.firstname==1130}.totalprice",equalTo(111));
    }
}
