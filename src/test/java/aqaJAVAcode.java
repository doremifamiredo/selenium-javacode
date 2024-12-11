import Pages.Account;
import Pages.BaseSeleniumTest;
import Pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class aqaJAVAcode extends BaseSeleniumTest {

    @FindBy(xpath = "//*[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;

    @BeforeEach
    void login() {
        LoginPage loginPage = new LoginPage();
        Account account = loginPage.autorizationAccount();
    }

    @Test
    @DisplayName("8. создание голосовой записи")
    void newVoice() throws InterruptedException {
        Account account = new Account();
        account.newVoice();
        assertEquals("Оцените ваш аудио ответ", account.answer.getText());
    }
}
