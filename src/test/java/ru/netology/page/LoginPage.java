package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] [class='input__control']");
    private SelenideElement passwordField = $("[data-test-id='password'] [class='input__control']");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage validLogin(DataHelper.AuthInfo info){
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
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
