package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

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

//    Генерируем пользователя со случайными данными для проверки авторизации пользователем не из демо-данных
//    @SneakyThrows
//    void generateUser() {
//        String dataSQL = "INSERT INTO users(id, login, password) VALUES(?, ?, ?);";
//
//        try (PreparedStatement preparedStatement = getConnection().prepareStatement(dataSQL);
//        ) {
////            Присваеваем ID для поиска по БД данных сгенерированного пользователя
//            preparedStatement.setString(1, "1");
//            preparedStatement.setString(2, faker.name().username());
//            preparedStatement.setString(3, faker.internet().password());
//            preparedStatement.executeUpdate();
//        }
//    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    // Метод для поиска данных сгенерированного пользователя
//    @SneakyThrows
//    public static AuthInfo getGenerateUser() {
////        Находим login по ID
//        String login = null;
//        val loginSQL = "SELECT login FROM users WHERE id = ?";
//        try (val conn = getConnection();
//             val loginStMt = conn.prepareStatement(loginSQL)) {
//            loginStMt.setString(1, "1");
//            try (val rs = loginStMt.executeQuery()) {
//                if (rs.next()) {
//                    login = rs.getString("login");
//                }
//            }
//        }
//        Находим пароль по найденному выше логину
//        String password = null;
//        val passwordSQL = "SELECT password FROM users WHERE login = ?";
//        try (val conn = getConnection();
//             val passwordStMt = conn.prepareStatement(passwordSQL)) {
//            passwordStMt.setString(1, login);
//            try (val rs = passwordStMt.executeQuery()) {
//                if (rs.next()) {
//                    password = rs.getString("password");
//                }
//            }
//        }
//        Найденные значения передаем в AuthInfo
//        return new AuthInfo(login, password);
//    }
    @Value
    public static class VerificationCode {
        String verificationCode;
    }

    // Метод принимает AuthInfo для нахождения сгенерированного пароля по необходимому нам логину
    @SneakyThrows
    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
//        Находим в БД сначала ID т.к. он является FOREIGN_KEY таблицы users для таблицы auth_codes
        String userID = null;
        var idSQL = "SELECT login FROM app.users WHERE login = ?";
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
        var verificationCodeSQL = "SELECT code FROM app.auth_codes WHERE user_id = ? ORDER BY created DESC limit 1";
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


}
