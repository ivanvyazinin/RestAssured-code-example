package ru.yandex.practicum.scooter.api.model;

public class LoginCourierRequest {
    String login;
    String password;

    public LoginCourierRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourierRequest() {
    }
}
