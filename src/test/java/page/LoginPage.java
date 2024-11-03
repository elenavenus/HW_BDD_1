package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput;
    private SelenideElement passwordInput;
    private SelenideElement loginButton;

    public LoginPage(){
        SelenideElement form = $("form");
        loginInput = form.$("[data-test-id=login] input");
        passwordInput = form.$("[data-test-id=password] input");
        loginButton = form.$("button");
    }



    public void login(String login, String password){
        loginInput.setValue(login);
        passwordInput.setValue(password);
        loginButton.click();
    }
}
