package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Pages.UserGenerator.faker;

public class CreationPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[contains(text(), '+ Добавить')]")
    private WebElement addBtn;

    @FindBy(xpath = "//button[contains(text(), '+ Модуль')]")
    private WebElement mdlBtn;

    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement inputName;

    @FindBy(xpath = "//textarea")
    private WebElement textarea;

    @FindBy(xpath = "//*[contains(text(), 'Create')]")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@data-id]")
    private List<WebElement> questions;

    private final String xPathInput = "//*[contains(text(), '%s')]/following-sibling::input";

    public CreationPage() {
        PageFactory.initElements(driver,this);
    }

    public void addInterview(String topic) throws InterruptedException {
        addBtn.click();
        inputName.sendKeys(topic);
        createBtn.click();
        Thread.sleep(1000);
    }

    public void addNote(String topic) throws InterruptedException {
        addBtn.click();
        textarea.sendKeys(topic);
        createBtn.click();
        Thread.sleep(1000);
    }

    public void addModule(String topic) throws InterruptedException {
        addBtn.click();
        inputName.sendKeys(topic);
        questions.get(faker.random().nextInt(questions.size())).click();
        createBtn.click();
        Thread.sleep(1000);
    }

    public void addCourse(String topic) throws InterruptedException {
        addBtn.click();
        inputName.sendKeys(topic);
        mdlBtn.click();
        createBtn.click();
        Thread.sleep(1000);
    }

    public void addUser(HashMap<String, String> user) throws InterruptedException {
        addBtn.click();
        int i = user.entrySet().size();
        for (Map.Entry<String, String> info : user.entrySet()) {
            driver.findElement(By.xpath(String.format(xPathInput, info.getKey()))).sendKeys(info.getValue());
        }
        createBtn.click();
        Thread.sleep(1000);
    }
}
