import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.DashboardPage;
import pageObject.LoginPage;
import pageObject.TransactionPage;
import pageObject.VerificationPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

class CardTransactionTest {


    @Test
    public void ShouldCorrectTransact(){
        open("http://localhost:9999");

        String firstCardId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String secondCardId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        DataHelper.CardsInfo cardsNumbers = DataHelper.getCardsInfoFor(authInfo);
        String firstCardNumber = cardsNumbers.getCardsNumbers().get(0);
        String secondCardNumber = cardsNumbers.getCardsNumbers().get(1);

        LoginPage loginPage = new LoginPage();
        loginPage.login(authInfo.getLogin(), authInfo.getPassword());

        VerificationPage verificationPage = new VerificationPage();
        verificationPage.verify(DataHelper.getVerificationCodeFor(authInfo).getCode());

        DashboardPage dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getCardBalance(firstCardId);
        int secondCardBalance = dashboardPage.getCardBalance(secondCardId);
        //переходим на страницу пополнения первой карты
        dashboardPage.goToTransaction(firstCardId);

        TransactionPage transactionPage = new TransactionPage();
        int amount = secondCardBalance / 2;
        transactionPage.makeTransaction(secondCardNumber, amount);

        int newFirstCardBalance = dashboardPage.getCardBalance(firstCardId);
        int newSecondCardBalance = dashboardPage.getCardBalance(secondCardId);
        //Логика проверок
        Assertions.assertEquals(firstCardBalance + amount, newFirstCardBalance);
        Assertions.assertEquals(secondCardBalance - amount, newSecondCardBalance);
    }
}
