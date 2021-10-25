package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class LoginPage {
    private SelenideElement loginField = Selenide.$("[data-test-id='login'] [class='input__control']");
    private SelenideElement passwordField = Selenide.$("[data-test-id='password'] [class='input__control']");
    private SelenideElement loginButton = Selenide.$("[data-test-id='action-login']");
    private SelenideElement errorNotification = Selenide.$("[data-test-id='error-notification']");

    public VerificationPage validLogin(){
        loginField.setValue(null);
        passwordField.setValue(null);
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(){
        loginField.setValue(null);
        passwordField.setValue(null);
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
    }
}
