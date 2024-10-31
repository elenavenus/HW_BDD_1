package pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement amountInput;
    private SelenideElement cardFromInput;
    private SelenideElement transactButton;

    public TransactionPage(){
        SelenideElement form = $("form");
        amountInput = form.$("[data-test-id=amount] input");
        cardFromInput = form.$("[data-test-id=from] input");
        transactButton = form.$("[data-test-id=action-transfer]");
    }


    public void makeTransaction(String cardFrom, int amount){
        amountInput.setValue(String.valueOf(amount));
        cardFromInput.setValue(cardFrom);
        transactButton.click();
    }
}
