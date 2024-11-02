package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public int getCardBalance(String id) {
        SelenideElement cardElement = cards.findBy(Condition.attribute("data-test-id",id));
        return extractBalance(cardElement.text());
    }

    public void goToTransaction(String id){
        cards.findBy(Condition.attribute("data-test-id",id))
                .$("button").click();
        $("[data-test-id=amount]").should(Condition.appear);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
