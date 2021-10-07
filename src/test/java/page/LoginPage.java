package page;

import com.codeborne.selenide.Condition;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public void invalidLogin (DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").doubleClick();
        $("[data-test-id=login] input").sendKeys(Keys.DELETE);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").doubleClick();
        $("[data-test-id=password] input").sendKeys(Keys.DELETE);
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();

    }

    public void checkInvalidMessage(){
        $("[class=notification__content]").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Ошибка! Неверно" +
                        " указан логин или пароль"));
    }

    public void systemBlockMessage(){
        $("[class=notification__content]").shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Система заблокирована, " +
                        "попробуйте позже"));
    }
}
