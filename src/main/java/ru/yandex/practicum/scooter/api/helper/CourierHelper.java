package ru.yandex.practicum.scooter.api.helper;

import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierResponse;

public class CourierHelper {
    CourierApiClient courierApiClient = new CourierApiClient();

    public LoginCourierResponse login(LoginCourierRequest loginCourierRequest){
        return courierApiClient.loginCourier(loginCourierRequest).then().statusCode(200).and().extract().as(LoginCourierResponse.class);
    }
}
