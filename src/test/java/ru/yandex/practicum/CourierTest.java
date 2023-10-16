package ru.yandex.practicum;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.helper.CourierHelper;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.CreateCourierResponse;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierResponse;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.*;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getRandomCourier;

public class CourierTest {
    CreateCourierRequest createCourierRequest;
    LoginCourierRequest loginCourierRequest;
    CourierApiClient courierApiClient;
    CourierHelper courierHelper;

    @Before
    public void setup() {
        courierApiClient = new CourierApiClient();
        createCourierRequest = getRandomCourier();
        loginCourierRequest = new LoginCourierRequest(createCourierRequest.login, createCourierRequest.password);
    }

    @Test
    public void courierCreateTest() {
        Response createResponse = courierApiClient.createCourier(createCourierRequest);
        assertEquals(SC_CREATED, createResponse.statusCode());
        CreateCourierResponse createCourierResponse = createResponse.as(CreateCourierResponse.class);
        assertTrue(createCourierResponse.ok);

        LoginCourierResponse loginCourierResponse = courierHelper.login(loginCourierRequest);
        assertNotNull(loginCourierResponse.id);
    }
}