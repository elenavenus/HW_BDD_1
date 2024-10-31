import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.DashboardPage;
import pageObject.LoginPage;
import pageObject.TransactionPage;
import pageObject.VerificationPage;

import static com.codeborne.selenide.Selenide.*;

class CardTransactionTest {


    @BeforeEach
    public void disableSecurity(){
        ChromeOptions options = new ChromeOptions();
        System.setProperty("chromeoptions.prefs", "profile.password_manager_leak_detection=false");
        Configuration.browserCapabilities = options;
    }

    @Test
    public void ShouldCorrectTransact(){
        open("http://localhost:9999");
        String firstCardNumber = "5559 0000 0000 0001";
        String secondCardNumber = "5559 0000 0000 0002";
        String firstCardId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String secondCardId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";


        LoginPage loginPage = new LoginPage();
        loginPage.login();
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.verify();
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
