package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Account extends BaseSeleniumPage {
    @FindBy(linkText = "Курсы")
    private WebElement courses;

    @FindBy(xpath = "//a[@class='mainCont3 coursePreview']")
    private List<WebElement> coursePreview;

    @FindBy(xpath = "//button[contains(text(), 'Проверить')]")
    private WebElement testBtn;

    @FindBy(xpath = "//button[contains(text(), 'Начать')]")
    private WebElement startBtn;

    @FindBy(xpath = "//button[contains(text(), 'Завершить')]")
    private WebElement finishBtn;

    @FindBy(xpath = "//small[contains(text(),'ответ')]")
    public WebElement answer;

    public Account() {
        PageFactory.initElements(driver, this);
    }

    public void newVoice() throws InterruptedException {
        Thread.sleep(800);
        courses.click();
        coursePreview.get(0).click();
        testBtn.click();
        startBtn.click();
        Thread.sleep(5000);
        finishBtn.click();
    }
}
