package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


public class AuthorizationTest {

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999");
    }

    @AfterEach
    void showDown() {
        Selenide.closeWindow();
    }

    @Test
    @DisplayName("Success authorization")
    void shouldBeSuccessAuth() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
    }


    @Test
    @DisplayName("Visible error notification and blocking user after input wrong password 3 times")
    void shouldBlockedUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getInvalidAuthInfo();
        var verificationPage = loginPage.invalidLoginInfo(authInfo);
        var status = DataHelper.getStatus(authInfo);
        Assertions.assertEquals("blocked", status);
    }

    @Test
    @DisplayName("Invalid verification after input wrong verification code")
    void shouldBeVerificationError() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.invalidVerificationCode();
        var dashboardPage = verificationPage.invalidVerify(verificationCode);
    }
}