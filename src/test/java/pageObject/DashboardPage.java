package pageObject;

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
        String text = null;
        for(SelenideElement element : cards){
            if(element.getAttribute("data-test-id").equals(id)){
                text = element.text();
                break;
            }
        }
        return extractBalance(text);
    }

    public void goToTransaction(String id){
        for(SelenideElement element : cards){
            if(element.getAttribute("data-test-id").equals(id)){
                //если нашли нужную карту по id, то находим кнопку внутри контейнера и переходим к пополнению
                element.$("button").click();
                break;
            }
        }
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
