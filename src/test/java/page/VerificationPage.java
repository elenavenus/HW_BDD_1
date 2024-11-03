package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput;
    private SelenideElement verifyButton;

    public VerificationPage(){
        $("[data-test-id=code] input").should(Condition.appear);
        SelenideElement form = $("form");
        codeInput = form.$("[data-test-id=code] input");
        verifyButton = form.$("button");
    }

    public void verify(String verificationCode){
        codeInput.setValue(verificationCode);
        verifyButton.click();

    }
}
