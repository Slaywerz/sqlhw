package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

public class VerificationPage {
    private SelenideElement codeField = Selenide.$("[data-test-id='code'] input");
    private SelenideElement verifyButton = Selenide.$("[data-test-id='action-verify']");
    private SelenideElement errorMessage = Selenide.$("[data-test-id='error-notification']");

    public VerificationPage(){
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode code){
        codeField.setValue(code.getVerificationCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify(){
        codeField.setValue(null);
        verifyButton.click();
        errorMessage.shouldBe(Condition.visible);
    }

}
