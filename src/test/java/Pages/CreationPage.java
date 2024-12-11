package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Pages.Generator.faker;

public class CreationPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[contains(text(), '+ Добавить')]")
    private WebElement addBtn;

    @FindBy(xpath = "//button[contains(text(), '+ Модуль')]")
    private WebElement mdlBtn;

    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement inputName;

    @FindBy(xpath = "//textarea")
    private WebElement textarea;

    @FindBy(xpath = "//*[contains(text(), 'Создать')]")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@data-id]")
    private List<WebElement> questions;

    @FindBy(xpath = "//tbody[@class]")
    private List<WebElement> item;

    private final String xPathInput = "//*[contains(text(), '%s')]/following-sibling::input";

    @FindBy(xpath = "//*[contains(text(), 'Тип')]/following::select[1]")
    private WebElement type;

    @FindBy(xpath = "//*[contains(text(), 'Дата')]/following::input[1]")
    private WebElement date;

    @FindBy(xpath = "//*[contains(text(), 'оценка интервью')]/following-sibling::textarea")
    private WebElement assessment;

    @FindBy(xpath = "//button[contains(text(), 'Сохранить')]")
    private WebElement saveBtn;

    @FindBy(xpath = "//a[contains(text(), 'Вернуться')]")
    private WebElement backLnk;

    public CreationPage() {
        PageFactory.initElements(driver, this);
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
        for (Map.Entry<String, String> info : user.entrySet()) {
            driver.findElement(By.xpath(String.format(xPathInput, info.getKey()))).sendKeys(info.getValue());
        }
        createBtn.click();
        Thread.sleep(1000);
    }

    public String editingInterview(HashMap<String, String> interview) throws InterruptedException {
        int num = faker.random().nextInt(item.size());
        item.get(num).click();
        for (Map.Entry<String, String> info : interview.entrySet()) {
            switch (info.getKey()) {
                case "Дата" -> date.sendKeys(info.getValue());
                case "Тип" -> {
                    Select select = new Select(type);
                    select.selectByValue(info.getValue());
                }
                case "оценка интервью" -> assessment.sendKeys(info.getValue());
                default ->
                        driver.findElement(By.xpath(String.format(xPathInput, info.getKey()))).sendKeys(info.getValue());
            }
        }
        saveBtn.click();
       // Thread.sleep(1000);
        backLnk.click();
        return item.get(num).findElement(By.xpath("//td[1]")).getText();
    }
}
