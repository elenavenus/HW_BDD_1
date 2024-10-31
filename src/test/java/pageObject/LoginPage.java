package pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final String login = "vasya";
    private final String password = "qwerty123";
    private SelenideElement loginInput;
    private SelenideElement passwordInput;
    private SelenideElement loginButton;

    public LoginPage(){
        SelenideElement form = $("form");
        loginInput = form.$("[data-test-id=login] input");
        passwordInput = form.$("[data-test-id=password] input");
        loginButton = form.$("button");
    }



    public void login(){
        loginInput.setValue(login);
        passwordInput.setValue(password);
        loginButton.click();
    }
}
