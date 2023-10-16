package ru.yandex.practicum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.CourierClient;
import ru.yandex.practicum.scooter.api.model.Courier;
import ru.yandex.practicum.scooter.api.model.CourierCredentials;
import ru.yandex.practicum.scooter.api.model.CreateCourierResponse;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.scooter.api.model.Courier.getRandomCourier;

public class CourierTest {
    int courierId;
    Courier courier;

    CourierClient courierClient;

    @Before
    public void init() {
        courier = getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void clear() {
        courierClient.deleteCourier(courierId);
    }

    @Test
    public void courierTest() {
        //Делаем действие
        Response responseCreate = courierClient.createCourier(courier);

        //Проверка
        assertEquals(SC_CREATED, responseCreate.statusCode());
        CreateCourierResponse createCourierResponse = responseCreate.as(CreateCourierResponse.class);
        assertTrue(createCourierResponse.ok);

        //Проверка дополнительная
        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
        Response responseLogin = courierClient.login(courierCredentials);
        assertEquals(SC_OK, responseLogin.statusCode());
        courierId = responseLogin.body().jsonPath().getInt("id");
    }
}