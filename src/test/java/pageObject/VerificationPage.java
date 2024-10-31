package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final String verificationCode = "12345";
    private SelenideElement codeInput;
    private SelenideElement verifyButton;

    public VerificationPage(){
        SelenideElement form = $("form");
        codeInput = form.$("[data-test-id=code] input");
        verifyButton = form.$("button");
    }

    public void verify(){
        codeInput.setValue(verificationCode);
        verifyButton.click();
        $(".list__item div").should(Condition.appear);
    }
}
