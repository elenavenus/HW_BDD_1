package test;

import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.TransactionPage;
import page.VerificationPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

class CardTransactionTest {


    @Test
    public void shouldCorrectTransact(){
        open("http://localhost:9999");

        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        List<DataHelper.CardInfo> cardsInfo = DataHelper.getCardsInfoFor(authInfo);
        String firstCardNumber = cardsInfo.get(0).getCardNumber();
        String secondCardNumber = cardsInfo.get(1).getCardNumber();
        String firstCardId = cardsInfo.get(0).getCardTestId();
        String secondCardId = cardsInfo.get(1).getCardTestId();
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

        dashboardPage = new DashboardPage();
        int newFirstCardBalance = dashboardPage.getCardBalance(firstCardId);
        int newSecondCardBalance = dashboardPage.getCardBalance(secondCardId);
        //Логика проверок
        Assertions.assertEquals(firstCardBalance + amount, newFirstCardBalance);
        Assertions.assertEquals(secondCardBalance - amount, newSecondCardBalance);
    }
}
