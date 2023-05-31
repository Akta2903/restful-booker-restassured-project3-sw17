package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {
    static String firstName = "Sally" + TestUtils.getRandomValue();
    static String lastName = "Brown";
    static int totalPrice = 121;
    static boolean depositPaid = true;
    static String additionalNeeds="Breakfast";
    static int bookingId;




    //Create booking
    @Test
    public void test001()

    {
        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstName);
        bookingPojo.setLastname(lastName);
        bookingPojo.setTotalprice(totalPrice);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds(additionalNeeds);
        Response response = given()
                .header("Content-Type","application/json")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
        bookingId = response.jsonPath().get("bookingid");
        System.out.println("Booking Id : " + bookingId);
    }
    //get single booking
    @Test
    public void test002()
    {
        Response response = given()

                .when()
                .get("/booking" + "/" + bookingId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    //update the booking
    @Test
    public void test003()
    {
        AuthCreateTest auth = new AuthCreateTest();
        String token = auth.getToken();

        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2023-03-29");
        bookingDates.put("checkout", "2023-04-02");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Jenifer" + firstName);
        bookingPojo.setLastname("Winget");
        bookingPojo.setTotalprice(1000);
        bookingPojo.setDepositpaid(true);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds("Lunch");

        Response response = given()
                .auth().preemptive()
                .basic("admin","password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token =02f5fd5946ffa39" )
                .body(bookingPojo)
                .when()
                .put("/booking" + "/" + bookingId);
        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }
    //update the partial booking
    @Test
    public void test004()
    {
        AuthCreateTest auth = new AuthCreateTest();
        String token = auth.getToken();

        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2022-03-13");
        bookingDates.put("checkout", "2022-04-01");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Michale" + firstName);
        bookingPojo.setLastname("George");
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds("Veg Meal");

        Response response = given()
                .auth().preemptive()
                .basic("admin","password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token = 02f5fd5946ffa39")
                .body(bookingPojo)
                .when()
                .patch("/booking" + "/" + bookingId);
        response.then().statusCode(200);
        response.prettyPrint();


    }//Delete the booking

    @Test
    public void test005()
    {
        AuthCreateTest auth = new AuthCreateTest();
        String token = auth.getToken();

        Response response = given()
                .auth().preemptive()
                .basic("admin","password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token = 02f5fd5946ffa39")

                .when()
                .delete("/booking" + "/" + bookingId);
                response.then().statusCode(201);
                response.prettyPrint();
    }
//Get all the bookings
@Test
public void test006()
{
    Response response = given()
            .when()
            .get("/booking");
    response.then().statusCode(200);
    response.prettyPrint();

}



}
