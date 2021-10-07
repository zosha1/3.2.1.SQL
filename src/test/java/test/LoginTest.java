package test;

import data.DataHelper;
import data.DbHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void clean() {
        DbHelper.cleanDB();
    };

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verify(DbHelper.getCode());;
    }

    @Test
    public void tripleLogin() {
        var loginPage = new LoginPage();
        var userInfo = DataHelper.getOtherAuthInfo();
        loginPage.invalidLogin(userInfo);
        loginPage.checkInvalidMessage();
        loginPage.invalidLogin(userInfo);
        loginPage.checkInvalidMessage();
        loginPage.invalidLogin(userInfo);
        loginPage.systemBlockMessage();
    }
}