package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker();

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    void generateUser() {
        String dataSQL = "INSERT INTO users(id, login, password) VALUES(?, ?, ?);";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(dataSQL);
        ) {
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, faker.name().username());
            preparedStatement.setString(3, "password321");
            preparedStatement.executeUpdate();
        }
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getFirstUserAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @SneakyThrows
    public static AuthInfo getGenerateUser() {
        String login = null;
        String password = "password321";
        var loginSQL = "SELECT login FROM app.users WHERE id = ?";
        try (var con = getConnection();
             var loginPrepareStatement = con.prepareStatement(loginSQL)) {
            loginPrepareStatement.setString(1, "1");
            try (var rs = loginPrepareStatement.executeQuery()) {
                if (rs.next()) {
                    login = rs.getString("login");
                }
            }
        }
        return new AuthInfo(login, password);
    }

    @Value
    private static class VerificationCode {
        String verificationCode;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode(){
        String userID = null;
        val idSQL  = "SELECT login FROM app.users WHERE login = ?";
        try (val conn = getConnection();
        val idPrepareStatement = conn.prepareStatement(idSQL))
        { idPrepareStatement.setString(1, "");
        }

        String verificationCode = null;
        return new VerificationCode(verificationCode);
    }


}
