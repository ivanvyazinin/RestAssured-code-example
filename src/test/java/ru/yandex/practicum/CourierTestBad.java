package ru.yandex.practicum;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class CourierTestBad {

    @Test
    public void courierTest() {
        String login = "courierTestLogin12345";
        String password = "courierTestPassword";
        String firstName = "courier Test";
        String body = "{\"login\":\"" + login + "\","
                      + "\"password\":\"" + password + "\"," +
                      "\"firstName\":\"" + firstName + "\"}";
        Boolean ok = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier")
                .then().assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
        assertTrue(ok);
        String bodyLogin = "{\"login\":\"" + login + "\"," +
                           "\"password\":\"" + password + "\"}";
        Integer id = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(bodyLogin)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        System.out.println(id);
    }
}