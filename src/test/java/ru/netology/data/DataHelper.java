package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker();

    //    Создаем подключение к БД
    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("petya", faker.internet().password());
    }

    @SneakyThrows
    public static String getStatus(AuthInfo authInfo) {
        String status = null;
        var loginSQL = "SELECT status FROM users WHERE login = ?";
        try (var conn = getConnection();
             var loginStMt = conn.prepareStatement(loginSQL)) {
            loginStMt.setString(1, authInfo.getLogin());
            try (var rs = loginStMt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @Value
    public static class VerificationCode {
        String verificationCode;
    }

    // Метод принимает AuthInfo для нахождения сгенерированного пароля по необходимому нам логину
    @SneakyThrows
    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
//        Находим в БД сначала ID т.к. он является FOREIGN_KEY таблицы users для таблицы auth_codes
        String userID = null;
        var idSQL = "SELECT id FROM users WHERE login = ?";
        try (var conn = getConnection();
             var idStMt = conn.prepareStatement(idSQL)) {
            idStMt.setString(1, authInfo.getLogin());
            try (var rs = idStMt.executeQuery()) {
                if (rs.next()) {
                    userID = rs.getString("id");
                }
            }
        }
//        После того, как нашли ID, по нему ищем необходимый нам код верификации
        String verificationCode = null;
        var verificationCodeSQL = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC limit 1";
        try (var conn = getConnection();
             var verificationCodeStMt = conn.prepareStatement(verificationCodeSQL)) {
            verificationCodeStMt.setString(1, userID);
            try (var rs = verificationCodeStMt.executeQuery()) {
                if (rs.next()) {
                    verificationCode = rs.getString("code");
                }
            }
        }
        return new VerificationCode(verificationCode);
    }

    public static VerificationCode invalidVerificationCode() {
        return new VerificationCode(faker.number().digits(5));
    }

}
